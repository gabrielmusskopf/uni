**Dia:** 10/10/2023 
**Matéria:** Analise de algoritmos
**Assunto:** Algoritmos Gulosos
**Tags:** #algoritmos 

Otimizar algoritmos de ordem exponencial
Minimizar complexidade 
Subdividir problema em partes, e cada uma toma uma decisão

Alguns problemas:
- Seleção de tarefas
- Árvore geradora mínima
- Caminhos mais curtos em grafos

Escolha gulosa = melhor escolha

## Kruskal
Árvore geradora mínima: Encontrar caminho único e menor de um nodo até outro

Etapas:
1. Dado um grafo, construir uma tabela com o valor da aresta, e os nodos ligados por ela. Essa tabela deve ser ordenada pelo valor da aresta de modo crescente
2. Iniciar um novo grafo a partir dessa tabela
3. Caso um ciclo seja fechado, a aresta de maior valor é descartada

A **parte gulosa** do algoritmo é a **decisão de eliminar a maior aresta**

## Dijkstra
