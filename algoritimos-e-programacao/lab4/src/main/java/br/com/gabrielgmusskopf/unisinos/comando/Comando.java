package br.com.gabrielgmusskopf.unisinos.comando;

import java.util.Arrays;

public enum Comando {

    NOVO_RESTAURANTE(1, "Novo restaurante"),
    ESCOLHER_RESTAURANTE(2, "Escolher restaurante"),
    CANCELAR(3, "Sair"),
    ;

    private final int numero;
    private final String descricao;

    Comando(int i, String s) {
        this.numero = i;
        this.descricao = s;
    }

    public static Comando numero(int n) {
        return Arrays.stream(Comando.values())
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
