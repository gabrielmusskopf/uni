package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoPreparando extends EstadoPedido {

    protected PedidoPreparando(Pedido pedido) {
        super(pedido);
    }

    protected void entregar() {
        pedido.alterarEstado(new PedidoAguardandoCliente(pedido));
    }

    public String toString() {
        return "Pronto para entrega";
    }

}
