package br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente;

import br.com.gabrielgmusskopf.unisinos.dominio.Cliente;

import java.util.List;
import java.util.Optional;

public class ArquivoClienteRepositorio implements ClienteRepositorio {
    @Override
    public Cliente salvar(Cliente cliente) {
        return null;
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
        return Optional.empty();
    }
}
