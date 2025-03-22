package br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MemoriaPedidoRepositorio implements PedidoRepositorio{

    private List<Pedido> pedidosMemoria = new LinkedList<>();

    @Override
    public Pedido salvar(Pedido pedido) {
        pedidosMemoria.add(pedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> buscar(Pedido pedido) {
        return Optional.empty();
    }

    @Override
    public Optional<Pedido> buscarPorId(String s) {
        return Optional.empty();
    }

    @Override
    public void remover(Pedido pedido) {

    }

    @Override
    public List<Pedido> buscarTodos() {
        return null;
    }

    @Override
    public List<Pedido> buscarParaCliente(Usuario usuario) {
        return pedidosMemoria.stream()
                .filter(p -> p.getCliente().getId().equals(usuario.getId()))
                .toList();
    }
}
