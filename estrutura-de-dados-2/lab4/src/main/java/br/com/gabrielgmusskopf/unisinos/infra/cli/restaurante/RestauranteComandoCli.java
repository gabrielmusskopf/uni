package br.com.gabrielgmusskopf.unisinos.infra.cli.restaurante;

import br.com.gabrielgmusskopf.unisinos.infra.cli.ComandoCli;

import java.util.Arrays;

enum RestauranteComandoCli implements ComandoCli {

    VER_PRODUTOS(1, "Produtos"),
    NOVO_PEDIDO(2, "Novo pedido"),
    FILA_PEDIDOS(3, "Ver fila de pedidos"),
    RETIRAR_PEDIO(4, "Retirar pedido"),
    ADICIONAR_PRODUTOS(5, "Adicionar produtos"),
    VER_ESTOQUE(6, "Ver estoque"),
    ABASTECER_ESTOQUE(7, "Abastecer estoque"),
    VOLTAR(Integer.MAX_VALUE - 1, "Voltar"),
    CANCELAR(Integer.MAX_VALUE, "Sair"),
    ;

    private final int ordem;
    private final String descricao;

    RestauranteComandoCli(int i, String s) {
        this.ordem = i;
        this.descricao = s;
    }

    public int getOrdem() {
        return ordem;
    }

    public String getDescricao() {
        return descricao;
    }

}
