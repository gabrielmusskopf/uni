**Dia:** 27/09/2023 
**Matéria:** Fundamentos de sistemas operacionais
**Assunto:** Escalonador
**Tags:** #faculdade #processos

Evolução de sistemas multiprogramação
- **batch:** Coloca processos na fila (em memória). Fila de jobs. Perde sentido quando processos são IO e CPU bound
- **time-sharing**: Dar um tempo para o processo, caso exceda, continua em outro ciclo. Compartilhando tempo de CPU
- **tempo real**: Processos com limite de tempo, caso exceda, não executa mais. Deadline
Máquinas comuns geralmente são time-sharing, mas é configurável

Partes do escalonador:
- **Escalonador:** política de seleção. Decide a estrutura de dados, algoritmos, que usa na seleção de processos
- **Dispatcher:** Faz a troca de um processo por outro na CPU. Antes de tirar do processador, salvar valores do processo que estão nos registradores, para que o próximo processo sobrescreva. Chaveamento e troca de contexto

Objetivos (possíveis) do escalonador:
- Maximizar a utilização do processador
- Maximizar a produção (throughput): Número de processos executados por unidade de tempo (PCs de uso diário, por exemplo)
- Minimizar o tempo de execução (turnaround): Tempo total para executar um processo
- Minimizar tempo de espera: Tempo que um processo permanece na lista de aptos
- Minimizar tempo de resposta: Tempo decorrido entre uma requisição e sua realização

Em abundância de recurso, não há problema.

Escalonador de curto prazo:
- Geralmente, máquinas de uso pessoal tem essa política
- Não define limite, caso estoure, ninguém é servido

Escalonador de longo prazo:
- Define limite de uso
- Antes de entrar na fila de aptos, valida de pode criar. Controle de admissão
- Quando há muita demanda, posso: ignorar novas demandas
- Proteger o sistema

![[Pasted image 20230927200855.png]]

Tipos de escalonadores:
- **Não preemptivo**: Processo inicia e vai até o final. Prioriza processos grandes (elefantes), pois demais processos pequenos ficam aguardando os grandes finalizarem
- **Preemptivo**: Processo ganha intervalo de tempo para ser executado (quantum, time slot). Soluções mais justas

## Algoritmos de escalonamento
Não preemptivos
- FIFO
	- Média de tempo na fila depende do tempo de cada processo
	- Prejudica processo I/O bound
- Shortes Job First (SJF)
	- Primeiro processa menores processos (I/O bound)
	- Não prevê o tempo do próximo processo
Preemptivo
- Round Robin (circular)
- Baseado em prioridades
Outros
- High Response Ratio Next (HRRN)
- Shortest Remaining Time (SRT)

**Solução ótima:** Algoritmo com a solução perfeita, solução exata
**Heurística:** Dada que não existe uma solução ótima (não tenho capacidade de dar uma solução ótimo), cria algoritmo com caminho viável
Não existe solução ótima por que não consegue prever qual o próximo processo.

#### Round Robin
Cada processo usa um quantum

![[Pasted image 20230927204743.png]]

Problema: Qual o tamanho do quantum?
Se $\infty$ se comporta como fila, se muito pequeno, muitas trocas de contexto

E quando precisa **priorizar**? Criar múltiplas filas de aptos (fila com prioridades)
![[Pasted image 20230927205104.png]]

Como definir uma prioridade?
- Estática: Nasce com o processo e permanece até o fim
- Dinâmica: Modifica de acordo com o ciclo de vida

Problemas:
- Starvation: Processo com menos prioridade pode não ser atendido nunca. Com o passar do tempo, ganha prioridade (algoritmo de envelhecimento/aging)