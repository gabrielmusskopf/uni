package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoCancelado extends EstadoPedido {

    protected PedidoCancelado(Pedido pedido) {
        super(pedido);
    }

    public String toString() {
        return "Cancelado";
    }

}
