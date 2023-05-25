package br.com.gabrielgmusskopf.unisinos.infra.repositorio;

import br.com.gabrielgmusskopf.unisinos.dominio.Produto;

import java.util.List;
import java.util.Optional;

public interface Repositorio<ID, T> {

    T salvar(T t);

    Optional<T> buscar(T t);

    void remover(T t);

    List<T> buscarTodos();
}
