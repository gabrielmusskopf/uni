package br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.Repositorio;

import java.util.Optional;

public interface UsuarioRepositorio extends Repositorio<String, Usuario> {

    Optional<Usuario> buscarPorNome(String nome);

}
