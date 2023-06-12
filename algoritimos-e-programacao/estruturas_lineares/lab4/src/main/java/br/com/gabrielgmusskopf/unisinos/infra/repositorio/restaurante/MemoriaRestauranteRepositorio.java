package br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante;

import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MemoriaRestauranteRepositorio implements RestauranteRepositorio {

    private List<Restaurante> restaurantes = new LinkedList<>();

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        restaurantes.add(restaurante);
        return restaurante;
    }

    @Override
    public Optional<Restaurante> buscar(Restaurante restaurante) {
        return restaurantes.stream()
                .filter(r -> r.getId().equals(restaurante.getId()))
                .findFirst();
    }

    @Override
    public Optional<Restaurante> buscarPorId(String s) {
        return Optional.empty();
    }

    @Override
    public void remover(Restaurante restaurante) {
        restaurantes.remove(restaurante);
    }

    @Override
    public List<Restaurante> buscarTodos() {
        return restaurantes;
    }

}
