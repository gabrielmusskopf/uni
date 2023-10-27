#1. Implemente um programa que define um vetor de 6 posições, inicializado com zeros. Em seguida, faça um laço que atualize o vetor de forma dinâmica com os valores [2 4 6 8 10 12], utilizando acesso indexado e uma fórmula matemática para calcular os valores. Nenhum elemento do vetor deve deixar de ser atualizado. Depois, mostre todo o vetor na tela;


def exercicio1():
    print("Exercício 1")
    vetor = [0, 0, 0, 0, 0, 0]
    
    i=0
    while i < len(vetor):
        # i - valor
        # 0 - 2
        # 1 - 4
        # 2 - 6
        # 3 - 8
        # Fórmula = i * 2 + 2
        vetor[i] = (i * 2) + 2
        i+=1
    
    print(vetor)

# 2 . Implemente um programa que define um vetor vazio. Em seguida, faça um laço que adicione no vetor os valores [−1.5 −1 −0.5 0 0.5 1 1.5 2 2.5 3] de forma dinâmica, utilizando uma fórmula matemática para calcular os valores. Nenhum elemento do vetor deve deixar de ser inicializado. Depois, faça outro laço que mostre todo o vetor na tela;


def exercicio2():
    print("Exercício 2")
    vetor = []
    
    i=0
    valor=-1.5
    while i < 10:
        vetor.append(valor)
        i+=1
        valor+=0.5
    
    for n in vetor:
        print(n)

# 3 Monte um programa que peça para o usuário digitar 10 números fracionários, os armazene em um vetor e em seguida mostre na tela:
#a. O índice e o conteúdo do elemento de menor valor;
#b. O índice e o conteúdo do elemento de maior valor;
#c. A diferença entre os elementos de maior e menor valor;


def exercicio3():
    print("Exercício 3")
    
    vetor = []
    for i in range(0,10):
        n = float(input("Digite um número: "))
        vetor.append(n)
    
    i=0
    indiceMenor = 0
    indiceMaior = 0
    
    while i < len(vetor):
        if vetor[i] < vetor[indiceMenor]:
            indiceMenor = i
    
        if vetor[i] > vetor[indiceMaior]:
            indiceMaior = i
    
        i+=1
    
    print("Índice menor:", indiceMenor, "Valor:", vetor[indiceMenor])
    print("Índice maior:", indiceMaior, "Valor:", vetor[indiceMaior])
    print("Diferença:", vetor[indiceMaior] - vetor[indiceMenor])

# 4 Monte um programa que leia 10 números inteiros positivos e os armazene em um vetor. Mostre os números na ordem inversa a que foram digitados;


def exercicio4():
    print("Exercício 4")
    
    vetor = []
    
    while len(vetor) < 10:
        n = int(input("Digite um número: "))
        if n >= 0:
            vetor.append(n)
        else:
            print("Somente positivos!")
    
    for n in reversed(vetor):
        print(n)

# 5 Monte um programa que leia 10 números inteiros positivos e os armazene em um vetor. Crie um segundo vetor e o alimente com os valores em ordem inversa ao primeiro. Mostre ambos na tela, percorrendo do primeiro ao último elemento;


def exercicio5():
    print("Exercício 5")
    
    vetor = []
    
    while len(vetor) < 10:
        n = int(input("Digite um número: "))
        if n >= 0:
            vetor.append(n)
        else:
            print("Somente positivos!")
    
    invertido = []
    for n in reversed(vetor):
        invertido.append(n)
    
    print("Vetor:")
    for n in vetor:
        print(n)
    
    print("Invertido:")
    for n in invertido:
        print(n)

# 6 Monte um programa que leia 10 números inteiros positivos e os armazene em um vetor. Reorganize o próprio vetor armazenando seus elementos em ordem inversa.Não utilize outro vetor para isso, use apenas uma variável auxiliar. Mostre o vetor na tela após a inversão dos elementos;


def exercicio6():
    print("Exercício 6")
    
    vetor = []
    
    while len(vetor) < 10:
        n = int(input("Digite um número: "))
        if n >= 0:
            vetor.append(n)
        else:
            print("Somente positivos!")
    
    print("Vetor original", vetor)
    
    i=len(vetor) - 1
    j=0
    while i >= len(vetor)/2:
        aux = vetor[j]
        vetor[j] = vetor[i]
        vetor[i] = aux
        
        i-=1
        j+=1
    
    print("Vetor invertido", vetor)

# 7 Alimente um vetor com 10 números e o imprima. Peça para o usuário informar um número e o procure no vetor. Se encontrar, imprima o número lido e a(s) posição(ões) em que foi(foram) encontrado(s). Se não encontrar, imprima o número lido e a mensagem "NÃO ENCONTRADO";


def exercicio7():
    print("Exercício 7")
    
    vetor = []
    
    while len(vetor) < 10:
        n = float(input("Digite um número: "))
        vetor.append(n)
    
    procurado = float(input("Buscar número:"))
    
    encontrados = []
    i=0
    while i < len(vetor):
        if vetor[i] == procurado:
            encontrados.append(i)
        i+=1
    
    if len(encontrados) > 0:
        for encontrado in encontrados:
            print("i:", encontrado, "valor:", vetor[encontrado])
    else:
        print("NÃO ENCONTRADO")

# 8. Crie um programa que solicita ao usuário digitar 5 números fracionários e os armazena em um vetor “A”. Depois, solicite mais 5 números e armazene em um segundo vetor “B”. Mostre na tela as operações matemáticas soma, subtração, multiplicação e divisão, índice por índice dos vetores;


def exercicio8():
    print("Exercício 8")
    A = []
    B = []

    print("A:")
    for i in range(0, 5):
        n = float(input("Número: "))
        A.append(n)

    print("B:")
    for i in range(0, 5):
        n = float(input("Número: "))
        B.append(n)

    i=0
    while i < len(A):
        print("Soma:", A[i], "+", B[i], "=", A[i] + B[i])
        i+=1

    i=0
    while i < len(A):
        print("Subtração:", A[i], "-", B[i], "=", A[i] - B[i])
        i+=1
    i=0
    while i < len(A):
        print("Multiplicação:", A[i], "*", B[i], "=", A[i] * B[i])
        i+=1
    i=0
    while i < len(A):
        print("Divisão:", A[i], "/", B[i], "=", A[i] / B[i])
        i+=1


# 9. Desafio: Monte um programa onde o usuário entra com o valor das diversas notas alcançadas por uma turma de alunos. O programa inicia perguntando o tamanho da turma e parte para a entrada de dados. A seguir, o programa deve ser capaz de exibir um histograma na tela, além de outras informações, conforme é mostrado a seguir:
#Obs 1: Ao lado da menor e da maior nota, deve ser mostrado entre parênteses a quantidade de vezes que essa nota apareceu;
#Obs 2: Por exemplo, na linha "7.1~8.0" os 5 “*” significam que 5 alunos alcançaram uma nota > 7.0 mas <= 8.0.


#Resultado da avaliação da turma:
#Menor nota: 2.1 (1x)
#Maior nota: 10.0 (2x)
#Média da turma: 6.5
#Histograma das notas:
#0.0 ~ 3.0: ***
#3.1 ~ 4.0: **
#4.1 ~ 5.0: ****
#5.1 ~ 6.0: *******
#6.1 ~ 7.0: **********
#7.1 ~ 8.0: *****
#8.1 ~ 9.0: *
#9.1 ~ 10.0: **


def exercicio9():
    print("Exercício 9")
    alunos = -1
    while alunos < 1:
        alunos = int(input("Quantidade de alunos: "))

    def isNotaInvalida(nota):
        return nota < 0 or nota > 10

    notas=[]
    for aluno in range(0, alunos):
        nota = -1

        while isNotaInvalida(nota):
            nota = float(input("Nota aluno {}: ".format(aluno+1)))
            if isNotaInvalida(nota):
                print("Nota inválida!")

        notas.append(nota)

    i = 0
    indiceMaior = 0
    indiceMenor = 0
    while i < len(notas):
        if notas[i] > notas[indiceMaior]:
            indiceMaior = i
        if notas[i] < notas[indiceMenor]:
            indiceMenor = i
        i+=1

    i = 0
    indicesMaiores = []
    indicesMenores = []
    notaTotal = 0

    while i < len(notas):
        if notas[i] == notas[indiceMaior]:
            indicesMaiores.append(i)
        if notas[i] == notas[indiceMenor]:
            indicesMenores.append(i)

        notaTotal += notas[i]
        i+=1

    mediaTurma = notaTotal / alunos

    print("\nResultado da avaliação")
    print("Menor nota: {} ({}x)".format(notas[indiceMenor], len(indicesMenores)))
    print("Maior nota: {} ({}x)".format(notas[indiceMaior], len(indicesMaiores)))
    print("Média: {:.2f}".format(mediaTurma))

    print("\nHistograma:")
    histograma = [0,0,0,0,0,0,0,0,0,0]

    for nota in notas:
        classe = int(nota)
        if nota != 0 and (nota - classe) == 0:
            classe-=1
        
        histograma[classe] += 1 

    i = 0
    while i < len(histograma):
        if histograma[i] != 0:
            inicio = float(i)
            fim = float(i + 1)
            if i != 0:
                inicio += 0.1

            print("{} ~ {}: {}".format(inicio, fim, "*" * histograma[i]))

        i+=1
        

exercicio1()
exercicio2()
exercicio3()
exercicio4()
exercicio5()
exercicio6()
exercicio7()
exercicio8()
exercicio9()
