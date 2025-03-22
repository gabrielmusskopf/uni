package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto.ProdutoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;

public class NovoProdutoComando {

    private final RestauranteRepositorio restauranteRepositorio;
    private final ProdutoRepositorio produtoRepositorio;

    public NovoProdutoComando(RestauranteRepositorio restauranteRepositorio, ProdutoRepositorio produtoRepositorio) {
        this.restauranteRepositorio = restauranteRepositorio;
        this.produtoRepositorio = produtoRepositorio;
    }

    public void adicionar(Restaurante restaurante, Produto... produtos) {
        restaurante.adicionarProdutos(produtos);
        restauranteRepositorio.salvar(restaurante);
        for (Produto produto : produtos) {
            produtoRepositorio.salvar(produto);
        }
    }
}
