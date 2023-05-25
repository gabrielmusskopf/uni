package br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ArquivoRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArquivoUsuarioRepositorio extends ArquivoRepositorio implements UsuarioRepositorio {

    private final List<Usuario> usuarios;

    public ArquivoUsuarioRepositorio() {
        usuarios = new ArrayList<>();
        carregar(usuarios);
        escreverAoFinal();
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    @Override
    public Optional<Usuario> buscar(Usuario usuario) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(usuario.getId()))
                .findFirst();
    }

    @Override
    public void remover(Usuario usuario) {
        usuarios.remove(usuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarios;
    }

    @Override
    public Optional<Usuario> buscarPorNome(String nome) {
        return usuarios.stream()
                .filter(u -> u.getNome().equals(nome))
                .findFirst();
    }

    @Override
    protected String caminhoData() {
        return "data/usuarios.ser";
    }
}
