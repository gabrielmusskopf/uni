**Dia:** 19/08/2023 
**Matéria:**  Fundamentos de sistemas operacionais
**Assunto:**  Processos
**Tags:** #linux #faculdade 

1. Quais são os limites máximos do processo?
R:
```shell
root@3893b19fb1ca:/# cat /proc/307/limits
Limit                     Soft Limit           Hard Limit           Units
Max cpu time              unlimited            unlimited            seconds
Max file size             unlimited            unlimited            bytes
Max data size             unlimited            unlimited            bytes
Max stack size            8388608              unlimited            bytes
Max core file size        unlimited            unlimited            bytes
Max resident set          unlimited            unlimited            bytes
Max processes             unlimited            unlimited            processes
Max open files            1048576              1048576              files
Max locked memory         8388608              8388608              bytes
Max address space         unlimited            unlimited            bytes
Max file locks            unlimited            unlimited            locks
Max pending signals       62589                62589                signals
Max msgqueue size         819200               819200               bytes
Max nice priority         0                    0
Max realtime priority     0                    0
Max realtime timeout      unlimited            unlimited            us
```

2. Analisando o arquivo maps, como identificar a seção de texto do processo em execução?

R: O arquivo `/proc/[PID]/maps` contem a Virtual Address Space (VAS), ou seja, as segmentações que indicam os endereços de memória virtual que esse processo se refere. Cada seção contém permissões, e olhando essas permissões podemos identificar o que cada representa:
```shell
root@3893b19fb1ca:/# cat /proc/307/maps
00400000-00401000 r--p 00000000 00:5f 5446010                            /process
00401000-00402000 r-xp 00001000 00:5f 5446010                            /process
00402000-00403000 r--p 00002000 00:5f 5446010                            /process
00403000-00404000 r--p 00002000 00:5f 5446010                            /process
00404000-00405000 rw-p 00003000 00:5f 5446010                            /process
00bc7000-00be8000 rw-p 00000000 00:00 0                                  [heap]
7faf60aa1000-7faf60aa4000 rw-p 00000000 00:00 0
7faf60aa4000-7faf60aca000 r--p 00000000 00:5f 4923039                    /usr/lib/x86_64-linux-gnu/libc.so.6
7faf60aca000-7faf60c1f000 r-xp 00026000 00:5f 4923039                    /usr/lib/x86_64-linux-gnu/libc.so.6
7faf60c1f000-7faf60c72000 r--p 0017b000 00:5f 4923039                    /usr/lib/x86_64-linux-gnu/libc.so.6
7faf60c72000-7faf60c76000 r--p 001ce000 00:5f 4923039                    /usr/lib/x86_64-linux-gnu/libc.so.6
7faf60c76000-7faf60c78000 rw-p 001d2000 00:5f 4923039                    /usr/lib/x86_64-linux-gnu/libc.so.6
7faf60c78000-7faf60c85000 rw-p 00000000 00:00 0
7faf60c8c000-7faf60c8e000 rw-p 00000000 00:00 0
7faf60c8e000-7faf60c8f000 r--p 00000000 00:5f 4923021                    /usr/lib/x86_64-linux-gnu/ld-linux-x86-64.so.2
7faf60c8f000-7faf60cb4000 r-xp 00001000 00:5f 4923021                    /usr/lib/x86_64-linux-gnu/ld-linux-x86-64.so.2
7faf60cb4000-7faf60cbe000 r--p 00026000 00:5f 4923021                    /usr/lib/x86_64-linux-gnu/ld-linux-x86-64.so.2
7faf60cbe000-7faf60cc0000 r--p 00030000 00:5f 4923021                    /usr/lib/x86_64-linux-gnu/ld-linux-x86-64.so.2
7faf60cc0000-7faf60cc2000 rw-p 00032000 00:5f 4923021                    /usr/lib/x86_64-linux-gnu/ld-linux-x86-64.so.2
7fff54981000-7fff549a2000 rw-p 00000000 00:00 0                          [stack]
7fff549a7000-7fff549ab000 r--p 00000000 00:00 0                          [vvar]
7fff549ab000-7fff549ad000 r-xp 00000000 00:00 0                          [vdso]
ffffffffff600000-ffffffffff601000 --xp 00000000 00:00 0                  [vsyscall]
```

A seção que indica o caminho do processo e tem a permissão `x` (executável), é o endereço que guarda o programa em si.

3. Quantas trocas de contexto foram realizadas até o momento da consulta?

R: Até o momento, foram realizados 35404 trocas involuntárias

```shell
root@3893b19fb1ca:/# cat /proc/307/status
//...
nonvoluntary_ctxt_switches:     35404
```

4. Qual é o id do processo pai? Quem é o processo pai?

R: O processo pai é o bash, no caso da execução desse teste, que utiliza containers

```shell
root@3893b19fb1ca:/# cat /proc/307/status | grep PPid
PPid:   1
```

```shell
root@3893b19fb1ca:/# top
top - 20:15:48 up 1 day,  9:17,  0 user,  load average: 2.13, 1.95, 1.74
Tasks:   3 total,   2 running,   1 sleeping,   0 stopped,   0 zombie
%Cpu(s):  0.0 us,  0.0 sy,  0.0 ni,100.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
MiB Mem :  15728.7 total,   1652.9 free,   9334.3 used,   6833.0 buff/cache
MiB Swap:   7629.0 total,   4702.0 free,   2927.0 used.   6394.4 avail Mem

    PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND
    307 root      20   0    2460   1024   1024 R 100.0   0.0  39:47.66 process
      1 root      20   0    4324   3328   2816 S   0.0   0.0   0:00.16 bash
    336 root      20   0    8648   4864   2816 R   0.0   0.0   0:00.00 top
```

5.  O kernel ou núcleo contém os componentes centrais do sistema operacional. Assinale a alternativa que apresenta corretamente os referidos componentes

R: B)  Escalonador de processos, gerenciador de memória, gerenciador de E/S e gerenciador de comunicação inter processos, gerenciador de sistemas de arquivos.

6. Um processo em um sistema operacional pode mudar para o estado de pronto a partir do estado de

R: E) criação, espera ou execução
![[Pasted image 20230816211535.png]]

7. Uma chamada para o sistema operacional, refere-se:
I. O sistema chama o procedimento de serviço.
II. O programa de usuário gera uma interrupção para o kernel.
III. O controle é retornado para o programa de usuário.
IV. O sistema operacional determina o número do serviço necessário.
A execução natural das operações acontece na sequência:

R: A) II, IV, I, III

8. O processo é um conceito chave em todos os sistemas operacionais. Nesse contexto, considere:
I. O espaço de endereçamento é associado ao processo e contém o programa executável, os dados do programa e sua pilha e um conjunto de registradores, incluindo o contador de programa e o ponteiro da pilha.
II. Tanto as hierarquias de processos quanto as de arquivos são organizadas como árvores, inclusive nos seus níveis de profundidade que podem ser acessados indistintamente pelo processo pai e seus processos filhos.
III. Em relação aos sistemas de arquivos, uma função importante do sistema
operacional é esconder as peculiaridades dos discos e outros dispositivos de entrada e saída e apresentar ao programador um modelo abstrato de arquivos.
IV. Um processo suspenso consiste na imagem de núcleo e sua entrada na tabela de processos, que contém seus registradores, entre outros elementos. 

~~R: É correto o que se afirma em: A) I, II, III, IV~~
R Correta: É correto o que se afirma em: C) I, III, IV
