package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Estoque;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.estoque.EstoqueRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;

public class NovoRestauranteComando {

    private final RestauranteRepositorio repositorio;
    private final EstoqueRepositorio estoqueRepositorio;

    public NovoRestauranteComando(RestauranteRepositorio repositorio, EstoqueRepositorio estoqueRepositorio) {
        this.repositorio = repositorio;
        this.estoqueRepositorio = estoqueRepositorio;
    }

    public Restaurante criar(String nome) {
        var estoque = new Estoque();
        var r = new Restaurante(nome, estoque);
        repositorio.salvar(r);
        estoqueRepositorio.salvar(estoque);

        return r;
    }

}
