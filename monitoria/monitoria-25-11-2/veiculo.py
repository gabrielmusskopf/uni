
class Veiculo:
    def __init__(self, ano, peso, tanque, modelo):
        self.ano = ano
        self.peso = peso
        self.tanque = tanque
        self.modelo = modelo

    def info(self):
        print(f'Ano: {self.ano}')
        print(f'Peso: {self.peso}')
        print(f'Tanque: {self.tanque}')
        print(f'Modelo: {self.modelo}')
