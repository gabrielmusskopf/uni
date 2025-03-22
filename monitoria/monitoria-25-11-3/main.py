from nadador import Nadador
from corredor import Corredor
from competicao import Competicao
from data import Data
from informacoes_atletas import InformacoesAtletas

competicao = Competicao("Correr é show", Data(25, 9, 2024))

print(f'Competição: {competicao.get_nome()}')
competicao.imprime_data()

nadador = Nadador("borboleta", "Cielo", 36)
nadador.imprime_info()

corredor = Corredor(68, competicao, "Josenildo", 91)
corredor.imprime_info()

corredor.get_competicao().get_data().set_mes(10)
corredor.imprime_info()

competicao2 = Competicao("São Silvestre", Data(31, 12, 2024))
petrolina = Corredor(60, competicao2, "Petrolina", 100)
petrolina.imprime_info()

print('Para criar, digite:')
print('1 - Nadador')
print('2 - Corredor')

tipo = int(input('-> '))
atleta = None

if tipo == 1:
    print('Para um nadador, informe')
    categoria = input('Categoria: ')
    nome = input('Nome: ')
    idade = int(input('Idade: '))
    atleta = Nadador(categoria, nome, idade)
elif tipo == 2:
    print('Para um corredor, informe')
    nome = input('Nome: ')
    peso = float(input('Peso: '))
    competicao = input('Competição: ')
    dia = int(input('Dia da competição: '))
    mes = int(input('Mês da competição: '))
    ano = int(input('Ano da competição: '))
    idade = int(input('Idade: '))
    atleta = Corredor(peso, Competicao(competicao, Data(dia, mes, ano)), nome, idade)

informacoes = InformacoesAtletas()
informacoes.imprime_exclusivos_atletas(atleta)

if isinstance(atleta, Nadador):
    atleta.set_categoria('livre')
elif isinstance(atleta, Corredor):
    atleta.set_peso(89)

atleta.imprime_info()
