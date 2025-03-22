from veiculo import Veiculo


class Aquatico(Veiculo):
    def __init_(self, qtConves, qtMotor, ano, peso, tanque, modelo):
        super().__init__(ano, peso, tanque, modelo)
        self.qtConves = qtConves
        self.qtMotor = qtMotor

    def consumo(self):
        return 1 / ((super().peso * 0.00002) + (self.qtConves * 0.02))

    def autonomia(self):
        return super().tanque * self.consumo()

    def info(self):
        super().info()
        print(f'Quantidade de conves: {self.qtConves}')
        print(f'Quantidade de motores: {self.qtMotor}')
