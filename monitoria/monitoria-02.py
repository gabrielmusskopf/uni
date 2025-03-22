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
        self.memoria = a*a
        return self.memoria

    def eleva_ao_cubo(self, a):
        self.memoria = a*a*a
        return self.memoria

    def imprime_info(self):
        print("Cor:", self.cor, "- memória:", self.memoria)


# 2. Classe FuncionarioCaixa: ◦ Possui um nome, um endereço e uma calculadora. 
# ◦ Crie um construtor que recebe os parâmetros para inicializar todos os atributos. 
# ◦ Crie os métodos de acesso dos atributos desta classe (get e set). 
# ◦ Crie os métodos soma, subtrai, multiplica, divide, eleva_ao_quadrado e eleva_ao_cubo. Cada método destes deve chamar o método correspondente da calculadora, retornando o valor obtido na operação.# ◦ Crie um método chamado imprime_info, que não recebe parâmetros de entrada e imprime na tela as informações da classe, inclusive da calculadora (sem as operações, apenas os atributos).

print("\nExercício 2")

class FuncionarioCaixa:
    def __init__(self, nome, endereco, calculadora):
        self.nome = nome
        self.endereco = endereco
        self.calculadora = calculadora

    def getNome():
        return self.nome

    def getEndereco():
        return self.endereco

    def getCalculadora():
        return self.calculadora

    def setNome(self, nome):
        self.nome = nome

    def setEndereco(self, endereco):
        self.endereco = endereco

    def setCalculadora(self, calculadora):
        self.calculadora = calculadora

    # Reutilzar métodos da calculadora
    # self.calculadora.<nome do método>()

    def soma(self, a, b):
        return self.calculadora.soma(a, b)

    def subtrai(self, a, b):
        return self.calculadora.subtrai(a, b)

    def divide(self, a, b):
        return self.calculadora.divide(a, b)

    def multiplica(self, a, b):
        return self.calculadora.multiplica(a, b)

    def eleva_ao_quadrado(self, a):
        return self.calculadora.eleva_ao_quadrado(a, b)

    def eleva_ao_cubo(self, a):
        return self.calculadora.eleva_ao_cubo(a, b)

    def imprime_info(self):
        print(f"Nome: {self.nome}")
        print(f"Endereço: {self.endereco}")
        print("Calculadora:")
        self.calculadora.imprime_info()


# funcionarioCaixa = FuncionarioCaixa("Roberto", "São Leopoldo", Calculadora("Vermelha"))
# 
# print(f"10+5={funcionarioCaixa.soma(10, 5)}")
# print(f"10*5={funcionarioCaixa.multiplica(10, 5)}")
# print(f"10/5={funcionarioCaixa.divide(10, 5)}")
# funcionarioCaixa.imprime_info()


#################################


print("\nExercício 3")

# 3. Classe Empresa:
# ◦ Uma empresa tem um nome e dois funcionários do caixa.
# ◦ Crie um construtor que recebe todos os parâmetros para inicializar os atributos.
# ◦ Crie os métodos de acesso dos atributos desta classe (get e set).
# ◦ Crie um método imprime_info, que imprime as informações da classe

class Empresa:
    def __init__(self, nome, funcionario1, funcionario2):
        self.nome = nome
        self.funcionario1 = funcionario1
        self.funcionario2 = funcionario2

    # getters
    def getNome():
        return self.nome

    def getFuncionario1():
        return self.funcionario1

    def getFuncionario2():
        return self.funcionario2

    # setters
    def setNome(self, nome):
        self.nome = nome

    def setFuncionario1(self, funcionario1):
        self.funcionario1 = funcionario1

    def setFuncionario2(self, funcionario2):
        self.funcionario2 = funcionario2

    def imprime_info(self):
        print(f"Nome: {self.nome}")
        print("Funcionário 1:")
        self.funcionario1.imprime_info()
        print("Funcionário 1:")
        self.funcionario2.imprime_info()

# empresa = Empresa("Empresa", "Funcionário 1", "Funcionário 2")
# empresa.imprime_info()

###############################3


print("\nExercício 4")

# 4. Na função main:
# • Crie um objeto do tipo FuncionarioCaixa, chamado funcionario. Tudo que for necessário para criar este objeto, deve ser solicitado para o usuário.
# • Imprima o resultado das operações: 2+2, 5-4, 2x3, 6/3, 7+2, 8x3. As operações devem ser feitas através da calculadora do objeto funcionário criado.
# • Neste método, crie um objeto do tipo Empresa chamado empresa1, com nome solicitado para o usuário, com o funcionário criado anteriormente e um novo funcionário que deve ser criado. Tudo que for necessário para criar esta empresa (que já não tenha sido criado) deve ser solicitado para o usuário.
# • Imprima as informações desta empresa.

def main():
    funcionario = FuncionarioCaixa(input("Nome do funcionário: "), input("Endereço: "), Calculadora(input("Cor da calculadora: ")))

    print(f"2+2={funcionario.soma(2,2)}")
    print(f"5-4={funcionario.subtrai(5,4)}")
    print(f"2x3={funcionario.multiplica(2,3)}")
    print(f"6\\3={funcionario.divide(6,3)}")
    print(f"7+2={funcionario.soma(7,2)}")
    print(f"8x3={funcionario.multiplica(8,3)}")

    funcionario2 = FuncionarioCaixa(
            input("Nome do outro funcionário: "), 
            input("Endereço: "), 
            Calculadora(input("Cor da calculadora: ")))

    empresa1 = Empresa(input("Nome da empresa: "), funcionario, funcionario2 )

    print()
    empresa1.imprime_info()


# Confere se foi executado como programa principal
if __name__ == '__main__':
    main()

