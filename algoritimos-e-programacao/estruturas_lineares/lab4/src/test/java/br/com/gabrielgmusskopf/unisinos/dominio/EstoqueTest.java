package br.com.gabrielgmusskopf.unisinos.dominio;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstoqueTest {

    @Test
    void deve_adicionar_item_novo_ao_estoque() {
        var e = new Estoque();

        e.abastecer("cafe");

        var i = e.getIngredientes();
        assertEquals(1, i.size());
        assertEquals(1, i.get("cafe"));
    }

    @Test
    void deve_adicionar_item_repetido_ao_estoque() {
        var e = new Estoque();

        e.abastecer("cafe");
        e.abastecer("cafe");

        var i = e.getIngredientes();
        assertEquals(1, i.size());
        assertEquals(2, i.get("cafe"));
    }

    @Test
    void deve_validar_se_contem_item() {
        var e = new Estoque();

        e.abastecer("cafe");

        assertTrue(e.contem(List.of("cafe")));
    }

    @Test
    void deve_validar_se_contem_item_ao_remover() {
        var e = new Estoque();

        e.abastecer("cafe");
        assertTrue(e.contem(List.of("cafe")));

        e.retirar(List.of("cafe"));
        assertFalse(e.contem(List.of("cafe")));
    }

}
