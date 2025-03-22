# 1. Classe Pais:
# ◦ Classe que representa um país.
# ◦ Um país possui um código ISO 3166-1 (ex.: BRA), um nome (ex.: Brasil), uma população (ex.: 193.946.886) e uma dimensão em Km2 (ex.: 8.515.767,049).
# 
# Além disso, um país mantém uma lista de outros países com os quais ele faz fronteira.
# ◦ Crie um construtor que inicialize o código ISO, o nome e a dimensão do país.
# ◦ Crie os métodos de acesso e modificadores (getter/setter) para os atributos código ISO, nome, população e dimensão.
# ◦ Crie um método que permita verificar se dois objetos representam o mesmo país (igualdade semântica). Dois países são iguais se tiverem o mesmo código ISO.
# ◦ Crie um método que informe se outro país é limítrofe do país que recebeu a mensagem.
# ◦ Crie um método que retorne a densidade populacional do país.
# ◦ Crie um método que receba um país como parâmetro e retorne a lista de vizinhos comuns aos dois países. Considere que um país tem no máximo 40 outros países com os quais ele faz fronteira.

print("Exercício 1")

class Pais:
    def __init__(self, codigo, nome, populacao, dimensao):
        self.codigo = codigo
        self.nome = nome
        self.populacao = populacao
        self.dimensao = dimensao
        self.paises_vizinhos = []

    # getters e setters

    def get_paises_vizinhos(self):
        return self.paises_vizinhos

    def is_mesmo_pais(self, codigo):
        if self.codigo == codigo:
            return True
        else:
            return False

    def adiciona_vizinho(self, codigo):
        # já existe na lista, não adiciona novamente
        for pais in paises_vizinhos:
            if is_mesmo_pais(codigo):
                return

        # máximo 40 vizinhos, não adiciona mais
        if len(paises_vizinhos) < 40:
            paises_vizinhos.append(codigo)

    def is_vizinho(self, codigo):
        for pais in paises_vizinhos:
            if is_mesmo_pais(codigo):
                return True
        return False

    def densidade_populacional(self):
        return self.populacao / self.dimensao

    def vizinhos_em_comum(self, pais):
        vizinhos_em_comum = []
        for meu_vizinho in paises_vizinhos:
            for outro_vizinho in pais.get_paises_vizinhos:
                if self.is_mesmo_pais(meu_vizinho, outro_vizinho)
                    vizinhos_em_comum.append(meu_vizinho)
        
        return vizinhos_em_comum

