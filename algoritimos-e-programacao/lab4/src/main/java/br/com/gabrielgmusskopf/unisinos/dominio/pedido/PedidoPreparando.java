package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoPreparando extends EstadoPedido {

	@Override
	public int getOrdem() {
		return 4;
	}

	protected PedidoPreparando(Pedido pedido) {
        super(pedido);
    }

    @Override
    protected void aguardandoCliente() {
        pedido.alterarEstado(new PedidoAguardandoCliente(pedido));
    }

    public String toString() {
        return "PREPARANDO";
    }

}
