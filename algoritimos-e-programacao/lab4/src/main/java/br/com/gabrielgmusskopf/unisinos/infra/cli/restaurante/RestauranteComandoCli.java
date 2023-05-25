package br.com.gabrielgmusskopf.unisinos.infra.cli.restaurante;

import br.com.gabrielgmusskopf.unisinos.infra.cli.ComandoCli;

import java.util.Arrays;

public enum RestauranteComandoCli implements ComandoCli {

    NOVO_PEDIDO(1, "Novo pedido"),
    VOLTAR(2, "Voltar"),
    CANCELAR(3, "Sair"),
    ;

    private final int numero;
    private final String descricao;

    RestauranteComandoCli(int i, String s) {
        this.numero = i;
        this.descricao = s;
    }

    public static RestauranteComandoCli numero(int n) {
        return Arrays.stream(RestauranteComandoCli.values())
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
