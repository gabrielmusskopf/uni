package br.com.gabrielgmusskopf.unisinos;

import br.com.gabrielgmusskopf.unisinos.pedido.Pedido;

public class Main {

    public static void main(String[] args) {
        var restaurante = new Restaurante("Happy Station");
        restaurante.getEstoque().abastecer("Sal", "Tomate", "Farinha");

        var c1 = new Cliente("Cliente Faminto");
        var p1 = new Pedido(c1, new Focaccia());

        restaurante.novoPedido(p1);

    }
}