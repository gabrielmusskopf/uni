package br.com.gabrielgmusskopf.unisinos.infra.cli.home;

import br.com.gabrielgmusskopf.unisinos.infra.cli.ComandoCli;

import java.util.Arrays;

public enum HomeComandoCli implements ComandoCli {

    NOVO_RESTAURANTE(1, "Novo restaurante"),
    ESCOLHER_RESTAURANTE(2, "Escolher restaurante"),
    MEUS_PEDIDOS(3, "Meus pedidos"),
    CANCELAR(4, "Sair"),
    ;

    private final int numero;
    private final String descricao;

    HomeComandoCli(int i, String s) {
        this.numero = i;
        this.descricao = s;
    }

    public static HomeComandoCli numero(int n) {
        return Arrays.stream(HomeComandoCli.values())
                .filter(c -> c.getNumero() == n)
                .findFirst()
                .orElse(null);
    }

    public int getNumero() {
        return numero;
    }

    public String getDescricao() {
        return descricao;
    }

}