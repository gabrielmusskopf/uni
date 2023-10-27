#26/10/23
#monitoria
#matriz

# 1. Criar uma matriz de números fracionários com 10 linhas e 10 colunas. Inicializar cada elemento da matriz com a parte inteira correspondente ao índice da linha e a parte fracionária correspondente ao índice da coluna. Imprimir a matriz na tela mostrando os números com precisão de uma casa decimal

print("Exercício 1")

# lista:  [1, 2, 3]
# matriz: [[1], [2], [3]]
M = []

for i in range(1, 10):
    coluna = []
    for j in range(1, 10):
        elemento = i + (j/10)
        coluna.append(elemento)
    M.append(coluna)

for i in range(0, 9):
    print(M[i])

# 2. Na matriz criada no item 1, percorrer a diagonal principal de [0][0] até [9][9] e mostrar os elementos na tela;

print("\nExercício 2")

for i in range(0, 9):
    print(M[i][i])

# 3. Na matriz criada no item 1, percorrer a diagonal secundária de [0][9] até [9][0] e mostrar os elementos na tela;

print("\nExercício 3")

i = 0
for j in reversed(range(0, 9)):
    print(M[i][j])
    i += 1

# 4. Na matriz criada no item 1, percorrer as bordas e mostrar os elementos na tela, em formato tabular;

print("\nExercício 4")

for i in range(0,9):
    for j in range(0,9):
        if i == 0 or i == len(M)-1 or j == 0 or j == len(M)-1:
            print(M[i][j])

# 5. Transpor a matriz criada no item 1, criando uma nova matriz e permutando as linhas pelas colunas. A linha 1 para coluna 1, linha 2 para coluna 2, linha “n” para coluna “n”. Imprimir a matriz transposta;

print("\nExercício 5")

T = []

for i in range(0, 9):
    coluna = []
    for j in range(0, 9):
        coluna.append(M[j][i])
    T.append(coluna)

for i in range(0, 9):
    print(T[i])


# 6. Solicitar que o usuário informe as dimensões de uma matriz, obrigatoriamente menor que 10 x 10. Copiar as últimas linhas e colunas da matriz do item 1 para dentro dessa nova matriz e exibir na tela;

print("\nExercício 6")

d = int(input("Dimensão: "))
if d < 10:
    A = []
    for i in range(0, d):
        A.append([0] * d)
    
    for i in range(0, d):
        for j in range(0, d):
            A[i][j] = M[i][j]
    
    for i in range(0, d):
        print(A[i])

# 7. Criar uma matriz de caracteres 10 x 10 e inicializar todos os elementos com “-”. Depois, alterar para o valor “X” os elementos das duas diagonais utilizando estruturas de repetição. Imprimir a matriz na tela;
    
print("\nExercício 7")

B = []

for i in range(0, 9):
    B.append(['-'] * 9)

# Diagonal principal
for i in range(0, 9):
    B[i][i] = 'X'

# Diagonal secundária
i = 0
for j in reversed(range(0, 9)):
    B[i][j] = 'X'
    i += 1

for i in range(0, 9):
    print(B[i])

# 8. Desafio: Solicitar que o usuário informe um número entre 3 e 15. Criar uma matriz de caracteres quadrada, com a quantidade de linhas e colunas igual ao número informado. Inicializar todos os elementos com “-”. Depois, alterar para o valor “X” os elementos que formem um losango utilizando estruturas de repetição. Imprimir a matriz na tela.

print("\nExercício 8")

d = int(input("Dimensão (3 a 15): "))
while d < 3 or d > 15:
    d = int(input("Inválido, digite novamente: "))

C = []
for i in range(d+1):
    C.append(['-'] * d)

impar = d % 2 != 0
mid = d // 2

for i in range(mid + 1):
    left = mid - i if impar else mid - i - 1
    right = mid + i

    if not impar and i == mid:  
        continue
    
    for j in range(left, right + 1):
        if j == left or j == right:
            C[i][j] = 'X'
            C[d - i - 1][j] = 'X'

for n in range(d):
    print(C[n])

