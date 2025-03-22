import os


class Reserva:
    def __init__(self, data_inicio, data_fim, cliente, quarto, status):
        self.data_inicio = data_inicio
        self.data_fim = data_fim
        self.cliente = cliente
        self.quarto = quarto
        self.status = status

    def get_data_inicio(self):
        return self.data_inicio

    def get_data_fim(self):
        return self.data_fim

    def get_cliente(self):
        return self.cliente

    def get_quarto(self):
        return self.quarto

    def get_status(self):
        return self.status

    def esta_dentro_das_datas(self, data):
        return self.data_inicio <= data and data <= self.data_fim

    def __str__(self):
        return f"{self.data_inicio};{self.data_fim};{self.cliente};{self.quarto.get_numero()};{self.status}"


# Produtos da pousada
produtos = []


class Quarto:
    def __init__(self, numero, categoria, diaria, consumo):
        self.numero = numero
        self.categoria = categoria
        self.diaria = diaria
        self.consumo = consumo

    def get_numero(self):
        return self.numero

    def adiciona_consumo(self, consumo):
        self.consumo.append(consumo)

    def lista_consumo(self):
        self.consumo

    def valor_total_consumo(self):
        total = 0
        for produto in produtos:
            for codigo in self.consumo:
                if produto.codigo == codigo:
                    total = total + produto.preco
        return total

    def limpa_consumo(self):
        self.consumo = []

    def __str__(self):
        # Converter esse quarto em texto para salvar em quarto.txt
        return f"{self.numero};{self.categoria};{self.diaria};{self.consumo}"


class Pousada:
    def __init__(self, nome, contato, quartos, reservas, produtos):
        self.nome = nome
        self.contato = contato
        self.quartos = quartos
        self.reservas = reservas
        self.produtos = produtos

    def get_quartos(self):
        return self.quartos

    def get_reservas(self):
        return self.reservas

    def get_produtos(self):
        return self.produtos

    def existe_quarto(self, numero):
        for quarto in self.quartos:
            if quarto.get_numero() == numero:
                return True
        return False

    def buscar_quarto(self, numero):
        for quarto in self.quartos:
            if quarto.get_numero() == numero:
                return quarto
        return None

    def consulta_disponibilidade(self, data, numero_quarto):
        if not self.existe_quarto(numero_quarto): 
            print("Quarto não existe")
            return

        quarto = self.buscar_quarto(numero_quarto)

        for reserva in self.reservas:
            quarto = reserva.get_quarto()
            if quarto.get_numero() == numero_quarto and reserva.esta_dentro_das_datas(data):
                print("Quarto existe mas está reservado nesse período")
                return

        return quarto


def serializar(pousada):
    # Converte os objetos em texto e armazena nos respectivos arquivos

    print("Serializando objetos em seus arquivos...")

    arquivo_quarto = open("quarto.txt", "w")
    for quarto in pousada.get_quartos():
        arquivo_quarto.write(str(quarto)+"\n")

    arquivo_reserva = open("reserva.txt", "w")
    for reserva in pousada.get_reservas():
        arquivo_reserva.write(str(reserva)+"\n")

    arquivo_produto = open("produto.txt", "w")
    for produto in pousada.get_produtos():
        arquivo_produto.write(str(produto)+"\n")

    print("Objetos serializados")


def deserializar_lista(texto):
    # Converter lista em texto em array
    arr = texto.replace('[', '').replace(']', '').split(', ')
    if len(arr) == 1 and arr[0] == '':
        return []
    return arr


def deserializar():
    # Lê dos respectivos arquivos, caso existam, e cria os objetos
    print("Deserializando arquivos...")

    quartos_arquivo = "quarto.txt"
    quartos = []
    if os.path.isfile(quartos_arquivo):
        arquivo_quarto = open(quartos_arquivo, "r")
        for linha in arquivo_quarto:
            valores = linha.replace("\n", "").split(";")
            numero = int(valores[0])
            categoria = valores[1]
            diaria = float(valores[2])
            consumo = deserializar_lista(valores[3])

            quarto = Quarto(numero, categoria, diaria, consumo)
            quartos.append(quarto)
        print("Quartos deserializados")

    reservas_arquivo = "reserva.txt"
    reservas = []
    if os.path.isfile(reservas_arquivo):
        arquivo_reserva = open(reservas_arquivo, "r")
        for linha in arquivo_reserva:
            valores = linha.split(';')
            data_inicio = int(valores[0])
            data_fim = int(valores[1])
            cliente = valores[2]
            status = valores[4]

            reserva = Reserva(data_inicio, data_fim, cliente, quarto, status)
            reservas.append(reserva)
        print("Reservas deserializadas")

    pousada_arquivo = "pousada.txt"
    if not os.path.isfile(pousada_arquivo):
        print(f"É necessário um arquivo {pousada_arquivo} com nome e contato da pousada!")
        exit(1)

    arquivo_pousada = open(pousada_arquivo, "r")
    linha = arquivo_pousada.readline()
    valores = linha.split(';')
    nome = valores[0]
    contato = valores[1]

    print("Pousada deserializada")

    return Pousada(nome, contato, quartos, reservas, [])


pousada = deserializar()

def consultar_disponibilidade(pousada):
    data = int(input("Ano: "))
    numero = int(input("Número de quarto: "))
    print("Consultando disponibilidade...\n")
    
    quarto = pousada.consulta_disponibilidade(data, numero)
    if quarto == None:
        print("Quarto não disponível")
    else:
        print("Quarto liberado")
        print(f"Número: {quarto.numero} Categoria: {quarto.categoria} Diária: {quarto.diaria}")



def main():
    print()
    print("Bem-vindo ao melhor lugar para fazer sua reserva! \\o/")
    opcao = -1
    while opcao != 0:
        print()
        print("1 - Consultar disponibilidade")
        print("2 - Consultar reserva")
        print("3 - Realizar reserva")
        print("4 - Cancelar reserva")
        print("5 - Realizar check-in")
        print("6 - Realizar check-out")
        print("7 - Realizar consumo")
        print("8 - Salvar")
        print("0 - Sair")
        opcao = int(input("\nDigite uma opção: "))

        if opcao == 1:
            consultar_disponibilidade(pousada)
        elif opcao == 2:
            print("Opção número 2")
        elif opcao == 3:
            print("Opção número 3")
        elif opcao == 4:
            print("Opção número 4")
        elif opcao == 5:
            print("Opção número 5")
        elif opcao == 6:
            print("Opção número 6")
        elif opcao == 7:
            print("Opção número 7")
        elif opcao == 8:
            serializar(pousada)
        elif opcao != 0:
            print("\nOpção inválida :(\n")

    print("\nObrigado, até mais!")


main()
