package br.com.gabrielgmusskopf.unisinos.infra.cli;

import java.util.Arrays;

public enum HomeComando implements Comando {

    NOVO_RESTAURANTE(1, "Novo restaurante"),
    ESCOLHER_RESTAURANTE(2, "Escolher restaurante"),
    CANCELAR(3, "Sair"),
    ;

    private final int numero;
    private final String descricao;

    HomeComando(int i, String s) {
        this.numero = i;
        this.descricao = s;
    }

    public static HomeComando numero(int n) {
        return Arrays.stream(HomeComando.values())
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
