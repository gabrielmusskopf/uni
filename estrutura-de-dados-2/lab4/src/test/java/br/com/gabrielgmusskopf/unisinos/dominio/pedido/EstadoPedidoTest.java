package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

import br.com.gabrielgmusskopf.unisinos.dominio.execao.EstadoPedidoInvalidoException;
import br.com.gabrielgmusskopf.unisinos.util.Mocked;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EstadoPedidoTest {

    @Test
    void deve_validar_estado_partindo_de_criado() {
        var estado = new PedidoCriado(Mocked.pedido());

        assertEstado(estado::aprovar);
        assertEstado(estado::criar);
        assertEstado(estado::finalizar);
        assertEstado(estado::preparar);
        assertEstado(estado::aguardandoCliente);
        assertDoesNotThrow(estado::processarPagamento);
        assertDoesNotThrow(estado::cancelar);
    }

    @Test
    void deve_validar_estado_partindo_de_processando_pagamento() {
        var estado = new PedidoProcessandoPagamento(Mocked.pedido());

        assertEstado(estado::processarPagamento);
        assertEstado(estado::criar);
        assertEstado(estado::finalizar);
        assertEstado(estado::preparar);
        assertEstado(estado::aguardandoCliente);
        assertDoesNotThrow(estado::aprovar);
        assertDoesNotThrow(estado::cancelar);
    }

    @Test
    void deve_validar_estado_partindo_de_aprovado() {
        var estado = new PedidoAprovado(Mocked.pedido());

        assertEstado(estado::processarPagamento);
        assertEstado(estado::criar);
        assertEstado(estado::finalizar);
        assertEstado(estado::aprovar);
        assertEstado(estado::aguardandoCliente);
        assertEstado(estado::cancelar);
        assertDoesNotThrow(estado::preparar);
    }

    @Test
    void deve_validar_estado_partindo_de_preparando() {
        var estado = new PedidoPreparando(Mocked.pedido());

        assertEstado(estado::processarPagamento);
        assertEstado(estado::criar);
        assertEstado(estado::finalizar);
        assertEstado(estado::aprovar);
        assertEstado(estado::preparar);
        assertEstado(estado::cancelar);
        assertDoesNotThrow(estado::aguardandoCliente);
    }

    @Test
    void deve_validar_estado_partindo_de_aguardando_cliente() {
        var estado = new PedidoAguardandoCliente(Mocked.pedido());

        assertEstado(estado::processarPagamento);
        assertEstado(estado::criar);
        assertEstado(estado::aguardandoCliente);
        assertEstado(estado::aprovar);
        assertEstado(estado::preparar);
        assertEstado(estado::cancelar);
        assertDoesNotThrow(estado::finalizar);
    }

    @Test
    void deve_validar_estado_partindo_de_finalizado() {
        var estado = new PedidoFinalizado(Mocked.pedido());

        assertEstado(estado::processarPagamento);
        assertEstado(estado::criar);
        assertEstado(estado::aguardandoCliente);
        assertEstado(estado::aprovar);
        assertEstado(estado::preparar);
        assertEstado(estado::cancelar);
        assertEstado(estado::finalizar);
    }

    private void assertEstado(Executable r){
        assertThrows(EstadoPedidoInvalidoException.class, r);
    }

}
