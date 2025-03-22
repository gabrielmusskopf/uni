
class Atleta:
    def __init__(self, nome, idade):
        self.nome = nome
        self.idade = idade

    def get_nome(self):
        return self.nome

    def get_idade(self):
        return self.idade

    def imprime_info(self):
        print(f'Nome: {self.nome}')
        print(f'Idade: {self.idade}')
