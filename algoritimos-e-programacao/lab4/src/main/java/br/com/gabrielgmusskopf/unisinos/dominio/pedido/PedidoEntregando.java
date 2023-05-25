package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoEntregando extends EstadoPedido {

    protected PedidoEntregando(Pedido pedido) {
        super(pedido);
    }

    protected void finalizar() {
        pedido.alterarEstado(new PedidoFinalizado(pedido));
    }

    public String toString() {
        return "Entregando";
    }

}
