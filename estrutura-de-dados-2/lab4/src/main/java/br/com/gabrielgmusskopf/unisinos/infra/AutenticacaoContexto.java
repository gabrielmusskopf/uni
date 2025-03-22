package br.com.gabrielgmusskopf.unisinos.infra;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;

public class AutenticacaoContexto {

    private static Usuario autenticado;

    private AutenticacaoContexto() {}

    public static void autenticar(Usuario usuario) {
        autenticado = usuario;
    }

    public static Usuario getAutenticado() {
        return autenticado;
    }

}
