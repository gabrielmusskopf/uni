package br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.infra.Log;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.RepositorioCSV;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArquivoUsuarioRepositorio extends RepositorioCSV<Usuario> implements UsuarioRepositorio {

    private final List<Usuario> usuarios;

    public ArquivoUsuarioRepositorio() {
        usuarios = new ArrayList<>();
        inicializar();
        Log.debug("Repositorio CSV de usuário criado");
    }

    @Override
    protected String cabecalho() {
        return "id,nome";
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        buscarPorId(usuario.getId()).ifPresent(this::remover);
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
    public Optional<Usuario> buscarPorId(String s) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(s))
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
    protected void recuperarElemento(String[] valores) {
        usuarios.add(Usuario.recuperar(valores[0], valores[1]));
    }

    @Override
    protected List<?> escreverValores(Usuario usuario) {
        return List.of(usuario.getId(), usuario.getNome());
    }

    @Override
    public Optional<Usuario> buscarPorNome(String nome) {
        return usuarios.stream()
                .filter(u -> u.getNome().equals(nome))
                .findFirst();
    }

    @Override
    protected String caminhoData() {
        return "data/usuarios.csv";
    }

}
