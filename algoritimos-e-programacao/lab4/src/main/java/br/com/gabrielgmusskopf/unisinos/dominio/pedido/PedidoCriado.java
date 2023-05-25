package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoCriado extends EstadoPedido {

    protected PedidoCriado(Pedido pedido) {
        super(pedido);
    }

    protected void processarPagamento() {
        pedido.alterarEstado(new PedidoProcessandoPagamento(pedido));
    }

    protected void cancelar() {
        pedido.alterarEstado(new PedidoCancelado(pedido));
    }

    public String toString() {
        return "Criado";
    }

}
