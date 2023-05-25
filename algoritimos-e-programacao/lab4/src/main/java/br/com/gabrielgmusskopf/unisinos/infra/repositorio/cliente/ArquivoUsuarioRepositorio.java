package br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;

import java.util.List;
import java.util.Optional;

public class ArquivoUsuarioRepositorio implements UsuarioRepositorio {
    @Override
    public Usuario salvar(Usuario usuario) {
        return null;
    }

    @Override
    public Optional<Usuario> buscar(Usuario usuario) {
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
        return Optional.empty();
    }
}
