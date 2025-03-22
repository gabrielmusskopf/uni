from data import Data


class Competicao:
    def __init__(self, nome: str, data: Data):
        self.nome = nome
        self.data = data

    def get_nome(self):
        return self.nome

    def get_data(self):
        return self.data

    def imprime_data(self):
        print('Data da competição:')
        self.data.imprime_data()
