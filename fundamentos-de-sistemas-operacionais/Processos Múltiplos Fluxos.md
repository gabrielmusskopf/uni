**Dia:** 06/09/2023 
**Matéria:** Fundamentos de sistemas operacionais
**Assunto:** Processos múltiplos fluxos de execução
**Tags:** #faculdade #processos

Utilizar a estrutura pesada de um processo existente para adicionar novos "processos" na mesma estrutura (threads)

![[Pasted image 20230906195840.png]]

Múltiplos fluxos tem acesso a mesma variável. Possibilidade de **race condition**. 

OBS: Race condition ocorre em fluxos múltiplos e singulares

## Multiprogramação pesada
- IPC (estruturas mais complexas)
- Gerenciamento do modelo de dados
- Trocas de contexto

## Multiprogramação leve
- Mais leve por que compartilha a mesma estrutura do processo
- Descritor de threads
- Menos complexa (mais leve)

![[Pasted image 20230906201212.png]]

## Modelo N:1
- Para o OS, um único processo. N fluxos para 1 processo
- API cria, sincroniza threads
- User-level threads
- Permite realizar múltiplas coisas "ao mesmo tempo" no processo (exemplo: browser, aplicativos...)
- Performance não priorizada, mas sim, funcionalidade
- **Não tem paralelismo em máquinas multiprocessadas**

A API tem um escalonador próprio, pois o OS não entende múltiplos fluxos.

## Modelo 1:1
- OS entende o que é thread
- Thread a nível de sistema
- Troca de contexto por fluxo de execução
- **Paralelismo em máquinas multiprocessadas**

![[Pasted image 20230906203058.png]]

## Modelo M:N
- OS entende thread, mas ainda existe a API (biblioteca)
- Modelo híbrido

![[Pasted image 20230906203431.png]]


### Desvantagens
- Sincronismo