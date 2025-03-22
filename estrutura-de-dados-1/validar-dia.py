# Exercício 7

#   Solicite que o usuário informe uma data, solicitando de forma separada o ano, o mês e o dia (nesta ordem), todos valores inteiros. 
# O ano pode ser qualquer valor entre 0 e 2022. O mês deve ser um valor entre 1 (inclusive) e 12 (inclusive). O dia deve estar de acordo 
# com o mês informado e, ainda, com o ano (bissexto ou não). Acesse ESTE LINK para ver como calcular se um ano é ou não bissexto.
  
import re

def tem31Dias(numMes):
  return (numMes - 1 ) % 2 == 0

def excedeQuantidadeDeDias(dia, mes):
  return dia < 0 or dia > 31 or (tem31Dias(mes) and dia > 31)

def isDiaBissexto(dia, mes, ano):
  isBissexto = ano % 100 == 0 and ano % 400 == 0
  return isBissexto and mes == 2 and dia > 28
  
SEPARADORES = r"[-/ ]"
ESPACO = " "
DIA = 0
MES = 1
ANO = 2

data = []
dataValida = False

while(not dataValida):
  entrada = input("Informe uma data: ")
  data = re.split(SEPARADORES, entrada)

  if len(data) != 3: 
    print("Data inválida. Insira dia/mes/ano.\n")
    continue

  for i in range(len(data)):
    data[i] = int(data[i])
  
  if data[ANO] < 0 or data[ANO] > 2022:
    print("Ano inválido.\n")
    continue
  
  if data[MES] < 1 or data[MES] > 12:
    print("Mês inválido\n")
    continue

  if excedeQuantidadeDeDias(data[DIA], data[MES]) or isDiaBissexto(data[DIA], data[MES], data[ANO]):
    print("Quantidade de dias inválido para este mês.\n")
    continue

  else:
    dataValida = True
    print("\nTudo certo com a data! \o/")
