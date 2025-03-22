from veiculo import Veiculo


class Aereo(Veiculo):
    def __init_(self, qtAsa, qtMotor, ano, peso, tanque, modelo):
        super().__init__(ano, peso, tanque, modelo)
        self.qtAsa = qtAsa
        self.qtMotor = qtMotor

    def consumo(self):
        return 1 / ((super().peso * 0.00003) + (self.qtAsa * 0.01))

    def autonomia(self):
        return super().tanque * self.consumo()

    def info(self):
        super().info()
        print(f'Quantidade de asas: {self.qtAsa}')
        print(f'Quantidade de motores: {self.qtMotor}')
