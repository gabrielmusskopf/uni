package br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente;

import br.com.gabrielgmusskopf.unisinos.dominio.Cliente;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MemoriaClienteRepositorio implements ClienteRepositorio {

    private List<Cliente> clientesMemoria = new LinkedList<>();

    @Override
    public Cliente salvar(Cliente cliente) {
        clientesMemoria.add(cliente);
        return cliente;
    }

    @Override
    public Optional<Cliente> buscar(Cliente cliente) {
        return Optional.empty();
    }

    @Override
    public void remover(Cliente cliente) {

    }

    @Override
    public List<Cliente> buscarTodos() {
        return null;
    }

    @Override
    public Optional<Cliente> buscarPorNome(String nome) {
        //Remover
        return Optional.of(new Cliente("Gabriel"));
    }
}
