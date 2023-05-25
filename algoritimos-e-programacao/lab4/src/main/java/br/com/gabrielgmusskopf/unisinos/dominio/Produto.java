package br.com.gabrielgmusskopf.unisinos.dominio;

import java.util.List;
import java.util.UUID;

public class Produto {

    private final UUID id;
    private final String nome;
    private final double valor;
    private final List<String> ingredientes;

    public Produto(String nome, double valor, List<String> ingredientes) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.valor = valor;
        this.ingredientes = ingredientes;
    }

    public String getId() {
        return id.toString();
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public String toString(){
        return """
                [%s] %s %.2f
                """.formatted(id, nome, valor);
    }

}
