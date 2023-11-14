**Dia:** 31/10/2023 
**Matéria:** Fundamentos de Sistemas Operacionais
**Assunto:** Gerenciamento de memória
**Tags:** 

1. Analisar Page Size
   ```shell
$ getconf PAGE_SIZE
4096
//...
$ getconf -a | grep PAGES
PAGESIZE                           4096
_AVPHYS_PAGES                      107744
_PHYS_PAGES                        4026572
```

Ubuntu 22.04 não foi possível executar o `perf` devido a restrições do SO:

```shell
Access to performance monitoring and observability operations is limited.
Consider adjusting /proc/sys/kernel/perf_event_paranoid setting to open
access to performance monitoring and observability operations for processes
without CAP_PERFMON, CAP_SYS_PTRACE or CAP_SYS_ADMIN Linux capability.
More information can be found at 'Perf events and tool security' document:
https://www.kernel.org/doc/html/latest/admin-guide/perf-security.html
perf_event_paranoid setting is 4:
```

3. Visando a melhorar a tradução de endereços da memória virtual, existe uma memória cache cuja função é diminuir os acessos à tabela de páginas. Assinale a alternativa que representa tal memória.
   A) Cache Write-trough.
   B) Cache Multi-nível.
   C) Translation Lookaside Buffer (TLB).
   D) Registrador de tabela de páginas (RTP).
   E) Cache Write-back.
   
   Resposta: C)
   
4. A memória virtual permite que programas de computadores que excedam o tamanho disponível na memória física possam ser executados utilizando, para isso, métodos de paginação e segmentação. Sendo assim, é correto afirmar que 
   
   A) Paginação faz referência ao espaço de endereçamento virtual dividido em unidades de tamanho fixo *[certo]*. Considerando que as páginas são pequenas e de comprimento fixo, a fragmentação deixa de ser um problema. *[falso] fragmentação ainda ocorre, mas em muito menor quantidade*
   
   B) Paginação não faz referência ao espaço de endereçamento virtual dividido em unidades de tamanho fixo *[falso]*. Considerando que as páginas são pequenas e de comprimento fixo, a fragmentação pode ser melhor gerenciada com a utilização do método de segmentação de memória.
   
   C) Paginação faz referência ao espaço de endereçamento real dividido em unidades de tamanho fixo *[falso, faz referência ao endereçamento virtual]*. Considerando que as páginas são pequenas e de comprimento fixo, a fragmentação deixa de ser um problema.
   
   D) Paginação não faz referência ao espaço de endereçamento real dividido em unidades de tamanho fixo *[verdadeiro, pois é o virtual]*. Considerando que as páginas são pequenas e de comprimento fixo, a fragmentação pode ser melhor gerenciada com a utilização de segmentação de memória. *[verdadeiro]*
   
   E) Paginação faz referência ao espaço de endereçamento real dividido em unidades de tamanho não determinado. Portanto a utilização da segmentação de memória é mais apropriada, se considerarmos o problema da fragmentação.
   
Resposta: D)

5. Considerando-se a arquitetura de sistemas operacionais, assinale a única
alternativa em que os conceitos de gerenciamento de memória e a respectiva
definição estão CORRETOS.

A) Swapping: técnica que amplia o espaço de endereçamento do processo
guardando-o no disco quando este assume a condição de ocioso/inativo. [falso, swapping para desfragmentação]

B) Memória virtual: técnica que reúne o espaço de endereçamento do programa em pequenos pedaços com faixas de endereços descontínuos, chamados páginas. [verdadeiro]

C) LRU (Least Recently Used): algoritmo usado no gerenciamento memória virtual, substitui a página que possui o menor tempo de ociosidade. [falso, maior tempo de ociosidade]

D) Espaço de endereçamento de um processo: conjunto de endereços que um processo utiliza para acessar a memória do sistema, para executar instruções e armazenar dados. [verdadeiro]

E) MMU (Memory Managment Unity): módulo do sistema operacional
responsável por mapear o espaçamento de endereçamento virtual no espaço de endereçamento físico. [falso, implementado em hardware]

Resposta: B e D