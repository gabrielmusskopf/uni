package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Cliente;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.ClienteRepositorio;

import java.util.Optional;

public class BuscarClienteComando {

    private final ClienteRepositorio clienteRepositorio;

    public BuscarClienteComando(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public Optional<Cliente> buscar(String nome) {
        return clienteRepositorio.buscarPorNome(nome);
    }

}
