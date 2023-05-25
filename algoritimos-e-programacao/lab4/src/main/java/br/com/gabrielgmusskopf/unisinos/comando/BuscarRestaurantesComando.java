package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;

import java.util.List;

public class BuscarRestaurantesComando {

    private final RestauranteRepositorio restauranteRepositorio;

    public BuscarRestaurantesComando(RestauranteRepositorio restauranteRepositorio) {
        this.restauranteRepositorio = restauranteRepositorio;
    }

    public List<Restaurante> buscar() {
        return restauranteRepositorio.buscarTodos();
    }

}
