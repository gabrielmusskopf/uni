#include <signal.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#define FD_READ 0
#define FD_WRITE 1

int main(void) {
    int bytes;
    int fd[2];
    char some_data[] = "123";
    char buffer[BUFSIZ] = {0};
    pid_t pid;

    if (pipe(fd) != 0) {
        return 1; 
    }

    pid = fork();
    switch (pid) {
        case -1:
            fprintf(stderr, "ERROR: Não foi possível criar processo");
            return 1;
        case 0:
            bytes = read(fd[FD_READ], buffer, BUFSIZ);
            printf("Filho: Lido %d bytes: %s\n", bytes, buffer);
            return 0;
        default:
            bytes = write(fd[FD_WRITE], some_data, strlen(some_data));
            printf("Pai: Escrito %d bytes\n", bytes);
    }

    return 0;
}
