**Dia:** 16/08/2023 
**Matéria:** 60968 - Fundamentos de sistemas operacionais
**Assunto:** Processos
**Tags:** #faculdade

## Glossário
- Priorizar = escalonar
- Características = métricas

# Ciclo de vida de um processo

Objetivo do SO é aproveitar os recursos do hardware

### Multiprogramação
>Ou, multiprocessos

Assumir que tem mais processos do que hardware.
Processos não ocorrem simultaneamente no processador, mas são tão rápidos que parem ser
Interação entre processos é dada por interrupções

SO também é um processo

Um programa na memória já é um processo
Buffer Overflow significa "invadir" outro processo, ou seja, acessar um espaço na memória onde está (ou pode estar) alocado outro processo

#### Programa vs Processo
Programa
- Programa é estático
- Sequência de instruções

Processo
- Dinâmico, ou seja, o usuário não tem controle. Não tem controle de onde será alocado, pode estar aguardando CPU...
- Instância de um programa (Parecido com OO, classes e instâncias)

Informações do processo ficam no processo, e não no SO. Como por exemplo, onde está na memória, se usa processador, seu PID

Privilégios dependem do usuário que executa
Processos do sistema operacional, que oferecem serviços, são chamados de *deamons*
Serviços são processos que "aguardam" interação 

## Ciclo de vida
Versão simplificada:

- #### Criação
- #### Execução
Ciclo de processador (tempo de CPU)
Ciclo de entrada e saída (IO)

CPU => IO = Chamada de sistema
IO => CPU = ocorrência de evento (interrupção)

CPU bound => Média de processos de CPU
IO bound => Média de processos de IO
Para OS equilibrado, CPU bound e IO bound equilibrado
Para performance, pode ser que tenha um com maior valor

- #### Término
Término por fim, por que deveria de fato
Término por error
Intervenção por outros processos (kill)
Log off de usuários

# Relacionamento entre processos

####  Processos independentes
- Não apresentam relacionamentos
#### Grupo de processos
- Apresentam afiliação. Exemplo: boot no sistema, um processo inicia outro
- Podem compartilhar recursos

### Hierarquia de processos

Processo criador => Processo pai
Processo criado => Processo filho
Relação relevante é só entre pai/filho

![[Pasted image 20230816210604.png]]

Quantidade total de PIDs é $2^{64}$

## Modelo de dois estados

Processo criado vai para um buffer para o escalonador (dispatcher) decidir quando executar
Manter uma fila de processos

![[Pasted image 20230816211335.png]]

## Limitação do modelo simplificado

## Modelo de cinco estados

![[Pasted image 20230816211535.png]]

Como IO 

##### Troca de contexto/chaveamento de contexto
Quando processo ganha processador as salva nos registradores
Quado perde, salva o contexto dos registradores

## Processos Suspensos

SO tem tratador de interrupções

Tipos de interrupção:
- Hardware: Evento externo
- Software: Execução de uma instrução
- Exceção: Erros de execução, overflow, underflow...

Identificadas por um número: Vetor de interrupção
# Modos de operação