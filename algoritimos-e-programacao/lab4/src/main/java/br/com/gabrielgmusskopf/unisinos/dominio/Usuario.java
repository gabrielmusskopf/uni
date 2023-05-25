package br.com.gabrielgmusskopf.unisinos.dominio;

import java.util.UUID;

// Adicionar listener para o pedido. Ser notificado quando alterar o estado

public class Usuario {

    private final UUID id;
    private final String nome;

    public Usuario(String nome) {
        this.id = UUID.randomUUID();
        this.nome = nome;
    }

    public String getId() {
        return id.toString();
    }

    public String getNome() {
        return nome;
    }

    public String toString() {
        return id + "-" + nome;
    }
}
