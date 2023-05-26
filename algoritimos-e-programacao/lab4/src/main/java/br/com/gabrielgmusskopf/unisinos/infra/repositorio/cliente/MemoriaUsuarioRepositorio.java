package br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MemoriaUsuarioRepositorio implements UsuarioRepositorio {

    private List<Usuario> clientesMemoria = new LinkedList<>();

    @Override
    public Usuario salvar(Usuario usuario) {
        clientesMemoria.add(usuario);
        return usuario;
    }

    @Override
    public Optional<Usuario> buscar(Usuario usuario) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> buscarPorId(String s) {
        return Optional.empty();
    }

    @Override
    public void remover(Usuario usuario) {

    }

    @Override
    public List<Usuario> buscarTodos() {
        return null;
    }

    @Override
    public Optional<Usuario> buscarPorNome(String nome) {
        return clientesMemoria.stream()
                .filter(u -> u.getNome().equals(nome))
                .findFirst();
    }
}
