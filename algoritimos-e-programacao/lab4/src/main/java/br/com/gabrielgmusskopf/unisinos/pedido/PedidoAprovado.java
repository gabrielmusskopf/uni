package br.com.gabrielgmusskopf.unisinos.pedido;

class PedidoAprovado extends EstadoPedido {

    protected PedidoAprovado(Pedido pedido) {
        super(pedido);
    }

    protected void entregar() {
        pedido.alterarEstado(new PedidoEntregando(pedido));
    }

    public String toString() {
        return "Aprovado";
    }

}
