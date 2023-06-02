package br.com.gabrielgmusskopf.unisinos.dominio;

import java.util.UUID;

public class Usuario implements Dominio {

    private static final long serialVersionUID = 6008152717531275174L;
    private final UUID id;
    private final String nome;

    public Usuario(String nome) {
        this.id = UUID.randomUUID();
        this.nome = nome;
    }

    private Usuario(String id, String nome) {
        this.id = UUID.fromString(id);
        this.nome = nome;
    }

    public static Usuario recuperar(String id, String nome) {
        return new Usuario(id, nome);
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
