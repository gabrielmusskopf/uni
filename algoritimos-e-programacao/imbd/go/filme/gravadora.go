package filme

import (
    "errors"
)

type Gravadora struct {
    nome string
    filmes []Filme
}

func NovaGravadora(nome string) *Gravadora {
    return &Gravadora{nome: nome}
}

func (g *Gravadora) AdicionarFilme(filme Filme) {
    g.filmes = append(g.filmes, filme)
}

func (g *Gravadora) AdicionarFilmes(filmes []Filme) {
    g.filmes = append(g.filmes, filmes...)
}

func (g *Gravadora) RemoverFilme(i int) error {
    if len(g.filmes) <= i {
        return errors.New("não existe filme neste índice")
    }

    g.filmes = append(g.filmes[:i], g.filmes[i+1:]...)

    return nil
}

func (g *Gravadora) RemoveFilme(filme Filme) error {
    for i, f := range g.filmes {
        if f == filme { 
            return g.RemoverFilme(i)
        } 
    }

    return errors.New("filme não está na lista")
}

func (g *Gravadora) RemoveFilmes(filmes []Filme) error {
    for _, f := range g.filmes {
        error := g.RemoveFilme(f)
        if error != nil {
            return error
        }
    }

    return nil
}
