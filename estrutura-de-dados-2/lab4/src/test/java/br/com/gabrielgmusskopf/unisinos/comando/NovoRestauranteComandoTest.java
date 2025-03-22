package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Estoque;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.estoque.EstoqueRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NovoRestauranteComandoTest {

    @InjectMocks
    private NovoRestauranteComando testado;

    @Mock
    private RestauranteRepositorio restauranteRepositorio;

    @Mock
    private EstoqueRepositorio estoqueRepositorio;

    @Test
    void deve_criar_restaurante() {
        var nome = "Nome";

        var r = testado.criar(nome);

        verify(restauranteRepositorio).salvar(r);
        verify(estoqueRepositorio).salvar(any(Estoque.class));
    }
}
