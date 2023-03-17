package filme

type Filme struct {
    codigo, ano int
    nome, diretor string
}

func NovoFilme(codigo int, ano int, nome string, diretor string) *Filme {
    return &Filme{codigo, ano, nome, diretor}
}
