package br.com.gabrielgmusskopf.unisinos.infra;

import br.com.gabrielgmusskopf.unisinos.dominio.Cliente;

public class AutenticacaoContexto {

    private static Cliente autenticado;

    public static void autenticar(Cliente cliente) {
        autenticado = cliente;
    }

    public static Cliente getAutenticado() {
        return autenticado;
    }

}
