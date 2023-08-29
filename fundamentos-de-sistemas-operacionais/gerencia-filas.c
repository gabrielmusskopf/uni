/*
 * Este programa simula a criação de processos e mostra como o bloco descritor é utilizado 
 * para "migrar" esses processos entre as filas do sistema.
 *
 * Este programa foi criado apenas para fins didáticos, pois abstrai detalhes implementados 
 * por um SO real para gerenciar a execução de processos durante seu ciclo de vida.
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int luck(int s, int e) {
    return (rand()%e) + s;
}

#define MAX_PROCESS 10 

typedef struct DescProc {
    char     estado_atual;
    int      prioridade;
    unsigned inicio_memoria;
    unsigned tamanho_memoria;
    unsigned tempo_cpu;
    unsigned proc_pc;
    unsigned proc_sp;
    unsigned proc_acc;
    unsigned proc_rx;
    struct   DescProc *proximo;
} DescProc;

typedef struct DescProcLista {
    DescProc* primeiro;
    DescProc* ultimo;
} DescProcLista;

void cria_processo();
void define_info(DescProc* p);

DescProc tab_desc[MAX_PROCESS]; 
DescProcLista desc_livre;
DescProcLista espera_cpu;
DescProcLista usando_cpu;
DescProcLista bloqueados;

void show_process(DescProc *p) {
    printf("\nestado atual: %c\n", p->estado_atual);
    printf("prioridade: %d\n", p->prioridade);
    printf("inicio memoria: %u\n", p->inicio_memoria);
    printf("tamanho memoria: %u\n", p->tamanho_memoria);
    printf("tempo cpu: %u\n", p->tempo_cpu);
    printf("pc: %u\n", p->proc_pc);
    printf("sp: %u\n", p->proc_sp);
    printf("acc: %u\n", p->proc_acc);
    printf("rx: %u\n", p->proc_rx);
}

void show_queue(DescProcLista *lista) {
    DescProc *p = lista->primeiro;
    DescProc *aux = p;

    while (aux) {
        show_process(aux);
        printf("\n");
        aux = aux->proximo;
    }
}

void show_state(char *title){
    printf("--- %s ---\n", title);
    printf("< Espera CPU: > [");
    show_queue(&espera_cpu);
    printf("]\n");

    printf("< Usando: > [");
    show_queue(&usando_cpu);
    printf("]\n");

    printf("< Bloqueado: > [");
    show_queue(&bloqueados);
    printf("]\n");
    printf("--------------------\n\n");
}

void define_info(DescProc* p) { 
    p->estado_atual= 'A';
    p->prioridade = luck(1, 100);
    p->inicio_memoria = 10;
    p->tamanho_memoria = 10;
    p->tempo_cpu = 10;
    p->proc_pc = 12;
    p->proc_rx = 11;
    p->proc_acc= 11;
    p->proc_sp= 11;
}

void add_to_queue(DescProc *p, DescProcLista *l) {
    if(l->primeiro == NULL) {
        l->primeiro = l->ultimo = p;
        p->proximo = NULL;
    } else {
        l->ultimo->proximo = p;
        l->ultimo = p;
        p->proximo = NULL;
    }
}

DescProc *remove_first(DescProcLista *l) {
    if (l->primeiro == NULL) {
        return NULL;
    }
    DescProc *p = l->primeiro;
    l->primeiro = l->primeiro->proximo;
    return p;
}

void remove_last(DescProcLista *l) {
    if (l->primeiro == l->ultimo) {
        l->primeiro= NULL;
    }
    l->ultimo = NULL;
}

void cria_processo() {
    DescProc* p = desc_livre.primeiro;

    printf("Definindo informacoes do processo (PCB)\n");
    define_info(p);

    printf("Atualizando a fila desc_livre... O processo em execucao esta usando um dos descritores.\n");
    DescProc* proximo_livre = desc_livre.primeiro->proximo;
    desc_livre.primeiro = proximo_livre;

    printf("Processo criado... migramos ele para a fila espera_cpu.\n");
    add_to_queue(p, &espera_cpu);

}

void cpu_bound() {
    DescProc *p = remove_first(&espera_cpu);
    if (p == NULL) {
        printf("Nada mais a executar!\n");
        return;
    }

    add_to_queue(p, &usando_cpu);
    show_state("Processo com CPU");
    remove_last(&usando_cpu);

    if (luck(1, 10) <= 5) {
        printf("Processo finalizado com sucesso!\n");
        return;
    }

    add_to_queue(p, &espera_cpu);
    show_state("Tempo do processo esgotado, volta para a fila de espera");
}

void io_bound() {
    DescProc *p = remove_first(&espera_cpu);
    if (p == NULL) {
        printf("Nada mais a executar!\n\n");
        return;
    }

    printf("Processo ganhou CPU\n");
    add_to_queue(p, &bloqueados);
    show_state("Processo iniciou IO bound e foi bloqueado");

    remove_last(&bloqueados);
    add_to_queue(p, &espera_cpu);
    show_state("Processo finalizou IO bound e está aguardando CPU novamente");
}

void do_work() {
    printf("CPU trabalhando...\n");

    while (espera_cpu.primeiro != NULL) {
        cpu_bound();
        io_bound();
        show_state("Ciclo finalizado");
    }
}

int main() {
    srand(time(NULL));

    int i;

    printf("Organizando a estrutura de descritores disponiveis no sistema operacional.\n");
    for(i = 0; i < MAX_PROCESS - 1; i++)
        tab_desc[i].proximo = &tab_desc[i + 1];

    tab_desc[i].proximo = NULL;

    printf("Organizando a fila desc_livres para uso dos processos.\n");
    desc_livre.primeiro = &tab_desc[0];
    desc_livre.ultimo = &tab_desc[MAX_PROCESS - 1];

    printf("Inicializando as filas: espera_cpu, usando_cpu e bloqueados.\n");
    espera_cpu.primeiro = NULL;
    espera_cpu.ultimo   = NULL;
    usando_cpu.primeiro = NULL;
    usando_cpu.ultimo   = NULL;
    bloqueados.primeiro = NULL;
    bloqueados.ultimo   = NULL;
    
    printf("Criando o primeiro processo...\n");

    cria_processo();
    cria_processo();

    show_state("Antes de iniciar");

    do_work();

    show_state("Após finalizado");

    printf("Done!\n");
    return 0;
}

