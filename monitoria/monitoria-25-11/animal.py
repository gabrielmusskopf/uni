# Seu nome

class Animal:
    def __init__(self, nome, tipo, peso, idade):
        self.nome = nome
        self.tipo = tipo
        self.peso = peso
        self.idade = idade
        self.preco = 0.0

    def get_nome(self):
        return self.nome

    def get_idade(self):
        return self.idade

    def get_tipo(self):
        return self.tipo

    def get_preco(self):
        return self.preco

    def set_nome(self, nome):
        self.nome = nome

    # Getters e setters das demais propriedades

    def aumenta_idade(self):
        self.idade += 1  # += mesma coisa que => self.idade = self.idade + 1

    def calcula_preco_animal(self):
        if self.idade <= 10:
            self.preco = self.peso / 3
        elif self.idade <= 20:
            self.preco = self.peso / 2
        elif self.idade <= 30:
            self.preco = self.peso + (0.2 * self.peso)  # peso + 20% do peso
        else:
            self.preco = self.peso + (0.73 * self.peso)  # peso + 73% do peso

        return self.preco

    def calcula_juros(self):
        if self.preco >= 1.0 and self.preco <= 100.0:
            return 0.1 * self.preco
        elif self.preco <= 249.0:
            return 0.29 * self.preco
        elif self.preco <= 986.0:
            return 0.61 * self.preco
        else:
            return 0.75 * self.preco

    def calcula_valor_de_venda(self):
        if self.preco == 0:
            self.calcula_preco_animal()

        return self.preco + self.calcula_juros()

    def imprime_info(self):
        print(
            f'Nome: {self.nome} Tipo: {self.tipo} - Idade: {self.idade} - Peso: {self.peso} - Preço: {self.preco}'
        )
