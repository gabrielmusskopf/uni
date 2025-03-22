# 1. Crie um programa que solicite ao usuário o seu nome completo (string), idade (int),
# altura (float) e peso (float). Salve cada uma dessas informações em linhas
# separadas de um arquivo texto.

print("Excerício 1")

nome = input("Nome: ")
idade = int(input("Idade: "))
altura = float(input("Altura: "))
peso = float(input("Peso: "))

arquivo = open("exercicio-1.txt", "w")
arquivo.write(f'{nome}\n')
arquivo.write(f'{idade}\n')
arquivo.write(f'{altura}\n')
arquivo.write(f'{peso}\n')
arquivo.close()

# 2. Crie um programa que leia o arquivo criado anteriormente e mostre os dados na tela,
# identificando o nome e valor de cada informação. Ex: Nome: <nome> \n Idade:
# <idade> \n, etc.

arquivo = open("exercicio-1.txt", "r")

nomeLido = arquivo.readline()[:-1] # remover \n, últimos dois caracteres
idadeLida = arquivo.readline()[:-1]
alturaLida = arquivo.readline()[:-1]
pesoLido = arquivo.readline()[:-1]

arquivo.close()

# 3. Crie a classe “Pessoa” com os atributos do Exercício 1. Além dos métodos getters
# e setters de cada atributo, crie o método “visualizar()”, que deverá mostrar
# na tela os nomes e valores dos atributos no mesmo formato do Exercício 2. Instancie
# uma pessoa, defina seus atributos e visualize.

class Pessoa:
    def __init__(self, nome, idade, altura, peso):
        self.nome = nome
        self.idade = idade
        self.altura = altura
        self.peso = peso

    def get_nome(self):
        return self.nome

    def get_idade(self):
        return self.idade

    def get_altura(self):
        return self.altura

    def get_peso(self):
        return self.peso

    def set_nome(self, nome):
        self.nome = nome

    def set_idade(self, idade):
        self.idade = idade

    def set_altura(self, altura):
        self.altura = altura

    def set_peso(self, peso):
        self.peso = peso

    def visualizar(self):
        print(f'Nome: {self.nome}')
        print(f'Idade: {self.idade}')
        print(f'Altura: {self.altura}')
        print(f'Peso: {self.peso}')

    def carregar(self, nomeArquivo):
        # lê o arquivo e atualiza atributos
        arquivo = open(nomeArquivo, "r")
        self.nome = arquivo.readline()[:-1] # remover \n, últimos dois caracteres
        self.idade = arquivo.readline()[:-1]
        self.altura = arquivo.readline()[:-1]
        self.peso = arquivo.readline()[:-1]
        arquivo.close()

    def salvar(self, nomeArquivo):
        arquivo = open(nomeArquivo, "w")
        arquivo.write(f'{self.nome}\n')
        arquivo.write(f'{self.idade}\n')
        arquivo.write(f'{self.altura}\n')
        arquivo.write(f'{self.peso}\n')
        arquivo.close()

p = Pessoa(nomeLido, idadeLida, alturaLida, pesoLido)
p.visualizar()
print()

# 4. Altere o programa do Exercício 3 adicionando os métodos
# “carregar(nomeArquivo)” e “salvar(nomeArquivo)”. Instancie uma pessoa,
# carregue seus atributos a partir de um arquivo e visualize. Depois, altere o valor de
# alguns atributos e salve.

print("Exercício 4\n")
pessoa = Pessoa("Pedro", "19", 1.87, 82.0)
pessoa.visualizar()

print()
pessoa.carregar("exercicio-1.txt")
pessoa.visualizar()

print()
pessoa.set_nome("Marcos")
pessoa.set_idade(37)
pessoa.visualizar()
pessoa.salvar("exercicio-4.txt")
