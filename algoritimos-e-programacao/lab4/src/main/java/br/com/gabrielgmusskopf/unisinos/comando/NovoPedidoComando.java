package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.PedidoRepositorio;

import java.util.List;

public class NovoPedidoComando {

    private final PedidoRepositorio pedidoRepositorio;

    public NovoPedidoComando(PedidoRepositorio pedidoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
    }

    public Pedido criar(Usuario usuario, Restaurante restaurante, List<Produto> produtos) {
        var p = new Pedido(usuario, restaurante, produtos);
        restaurante.novoPedido(p);
        pedidoRepositorio.salvar(p);

        return p;
    }

}
