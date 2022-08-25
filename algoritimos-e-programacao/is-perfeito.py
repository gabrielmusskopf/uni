# Gabriela Bley
# Gabriel Musskopf
import math

def divisoresRapido(n):
  divisores = []
  for i in range(1, int(math.sqrt(n) + 1)):
      if n % i == 0:
        yield i
        if i != 1:
          divisores.append(n / i)

  for divisor in reversed(divisores):
    yield int(divisor)

def divisoresLento(n):
  divisores = []
  for n in range(1, numero):
    if numero % n == 0:
      divisores.append(n)
      
  return divisores

numero = 0
while numero >= 0:
  numero = int(input("Digite um número: "))

  
  total = 0
  for divisor in divisoresRapido(numero):
    total += divisor
  
  print("É perfeito") if total == numero else print("Não é perfeito")
  print()
