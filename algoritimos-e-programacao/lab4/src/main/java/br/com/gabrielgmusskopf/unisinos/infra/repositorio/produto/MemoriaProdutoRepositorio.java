package br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto;

import br.com.gabrielgmusskopf.unisinos.dominio.Produto;

import java.util.List;
import java.util.Optional;

public class MemoriaProdutoRepositorio implements ProdutoRepositorio{
    @Override
    public Produto salvar(Produto produto) {
        return null;
    }

    @Override
    public Optional<Produto> buscar(Produto produto) {
        return Optional.empty();
    }

    @Override
    public void remover(Produto produto) {

    }

    @Override
    public List<Produto> buscarTodos() {
        return null;
    }
}
