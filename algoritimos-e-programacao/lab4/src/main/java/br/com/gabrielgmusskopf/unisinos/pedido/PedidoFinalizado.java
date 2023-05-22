package br.com.gabrielgmusskopf.unisinos.pedido;

class PedidoFinalizado extends EstadoPedido {

    protected PedidoFinalizado(Pedido pedido) {
        super(pedido);
    }

    public String toString() {
        return "Finalizado";
    }

}
