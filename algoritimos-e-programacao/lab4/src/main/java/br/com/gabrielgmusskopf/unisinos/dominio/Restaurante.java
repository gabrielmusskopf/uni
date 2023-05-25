package br.com.gabrielgmusskopf.unisinos.dominio;

import br.com.gabrielgmusskopf.unisinos.dominio.execao.IngredientesInsuficientesException;
import br.com.gabrielgmusskopf.unisinos.dominio.execao.RestauranteException;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;

import java.io.Serializable;
import java.util.*;

public class Restaurante implements Serializable {

    private static final long serialVersionUID = 8453435229433626592L;
    private final UUID id;
    private final String nome;
    private Queue<Pedido> pedidos;
    private Estoque estoque;
    private Set<Produto> produtos;
    private PedidoProcessador pedidoProcessador;

    public Restaurante(String nome) {
        this(nome, new Estoque());
    }

    public Restaurante(String nome, Estoque estoque) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.estoque = estoque;
        this.pedidos = new LinkedList<>();
        this.produtos = new HashSet<>();
        this.pedidoProcessador = new PedidoProcessador();
    }

    public void novoPedido(Pedido pedido) {
        if (!ingredientesEmEstoque(pedido)) {
            throw new IngredientesInsuficientesException();
        }
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
