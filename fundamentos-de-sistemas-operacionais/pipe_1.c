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
