package br.com.gabrielgmusskopf.unisinos.infra.repositorio.estoque;

import br.com.gabrielgmusskopf.unisinos.dominio.Estoque;
import br.com.gabrielgmusskopf.unisinos.infra.Log;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.RepositorioCSV;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ArquivoEstoqueRepositorio extends RepositorioCSV<Estoque> implements EstoqueRepositorio {

    private final List<Estoque> estoques;

    public ArquivoEstoqueRepositorio() {
        estoques = new ArrayList<>();
        inicializar();
        Log.debug("Repositório CSV de estoque criado");
    }

    @Override
    protected String cabecalho() {
        return "id,ingredientes";
    }

    @Override
    public Estoque salvar(Estoque estoque) {
        buscarPorId(estoque.getId()).ifPresent(this::remover);
        estoques.add(estoque);
        return estoque;
    }


    @Override
    public Optional<Estoque> buscar(Estoque e) {
        return estoques.stream()
                .filter(u -> u.getId().equals(e.getId()))
                .findFirst();
    }

    @Override
    public Optional<Estoque> buscarPorId(String s) {
        return estoques.stream()
                .filter(e -> e.getId().equals(s))
                .findFirst();
    }

    @Override
    public void remover(Estoque estoque) {
        estoques.remove(estoque);
    }

    @Override
    public List<Estoque> buscarTodos() {
        return estoques;
    }

    @Override
    protected void recuperarElemento(String[] valores) {
        var id = valores[0];
        var ingredientes = lerMap(valores[1], Function.identity(), Integer::parseInt);

        estoques.add(Estoque.recuperar(id, ingredientes));
    }

    @Override
    protected List<?> escreverValores(Estoque e) {
        return List.of(e.getId(), e.getIngredientes());
    }

    @Override
    protected String caminhoData() {
        return "data/estoques.csv";
    }

}
