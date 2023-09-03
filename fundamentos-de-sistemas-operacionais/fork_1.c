#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>


int main(void) {
    pid_t pid;
    char *message;
    int n;

    printf("Iniciando FORK\n");
    pid = fork();

    switch (pid) {
        case -1:
            fprintf(stderr, "ERROR: Não pode criar processo");
            return 1;
        case 0:
            message = "FILHO";
            n = 5;
            break;
        default:
            message = "PAI";
            n = 3;
            break;
    }

    for (; n > 0; n--) {
        printf("PID=%d\n", pid);
        puts(message);
        sleep(1);
    }

    return 0;
}
