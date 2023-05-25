package br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto;

import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ArquivoRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArquivoProdutoRepositorio extends ArquivoRepositorio implements ProdutoRepositorio {

    private final List<Produto> produtos;

    public ArquivoProdutoRepositorio() {
        produtos = new ArrayList<>();
        carregar(produtos);
        escreverAoFinal();
    }

    @Override
    protected String caminhoData() {
        return "data/produtos.ser";
    }

    @Override
    public Produto salvar(Produto produto) {
        produtos.add(produto);
        return produto;
    }

    @Override
    public Optional<Produto> buscar(Produto produto) {
        return produtos.stream()
                .filter(p -> p.getId().equals(produto.getId()))
                .findFirst();
    }

    @Override
    public void remover(Produto produto) {
        produtos.remove(produto);
    }

    @Override
    public List<Produto> buscarTodos() {
        return produtos;
    }
}
