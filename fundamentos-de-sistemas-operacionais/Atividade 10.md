**Dia:** 06/11/2023 
**Matéria:** Fundamentos de Sistemas Operacionais
**Assunto:** Atividade 10

```shell
Disco /dev/nvme0n1: 238,47 GiB, 256060514304 bytes, 500118192 setores
Disk model: PC SN520 NVMe WDC 256GB
Unidades: setor de 1 * 512 = 512 bytes
Tamanho de setor (lógico/físico): 512 bytes / 512 bytes
Tamanho E/S (mínimo/ótimo): 512 bytes / 512 bytes
Tipo de rótulo do disco: gpt
Identificador do disco: 468627CB-0363-408B-ABC3-27CABCEEB80C

Dispositivo       Início       Fim   Setores Tamanho Tipo
/dev/nvme0n1p1      2048    976895    974848    476M Sistema EFI
/dev/nvme0n1p2 484493312 500117503  15624192    7,5G Linux swap
/dev/nvme0n1p3    976896 484493311 483516416  230,6G Linux sistema de arquivos
```

**Setores:** 512 bytes
Divididos em 3 setores:
- [2048, 976895]: 974848 setores 
- [484493312,500117503]:  15624192 setores
- [976896,484493311]: 483516416 setores

**Sistema de arquivos**
```shell
Sist. Arq.      Tam. Usado Disp. Uso% Montado em
tmpfs           1,6G  2,8M  1,6G   1% /run
/dev/nvme0n1p3  226G  129G   86G  61% /
tmpfs           7,7G  604M  7,1G   8% /dev/shm
tmpfs           5,0M  4,0K  5,0M   1% /run/lock
/dev/nvme0n1p1  476M  6,1M  469M   2% /boot/efi
tmpfs           1,6G  172K  1,6G   1% /run/user/1001
```

Espaço usado no sistema montado em `/` : 61%. Livre 39%

**Diretório**: 
```shell
 du -h -d 1
16K     ./@templates
4,8M    ./algoritimos-e-programacao
92K     ./projeto-sistemas-digitais
110M    ./.git
8,7M    ./analise-algoritimos
20K     ./monitoria
14M     ./@imagens
15M     ./estrutura-de-dados-avancada
3,7M    ./.obsidian
61M     ./fundamentos-de-sistemas-operacionais
2,8M    ./calculo-diferencial
218M    .
```

**i-nodes**
```shell
➜ df -i
Sist. Arq.         Inós  IUsado   ILivre IUso% Montado em
tmpfs           2013285    1613  2011672    1% /run
/dev/nvme0n1p3 15114240 2134620 12979620   15% /
tmpfs           2013285    1832  2011453    1% /dev/shm
tmpfs           2013285       4  2013281    1% /run/lock
/dev/nvme0n1p1        0       0        0     - /boot/efi
tmpfs            402657     203   402454    1% /run/user/1001
```

i-nodes usados no sistema montado em `/` : 2134620, livre: 12979620

Não foi possível obter informações de cilindros, setores e trilhas por contar somente com SSD.

2. O gerenciamento dos sistemas de entrada/saída de dados é normalmente implementado em duas camadas: uma responsável pelo controle do dispositivo e outra, pelo gerenciamento de entrada/saída. Por que isso representa um projeto eficiente? 

   (a) Porque permite o uso de duas linguagens de programação na sua implementação, pois o controle do dispositivo exige a programação em linguagem de máquina. 
   (b) Porque permite separar as operações de entrada das operações de saída de dados. 
   (c) Porque permite o compartilhamento dos dispositivos de entrada/saída através do gerenciamento de entrada/saída. 
   (d) Porque permite evitar o uso de DMA para a operação de entrada/saída. 
   (e) Porque permite separar características de hardware de características funcionais do dispositivo de entrada/saída.o
   
   Resposta: **e)**

   3. Em relação ao acesso direto à memória no gerenciamento de E/S de um sistema operacional, assinale a alternativa correta.
      
      (a) A controladora de DMA não permite um acesso independente ao barramento do sistema, dependendo sempre da CPU. 
      b) A controladora de DMA só depende da CPU quando o processador possui mais de um núcleo. 
      (c) A localização física da controladora de DMA deve ficar próxima à CPU para que o acesso ao barramento do sistema seja independente da CPU.
      (d) A localização física da controladora de DMA deve ficar distante da CPU para que o acesso ao barramento do sistema seja independente da CPU. 
      (e) Não importa a localização física da controladora de DMA, ela sempre terá acesso ao barramento do sistema de forma independente da CPU
	  
   Resposta: **e)**

4. O sistema operacional desempenha um papel importante no tratamento da E/S, atuando como interface entre o hardware e o software que solicita a E/S. Neste contexto é correto afirmar que: 
   
   (a) os sistemas de E/S normalmente usam interrupções para comunicar informações sobre operações de E/S. Como essas interrupções causam uma transferência ao modo kernel ou supervisor, elas precisam ser tratadas pelo sistema operacional (SO). 
   (b) não é responsabilidade do sistema operacional fornecer abstrações para acessar dispositivos nem fornecer rotinas que tratam as operações de baixo nível dos dispositivos. 
   (c) o sistema operacional tenta oferecer acesso equilibrado aos recursos de E/S, mas não é responsabilidade do SO escalonar acessos a fim de melhorar a vazão do sistema. 
   (d) o sistema operacional precisa ser capaz de dar comandos aos dispositivos E/S. Esses comandos incluem apenas operações como ler e escrever. 
   (e) o sistema operacional precisa ser capaz de comunicar-se com os dispositivos de E/S mas não pode impedir que o programa do usuário se comunique com os dispositivos de E/S diretamente.

   Resposta: **d)**