from atleta import Atleta
from competicao import Competicao


class Corredor(Atleta):
    def __init__(self, peso: float, competicao: Competicao, nome: str, idade: int):
        super().__init__(nome, idade)
        self.peso = peso
        self.competicao = competicao

    def get_peso(self):
        return self.peso

    def set_peso(self, peso):
        self.peso = peso

    def get_competicao(self):
        return self.competicao

    def imprime_competicao(self):
        print(f'Nome competicao: {self.competicao.get_nome()}')
        self.competicao.imprime_data()

    def imprime_info(self):
        super().imprime_info()
        print(f'Peso: {self.peso}')
        self.imprime_competicao()
