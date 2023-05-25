package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoProcessandoPagamento extends EstadoPedido {

    protected PedidoProcessandoPagamento(Pedido pedido) {
        super(pedido);
    }

    protected void aprovar() {
        pedido.alterarEstado(new PedidoAprovado(pedido));
    }

    protected void cancelar() {
        pedido.alterarEstado(new PedidoCancelado(pedido));
    }

    public String toString() {
        return "Processando pagamento";
    }

}
