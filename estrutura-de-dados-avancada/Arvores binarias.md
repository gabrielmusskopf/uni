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