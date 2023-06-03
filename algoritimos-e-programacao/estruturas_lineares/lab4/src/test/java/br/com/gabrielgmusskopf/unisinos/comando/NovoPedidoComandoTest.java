package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.PedidoProcessador;
import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.dominio.execao.RestauranteException;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.PedidoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;
import br.com.gabrielgmusskopf.unisinos.util.Mocked;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.VerificationCollector;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class NovoPedidoComandoTest {

    @InjectMocks
    private NovoPedidoComando testado;

    @Mock
    private PedidoRepositorio pedidoRepositorio;

    @Mock
    private RestauranteRepositorio restauranteRepositorio;

    @Mock
    private PedidoProcessador pedidoProcessador;

    @Test
    void nao_deve_cadastrar_um_pedido_com_ingredientes_insuficientes() {
        var usuario = Mocked.usuario();
        var restaurante = Mocked.restaurante();
        var produtos = List.of(
                new Produto("Café", 2, List.of("cafe"))
        );

        var err = assertThrows(RestauranteException.class, () -> testado.criar(usuario, restaurante, produtos));

        assertEquals("Ingredientes insuficientes.", err.getMessage());
        verifyNoInteractions(pedidoProcessador);
        verifyNoInteractions(pedidoRepositorio);
        verifyNoInteractions(restauranteRepositorio);
    }

    @Test
    void deve_cadastrar_um_pedido() {
        var usuario = Mocked.usuario();
        var restaurante = Mocked.restaurante();
        restaurante.getEstoque().abastecer("cafe");
        var produtos = List.of(
                new Produto("Café", 2, List.of("cafe"))
        );

        var pedido = testado.criar(usuario, restaurante, produtos);

        var pedidos = restaurante.getPedidos();
        assertEquals(1, pedidos.size());
        assertEquals(pedido, pedidos.peek());

        verify(pedidoProcessador).processar(pedido);
        verify(pedidoRepositorio).salvar(pedido);
        verify(restauranteRepositorio).salvar(restaurante);
    }

}
