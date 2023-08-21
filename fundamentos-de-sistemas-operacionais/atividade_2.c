#include <stdio.h>

int main ( ) {
    FILE *fd1, *fd2; // cria dois file descriptor
    printf("O programa cria dois arquivos\n");
    fd1 = fopen("teste1.txt",
            "w+"); // cria um arquivo teste1.txt
    fd2 = fopen("teste2.txt",
            "w+"); // cria um arquivo teste2.txt
    while(1) {} // cpu bound
    return 0;
}
