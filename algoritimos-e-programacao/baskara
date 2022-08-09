import math

def baskara (a, b, c):
  div = lambda b, delta, op: op(-b, math.sqrt(delta))
  
  delta = b**2 - (4*a*c)
  x1 = div(b, delta, lambda a, b: a+b) / 2*a
  x2 = div(b, delta, lambda a, b: a-b) / 2*a
  
  return [x1, x2]

a = int(input("a: "))
b = int(input("b: "))
c = int(input("c: "))

resultado = baskara(a,b,c)
print("\n\nx1:", resultado[0])
print("x2:", resultado[1])
