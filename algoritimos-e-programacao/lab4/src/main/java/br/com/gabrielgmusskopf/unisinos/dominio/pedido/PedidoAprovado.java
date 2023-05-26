package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoAprovado extends EstadoPedido {

    protected PedidoAprovado(Pedido pedido) {
        super(pedido);
    }

    @Override
    protected void preparar() {
        pedido.alterarEstado(new PedidoPreparando(pedido));
    }

    public String toString() {
        return "Aprovado";
    }

}
