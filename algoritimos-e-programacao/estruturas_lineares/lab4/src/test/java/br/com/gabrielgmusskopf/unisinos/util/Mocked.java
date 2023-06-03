package br.com.gabrielgmusskopf.unisinos.util;

import br.com.gabrielgmusskopf.unisinos.dominio.Estoque;
import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;

import java.util.List;

public class Mocked {

    private Mocked(){}

    public static Usuario usuario() {
        return new Usuario("Usuário");
    }

    public static Estoque estoque() {
        return new Estoque();
    }

    public static Restaurante restaurante() {
        return new Restaurante("Restaurante", estoque());
    }

    public static Produto produto() {
        return new Produto("Café", 2, List.of("cafe"));
    }

    public static Pedido pedido() {
        return new Pedido(usuario(), List.of(produto()));
    }

}
