package br.com.gabrielgmusskopf.unisinos.dominio;

import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;

public class PedidoProcessador {

    public void processar(Pedido pedido) {
        new Thread(() -> {
            delay(5000);

            pedido.processarPagamento();
            delay(5000);

            pedido.aprovar();
            delay(5000);

            pedido.entregar();
            delay(3000);

            pedido.aguardandoCliente();
        }).start();
    }

    private static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
