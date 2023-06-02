package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.PedidoProcessador;
import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;
import br.com.gabrielgmusskopf.unisinos.infra.Log;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.PedidoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;

import java.util.List;

public class NovoPedidoComando {

    private final PedidoRepositorio pedidoRepositorio;
    private final RestauranteRepositorio restauranteRepositorio;
	private final PedidoProcessador pedidoProcessador;

    public NovoPedidoComando(PedidoRepositorio pedidoRepositorio, RestauranteRepositorio restauranteRepositorio, PedidoProcessador pedidoProcessador) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.restauranteRepositorio = restauranteRepositorio;
		this.pedidoProcessador = pedidoProcessador;
	}

    public Pedido criar(Usuario usuario, Restaurante restaurante, List<Produto> produtos) {
        var p = new Pedido(usuario, produtos);
        restaurante.novoPedido(p);
		pedidoProcessador.processar(p);

        pedidoRepositorio.salvar(p);
        restauranteRepositorio.salvar(restaurante);
        Log.debug("Pedido " + p.getId() + " criado para o " + restaurante.getNome());

        return p;
    }

}
