---

---
**Dia:** 01/11/2023 
**Matéria:** Fundamentos de sistemas operacionais
**Assunto:** Dispositivos de entrada e saída
**Tags:** 

**Dificuldade:** Vários tipos de dispositivos de entrada/saída

Drivers ficam nos controladores e controlam/tem informação da parte mecânica do dispositivo.
O OS se comunica com o driver de forma genérica

---

**Dia:** 08/11/2023 
**Matéria:** Fundamentos de sistemas operacionais
**Assunto:** Sistemas de arquivos
**Tags:** 

**Objetivos:**
- Criar recurso lógico a partir de físico, de maneira amigável
- Validade dos dados
- Otimizar o acesso
- Fornecer suporte a outros sistemas de arquivo
- Vários usuários

Informações típicas (atributos de um arquivo): 
- Nome do arquivo 
- Tamanho em bytes  
- Data e hora da criação, do último acesso da última modificação 
- Identificação do usuário que criou o arquivo 
- Listas de controle de acesso – Localização no disco físico do arquivo

**Tabela de Descritores do Arquivo Abertos (TDAA)**
- Um arquivo aberto está vinculado a um processo

**Tabelas de Arquivos Abertos por Processo (TAAP)**
- Tabela que vincula o processo ao arquivo aberto

**Formas de alocação em disco**
- **Contígua** (alocação contígua). Mais rápida, mas pode faltar espaço. Bom para arquivos sem alterações futuras. Fragmentação interna.

**Alocação contígua: File Allocation Table (FAT)**
![[Pasted image 20231108202518.png]]


- **Não-contígua** (alocação encadeada e alocação indexada). Qualquer ponteiro perdido, já era pro arquivo. Mais demorado pois exige mais rotações, porém utiliza melhor o disco

**Alocação encadeada**
![[Pasted image 20231108202747.png]]

**Alocação Indexada**
- Bloco que mantem índices para demais. Limitação sobre o tamanho do arquivo (se o bloco for 512 bytes, arquivo máximo 512x512 bytes)

![[Pasted image 20231108203832.png]]

### i-node
Unix implementa um acesso híbrido. Parte para blocos diretos (~5M), parte para única, dupla e tripla indireção

![[Pasted image 20231108205139.png]]

Curiosidade:  "The maximum size of a file will be **8KB*(10 + 2**10 + 2**20 + 2**30), that is more than 8TB."
ref: https://cis.temple.edu/~ingargio/cis307/readings/stable.html