**Dia:** 27/08/2023 
**Matéria:** Fundamentos de sistemas operacionais
**Assunto:**  Processos

4. Em relação ao gerenciamento de processos, atribua V (verdadeiro) ou F (falso) à
afirmativas a seguir.
- [ ] Na espera ocupada, o processo é transferido para estado de bloqueado até que sua
fatia de tempo termine e então ele retorna para fila de prontos.
- [v] O bloco de controle de processos (BCP – Process Control Block) é utilizado para
armazenar informações sobre processos, e essas informações são utilizadas na troca de
contexto de processos.
- [v] Threads apresentam menor custo de criação quando comparadas aos processos, pois
compartilham alguns elementos do processo, como espaço de endereçamento.
- [ ] Um processo pode estar nos seguintes estados: pronto, aguardando execução, em
execução e bloqueado.
- [v] Um processo pode ser criado por uma chamada de sistema fork (), nesse caso, o
processo gerado (conhecido como “filho”) é uma cópia exata do processo original, com os
mesmos valores de variáveis em memória, diferenciando-se apenas no identificador do
processo.

D) F, V, V, F, V

5. Em relação ao escalonamento implementado por um sistema operacional,
analise as seguintes proposições.
1) No sistema conhecido como preemptivo, quem está com a CPU deve,
voluntariamente, devolvê-la ao sistema operacional após o término de sua fatia
de tempo.

R: Não está correto pois não é o processo que devolve os recursos, mas sim, o SO que interrompe o processo, armazenando seu contexto atual, pois outro processo teve prioridade, ou o processo está executando I/O

2) Com o uso de interrupções em um hardware protegido, o sistema operacional
pode definir por quanto tempo a CPU é cedida, pegando-a de volta quando esse
tempo acabar.

3) No escalonamento conhecido como Time Sharing, o tempo de CPU é dividido
entre ciclos CPU bound e I/O bound. Diz-se que o sistema é preemptivo quando
ele possui muito mais do tipo CPU bound e poucos do tipo I/O bound.

R: Não está correto pois um sistema preemptivo geralmente é um sistema utilizado por usuário, e esses exigem diversos I/O bound para a interface gráfica, fazendo com que muitos processos fiquem bloqueados aguardando a conclusão da interação I/O

Está(ão) correta(s), apenas: 

B) 2

6. Descreva uma vantagem e uma desvantagem de um Bloco Descritor de Processo. Qual a relação de Blocos Descritores com os processos?

A vantagem do bloco descritor de processo é a possibilidade de executar outros processos enquanto o processo está executando uma chamada de sistema que exige I/O. Como a frequência do processador é muito maior que a frequência de dispositivos I/O, nesse tempo outros processos podem ser executados.

Uma desvantagem dessa abordagem é que a troca de contexto é uma operação cara para o sistema operacional.

7. Sistemas Operacionais podem ser vistos como Alocadores de Recursos e Sistemas de Controle. Descreva exemplos sobre essas duas diferentes visões de Sistemas Operacionais. Como podem ser relacionadas essas visões com os objetivos de abstração e eficiência de Sistemas Operacionais?

Sistemas operacionais são alocadores de recursos pois fazem a ponte entre as aplicações (software) e a máquina (hardware). Um programa precisa de recursos computacionais para que seja executado, como ciclos de processamento, um espaço (endereço) na memória RAM, acesso ao disco físico, etc. 
Sistemas operacionais também são sistemas de controle pois não será executada somente uma aplicação, na verdade, o que ocorre é o contrário, quanto mais aplicações executarem, melhor (normalmente). Para que múltiplas aplicações ou processos ocorram "simultaneamente", o sistema operacional precisa distribuir os recursos de forma justa aos processos (em casos de sistemas operacionais que não são tempo real), fazendo com que atue como um controle de recursos para os processos.
Ambos comportamentos são necessários para um sistema operacional pois desejamos usufruir o máximo do potencial de hardware, e executar múltiplos processos de forma que pareçam simultâneos.

#glossário
> **Termo:** Preemptivo
> **Fonte:** Wikipedia
> **Data:** 27/08/2023
> **Significado:** Em computação, preemptividade (algumas vezes preempção) é o ato de interromper temporariamente uma tarefa sendo executada por um sistema computacional, sem exigir sua cooperação, e com a intenção de retomar à tarefa posteriormente. Tais mudanças da tarefa executada são conhecidas como trocas de contexto. Normalmente, é realizada por uma tarefa privilegiada ou parte do sistema conhecido como escalonador preemptivo, que tem o poder de antecipar, interromper e, posteriormente, retomar outras tarefas no sistema.