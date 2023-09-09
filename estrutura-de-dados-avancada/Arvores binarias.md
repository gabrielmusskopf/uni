**Dia:** 25/08/2023 
**Matéria:** Estrutura de dados avançada
**Assunto:** árvores
**Tags:** #faculdade #algoritmos

Árvore de grau dois (nenhum, um, ou dois filhos)
n nodos, pode ter n + 1 filhos
Número de folhas indica se é boa ou ruim

Árvore estritamente binária => Sempre dois filhos (ou zero)
Árvore binária completa => Qualquer folha deve estar no nível n ou n - 1
Árvore binária cheia => Todas folhas no último nível 
Árvore degenerada => Cada nó possui só um filho, mesmo número de níveis que nós. Árvore ruim pois na verdade é uma lista

## Caminhamento/Percurso

### Profundidade 
Canônicos
- Pré-ordem
- Pós-ordem
- Em-ordem = Bom para testar se a árvore está correta, sem ver graficamente

### Largura (amplitude)
- Em nível, somente

## Pesquisa
> Ou, de busca

Precisa de uma regra para inserir os valores
Ordem definida pelo campo "chave"
Maiores para um lado, menores para o outro. **Não tem valores repetidos**.  Repetidas não tem como buscar!

##### Complexidade $O(log(n))$

## Exclusão

- Excluir folha
- Excluir nó com um filho
- Excluir nó com dois filhos

# Árvores de Expressão

- Nós são operadores
- Folhas são operandos
![[Pasted image 20230825201758.png]]

Pós-ordem: 123 * +
Em-ordem: 1 + 2 * 3
Pré-ordem:  + 1 *2 3


# Exercício

![[arvores_binarias_ex_2.png]]


**Dia:** 01/09/2023 
**Matéria:** Estrutura de dados avançada
**Assunto:** Árvores AVL
**Tags:** #faculdade #algoritmos

Na ABP (Árvore Binárias de Pesquisa) a busca depende da ordem de inserção dos dados. Fica desbalancedada possivelmente

### Árvore balanceada
Altura da sub-árvore da esquerda é praticamente a mesma da altura da sub-árvore da direite.

#### Estático
Retirar tudo e inserir de novo => Horrível
#### Dinâmico
A árvore sabe que desbalanceou e organiza sozinho

# AVL 

ABP balanceada
Cada nó tem seu fator de balanceamento. Esse é a altura da sub-árvore da direita menos a altura da sub-árvore da esquerda
Os fatores vão de $-1 < FB < 1$

## Como ajeita os nós

### Rotações

#### Rotação simples
Sinal do FB for positivo, rotação simples. Significa que está desbalanceado é o esquerdo

> [!INFO]
> Rotação nunca mexe com o pai do desregulado

FIlho a esquerda vira o pai do nó desbalanceado

![[Pasted image 20230901195401.png]]


Fator negativo, **rotação à esquerda**


#### Rotação Dupla
Quando o nó com probelma tem FB **positivo** e a sub-árvore da esquerda tem valor **negativo**

## Inserção
Percorrer tudo para ver se já existe (como ABP normal), se tem, para.
Se não, insere na posição e valida e está balanceada ou não

## Exclusão
Duas maneiras, fusão ou cópia. Após excluir, validar balanceamento desde o início.
Parte da folha até a raiz


## Complexidade
Restruturação: O(1)
Pesquisa: O(logn)
Inserir: O(logn)*
Remover: O(logn)*

* De fato é 2 logn, pois precisa buscar uma vez primeiro para ver se existe. Mas Big O não considera a constante

![[arvores_binarias_ex_3.png]]


**Dia:** 08/09/2023 
**Matéria:** Estrutura de dados avançadas
**Assunto:** Árvores B
**Tags:** 

# Árvores B
> [!INFO]
> Vários tipos. Vamos usar o tipo visto em aula!

## Introdução
- Sempre balanceada
- Cada nó tem mais de um elemento, diferente da AVL
- Feito isso para evitar disperdício de espaço dentro de um nó

## Características
- Ao contrário da AVL, raiz sobre, ao invés de crescer para baixo
- Todas folhas tem exatamente mesma distância da raiz. Diferente da AVL, que é praticamente
- Nós são chamados de páginas
- Ordem **m**, tamanho da página. Exemplo, m=2 tem no mínimo 2 elementos e no máximo 4 (2m)
- Ocupação mínima de 50% em cada nó (exceto raiz)
- Raiz é o único nó que pode ter 1 elemento

## Operações
### Inserção
- Pesquisar antes de inserir
- Após achar a posição e inserir, caso exceda o limite (2m), ocorre um **overflow**
	- Nesse caso, o elemento do **meio** passa para a página de cima (cria, se não existir), e os outros elementos, em páginas
	- Se a página de cima estiver cheia, repete o procedimento
- Inserir sempre na folha, mesmo que na raiz tenha espaço

Utilização de elementos
![[Pasted image 20230909153801.png]]

Número de acessos, no pior caso
![[Pasted image 20230909153826.png]]

## Complexidade
$$
\begin{equation}
\begin{split}
	Inserção \ e \ busca:& \ O(log_m \ n)\\
	Remoção:& \ O(1)\\
\end{split}
\end{equation}
$$
Sendo $m$ a ordem e $n$ o número de elementos


## Remoção

Casos:
1. Registro em folha
	1. Folha possui mais do que $m$ registros
	2. Folha possui $m$ registros mais irmão possui mais do que $m$
	3. Folha e irmãos possuem $m$ registros
		1. Pai pode emprestar
		2. Pai não pode emprestar
2. Registro em nó
	1. Sub-árvore vizinha possui registros para emprestar
	2. Sub-árvore vizinha não possui registros para emprestar
		1. Irmão possui registro para emprestar
		2. Irmão não possui registro para emprestar

#### 1.1 Folha possui mais do que $m$ registros
Basta remover da folha, sem mais arranjos.
![[Pasted image 20230909155352.png]]

#### 1.2 Folha não possui mais do que $m$ registros, mas o irmão sim
É feito um empréstimo, rotacionando com o pai
![[Pasted image 20230909155722.png]]

#### 1.3.1 Folha e irmãos não possuem mais do que $m$ registros, mas pai pode emprestar

Passar o menor/maior do pai para a página filha e unificar com irmão
![[Pasted image 20230909160904.png]]

#### 1.3.2 Folha e irmãos não possuem mais do que $m$ registros e pai não pode emprestar

Nesse caso é onde ocorre um **undeflow**, situação onde a página tem menos elementos do que o mínimo necessário. 
Para resolver, os elementos da página se unem com um irmão, junto com o elemento da página pai.

![[Pasted image 20230909161807.png]]

Após resolver a situação das folhas, ocorreu um underflow no nó com o elemento laranja. Por isso, a solução é recursiva

Primeiro problema a resolver é as páginas filhas

#### 2.1 Sub-árvore vizinha possui registros para emprestar

Qualquer um dos elementos da ponta nesse caso podem subir
![[Pasted image 20230909162219.png]]

#### 2.2.1 Sub-árvore vizinha não possui registros para emprestar, mas irmão sim

Parecido com a remoção da folha na 1.2.2, pega emprestado do pai fazendo movimento cíclico

![[Pasted image 20230909162924.png]]

#### 2.2.1 Sub-árvore vizinha e irmão não possuem registros para emprestar

Primeiro resolve os filhos, unificando, depois unifica com pai e irmão
![[Pasted image 20230909163720.png]]

> [!WARNING]
> Primeiro resolver os filhos

## Exercício
![[arvores_binarias_ex_4.png]]