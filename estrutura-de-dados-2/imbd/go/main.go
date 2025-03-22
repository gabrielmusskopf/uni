package main

import (
    "fmt"
    //"log"
    "github.com/unisinos/imdb/filme"
)

func main()  {

    f := *filme.NovoFilme(1, 2023, "Nome", "Diretor")
    f2 := *filme.NovoFilme(2, 2023, "Nome Filme 2", "Diretor")
    g := filme.NovaGravadora("Gravadora")

    //g.AdicionarFilme(f)

    //filmes_slice := make([]filme.Filme, 2, 2)
    //filmes := append(filmes_slice, f, f2)

    filmes := []filme.Filme {f, f2,}
    g.AdicionarFilmes(filmes)

    fmt.Println("Gravadora: ", g)
    
    /*
    g.RemoverFilme(0)
    error := g.RemoveFilmes(filmes)

    if error != nil {
       log.Fatal(error) 
    }
    */

    fmt.Println("Gravadora no final: ", g)
    fmt.Println("Todos filmes: ", g.BuscarFilmes())

    filmesEncontrados := g.BuscarFilmePorNome("Nome")
    fmt.Println("Filme encontrado por nome: ", filmesEncontrados)

}

