package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Estoque;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.estoque.EstoqueRepositorio;

public class AbastecerEstoqueComando {

    private final EstoqueRepositorio estoqueRepositorio;

    public AbastecerEstoqueComando(EstoqueRepositorio estoqueRepositorio) {
        this.estoqueRepositorio = estoqueRepositorio;
    }

    public void abastecer(Estoque estoque, String... ingredientes) {
        estoque.abastecer(ingredientes);
        estoqueRepositorio.salvar(estoque);
    }
}
