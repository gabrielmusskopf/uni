import random

def inserirJoioETrigo(quantidade):
  return map(lambda x: random.choice(["Trigo", "Joio"]), range(0, quantidade))

quant = ''
while not quant.isdigit():
  quant = input("Quantidade: ")

joios, trigos = [], []
for elemento in inserirJoioETrigo(int(quant)):
  joios.append(elemento) if elemento == "Joio" else trigos.append(elemento)

print("São", len(joios), " joios = ", joios)
print("\nS]ao", len(trigos), " trigos = ", trigos)
