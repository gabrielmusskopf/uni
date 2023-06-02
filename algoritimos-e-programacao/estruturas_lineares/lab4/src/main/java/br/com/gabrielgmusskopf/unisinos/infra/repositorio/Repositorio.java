package br.com.gabrielgmusskopf.unisinos.infra.repositorio;

import java.util.List;
import java.util.Optional;

public interface Repositorio<ID, T> {

    T salvar(T t);

    Optional<T> buscar(T t);

    Optional<T> buscarPorId(ID id);

    void remover(T t);

    List<T> buscarTodos();
}
