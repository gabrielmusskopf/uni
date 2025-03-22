package br.com.gabrielgmusskopf.unisinos.infra.cli.home;

import br.com.gabrielgmusskopf.unisinos.infra.cli.ComandoCli;

enum HomeComandoCli implements ComandoCli {

    NOVO_RESTAURANTE(1, "Novo restaurante"),
    ESCOLHER_RESTAURANTE(2, "Escolher restaurante"),
    MEUS_PEDIDOS(3, "Meus pedidos"),
    CANCELAR(Integer.MAX_VALUE, "Sair"),
    ;

    private final int ordem;
    private final String descricao;

    HomeComandoCli(int i, String s) {
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
