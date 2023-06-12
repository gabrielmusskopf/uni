package br.com.gabrielgmusskopf.unisinos.infra.cli.auth;

import br.com.gabrielgmusskopf.unisinos.infra.cli.ComandoCli;

enum AuthComandoCli implements ComandoCli {

    CADASTRAR(1, "Cadastrar"),
    ENTRAR(2, "Entrar"),
    SAIR(Integer.MAX_VALUE, "Sair"),
    ;

    private final int ordem;
    private final String descricao;

    AuthComandoCli(int i, String s) {
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
