package br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante;

import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;

import java.util.List;
import java.util.Optional;

public class ArquivoRestauranteRepositorio implements RestauranteRepositorio {

    private List<Restaurante> restaurantes;

    public ArquivoRestauranteRepositorio() {
        inicializar();
    }

    private void inicializar() {
        //Buscar restaurantes de arquivo
    }

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        restaurantes.add(restaurante);
        return null;
    }

    @Override
    public Optional<Restaurante> buscar(Restaurante restaurante) {
        return Optional.empty();
    }

    @Override
    public void remover(Restaurante restaurante) {

    }

    @Override
    public List<Restaurante> buscarTodos() {
        return null;
    }
}
