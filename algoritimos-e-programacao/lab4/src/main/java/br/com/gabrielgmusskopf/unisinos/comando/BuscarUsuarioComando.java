package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.UsuarioRepositorio;

import java.util.Optional;

public class BuscarUsuarioComando {

    private final UsuarioRepositorio usuarioRepositorio;

    public BuscarUsuarioComando(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Optional<Usuario> buscar(String nome) {
        return usuarioRepositorio.buscarPorNome(nome);
    }

}
