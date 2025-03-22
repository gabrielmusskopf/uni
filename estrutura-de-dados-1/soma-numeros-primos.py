def isPrimo(num):
  if num == 1:
    return True

  aux = 2
  while aux < 10:
    if aux != num and num % aux == 0:
      return False
    aux += 1
  return True

i = 0
soma = 0
while i < 200:
  if isPrimo(i):
    soma += i
    print(i)
  i += 1

print("\nSoma:", soma)
