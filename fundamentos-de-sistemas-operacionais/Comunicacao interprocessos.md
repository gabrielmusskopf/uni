**Dia:** 30/08/2023 
**Matéria:** Fundamentos de sistemas operacionais
**Assunto:** IPC (Inter Process Communication)
**Tags:** #faculdade #programacao-concorrente

> [!INFO]
> Lembrar de questionar sobre questão 8 da atividade 2

Primitiva `exec` sobrescreve a memória do processo atual

Quando processo pai termina antes do filho, o filho se torna zumbi

## IPC

Comunicação através de pipes, quando for mesmo sistema operacional

Usa file descriptions (FD) pois era o que era utilizado na época em que a tecnologia foi desenvolvida, estrutura já pronta
```
fd[0] - é usado para ler
fd[1] - é usado para escrever
```

### Zombie

Primitiva `wait` é um **busy wait**, ou seja, enquanto espera a conclusão do filho, fica alternando entre `apto` e `consumindo`, ou seja, consumindo CPU


