from datetime import datetime

# 1. Crie uma classe chamada Retângulo. Um retângulo possui uma base e uma
# altura. Crie os métodos necessários para que o usuário possa obter
# informações sobre a base, a altura e a área do retângulo.

print("Exercício 1")

class Retangulo:
    def __init__(self, base, altura):
        self.base = base
        self.altura = altura

    def getBase(self):
        return self.base

    def getAltura(self):
        return self.altura

    def calcularArea(self):
        return self.base * self.altura  # Área = base x altura


retangulo1 = Retangulo(10, 5)
print("Base:", retangulo1.getBase())
print("Altura:", retangulo1.getAltura())
print("Área:", retangulo1.calcularArea())

print()

# 2. Crie uma classe chamada Pessoa. Uma pessoa possui nome, idade, altura,
# quantidade de irmãos e endereço. Na classe Pessoa:
# crie um método chamado imprime_info, que imprime na tela as informações da
# pessoa, de maneira organizada.
# • crie um método chamado is_filho_unico, que retorna verdadeiro caso a pessoa
# seja filha única e falso caso contrário. No método main() faça o que se pede:
# • crie 3 pessoas informando todos os dados.
# • imprima as informações de todas as pessoas, de forma legível e organizada.
# • imprima na tela a frase “Filho(a) único(a)” para as pessoas que forem
# filhas únicas, e a frase “Não é filho(a) único(a)” para as pessoas que
# não forem filhas únicas.

print("Exercício 2")

class Pessoa:
    # construtor
    def __init__(self, nome, idade, altura, quantidadeIrmaos, endereco):
        self.nome = nome
        self.idade = idade
        self.altura = altura
        self.quantidadeIrmaos = quantidadeIrmaos
        self.endereco = endereco

    # métodos
    # 1. métodos da classe sempre recebem "self" como parâmetro
    # 2. para acessar atributos da classe (nome), comecar com "self"
    def imprime_info(self):
        print("Nome:", self.nome)
        print("Idade:", self.idade)
        print("Altura:", self.altura)
        print("Quantidade irmãos:", self.quantidadeIrmaos)
        print("Endereço:", self.endereco)

    def is_filho_unico(self):
        if self.quantidadeIrmaos == 0:
            return True
        else:
            return False

def print_filho_unico(pessoa):
    if pessoa.is_filho_unico():
        print("Filho(a) único(a)")
    else:
        print("Não é filho(a) único(a)")

pessoa1 = Pessoa("Maria", 24, 1.8, 1, "Rua Unisinos")
pessoa2 = Pessoa("João", 44, 1.8, 0, "Rio Grande do Sul")
pessoa3 = Pessoa("Marcio", 33, 1.8, 2, "São Leopoldo")

pessoa1.imprime_info()
print_filho_unico(pessoa1)
print()

pessoa2.imprime_info()
print_filho_unico(pessoa2)
print()

pessoa3.imprime_info()
print_filho_unico(pessoa3)
print()


print()

# 3. Crie uma classe para calcular o imposto de renda simplificado chamada IRSimplificado. 
# A classe IRSimplificado possui o nome do contribuinte, o ano de nascimento, a profissão, 
# a escolaridade, a renda mensal e o número de dependentes do contribuinte. Crie os seguintes métodos: 
# • método que calcula e retorna a idade do contribuinte 
# • método que calcula e retorna a renda anual do contribuinte 
# • método que calcula e retorna a renda per capita mensal 
# • método que retorna a alíquota de IR máxima 
# • método que retorna o valor da dedução do IR segundo a alíquota de IR máxima 
# • método que calcula e retorna o valor do imposto de renda mensal 
# • método que calcula e retorna o valor do imposto de renda anual Respeitar as faixas para as alíquotas segundo a tabela abaixo. 
# Escreva um programa que instancie um objeto de forma dinâmica e solicite que o usuário informe todos os atributos. Em seguida, 
# faça o cálculo do imposto de renda e imprima todos os dados.

print("Exercício 3")

class IRSimplificado:
    def __init__(self, nomeContribuinte, anoNascimento, profissao, escolaridade, rendaMensal, numeroDependentes):
        self.nomeContribuinte = nomeContribuinte
        self.anoNascimento = anoNascimento
        self.profissao = profissao
        self.escolaridade = escolaridade
        self.rendaMensal = rendaMensal
        self.numeroDependentes = numeroDependentes

    def calcularIdade(self):
        return datetime.now().year - self.anoNascimento

    def calcularRendaAnual(self):
        return self.rendaMensal * 12

    def calcularRendaPerCaptaMensal(self):
        # 0 dependentes -> renda / 1
        # 1 dependente -> renda / 2
        return self.rendaMensal / (self.numeroDependentes + 1)

    def calcularAliquotaMaxima(self):
        if self.rendaMensal < 2259.20:
            return 0

        if self.rendaMensal < 2826.65:
            return 7.5 / 100

        if self.rendaMensal < 3751.05:
            return 15 / 100

        if self.rendaMensal < 4664.86:
            return 22.5 / 100

        return 27.5 / 100

    def calcularDeducaoIR(self):
        if self.rendaMensal < 2259.20:
            return 0

        if self.rendaMensal < 2826.65:
            return 169.44

        if self.rendaMensal < 3751.05:
            return 381.44

        if self.rendaMensal < 4664.86:
            return 662.77

        return 896

    def calcularValorImposto(self):
        return self.rendaMensal * self.calcularAliquotaMaxima()

    def calcularValorImpostoAnual(self):
        return self.calcularValorImposto() * 12


nome = input("Nome: ")
ano = int(input("Ano de nascimento: "))
profissao = input("Profissão: ")
escolaridade = input("Escolaridade: ")
rendaMensal = float(input("Renda mensal: "))
numeroDependentes = int(input("Numero de dependentes: "))


ir = IRSimplificado(nome, ano, profissao, escolaridade, rendaMensal, numeroDependentes)
print("Idade:", ir.calcularIdade())
print("Renda anual:", ir.calcularRendaAnual())
print("Aliquota máxima:", ir.calcularAliquotaMaxima())
print("Decução IR:", ir.calcularDeducaoIR())
print("Valor imposto:", ir.calcularValorImposto())
print("Valor imposto anual:", ir.calcularValorImpostoAnual())

print()


# 1. Classe Calculadora: 
# ◦ Uma calculadora possui uma memória e uma cor. 
# ◦ Quando uma calculadora é criada, a memória deve ser inicializada com 0 e a cor deve ser recebida por parâmetro (construtor). 
# ◦ Crie os métodos de acesso para os atributos da classe (get e set). 
# ◦ Crie os métodos: soma, subtrai, multiplica e divide. Todos recebem dois valores (float) por parâmetro e retornam o valor da operação realizada. 
# ◦ Crie os métodos eleva_ao_quadrado e eleva_ao_cubo. Ambos recebem apenas um valor (int) e retornam o valor da operação realizada. 
# ◦ Crie um método imprime_info, que não recebe parâmetros e simplesmente imprime as informações da calculadora de maneira legível e organizada.

print("Exercício 4")

# 1. criar a classe
# 2. criar o construtor
# 3. receber parametros no construtor
# 4. inicializar os atributos
# 5. criar métodos
# 6. criar a instância da classe (calculadora = Calculadora(...))
# 7. testar os métodos

class Calculadora:
    def __init__(self, cor):
        self.memoria = 0
        self.cor = cor

    def getMemoria(self):
        return self.memoria

    def getCor(self):
        return self.cor

    def setMemoria(self, memoria):
        self.memoria

    def setCor(self, cor):
        self.cor

    def soma(self, a, b):
        self.memoria = a + b
        return self.memoria

    def subtrai(self, a, b):
        self.memoria = a - b
        return self.memoria

    def multiplica(self, a, b):
        self.memoria = a * b
        return self.memoria

    def divide(self, a, b):
        self.memoria = a / b
        return self.memoria

    def eleva_ao_quadrado(self, a):
        self.memoria =  a*a
        return self.memoria

    def eleva_ao_cubo(self, a):
        self.memoria = a*a*a
        return self.memoria

    def imprime_info(self):
        print("Cor:", self.cor, "- memória:", self.memoria)


calculadora = Calculadora("vermelho")
calculadora.imprime_info()
print("2+2=",calculadora.soma(2,2))
print("2x2=",calculadora.multiplica(2,2))
print("3²=",calculadora.eleva_ao_quadrado(3))
calculadora.imprime_info()
