**Dia:** 03/09/2023 
**Matéria:** Fundamentos de sistemas operacionais
**Assunto:** Intercomunicação de processos (IPC)
**Tags:** #faculdade #processos 

# Exercício 1:
## Código
```c
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
```

## Saída:
```shell
gabrielgmusskopf in unisinos/fundamentos-de-sistemas-operacionais on  main [!?]
➜ ./build/fork_1
Iniciando FORK
PID=3643922
PAI
PID=0
FILHO
PID=3643922
PID=0
PAI
FILHO
PID=3643922
PID=0
PAI
FILHO
PID=0
FILHO

gabrielgmusskopf in unisinos/fundamentos-de-sistemas-operacionais on  main [!?] took 3,0s
➜ PID=0
FILHO
```

## Resultado:
O comportamento desse código cria um processo chamado de zumbi. Um processo zumbi é um processo sem pai (ficou sem pai e agora o pai é o processo de PID 1). Isso ocorre pois o programa cria um processo que é finalizado após o término do processo pai, e esse não aguarda o fim do filho, fazendo com que o filho printe no terminal após o término do pai

# Exercício 2
## Código
```c
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#define FD_READ 0
#define FD_WRITE 1

int main(void) {
    int bytes;
    int fd[2];
    char some_data[] = "123";
    char buffer[1024] = {0};

    if (pipe(fd) != 0) {
        return 1; 
    }

    bytes = write(fd[FD_WRITE], some_data, strlen(some_data));
    printf("Escrito %d bytes\n", bytes);
    bytes = read(fd[FD_READ], buffer, 1024);
    printf("Read %d bytes: %s\n", bytes, buffer);

    return 0;
}
```
## Saída

```shell
gabrielgmusskopf in unisinos/fundamentos-de-sistemas-operacionais on  main [!?]
➜ ./build/pipe_1
Escrito 3 bytes
Read 3 bytes: 123
```
## Resultado
O programa está criando um pipe na configuração "loopback", ou seja, cria um canal de escrita e leitura no kernel, onde o próprio processo escreve e lê.

# Exercício 2
## Código
```c
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
```
## Saída
```shell
➜ ./build/pipe_2
Pai: Escrito 3 bytes
Filho: Lido 3 bytes: 123
```
## Resultado
Na minha máquina, os processos são executados nessa ordem "sempre" (umas 20 tentativas), porém não é regra, pois o processo pai não aguarda o fim do filho. Nesse código, ao invés do mesmo processo ler e escrever no kernel, a escrita e leitura são feitas por processos distintos

# Exercício 3
## Código
pipe4.c
```c
#include <stdio.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
    int bytes;
    int fd;
    char buffer[BUFSIZ] = {0};

    sscanf(argv[1], "%d", &fd);
    bytes = read(fd, buffer, BUFSIZ);

    printf("pipe4: Lido %d bytes: %s\n", bytes, buffer);

    return 0;
}
```
pipe_3.c
```c
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
            sprintf(buffer, "%d", fd[FD_READ]);
            (void)execl("pipe4", "pipe4", buffer, (char *)0);
            return 1;
        default:
            bytes = write(fd[FD_WRITE], some_data, strlen(some_data));
            printf("Escrito %d bytes\n", bytes);
    }

    return 0;
}
```
## Saída
```shell
➜ ./pipe_3
Pai: Escrito 3 bytes
pipe4: Lido 3 bytes: 123
```
## Resultado
Nesse código utilizamos a diretiva `execl` no processo filho para sobrescrever  o código já carregado em memória com o código do `pipe4`. Para a comunicação dos processos, utilizou um `pipe`, e para que o filho tivesse conhecimento do file descriptor, o mesmo foi passado por parâmetro ao `pipe4`. Assim, ambos processos tem o conhecimento do FD (file descriptor) e podem se comunicar escrevendo e lendo no kernel.

# Exercício 4
## Código
```c
#include <signal.h>
#include <stdio.h>
#include <unistd.h>

void ouch(int sig) {
    printf("\nOUCH, recebi sinal %d\n", sig);
    (void) signal(SIGINT, SIG_DFL);
}

int main(void) {
    (void) signal(SIGINT, ouch);

    while (1) {
        printf("Hello World\n");
        sleep(1);
    }
    return 0;
}
```
## Saída
```shel
➜ ./build/signal
Hello World
Hello World
Hello World
^C
OUCH, recebi sinal 2
Hello World
Hello World
^C
```
## Resultado
O código sobrescreve a tratativa do processo para o singal SIGINT (sinal recebido quando CTRL+C é pressionado) com o handler `ouch`. Após o sinal ser capturado e tratado, o handler redefine a tratativa para a tratativa padrão do sinal, fazendo com que no primeiro SIGINT seja executado o printf, e no segundo, termine o processo
