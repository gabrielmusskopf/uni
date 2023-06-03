package br.com.gabrielgmusskopf.unisinos.dominio;

import br.com.gabrielgmusskopf.unisinos.dominio.execao.IngredientesInsuficientesException;
import br.com.gabrielgmusskopf.unisinos.dominio.execao.RestauranteException;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.EstadoPedido;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;
import br.com.gabrielgmusskopf.unisinos.util.Mocked;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {

    @Test
    void deve_instanciar_um_restaurante() {
        var r = new Restaurante("Nome", new Estoque());
        assertNotNull(r);
    }

    @Test
    void nao_deve_cadastrar_pedido_quando_ingredientes_insuficientes() {
        var r = new Restaurante("Nome", new Estoque());

        var err = assertThrows(IngredientesInsuficientesException.class, () -> r.novoPedido(Mocked.pedido()));

        assertEquals("Ingredientes insuficientes.", err.getMessage());
    }

    @Test
    void deve_cadastrar_pedido() {
        var e = new Estoque();
        e.abastecer("cafe");

        var r = new Restaurante("Nome", e);
        var pr = new Produto("Café", 2, List.of("cafe"));
        var pe = new Pedido(Mocked.usuario(), pr);

        r.novoPedido(pe);

        var ing = e.getIngredientes();
        assertEquals(0, ing.get("cafe"));
        assertEquals(1, r.getPedidos().size());
        assertEquals(pe, r.getPedidos().peek());
    }

    @Test
    void nao_deve_retirar_pedido_quando_nao_tem() {
        var r = new Restaurante("Nome", new Estoque());

        var err = assertThrows(RestauranteException.class, r::retirarPedido);

        assertEquals("Nenhum pedido pronto para retirada", err.getMessage());
    }

    @Test
    void nao_deve_retirar_pedido_quando_nao_tem_com_status_correto() {
        var e = new Estoque();
        e.abastecer("cafe");

        var r = new Restaurante("Nome", e);
        var p = Pedido.recuperar(UUID.randomUUID().toString(), Mocked.usuario(), 2, List.of(), "PREPARANDO");

        r.novoPedido(p);

        var err = assertThrows(RestauranteException.class, r::retirarPedido);

        assertEquals("Nenhum pedido pronto para retirada", err.getMessage());
    }

    @Test
    void deve_retirar_pedido() {
        var e = new Estoque();
        e.abastecer("cafe");

        var r = new Restaurante("Nome", e);
        var p = Pedido.recuperar(UUID.randomUUID().toString(), Mocked.usuario(), 2, List.of(), "AGUARDANDO_CLIENTE");

        r.novoPedido(p);
        r.retirarPedido();

        assertTrue(r.getPedidos().isEmpty());
    }

}
