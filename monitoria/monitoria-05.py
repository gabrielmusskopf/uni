# ● Modelar, usando diagrama de classes UML e, implementar em Python, um Sistema para Gerenciamento de Empréstimos de
# Livros de uma Biblioteca.
# – Neste sistema, é importante que o usuário possa:
# ● Consultar livros                                            OK
# ● Solicitar o empréstimo de livros (locar)                    OK
# ● Solicitar a reserva, caso o livro não esteja disponível     OK
# ● Devolver o livro emprestado                                 OK

# – Estruturar o sistema de modo que:
# ● Os livros possuam um ou mais exemplares                     OK
# ● Novos livros possam ser cadastrados no acervo               OK
# ● Livros antigos sejam removidos do acervo
# ● Os usuários possam solicitar o empréstimo de um ou mais livros OK
# ● Relatórios sejam gerados para saber:
# – Quantos livros há no acervo da biblioteca
# – Quantos livros estão emprestados a um determinado usuário
# – Quantos exemplares estão emprestados e quantos estão disponíveis de um determinado livro

class Usuario:
    def __init__(self, nome):
        self.nome = nome
        self.livros = []
        self.reservas = []

    def locar(self, livro):
        self.livros.append(livro)

    def reservar(self, livro):
        self.reservas.append(livro)

    def esta_locado(self, livro):
        for l in self.livros:
            if l == livro:
                return True
        return False

    def devolver(self, livro):
        if not self.esta_locado(livro):
            # Se não tenho o livro, retorno sem fazer nada
            return
        self.livros.remove(livro)

    def imprime_info(self):
        print(f"Nome: {self.nome}\nLivros: {self.livros}\nReservas: {self.reservas}")

class Livro:
    def __init__(self, nome, data_escrita):
        self.nome = nome
        self.data_escrita = data_escrita

    def get_nome(self):
        return self.nome

    def get_data_escrita(self):
        return self.data_escrita

## Para fazer: utilizar a classe Livro

class Biblioteca:
    def __init__(self):
        self.acervo = []        # podem ser repeditos (mais de um exemplar)
        self.disponiveis = []
        self.emprestados = []
        self.reservados = []

    def adicionar_livro(self, livro):
        self.acervo.append(livro)
        self.disponiveis.append(livro)

    def consultar_livro(self, livro):
        for l in self.acervo:
            if l == livro:
                return True
        return False

    def esta_disponivel(self, livro):
        if not self.consultar_livro(livro):
            return False

        for disponivel in self.disponiveis:
            if disponivel == livro:
                return True

        return False

    def esta_emprestado(self, livro):
        for emprestado in self.emprestados:
            if emprestado == livro:
                return True
        return False

    def solicitar_emprestimo(self, usuario, livro):
        if not self.consultar_livro(livro):
            # Se livro não existe, não faz nada
            return
        
        if not self.esta_disponivel(livro):
            # Se livro não está disponível, solicitar reserva
            self.reservados.append(livro)
            usuario.reservar(livro)
            return

        # Existe e está disponível
        self.emprestados.append(livro)
        self.disponiveis.remove(livro)
        usuario.locar(livro)

    def devolver(self, usuario, livro):
        if not self.consultar_livro(livro):
            # Se livro não existe, não faz nada
            return

        if not self.esta_emprestado(livro):
            # Se não está emprestado, não faz nada
            return

        # Se chegou aqui, o livro existe e está emprestado
        self.disponiveis.append(livro)
        self.emprestados.remove(livro)
        usuario.devolver(livro)

    def imprime_info(self):
        print(f"Acervo: {self.acervo}\nDisponíveis: {self.disponiveis}\nEmprestados: {self.emprestados}\nReservados: {self.reservados}")

biblioteca = Biblioteca()
biblioteca.adicionar_livro("Livro 1")
biblioteca.adicionar_livro("Livro 2")

usuario1 = Usuario("Joao")
usuario2 = Usuario("Marcos")

biblioteca.imprime_info()
print()

biblioteca.solicitar_emprestimo(usuario1, "Livro 1")

biblioteca.imprime_info()
print()
usuario1.imprime_info()
print()

biblioteca.solicitar_emprestimo(usuario2, "Livro 1")

biblioteca.imprime_info()
print()
usuario2.imprime_info()
print()

print("Devolução")
biblioteca.devolver(usuario1, "Livro 1")

biblioteca.imprime_info()
print()
usuario1.imprime_info()
print()


 
