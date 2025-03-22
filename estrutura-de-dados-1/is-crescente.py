# Gabriela Bley
# Gabriel Musskopf

JOGAR = "S"

def exibirResultado(isCrescente, media):
  audiencia = "AUDIÊNCIA SEMPRE CRESCENTE" if isCrescente else "AUDIÊNCIA NEM SEMPRE CRESCENTE"
  
  print(audiencia + ". Média de audiência: %.1f" % media) 

jogar = JOGAR
while jogar == JOGAR:
  
  vezes = 0
  while vezes <= 0: 
    vezes = int(input("\nDigite a quantidade de índices: "))
  
  anterior = 0
  total = 0
  isCrescente = True
  
  for vez in range(0, vezes):
    indice = float(input())
    
    while indice <= 0:
      indice = float(input("Audiência negativa? Como isso é possível :O. Insira um valor válido:\n"))

    if indice < anterior and isCrescente:
      isCrescente = False
    
    anterior = indice
    total += indice
  
  exibirResultado(isCrescente, total/vezes)

  jogar = input("\nDeseja jogar novamente?\nDigite '" + JOGAR + "', caso queira: ").upper()

