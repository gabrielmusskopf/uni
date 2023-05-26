package br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto;

import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.RepositorioArquivos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ArquivoProdutoRepositorio extends RepositorioArquivos<Produto> implements ProdutoRepositorio {

    private final List<Produto> produtos;

    public ArquivoProdutoRepositorio() {
        produtos = new ArrayList<>();
        carregar(produtos);
        escreverAoFinal("id,nome,valor,ingredientes");
    }

    @Override
    protected String caminhoData() {
        return "data/produtos.csv";
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
    public Optional<Produto> buscarPorId(String s) {
        return Optional.empty();
    }

    @Override
    public void remover(Produto produto) {
        produtos.remove(produto);
    }

    @Override
    public List<Produto> buscarTodos() {
        return produtos;
    }

    @Override
    protected void recuperarElemento(String[] valores) {
        var ingredientes = stringToList(valores, 3);
        produtos.add(Produto.recuperar(valores[0], valores[1], Double.parseDouble(valores[2]), ingredientes));
    }


    @Override
    protected List<?> escreverValores(Produto produto) {
        return List.of(produto.getId(), produto.getNome(), produto.getValor(), produto.getIngredientes());
    }
}
