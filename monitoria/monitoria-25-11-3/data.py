class Data:
    def __init__(self, dia, mes, ano):
        self.dia = dia
        self.mes = mes
        self.ano = ano

    def set_mes(self, mes):
        self.mes = mes

    def imprime_data(self):
        print(f'{self.dia}/{self.mes}/{self.ano}')
