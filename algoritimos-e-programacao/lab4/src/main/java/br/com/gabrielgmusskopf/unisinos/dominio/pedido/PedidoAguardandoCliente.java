package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoAguardandoCliente extends EstadoPedido {

    protected PedidoAguardandoCliente(Pedido pedido) {
        super(pedido);
    }

    protected void finalizar() {
        pedido.alterarEstado(new PedidoFinalizado(pedido));
    }

    public String toString() {
        return "Aguardando cliente";
    }

}
