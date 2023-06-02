package br.com.gabrielgmusskopf.unisinos.dominio.execao;

import java.io.Serial;

public class EstadoPedidoInvalidoException extends RuntimeException {

    private static final long serialVersionUID = -628548725778395744L;

    public EstadoPedidoInvalidoException(String mensagem) {
        super(mensagem);
    }

}
