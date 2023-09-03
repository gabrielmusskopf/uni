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
