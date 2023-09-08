1. ps -eLf
```shell
ps -eLf | grep build/threads
gabriel+ 1907571    6292 1907571  0    5 19:48 pts/28   00:00:00 build/threads
gabriel+ 1907571    6292 1907663 99    5 19:48 pts/28   00:00:02 build/threads
gabriel+ 1907571    6292 1907664 99    5 19:48 pts/28   00:00:02 build/threads
gabriel+ 1907571    6292 1907665 99    5 19:48 pts/28   00:00:02 build/threads
gabriel+ 1907571    6292 1907666 99    5 19:48 pts/28   00:00:02 build/threads
```

Mostra o PID do processo e de cada thread criada por ele. Mostra que o SO entende a estrutura "thread", pois caso contrário, não seria possível visualizar junto com outros processos

2. 
```shell
top
PID USUARIO   PR  NI    VIRT    RES    SHR S  %CPU  %MEM    TEMPO+ COMANDO
1907663 gabriel+  25   5   35556   1116   1116 R  99,9   0,0   2:12.39 threads
1907666 gabriel+  25   5   35556   1116   1116 R  99,9   0,0   2:12.41 threads
1907664 gabriel+  25   5   35556   1116   1116 R  99,9   0,0   2:12.53 threads
1907665 gabriel+  25   5   35556   1116   1116 R  99,9   0,0   2:12.25 threads
```

Opção do comando top para visualizar threads. Mostra as mesmas do comando anterior, observando o mesmo PID

3. /proc
```shell
gabrielgmusskopf in /proc/1907571/task 
➜ ls
1907571  1907663  1907664  1907665  1907666

# TBD - Mostra thread block descriptor na pasta /proc/<pid>/tasks

gabrielgmusskopf in 1907571/task/1907571 
➜ ls
arch_status         cpuset             ksm_stat   numa_maps      sched         status
attr                cwd                limits     oom_adj        schedstat     syscall
auxv                environ            loginuid   oom_score      sessionid     uid_map
cgroup              exe                maps       oom_score_adj  setgroups     wchan
children            fd                 mem        pagemap        smaps
clear_refs          fdinfo             mountinfo  patch_state    smaps_rollup
cmdline             gid_map            mounts     personality    stack
comm                io                 net        projid_map     stat
cpu_resctrl_groups  ksm_merging_pages  ns         root           statm

# Estrutura semelhante ao PBD
```

4. A correta utilização de processos e threads é fundamental para garantir o desempenho e a transparência de sistemas distribuídos. Sobre esse tema, considere as afirmativas a seguir. 
   
   I. A sobreposição de threads em um processo é o principal recurso para obtenção de alto grau de transparência de distribuição em redes com longos tempos de propagação de mensagens. 
   II. A desvantagem de se estruturar um programa para utilizar múltiplas threads é que ele ficará dependente de sistemas multiprocessadores. 
   III. O modelo de threads implementado pelo sistema operacional deve ser aquele em que o gerenciamento de threads fica inteiramente no espaço de cada processo para evitar trocas de contexto entre processos e o núcleo (kernel) no chaveamento de threads. 
   IV. Servidores multithreaded têm melhor desempenho se estruturados com ao menos uma thread despachante e várias threads operárias para recebimento e processamento de requisições. Assinale a alternativa correta.

b) Somente as afirmativas I e IV são corretas.

![[Pasted image 20230907200327.png]]

b) badc ou bacd