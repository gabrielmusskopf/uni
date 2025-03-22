from veiculo import Veiculo


class Terrestre(Veiculo):
    def __init_(self, qtRoda, qtPorta, ano, peso, tanque, modelo):
        super().__init__(ano, peso, tanque, modelo)
        self.qtRoda = qtRoda
        self.qtPorta = qtPorta

    def consumo(self):
        return 1 / ((super().peso * 0.00005) + (self.qtRoda * 0.005))

    def autonomia(self):
        return super().tanque * self.consumo()

    def info(self):
        super().info()
        print(f'Quantidade de rodas: {self.qtRoda}')
        print(f'Quantidade de portas: {self.qtPorta}')
