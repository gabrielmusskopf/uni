package br.com.gabrielgmusskopf.unisinos.infra.repositorio.estoque;

import br.com.gabrielgmusskopf.unisinos.dominio.Estoque;
import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MemoriaEstoqueRepositorio implements EstoqueRepositorio {

    private List<Estoque> estoqueMemoria = new LinkedList<>();

    @Override
    public Estoque salvar(Estoque estoque) {
        estoqueMemoria.add(estoque);
        return estoque;
    }

    @Override
    public Optional<Estoque> buscar(Estoque estoque) {
        return estoqueMemoria.stream()
                .filter(e -> e.getId().equals(estoque.getId()))
                .findFirst();
    }

    @Override
    public Optional<Estoque> buscarPorId(String s) {
        return estoqueMemoria.stream()
                .filter(e -> e.getId().equals(s))
                .findFirst();
    }

    @Override
    public void remover(Estoque estoque) {
        estoqueMemoria.remove(estoque);
    }

    @Override
    public List<Estoque> buscarTodos() {
        return estoqueMemoria;
    }

}
