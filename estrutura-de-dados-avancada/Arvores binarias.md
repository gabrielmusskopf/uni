**Dia:** 25/08/2023 
**Matéria:** Estrutura de dados avançada
**Assunto:** árvores
**Tags:** #faculdade

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
