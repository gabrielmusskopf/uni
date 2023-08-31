**Dia:** 23/08/2023 
**Matéria:** Fundamentos de sistemas operacionais
**Assunto:** Programação concorrente
**Tags:** #faculdade #processos #programacao-concorrente 

Mais de um processo quer usar o mesmo **recurso computacional**
> Recurso computacional pode ser uma variável, um arquivo, um driver, dispositivo IO...

Paralelismo "real" em máquinas com multiprocessadores
Paralelismo "aparente" em máquinas monoprocessadas

### Alguns problemas

#### Race condition
- Vários processos manipulam os mesmo dados e o resultado depende da ordem que os acessos ocorrem

O problema é identificar onde a race condition ocorre
#### Seção crítica
- Parte onde pode ocorrer uma **race condition**, segmento de código

**Lembrete:** Desafio race condition
