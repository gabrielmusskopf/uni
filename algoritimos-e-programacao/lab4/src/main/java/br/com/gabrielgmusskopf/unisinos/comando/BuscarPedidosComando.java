package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.PedidoRepositorio;

import java.util.List;

public class BuscarPedidosComando {

   private final PedidoRepositorio pedidoRepositorio;

    public BuscarPedidosComando(PedidoRepositorio pedidoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
    }

    public List<Pedido> buscar(Usuario usuario) {
        return pedidoRepositorio.buscarParaCliente(usuario);
    }
}
