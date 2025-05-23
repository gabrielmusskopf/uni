package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

class PedidoAprovado extends EstadoPedido {

	@Override
	public int getOrdem() {
		return 3;
	}

	protected PedidoAprovado(Pedido pedido) {
        super(pedido);
    }

    @Override
    protected void preparar() {
        pedido.alterarEstado(new PedidoPreparando(pedido));
    }

    public String toString() {
        return "APROVADO";
    }

}
