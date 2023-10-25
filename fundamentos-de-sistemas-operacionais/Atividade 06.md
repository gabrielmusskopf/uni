1. Mutex
	A ordem de threads executando a tarefa, com mutex, é caótica, ou seja, não possui nenhum tipo de ordem. Para além de não ter ordem, a quantidade de vezes que cada thread executa, ou tenta executar, a tarefa também é aleatória.
	O mutex foca no elemento que é acessado por n threads (no caso, os garfos)

2. Semáforo
	No semáforo, ao invés de cada thread controlar o lock/unlock daquela seção, uma variável é responsável por controlar o estado das threads. Podemos observar essa diferença nos logs de ambas implementações: 
	
	Com mutex, cada filsósofo (thread) tenta pegar o garfo esquerdo, caso consiga, o garfo esquerdo fica com lock, depois o direito, caso consiga, lock no direito, não consiga em algum desses, libera os locks 
	
	![[Pasted image 20230918133527.png]]
	
	Já com semáforo, a cada interação, todos filósofos são imprimidos na tela, mostrando seu estado atual, e controlando quando cada filósofo vai comer
	![[Pasted image 20230918133640.png]]

3. Em um sistema computacional multiprocessado, onde o sistema operacional realiza escalonamento de tarefas do tipo preemptivo, três processos (P1, P2 e P3) compartilham recursos (R1, R2 e R3). Os processos P1 e P2 concorrem entre si ao acesso do recurso R1, enquanto P2 e P3 concorrem entre si ao acesso dos recursos R2 e R3. Os recursos R1 e R3 são preemptíveis, ou seja, podem sofrer preempção; R2 é um recurso não preemptível. Todos os três processos usam o mesmo mecanismo de exclusão mútua para garantir acesso exclusivo em suas seções críticas. Com base nesse cenário, é correto afirmar que:
   
   c) É possível ocorrer deadlock entre P2 e P3.

4. Analise as seguintes afirmativas. I. Condições de corrida podem ocorrer se múltiplas threads fazem leituras de um dado compartilhado, mesmo que nenhuma realize escritas. II. O uso de mutex para a exclusão mútua em seções críticas garante que não haja condição de corrida, porém pode ocasionar deadlocks se não for corretamente empregado. III. Monitores são baseados em um tipo abstrato de dados e um controle de acesso aos dados. Apenas funções do monitor acessam os dados e apenas uma thread ou processo pode executar funções de um monitor por vez. IV. Semáforos têm duas operações, P( ) e V( ), sendo que apenas a operação P( ) pode bloquear um processo ou thread.
   
   d) apenas as afirmativas II, III e IV são verdadeiras.

![[Pasted image 20230920161304.png]]

O código não satisfaz a regra 2 (progressão) pois quando uma thread X entra em uma seção crítica, mudando a flag de j (índice correspondente a posição no array de flags) para true, bloqueia a execução da thread Y, deixando-a em busy wait, sem trabalhar, e consumindo ciclos de CPU