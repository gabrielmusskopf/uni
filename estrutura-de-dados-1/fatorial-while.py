# Exercício 13

def fatorial(num):
  total = num
  while num > 1:
    num -= 1
    total *= num
  
  return total

num = -1
while num < 0:
  num = int(input("Digite um número inteiro: "))
  
print("Seu fatorial é:", fatorial(num))
