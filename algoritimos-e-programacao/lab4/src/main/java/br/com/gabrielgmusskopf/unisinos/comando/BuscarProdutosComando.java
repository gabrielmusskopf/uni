package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto.ProdutoRepositorio;

import java.util.List;

public class BuscarProdutosComando {

    private final ProdutoRepositorio produtoRepositorio;

    public BuscarProdutosComando(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }

    public List<Produto> buscar() {
        return  produtoRepositorio.buscarTodos();
    }
}
