# Seu nome

class Fazenda:
    def __init__(self, animais):
        self.animais = animais

    def get_animais(self):
        return self.animais

    def set_animais(self, animais):
        self.animais = animais

    def adiciona_animal(self, animal):
        self.animais.append(animal)

    def calcula_valor_animais(self):
        total = 0

        for animal in self.animais:
            total += animal.calcula_valor_de_venda()

        return total

    def idade_media(self):
        total = 0

        for animal in self.animais:
            total += animal.get_idade()

        return total / len(self.animais)

    def aumenta_idade(self, nome):
        for animal in self.animais:
            if animal.get_nome().lower() == nome.lower():
                animal.aumenta_idade()
                return True

        return False

    def imprime_info(self):
        for animal in self.animais:
            animal.imprime_info()
