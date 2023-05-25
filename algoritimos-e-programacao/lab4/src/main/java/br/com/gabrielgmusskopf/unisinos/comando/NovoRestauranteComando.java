package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;

public class NovoRestauranteComando {

    private final RestauranteRepositorio repositorio;

    public NovoRestauranteComando(RestauranteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Restaurante criar(NovoRestauranteInput input) {
        var r = new Restaurante(input.nome());
        repositorio.salvar(r);
        return r;
    }

    public record NovoRestauranteInput(String nome) {
    }

}
