package br.com.gabrielgmusskopf.unisinos;

import br.com.gabrielgmusskopf.unisinos.pedido.Pedido;

public class PedidoProcessador {

    public void processar(Pedido pedido) {
        new Thread(() -> {
            System.out.println(pedido);
            delay(3000);

            pedido.processarPagamento();
            System.out.println(pedido);
            delay(3000);

            pedido.aprovar();
            System.out.println(pedido);
            delay(3000);

            pedido.entregar();
            System.out.println(pedido);
            delay(3000);

            pedido.finalizar();
            System.out.println(pedido);
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
