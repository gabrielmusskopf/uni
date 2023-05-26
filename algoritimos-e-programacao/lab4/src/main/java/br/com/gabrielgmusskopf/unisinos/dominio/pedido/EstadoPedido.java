package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

import br.com.gabrielgmusskopf.unisinos.dominio.execao.EstadoPedidoInvalidoException;

import java.io.Serializable;

public abstract class EstadoPedido implements Serializable {

    private static final long serialVersionUID = -327681301896308662L;
    protected Pedido pedido;

    protected EstadoPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    protected void criar() {
        throw new EstadoPedidoInvalidoException("Não é possível criar o pedido.");
    }

    protected void processarPagamento() {
        throw new EstadoPedidoInvalidoException("Não é possível processar o pagamento do pedido");
    }

    protected void aprovar() {
        throw new EstadoPedidoInvalidoException("Não é possível aprovar o pedido");
    }

    protected void preparar() {
        throw new EstadoPedidoInvalidoException("Não é possível preparar o pedido");
    }

    protected void aguardandoCliente() {
        throw new EstadoPedidoInvalidoException("Não é possível retirar o pedido");
    }

    protected void finalizar() {
        throw new EstadoPedidoInvalidoException("Não é possível finalizar o pedido");
    }

    protected void cancelar() {
        throw new EstadoPedidoInvalidoException("Não é possível cancelar o pedido");
    }

}
