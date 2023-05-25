package br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto;

import br.com.gabrielgmusskopf.unisinos.dominio.Produto;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MemoriaProdutoRepositorio implements ProdutoRepositorio {

    private List<Produto> produtosMemoria;

    public MemoriaProdutoRepositorio() {
        produtosMemoria = new LinkedList<>();
        produtosMemoria.add(new Produto("Sanduíche", 5, List.of("Pão", "Queijo", "Manteiga")));
    }

    @Override
    public Produto salvar(Produto produto) {
        produtosMemoria.add(produto);
        return produto;
    }

    @Override
    public Optional<Produto> buscar(Produto produto) {
        return produtosMemoria.stream()
                .filter(p -> p.getId().equals(produto.getId()))
                .findFirst();
    }

    @Override
    public void remover(Produto produto) {
        produtosMemoria.remove(produto);
    }

    @Override
    public List<Produto> buscarTodos() {
        return produtosMemoria;
    }
}
