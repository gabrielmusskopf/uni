from atleta import Atleta
from nadador import Nadador
from corredor import Corredor


class InformacoesAtletas:

    def imprime_exclusivos_atletas(self, atleta: Atleta):
        if isinstance(atleta, Nadador):
            print(f'É um nadador e sua categoria é {atleta.get_categoria()}')
        elif isinstance(atleta, Corredor):
            print(f'É um nadador e o peso é {atleta.get_peso()}')
        else:
            print('Tipo de atleta desconhecido')

    def imprime_informacao_atleta(self, atleta: Atleta):
        atleta.imprime_info()
