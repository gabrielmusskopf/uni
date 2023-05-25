package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.ClienteRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.PedidoRepositorio;

import java.util.List;

public class NovoPedidoComando {

    private final PedidoRepositorio pedidoRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    public NovoPedidoComando(PedidoRepositorio pedidoRepositorio, ClienteRepositorio clienteRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.clienteRepositorio = clienteRepositorio;
    }

    public Pedido criar(NovoPedidoInput input) {
        var cliente = clienteRepositorio.buscarPorNome(input.nomeCliente())
                .orElseThrow();

        var p = new Pedido(cliente, input.produtos());
        pedidoRepositorio.salvar(p);

        return p;
    }

    public record NovoPedidoInput(String nomeCliente, List<Produto> produtos) {}
}
