
# Escrever o menu               OK
# Escrever a classe pai         OK
# Escrever ComputingProcess     OK
# Escrever WritingProcess       OK
# Escrever ReadingProcess       OK
# Escrever PrintingProcess      OK


class Process:
    def __init__(self, pid):
        self.pid = pid
        print(f'Adicionado o processo de PID {self.pid}')

    def execute(self):
        pass


class FilaProcessos:
    def __init__(self):
        self.pid_atual = 1
        self.maximo = 10
        self.ocupados = 0
        self.processos: list[Process] = [None] * self.maximo
        self.fila_arquivo = 'fila.txt'

    def adicionar(self, processo: Process):
        if self.ocupados >= self.maximo:
            print('Não existe espaco livre')
            return False

        for i in range(0, len(self.processos)):
            if self.processos[i] is None:
                self.processos[i] = processo
                self.ocupados += 1
                self.pid_atual += 1
                return True

        return False

    def _remover(self, indice):
        self.ocupados -= 1
        self.processos.pop(indice)
        self.processos.append(None)

    def executar_proximo(self):
        if self.ocupados == 0:
            print('Nada para ser executado')
            return

        for i in range(0, len(self.processos)):
            processo = self.processos[i]
            if processo is not None:
                processo.execute()
                self._remover(i)
                break

    def executar_pid(self, pid):
        if self.ocupados == 0:
            print('Nada para ser executado')
            return

        for i in range(0, len(self.processos)):
            processo = self.processos[i]
            if processo is not None and processo.pid == pid:
                processo.execute()
                self._remover(i)
                break

    def salvar(self):
        print(f'Escrevendo processos atuais em {self.fila_arquivo}')
        with open(self.fila_arquivo, 'w') as arquivo:
            for processo in fila.processos:
                # computing;pid;expressao
                # writing;pid;expressao
                # reading;pid
                # printing;pid

                if isinstance(processo, ComputingProcess):
                    expressao = processo.get_expressao()
                    arquivo.write(f'computing;{processo.pid};{expressao}\n')

                if isinstance(processo, WritingProcess):
                    expressao = processo.get_expressao()
                    arquivo.write(f'writing;{processo.pid};{expressao}\n')

                if isinstance(processo, ReadingProcess):
                    arquivo.write(f'reading;{processo.pid}\n')

                if isinstance(processo, PrintingProcess):
                    arquivo.write(f'printing;{processo.pid}\n')

        print('Processos escritos com sucesso')

    def ler(self):
        print(f'Reinicializando processos com base no arquivo {self.fila_arquivo}')
        self.processos = [None] * self.maximo
        self.ocupados = 0

        with open(self.fila_arquivo, 'r') as arquivo:
            for linha in arquivo:
                partes = linha.split(';')
                tipo = partes[0]
                self.pid_atual = int(partes[1])

                if tipo == 'computing':
                    processo = ComputingProcess(self.pid_atual, partes[2])
                    self.adicionar(processo)

                elif tipo == 'writing':
                    processo = WritingProcess(self.pid_atual, partes[2])
                    self.adicionar(processo)

                elif tipo == 'reading':
                    processo = ReadingProcess(self.pid_atual, self)
                    self.adicionar(processo)

                elif tipo == 'printing':
                    processo = PrintingProcess(self.pid_atual, self)
                    self.adicionar(processo)

                else:
                    print(f'Tipo {tipo} desconhecido')

        print('Reinicializado')


class ComputingProcess(Process):
    def __init__(self, pid, expressao):
        super().__init__(pid)

        self.is_ok = False
        self.operacoes_suportadas = ["+", "-", "*", "/"]

        partes = self.le_expressao(expressao)
        if partes is None or len(partes) != 3:
            return

        self.is_ok = True
        self.op1 = partes[0]
        self.operacao = partes[1]
        self.op2 = partes[2]

    def get_expressao(self):
        return f'{self.op1}{self.operacao}{self.op2}'

    def le_expressao(self, expressao):
        for operacao in self.operacoes_suportadas:
            partes = expressao.split(operacao)
            if len(partes) == 2:
                # Conseguiu fazer o split com a operação. Significa que é essa a operação desejada
                return [float(partes[0].strip()), operacao, float(partes[1].strip())]

        print(f'Expressão inválida: "{expressao}". Esperado <a> <operação> <b>')

    def execute(self):
        if not self.is_ok:
            print('Não foi possível realizar a operação')
            return

        resultado = 0
        if (self.operacao == "+"):
            resultado = self.soma()
        elif (self.operacao == "-"):
            resultado = self.subtracao()
        elif (self.operacao == "*"):
            resultado = self.multiplicacao()
        elif (self.operacao == "/"):
            resultado = self.divisao()
        else:
            print(f'Operação inválida. Suportadas: {self.operacoes_suportadas}')
            return

        print(f"{self.op1} {self.operacao} {self.op2} = {resultado}")

    def soma(self):
        return self.op1 + self.op2

    def subtracao(self):
        return self.op1 - self.op2

    def multiplicacao(self):
        return self.op1 * self.op2

    def divisao(self):
        return self.op1 / self.op2


class WritingProcess(Process):
    def __init__(self, pid, expressao):
        super().__init__(pid)

        self.nome_arquivo = 'computation.txt'
        self.expressao = expressao

    def get_expressao(self):
        return self.expressao

    def execute(self):
        print(f'Escrevendo expressão em {self.nome_arquivo}')

        with open(self.nome_arquivo, 'a') as arquivo:
            arquivo.write(f'{self.expressao}\n')

        print('Expressão escrita com sucesso')


class ReadingProcess(Process):
    def __init__(self, pid, fila: FilaProcessos):
        super().__init__(pid)
        self.nome_arquivo = 'computation.txt'
        self.fila = fila

    def execute(self):
        print(f'Lendo expressões em {self.nome_arquivo}')

        with open(self.nome_arquivo, 'r') as arquivo:
            for linha in arquivo:
                processo = ComputingProcess(self.fila.pid_atual, linha)
                self.fila.adicionar(processo)

        with open(self.nome_arquivo, 'w') as arquivo:
            # Abrir em modo de escrita para limpar o arquivo
            pass

        print('Expressoes lidas com sucesso!')


class PrintingProcess(Process):
    def __init__(self, pid, fila: FilaProcessos):
        super().__init__(pid)
        self.fila = fila

    def execute(self):
        for i in range(0, len(self.fila.processos)):
            processo = self.fila.processos[i]
            print(f'{i + 1} - {processo.pid if processo is not None else "<?>"}')


fila = FilaProcessos()


def criar_processo_menu():
    print("Escolha qual processo deseja criar")
    print("1. Processo de cálculo")
    print("2. Processo de gravação")
    print("3. Processo de leitura")
    print("4. Processo de impressão")
    print()

    opcao = int(input("-> "))

    if (opcao == 1):
        print("Digite a expressão a ser calculada (exemplo: 10 + 10)")
        expressao = input("-> ")
        processo = ComputingProcess(fila.pid_atual, expressao)
        fila.adicionar(processo)

    elif (opcao == 2):
        print("Digite a expressão a ser escrita (exemplo: 10 + 10)")
        expressao = input("-> ")
        processo = WritingProcess(fila.pid_atual, expressao)
        fila.adicionar(processo)

    elif (opcao == 3):
        processo = ReadingProcess(fila.pid_atual, fila)
        fila.adicionar(processo)

    elif (opcao == 4):
        processo = PrintingProcess(fila.pid_atual, fila)
        fila.adicionar(processo)

    else:
        print("Opção desconhecida")

    print()


def main():
    while (True):
        print("Simulador de Execução de Processos")
        print("1. Criar processos")
        print("2. Executar o próximo processo")
        print("3. Executar um processo específico")
        print("4. Salvar a fila de processos")
        print("5. Carregar do arquivo a fila de processos")
        print("0. Sair")
        print()

        opcao = int(input("-> "))

        if opcao == 0:
            break
        elif opcao == 1:
            criar_processo_menu()
        elif opcao == 2:
            fila.executar_proximo()
        elif opcao == 3:
            print("Qual o PID?")
            pid = int(input("-> "))
            fila.executar_pid(pid)
        elif opcao == 4:
            fila.salvar()
        elif opcao == 5:
            fila.ler()
        else:
            print("Opção desconhecida")

        print()


main()
