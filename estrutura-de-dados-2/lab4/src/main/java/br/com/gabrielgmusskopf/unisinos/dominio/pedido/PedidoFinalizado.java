package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoFinalizado extends EstadoPedido {

	@Override
	public int getOrdem() {
		return 7;
	}

	protected PedidoFinalizado(Pedido pedido) {
        super(pedido);
    }

    public String toString() {
        return "FINALIZADO";
    }

}
