**Dia:** 18/10/2023 
**Matéria:** Memória
**Assunto:** Fundamentos de Sistemas Operacionais
**Tags:** #faculdade #memoria

No início os PCs não tinham memória, fazendo com que os circuitos fossem programados na mão, de forma estática

#### Espaço x preço x taxa de transmissão
Memórias precisam ter a mesma velocidade do processador para poder alimentá-lo. Essas memórias são as mais caras, pois ficam dentro do processador, então não tem espaço

![[Pasted image 20231018201343.png]]

## Memória física x lógica

Processo enxerga memória lógica, pois o processo é lógico

![[Pasted image 20231018202615.png]]

O responsável por mapear a memória física para lógica é o MMU (Memory Management Unit). Não é 1:1, pois SO não fica eficiente por que processos são alocados dinamicamente e com tamanhos variados, causando "furos" na memória

MMU implementado em hardware (ou metade em software, para baratiar)
MMu pode alterar o endereço da memória para melhor aproveitar o processo e recursos computacionais (memória)


Podem ser absolutos ou realocáveis


### Fases

![[Pasted image 20231018203855.png]]

Onde alocar o recurso? Fluxo de alocação:
![[Pasted image 20231018203936.png]]

#### Alocação Contígua Simples
Toda memória é posta na mesma partição separada em "Sistema operacional" e "processo do usuário"

#### Alocação Contígua Particionada
Múltiplas partições. Cada processo tem a sua partição, assim, pode isolar e proteger (dividir a memória em blocos)

Aloca processos de forma contígua estática ou dinâmica

Processo 8 acaba, e fica "livre" a partição. Outros processos são alocados nessa posição
![[Pasted image 20231018205012.png]]

#### Fixa:
Cada partição é criada com um tamanho fixo. Processos menores que esse tamanho, executam na partição, mas **sobra espaço**, processos maiores, **não são executados**

Sobra do espaço: **fragmentação interna**
Vantagem: processos com alta criticidade, como o sistema operacional

Mover entre partições exige swapping, ou seja, copiar o processo para o disco, e depois copiar para outra partição

Evolução da partição fixa é criar partições com tamanhos diferentes, e pondo uma fila para criar processos em cada partição

![[Pasted image 20231018205918.png]]

#### Alocação Particionada Dinâmica
Com a evolução do MMU, foi possível criar partições de acordo com o tamanho do processo.
Isso cria uma **fragmentação externa**, muitos espaços pequenos sobrando. Para resolver, precisa desfragmentar o disco, isso é custoso por causa do swapping out

![[Pasted image 20231018210025.png]]

