def isPrimo(num):
  if num == 1:
    return True

  aux = 2
  while aux < 10:
    if aux != num and num % aux == 0:
      return False
    aux += 1
  return True

while n := int(input("Digite um número: ")):
  if n < 0:
    print("É primo.\n" if isPrimo(n) else "Não é primo.\n")
  
