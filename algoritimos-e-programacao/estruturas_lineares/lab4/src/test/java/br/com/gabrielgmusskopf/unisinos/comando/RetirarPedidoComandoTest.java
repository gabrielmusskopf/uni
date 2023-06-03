package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.pedido.EstadoPedido;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.PedidoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;
import br.com.gabrielgmusskopf.unisinos.util.Mocked;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RetirarPedidoComandoTest {

    @InjectMocks
    private RetirarPedidoComando testado;

   @Mock
   private RestauranteRepositorio restauranteRepositorio;

    @Mock
    private PedidoRepositorio pedidoRepositorio;

    @Test
    void deve_retirar_pedido() {
        var restaurante = Mocked.restaurante();
        restaurante.getEstoque().abastecer("cafe");
        var pedido = Pedido.recuperar(UUID.randomUUID().toString(), Mocked.usuario(), 2, List.of(Mocked.produto()), "AGUARDANDO_CLIENTE");
        restaurante.novoPedido(pedido);

        testado.retirar(restaurante);

        verify(restauranteRepositorio).salvar(restaurante);
        verify(pedidoRepositorio).salvar(pedido);
    }

}
