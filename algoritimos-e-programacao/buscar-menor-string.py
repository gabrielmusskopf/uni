def buscarMenorTexto(textos):
  if type(textos) != list or len(textos) <= 0:
    print("Entrada inválida.")
    quit()
    
  textos.sort(key=lambda e: len(e))
  return textos[0] 

palavras = []

print("Digite valores para a lista.\nDigite ':q' para sair.\n")
while inp := input("- ") :
  if inp != ':q':
    palavras.append(inp)
  else: 
    break

if len(palavras) > 0:
  print("O menor é", buscarMenorTexto(palavras))
