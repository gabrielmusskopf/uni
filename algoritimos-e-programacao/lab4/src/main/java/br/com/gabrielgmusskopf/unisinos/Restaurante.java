package br.com.gabrielgmusskopf.unisinos;

import br.com.gabrielgmusskopf.unisinos.execao.IngredientesInsuficientesException;
import br.com.gabrielgmusskopf.unisinos.pedido.Pedido;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class Restaurante {

    private final String nome;
    private Queue<Pedido> pedidos;
    private Estoque estoque;
    private PedidoProcessador pedidoProcessador;

    public Restaurante(String nome) {
        this.nome = nome;
        this.estoque = new Estoque();
        this.pedidos = new LinkedList<>();
        this.pedidoProcessador = new PedidoProcessador();
    }

    public Restaurante(String nome, Estoque estoque) {
        this.nome = nome;
        this.estoque = estoque;
        this.pedidos = new LinkedList<>();
    }

    public void novoPedido(Pedido pedido) {
        if (!ingredientesEmEstoque(pedido)) {
            throw new IngredientesInsuficientesException();
        }
        pedidos.add(pedido);
        pedidoProcessador.processar(pedido);
    }

    private boolean ingredientesEmEstoque(Pedido pedido) {
        var ingredientes = pedido.getProdutos().stream()
                .map(Produto::getIngredientes)
                .flatMap(Collection::stream)
                .toList();

        return estoque.contem(ingredientes);
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public Queue<Pedido> getPedidos() {
        return pedidos;
    }

}
