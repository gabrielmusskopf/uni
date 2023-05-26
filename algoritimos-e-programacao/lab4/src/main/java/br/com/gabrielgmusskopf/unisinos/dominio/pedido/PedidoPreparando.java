package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoPreparando extends EstadoPedido {

    protected PedidoPreparando(Pedido pedido) {
        super(pedido);
    }

    @Override
    protected void aguardandoCliente() {
        pedido.alterarEstado(new PedidoAguardandoCliente(pedido));
    }

    public String toString() {
        return "Preparando";
    }

}
