package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoAguardandoCliente extends EstadoPedido {

	@Override
	public int getOrdem() {
		return 6;
	}

	protected PedidoAguardandoCliente(Pedido pedido) {
        super(pedido);
    }

    protected void finalizar() {
        pedido.alterarEstado(new PedidoFinalizado(pedido));
    }

    public String toString() {
        return "AGUARDANDO_CLIENTE";
    }

}
