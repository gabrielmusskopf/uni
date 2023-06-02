def estrofe(numPatinhos):
  patinhosVoltaram = numPatinhos -1
  patinhos = "patinhos" if numPatinhos > 1 else "patinho"
  foiPassear = "\nForam passear" if numPatinhos > 1 else "foi passear" 
  
  if patinhosVoltaram > 1:
    voltaram = "Mas só " + str(patinhosVoltaram) + " patinhos\nVoltaram" 
  elif patinhosVoltaram == 1:
    voltaram = "Mas só " + str(patinhosVoltaram) + " patinho\nVoltou" 
  else:
    voltaram = "Mas nenhum patinho voltou" 
  
  print(numPatinhos, patinhos, foiPassear, "\nAlém das montanhas \nPara brincar \nA mamãe gritou \nQuack quack quack \n" + voltaram  + " de lá\n")

patinhos = int(input("A Xuxa quer saber: Qual a quantidade de patinhos? "))

while patinhos < 2:
  patinhos = int(input("Número muito baixo de patinhos, a Xuxa precisa de mais!\nQuantidade: "))

print()

patinhosAux = patinhos
while patinhosAux >= 1:
  estrofe(patinhosAux)
  patinhosAux -= 1

print("A mamãe patinha\nFoi procurar\nAlém das montanhas \nNa beira do mar \nA mamãe gritou \nQuack quack quack \nE os", patinhos, "patinhos \nVoltaram de lá")
