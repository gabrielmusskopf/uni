# Seu nome

from animal import Animal
from fazenda import Fazenda

animais = []

for i in range(2):
    nome = input("Digite o nome do animal: ")
    tipo = input("Digite o tipo do animal: ")
    peso = float(input("Digite o peso do animal: "))
    idade = int(input("Digite a idade do animal: "))
    animal = Animal(nome, tipo, peso, idade)
    animais.append(animal)

    print(f'{nome} criado!')

fazenda = Fazenda(animais)
print('Fazenda criada com animais')

nome = input("Digite o nome do animal: ")
tipo = input("Digite o tipo do animal: ")
peso = float(input("Digite o peso do animal: "))
idade = int(input("Digite a idade do animal: "))
animal = Animal(nome, tipo, peso, idade)
print(f'{nome} criado!')

fazenda.adiciona_animal(animal)

print(f'Idade média: {fazenda.idade_media()}')
print(f'Valor total: {fazenda.idade_media()}')
print('Tipo dos animais:')
for animal in fazenda.get_animais():
    print(f'- {animal.get_tipo()}')

nome = input("Digite o nome do animal para envelhecer: ")

if fazenda.aumenta_idade(nome):
    print(f'Idade do {nome} aumentada com sucesso!')
else:
    print(f'{nome} não encontrado...')

fazenda.imprime_info()

animais = fazenda.get_animais()
mais_caro = animais[0]

for animal in animais:
    if animal.calcula_valor_de_venda() > mais_caro.calcula_valor_de_venda():
        mais_caro = animal

print('Animail mais caro da fazenda:')
mais_caro.imprime_info()
