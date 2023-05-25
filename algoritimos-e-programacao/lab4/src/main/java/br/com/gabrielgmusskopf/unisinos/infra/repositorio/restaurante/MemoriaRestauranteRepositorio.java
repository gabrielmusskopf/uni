package br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante;

import br.com.gabrielgmusskopf.unisinos.dominio.Estoque;
import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MemoriaRestauranteRepositorio implements RestauranteRepositorio {

    private List<Restaurante> restaurantes;

    public MemoriaRestauranteRepositorio() {
        restaurantes = new LinkedList<>();
        var e = new Estoque();
        e.abastecer("Pão", "Manteiga", "Queijo", "Café", "Leite");

        var r = new Restaurante("Happy Station", e);
        r.adicionarProdutos(
                new Produto("Sanduíche", 5, List.of("Pão", "Manteiga", "Queijo")),
                new Produto("Café", 3, List.of("Café")),
                new Produto("Café com leite", 5, List.of("Café", "Leite"))
        );

        restaurantes.add(r);
    }

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
    public void remover(Restaurante restaurante) {
        restaurantes.remove(restaurante);
    }

    @Override
    public List<Restaurante> buscarTodos() {
        return restaurantes;
    }

}
