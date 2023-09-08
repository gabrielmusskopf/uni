#include <pthread.h>
#include <stdio.h>

#define NTHREADS 4

unsigned long soma[NTHREADS];

void *thread_fn(void *arg) {
  long id = (long)arg;
  int inicio = id * 25000;
  int i = 0;

  while (i < 25000) {
    soma[id] += (i + inicio);
    i++;
  }
  while (1) {
  }
  return NULL;
}

int main(void) {
  pthread_t t[NTHREADS];
  unsigned long resultado = 0;

  for (long i = 0; i < NTHREADS; i++) {
    pthread_create(&t[i], NULL, thread_fn, (void *)i);
  }
  for (long i = 0; i < NTHREADS; i++) {
    pthread_join(t[i], NULL);
  }
  for (long i = 0; i < NTHREADS; i++) {
    resultado += soma[i];
  }

  printf("%lu\n", resultado);

  return 0;
}
