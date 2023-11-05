# AVL Indexada

Aplicação que utiliza a estrutura de árvore binária de busca balanceada AVL. Carrega um arquivo contendo informações de pessoas e possibilitar rápido acesso aos registros por meio de consultas por campos individuais.

Funcionalidades:
- Leitura de arquivo CSV com campos predefinidos
- Indexação de informações de "nome", "CPF" e "data de nascimento" em árvores AVL. A indexação não causa novas alocações na memória, somente utiliza das referências existentes e computadas antes da indexação contidas em um array
- Busca por nome, retornando todos os registros que iniciam com o valor procurado
- Busca por CPF exato
- Busca por intervalo de data de nascimento

O algoritmo usa uma árvore AVL genérica com chave e valor para criar os vínculos entre a chave e a referência em memória. Portanto, para que um tipo seja usado como chave, é preciso que exista alguma maneira de compará-lo a outro, para que seja possível a inserção e busca na árvore.

Para isso, a árvore espera um tipo de dado que implemente `Ordered`. Na aplicação, é possível encontrar exemplos em `types`

## Como executar
Clonar o projeto e executar
```shell
make dev
```

## Testes
Existem alguns testes básicos que podem ser executados com
```shell
make tests
```
