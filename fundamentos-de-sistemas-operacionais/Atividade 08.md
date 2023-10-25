**Dia:** 22/10/2023 
**Matéria:** Fundamentos de sistemas operacionais
**Assunto:** Alocação de memória estática e dinâmica
**Tags:** #faculdade #memoria 

1. Relatório do código
```c
#include <stdio.h>
#include <unistd.h>
 
void estatica() {
    char nomedaMaquina[256];
    gethostname(nomedaMaquina, sizeof(nomedaMaquina));
    puts(nomedaMaquina);
}
 
void dinamica(int size){
    char nomedaMaquina[size];
    gethostname(nomedaMaquina, sizeof(nomedaMaquina));
    puts(nomedaMaquina);
}
 
int main( ){
    printf("Função estática: ");
    estatica();
 
    printf("Função dinâmica: ");
    dinamica(512);
 
    return 0;
}
```

O código é feito para demonstrar a alocação de memória em em cada função. Utilizando a extensão do compilador GCC, `-fstack-usage`, obtemos um arquivo de texto no formato `.su` com colunas representando respectivamente o nome da função e onde se encontra no programa, a quantidade de memória alocada em tempo de compilação, e uma label que representa o tipo de alocação da função, podendo ser **estática** (static), **dinâmica** (dynamic) ou **limitada** (bounded).

No caso do programa, o arquivo gerado foi
```shell
cat build/stack.su
material/stack.c:11:6:estatica  288     static
material/stack.c:17:6:dinamica  80      dynamic
material/stack.c:23:5:main      16      static
```
Nele vemos que a função `estatica` alocou 288 bytes na memória, que correspondem aos 256 bytes alocados para o vetor, mais alguns bytes para as subfunções. Já na `dinâmica`, em tempo de compilação, somente 80 bytes foram alocados. Isso acontece pois essa função aloca o vetor de forma dinâmica com o parâmetro recebido.
A opção `dynamic` indica que pode ser alocada mais memória do que os 80 bytes já alocados pela função.

Debugando o binário com a ferramenta `valgrind`, podemos ver algumas primitivas que da biblioteca `lib.c` usadas pelas subfunções do programa:

```shell
...
--246683-- REDIR: 0x491a720 (libc.so.6:strnlen) redirected to 0x483f220 (_vgnU_ifunc_wrapper)
--246683-- REDIR: 0x491a9e0 (libc.so.6:strpbrk) redirected to 0x483f220 (_vgnU_ifunc_wrapper)
--246683-- REDIR: 0x491a2e0 (libc.so.6:strcmp) redirected to 0x483f220 (_vgnU_ifunc_wrapper)
--246683-- REDIR: 0x4938cd0 (libc.so.6:wcsnlen) redirected to 0x483f220 (_vgnU_ifunc_wrapper)
--246683-- REDIR: 0x491b6d0 (libc.so.6:memset) redirected to 0x483f220 (_vgnU_ifunc_wrapper)
--246683-- REDIR: 0x4937540 (libc.so.6:wcslen) redirected to 0x483f220 (_vgnU_ifunc_wrapper)
--246683-- REDIR: 0x491bc10 (libc.so.6:memcpy@@GLIBC_2.14) redirected to 0x483f220 (_vgnU_ifunc_wrapper)
--246683-- REDIR: 0x4937370 (libc.so.6:wcschr) redirected to 0x483f220 (_vgnU_ifunc_wrapper)
--246683-- REDIR: 0x491a260 (libc.so.6:index) redirected to 0x483f220 (_vgnU_ifunc_wrapper)
--246683-- REDIR: 0x491a960 (libc.so.6:rindex) redirected to 0x483f220 (_vgnU_ifunc_wrapper)
...
```

E ao final, vemos a interrupção gerada ao final da execução do processo:
```shell
...
==246683== Process terminating with default action of signal 5 (SIGTRAP)
==246683==    at 0x498D441: gethostname (gethostname.c:28)
==246683==    by 0x1091DA: estatica (stack.c:13)
==246683==    by 0x109329: main (stack.c:25)
==246683==
==246683== HEAP SUMMARY:
==246683==     in use at exit: 1,024 bytes in 1 blocks
==246683==   total heap usage: 1 allocs, 0 frees, 1,024 bytes allocated
==246683==
==246683== Searching for pointers to 1 not-freed blocks
==246683== Checked 107,616 bytes
==246683==
==246683== LEAK SUMMARY:
==246683==    definitely lost: 0 bytes in 0 blocks
==246683==    indirectly lost: 0 bytes in 0 blocks
==246683==      possibly lost: 0 bytes in 0 blocks
==246683==    still reachable: 1,024 bytes in 1 blocks
==246683==         suppressed: 0 bytes in 0 blocks
==246683== Rerun with --leak-check=full to see details of leaked memory
==246683==
==246683== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)
``

2. Cite duas diferenças entre endereços lógicos e endereços
físicos.

Endereços lógicos são um mapeamento de endereço da memória física para uma memória lógica, conhecidos também como endereço virtual. Outra diferença é que o endereço lógico não necessariamente representa o mesmo endereço físico. A unidade gerenciadora de memória (MMU) pode aplicar transformações e validações no endereço lógico para filtrar endereços válidos, para limitar acesso de um processo, por exemplo, ou para decidir se deve ser alocado mais ao início ou fim da memória física, adicionando ou subtraindo um determinado valor.

3. Explique a diferença entre fragmentação interna e externa e
quando elas ocorrem.

Fragmentação interna ocorre na alocação fixa de memória, isso é, partições de tamanhos fixos são inicializadas e os processos são alocados nestas. Exemplo, quando um processo de 15KB é alocado em uma partição fixa de 20KB, um espaço de 5KB fica ocioso.
Fragmentação externa ocorre na alocação dinâmica, que, ao invés de alocar partições de tamanho fixo, cria conforme a demanda do processo. Após muitos processos, espaços pequenos sobram entre partições. Para resolver, é preciso desfragmentar, movendo os processos para o disco físico e depois movê-los de volta para a memória, de forma contígua, evitando as pequenas partições (swap)

4. Dadas as 5 partições de memória de 100 KB, 500 KB, 200
KB, 300 KB e 600 KB (em ordem), como os algoritmos do
primeiro apto (first-fit), do mais apto (best-fit) e do menos
apto (worst-fit) alocariam processos de 212 KB, 417 KB,
112 KB e 426 KB (em ordem)? Que algoritmo faz uso mais
eficiente da memória?

| Algoritmo | 100KB | 500KB | 200KB | 300KB | 600KB |
| --------- | ----- | ----- | ----- | ----- | ----- |
| first-fit | -     | 212KB | 112KB | -     | 417KB |
| best-fit  | -     | 417KB | 112KB | 212KB | 426KB |
| worst-fit | -     | 417KB | -     | 112KB | 212KB |

Para essas partições e esses processos, o algoritmo de melhor eficiência é o **best-fit**.





