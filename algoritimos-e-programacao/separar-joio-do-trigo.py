import random

def inserirJoioETrigo(quantidade):
  return map(lambda x: random.choice(["Trigo", "Joio"]), range(0, quantidade))
  
quant = ''
while not quant.isdigit():
  quant = input("Quantidade: ")
  
joio, trigo = [], []
for elemento in inserirJoioETrigo(int(quant)):
  joio.append(elemento) if elemento == "Joio" else trigo.append(elemento)


print("Tem", len(joio), " joios e", len(trigo), "trigos")
print(joio)
print(trigo)
