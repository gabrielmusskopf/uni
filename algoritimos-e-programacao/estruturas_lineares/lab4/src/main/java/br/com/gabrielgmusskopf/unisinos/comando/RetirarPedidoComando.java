package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.Log;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.PedidoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;

public class RetirarPedidoComando {

	private final RestauranteRepositorio restauranteRepositorio;
	private final PedidoRepositorio pedidoRepositorio;

	public RetirarPedidoComando(RestauranteRepositorio restauranteRepositorio, PedidoRepositorio pedidoRepositorio) {
		this.restauranteRepositorio = restauranteRepositorio;
		this.pedidoRepositorio = pedidoRepositorio;
	}

	public void retirar(Restaurante restaurante){
        var pedidoFinalizado = restaurante.retirarPedido();
		restauranteRepositorio.salvar(restaurante);
		pedidoRepositorio.salvar(pedidoFinalizado);
		Log.debug("Pedido " + pedidoFinalizado.getId() + " finalizado");
    }

}
