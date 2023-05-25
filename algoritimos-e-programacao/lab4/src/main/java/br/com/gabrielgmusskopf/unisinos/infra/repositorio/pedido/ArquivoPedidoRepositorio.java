package br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ArquivoRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArquivoPedidoRepositorio extends ArquivoRepositorio implements PedidoRepositorio {

    private final List<Pedido> pedidos;

    public ArquivoPedidoRepositorio() {
        pedidos = new ArrayList<>();
        carregar(pedidos);
        escreverAoFinal();
    }

    @Override
    protected String caminhoData() {
        return "data/pedido.ser";
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        pedidos.add(pedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> buscar(Pedido pedido) {
        return pedidos.stream()
                .filter(p -> p.getId().equals(pedido.getId()))
                .findFirst();
    }

    @Override
    public void remover(Pedido pedido) {
        pedidos.remove(pedido);
    }

    @Override
    public List<Pedido> buscarTodos() {
        return pedidos;
    }

    @Override
    public List<Pedido> buscarParaCliente(Usuario usuario) {
        return pedidos.stream()
                .filter((p -> p.getCliente().equals(usuario))) //TODO: implementar equals Usuario
                .toList();
    }

}
