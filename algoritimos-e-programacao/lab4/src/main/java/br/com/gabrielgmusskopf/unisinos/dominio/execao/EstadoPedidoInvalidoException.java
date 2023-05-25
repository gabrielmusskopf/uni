package br.com.gabrielgmusskopf.unisinos.dominio.execao;

public class EstadoPedidoInvalidoException extends RuntimeException {

    public EstadoPedidoInvalidoException(String mensagem) {
        super(mensagem);
    }

}
