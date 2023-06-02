package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.dominio.execao.UsuarioException;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.UsuarioRepositorio;

public class CadastrarUsuarioComando {

    private final UsuarioRepositorio usuarioRepositorio;

    public CadastrarUsuarioComando(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Usuario cadastrar(String nome) {
        var existente = usuarioRepositorio.buscarPorNome(nome);
        if (existente.isPresent()) {
            throw new UsuarioException("Nome já cadastrado");
        }

        var u = new Usuario(nome);
        usuarioRepositorio.salvar(u);

        return u;
    }

}
