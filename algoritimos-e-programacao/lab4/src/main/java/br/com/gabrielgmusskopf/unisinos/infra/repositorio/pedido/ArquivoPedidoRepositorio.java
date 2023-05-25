package br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;

import java.util.List;
import java.util.Optional;

public class ArquivoPedidoRepositorio implements PedidoRepositorio{
    @Override
    public Pedido salvar(Pedido pedido) {
        return null;
    }

    @Override
    public Optional<Pedido> buscar(Pedido pedido) {
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
        return null;
    }
}
