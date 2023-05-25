package br.com.gabrielgmusskopf.unisinos.dominio;

import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;

import java.util.List;
import java.util.UUID;

// Adicionar listener para o pedido. Ser notificado quando alterar o estado

public class Cliente {

    private final UUID id;
    private final String nome;
    private List<Pedido> pedidos;

    public Cliente(String nome) {
        this.id = UUID.randomUUID();
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String toString() {
        return id + "-" + nome;
    }
}
