package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoAprovado extends EstadoPedido {

    protected PedidoAprovado(Pedido pedido) {
        super(pedido);
    }

    protected void prontoParaEntrega() {
        pedido.alterarEstado(new PedidoPreparando(pedido));
    }

    public String toString() {
        return "Aprovado";
    }

}
