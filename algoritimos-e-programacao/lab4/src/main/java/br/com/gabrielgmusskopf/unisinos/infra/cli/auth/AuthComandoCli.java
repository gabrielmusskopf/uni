package br.com.gabrielgmusskopf.unisinos.infra.cli.auth;

import br.com.gabrielgmusskopf.unisinos.infra.cli.ComandoCli;

import java.util.Arrays;

public enum AuthComandoCli implements ComandoCli {

    CADASTRAR(1, "Cadastrar"),
    ENTRAR(2, "Entrar"),
    SAIR(3, "Sair"),
    ;

    private final int numero;
    private final String descricao;

    AuthComandoCli(int i, String s) {
        this.numero = i;
        this.descricao = s;
    }

    public static AuthComandoCli numero(int n) {
        return Arrays.stream(AuthComandoCli.values())
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
