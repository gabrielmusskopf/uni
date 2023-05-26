package br.com.gabrielgmusskopf.unisinos.dominio;

import br.com.gabrielgmusskopf.unisinos.dominio.execao.IngredientesInsuficientesException;
import br.com.gabrielgmusskopf.unisinos.dominio.execao.RestauranteException;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;

import java.io.Serializable;
import java.util.*;

public class Restaurante implements Dominio, Serializable {

    private static final long serialVersionUID = 8453435229433626592L;
    private final UUID id;
    private final String nome;
    private Queue<Pedido> pedidos;
    private Estoque estoque;
    private Set<Produto> produtos;
    private PedidoProcessador pedidoProcessador;

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
        this.pedidoProcessador = new PedidoProcessador();
    }

    public void atualizarElemento(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void atualizarElemento(Produto pedido) {
        produtos.add(pedido);
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
        pedidoProcessador.processar(pedido);
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
        var proximo = pedidos.peek();
        if (proximo == null || !proximo.isAguardandoCliente()){
            throw new RestauranteException("Nenhum pronto para retirada");
        }
        var p = pedidos.poll();
        if (p == null) {
            throw new RestauranteException("Nenhum pedido na fila");
        }
        p.finalizar();
        return p;
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
