from atleta import Atleta


class Nadador(Atleta):
    def __init__(self, categoria: str, nome: str, idade: int):
        super().__init__(nome, idade)
        self.categoria = categoria

    def get_categoria(self):
        return self.categoria

    def set_categoria(self, categoria):
        self.categoria = categoria

    def imprime_info(self):
        super().imprime_info()
        print(f'Categoria: {self.categoria}')
