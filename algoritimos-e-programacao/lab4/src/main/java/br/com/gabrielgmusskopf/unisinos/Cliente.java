package br.com.gabrielgmusskopf.unisinos;

import br.com.gabrielgmusskopf.unisinos.pedido.Pedido;

import java.util.List;

// Adicionar listener para o pedido. Ser notificado quando alterar o estado

public class Cliente {

    private final String nome;
    private List<Pedido> pedidos;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return nome;
    }
}
