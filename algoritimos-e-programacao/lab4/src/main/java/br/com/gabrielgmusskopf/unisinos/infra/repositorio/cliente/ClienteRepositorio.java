package br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente;

import br.com.gabrielgmusskopf.unisinos.dominio.Cliente;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.Repositorio;

import java.util.Optional;

public interface ClienteRepositorio extends Repositorio<String, Cliente> {

    Optional<Cliente> buscarPorNome(String nome);

}
