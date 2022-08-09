def calcularNota(nota, peso):
  if nota > 10 or nota < 0:
    print('Nota inválida, presta atenção!')
    quit()
   
  return (nota * peso / 100)

avaliacoes = [
  {
    "grau": "A",
    "peso": 33,
    "atividades": [
      {
        "nome": "Atividade prática",
        "peso": 45
      },
      {
        "nome": "Atividade teórica",
        "peso": 55
      }
     
    ]
  },
  {
    "grau": "B",
    "peso": 67,
    "atividades": [
      {
        "nome": "Prova em laboratório",
        "peso": 60
      },
      {
        "nome": "Teste teórico",
        "peso": 20
      },
      {
        "nome": "Trabalho extraclasse",
        "peso": 20
      }
     
    ]
  }
]

print('Buenas notches. \nNotas sao de 0 a 10\n')

nota = 0
for avaliacao in avaliacoes:
  print("\nGrau " + avaliacao["grau"])

  notaAtividade = 0
  for atividade in avaliacao["atividades"]:
    nota = int(input('Nota de ' + atividade["nome"] + ": "))
    notaAtividade += calcularNota(nota, atividade["peso"])

  nota = calcularNota(notaAtividade, avaliacao["peso"])

print(nota)

