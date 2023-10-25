// gcc -fstack-usage nome.c -o nome
// cat nome.su
// https://linux.die.net/man/1/gcc

// valgrind --tool=memcheck -v ./nome
// http://www.it.uc3m.es/pbasanta/asng/course_notes/memory_profiler_en.html

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
