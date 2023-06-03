package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto.ProdutoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;
import br.com.gabrielgmusskopf.unisinos.util.Mocked;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NovoProdutoComandoTest {

    @InjectMocks
    private NovoProdutoComando testado;

    @Mock
    private RestauranteRepositorio restauranteRepositorio;

    @Mock
    private ProdutoRepositorio produtoRepositorio;

    @Test
    void deve_criar_produto() {
        var restaurante = Mocked.restaurante();
        var produto = Mocked.produto();

        testado.adicionar(restaurante, produto);

        verify(restauranteRepositorio).salvar(restaurante);
        verify(produtoRepositorio).salvar(produto);
    }

}
