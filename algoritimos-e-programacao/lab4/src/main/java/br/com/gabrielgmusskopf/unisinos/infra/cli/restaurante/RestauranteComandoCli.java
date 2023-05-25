package br.com.gabrielgmusskopf.unisinos.infra.cli.restaurante;

import br.com.gabrielgmusskopf.unisinos.infra.cli.ComandoCli;

import java.util.Arrays;

public enum RestauranteComandoCli implements ComandoCli {

    VER_PRODUTOS(1, "Produtos"),
    NOVO_PEDIDO(2, "Novo pedido"),
    FILA_PEDIDOS(3, "Ver fila de pedidos"),
    VOLTAR(4, "Voltar"),
    RETIRAR_PEDIO(5, "Retirar pedido"),
    CANCELAR(6, "Sair"),
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
