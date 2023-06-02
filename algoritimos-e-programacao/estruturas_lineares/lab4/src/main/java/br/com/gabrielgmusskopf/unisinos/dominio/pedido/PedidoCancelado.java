package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoCancelado extends EstadoPedido {

	@Override
	public int getOrdem() {
		return -1;
	}

	protected PedidoCancelado(Pedido pedido) {
        super(pedido);
    }

    public String toString() {
        return "CANCELADO";
    }

}
