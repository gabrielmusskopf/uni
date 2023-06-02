package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Estoque;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.Log;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.estoque.EstoqueRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;

public class NovoRestauranteComando {

    private final RestauranteRepositorio restauranteRepositorio;
    private final EstoqueRepositorio estoqueRepositorio;

    public NovoRestauranteComando(RestauranteRepositorio restauranteRepositorio, EstoqueRepositorio estoqueRepositorio) {
        this.restauranteRepositorio = restauranteRepositorio;
        this.estoqueRepositorio = estoqueRepositorio;
    }

    public Restaurante criar(String nome) {
        var estoque = new Estoque();
        var r = new Restaurante(nome, estoque);
        restauranteRepositorio.salvar(r);
        estoqueRepositorio.salvar(estoque);
        Log.debug("Restaurante " + nome + " criado");

        return r;
    }

}
