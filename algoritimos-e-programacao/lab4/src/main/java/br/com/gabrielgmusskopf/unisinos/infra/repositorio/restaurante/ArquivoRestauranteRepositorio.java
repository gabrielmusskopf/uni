package br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante;

import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.RepositorioArquivos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArquivoRestauranteRepositorio extends RepositorioArquivos<Restaurante> implements RestauranteRepositorio {

    private final List<Restaurante> restaurantes;

    public ArquivoRestauranteRepositorio() {
        restaurantes = new ArrayList<>();
        carregar(restaurantes);
        escreverAoFinal("id,nome,pedido,estoque,produto");
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
    public Optional<Restaurante> buscarPorId(String s) {
        return restaurantes.stream()
                .filter(r -> r.getId().equals(s))
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

    @Override
    protected String caminhoData() {
        return "data/restaurantes.csv";
    }

    @Override
    protected void recuperarElemento(String[] valores) {
        var id = valores[0];
        var nome = valores[1];
        var pedidoIds = stringToList(valores, 2);
        var estoqueId = valores[3];
        var produtoIds = stringToList(valores, 4);

        var pedidos = pedidoIds.stream()
                .map(i -> ContextoRepositorio.pedidoRepositorio()
                        .buscarPorId(i)
                        .orElse(null))
                .collect(Collectors.toCollection(LinkedList::new));

        var estoque = ContextoRepositorio.estoqueRepositorio()
                .buscarPorId(estoqueId)
                .orElse(null);

        var produtos = produtoIds.stream()
                .map(i -> ContextoRepositorio.produtoRepositorio()
                        .buscarPorId(i)
                        .orElse(null))
                .collect(Collectors.toSet());

        restaurantes.add(Restaurante.recuperar(id, nome, pedidos, estoque, produtos));
    }

    @Override
    protected List<?> escreverValores(Restaurante t) {
        return List.of(t.getId(), t.getNome(), t.getPedidos(), t.getEstoque(), t.getProdutos());
    }

}
