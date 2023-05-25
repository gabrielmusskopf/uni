package br.com.gabrielgmusskopf.unisinos.dominio;

import java.io.Serializable;
import java.util.UUID;

// Adicionar listener para o pedido. Ser notificado quando alterar o estado

public class Usuario implements Serializable {

    private static final long serialVersionUID = 6008152717531275174L;
    private final UUID id;
    private final String nome;

    public Usuario(String nome) {
        this.id = UUID.randomUUID();
        this.nome = nome;
    }

    public Usuario(String id, String nome) {
        this.id = UUID.fromString(id);
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
