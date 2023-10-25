**Dia:** 20/10/2023 
**Matéria:** Estrutura de dados avançada
**Assunto:** Árvores TRIE
**Tags:** #faculdade #algoritmos 

TRIE: Retrival

**Objetivos (alguns)**
- Auto completar palavras
- Corretor ortográfica

Chaves não são mais um nodo. Caminho forma uma chave
Árvore ordenada
Árvore n-ária
Chaves podem ser letras, espaços, números...
Cada palavra tem um finalizador, mesmo que o caminho continue

Boa para dicionários muito grandes. Outras árvores para espaços menores, como PATRICIA

#### Deleção
1. Verifica se existe
2. Começa a remover do fim da palavra, até encontrar uma bifurcação (para não remover o prefixo de outra palavra). Lembrando que o **finalizador é um nodo**

#### Complexidade
Pior caso **O(AK)**: 
- A Tamanho do alfabeto (quantidade de letras no dicionário)
- K Tamanho da palavra


**Transposição:** Algoritmo deve validar todas as letras quando estiver comparando a segunda letra das letras invertidas. No exemplo, ao buscar o S, deve validar todos os S e os respectivos pais, até encontrar a letra que fecha