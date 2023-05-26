package br.com.gabrielgmusskopf.unisinos;

import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;

public class NovoProdutoComando {

    private final RestauranteRepositorio restauranteRepositorio;

    public NovoProdutoComando(RestauranteRepositorio restauranteRepositorio) {
        this.restauranteRepositorio = restauranteRepositorio;
    }

    public void adicionar(Restaurante restaurante, Produto... produtos) {
        restaurante.adicionarProdutos(produtos);
        restauranteRepositorio.salvar(restaurante);
    }
}
