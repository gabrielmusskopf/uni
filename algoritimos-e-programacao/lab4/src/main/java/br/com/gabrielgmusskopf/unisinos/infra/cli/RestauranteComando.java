package br.com.gabrielgmusskopf.unisinos.infra.cli;

import java.util.Arrays;

public enum RestauranteComando implements Comando {

    NOVO_PEDIDO(1, "Novo pedido"),
    CANCELAR(2, "Sair"),
    ;

    private final int numero;
    private final String descricao;

    RestauranteComando(int i, String s) {
        this.numero = i;
        this.descricao = s;
    }

    public static RestauranteComando numero(int n) {
        return Arrays.stream(RestauranteComando.values())
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
