package br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.Repositorio;

import java.util.List;

public interface PedidoRepositorio extends Repositorio<String, Pedido> {

    List<Pedido> buscarParaCliente(Usuario usuario);

}
