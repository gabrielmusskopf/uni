package br.com.gabrielgmusskopf.unisinos.dominio;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

import br.com.gabrielgmusskopf.unisinos.dominio.execao.IngredientesInsuficientesException;
import br.com.gabrielgmusskopf.unisinos.dominio.execao.RestauranteException;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;

public class Restaurante implements Dominio {

    private final UUID id;
    private final String nome;
    private Queue<Pedido> pedidos;
    private Estoque estoque;
    private Set<Produto> produtos;

    public static Restaurante recuperar(String id, String nome, Queue<Pedido> pedidos, Estoque estoque, Set<Produto> produtos) {
        return new Restaurante(id, nome, pedidos, estoque, produtos);
    }

    public Restaurante(String nome, Estoque estoque) {
        this(UUID.randomUUID().toString(), nome, new LinkedList<>(), estoque, new HashSet<>());
    }

    private Restaurante(String id, String nome, Queue<Pedido> pedidos, Estoque estoque, Set<Produto> produtos) {
        this.id = UUID.fromString(id);
        this.nome = nome;
        this.estoque = estoque == null ? new Estoque() : estoque;
        this.pedidos = pedidos;
        this.produtos = produtos;
    }

	public void novoPedido(Pedido pedido) {
        if (!ingredientesEmEstoque(pedido)) {
            throw new IngredientesInsuficientesException();
        }

        var ingredientes = pedido.getProdutos()
                .stream()
                .map(Produto::getIngredientes)
                .flatMap(Collection::stream)
                .toList();

        estoque.retirar(ingredientes);
        pedidos.add(pedido);
    }

    public void adicionarProdutos(Produto... p) {
        produtos.addAll(List.of(p));
    }

    private boolean ingredientesEmEstoque(Pedido pedido) {
        var ingredientes = pedido.getProdutos().stream()
                .map(Produto::getIngredientes)
                .flatMap(Collection::stream)
                .toList();

        return estoque.contem(ingredientes);
    }

    public Pedido retirarPedido() {
        var proximo = pedidos.poll();
        if (proximo == null || !proximo.isAguardandoCliente()){
            throw new RestauranteException("Nenhum pedido pronto para retirada");
        }
        proximo.finalizar();
        return proximo;
    }

    public String getId() {
        return id.toString();
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public Queue<Pedido> getPedidos() {
        return pedidos;
    }

    public String getNome() {
        return nome;
    }

    public List<Produto> getProdutos() {
        return produtos.stream().toList();
    }
}
