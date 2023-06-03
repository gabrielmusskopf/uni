package br.com.gabrielgmusskopf.unisinos.dominio;

import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;
import br.com.gabrielgmusskopf.unisinos.infra.Log;

import java.util.ArrayList;
import java.util.List;

public class PedidoProcessador {

	private static final List<Thread> processos = new ArrayList<>();

	public void processar(Pedido pedido) {
        var t = new Thread(() -> {
			Log.debug("Processando o pedido " + pedido.getId());
			if (pedido.getEstado().getOrdem() == 1) {
				informar(pedido.getId() + " - " + pedido.getEstado());
				delay(1000);
			}

			if (pedido.getEstado().getOrdem() < 2) {
				pedido.processarPagamento();
				informar(pedido.getId() + " - " + pedido.getEstado());
				delay(5000);
			}

			if (pedido.getEstado().getOrdem() < 3) {
				pedido.aprovar();
				informar(pedido.getId() + " - " + pedido.getEstado());
				delay(5000);
			}

			if (pedido.getEstado().getOrdem() < 4) {
				pedido.preparar();
				informar(pedido.getId() + " - " + pedido.getEstado());
				delay(5000);
			}

			if (pedido.getEstado().getOrdem() < 5) {
				pedido.aguardandoCliente();
				informar(pedido.getId() + " - " + pedido.getEstado());
			}
			Log.debug("Pedido processado com sucesso " + pedido.getId());
        }, "PROCESSO-PEDIDO-" + pedido.getId());

		processos.add(t);
		t.start();
    }

	private void informar(Object o) {
		Log.debug(" PEDIDO PROCESSADOR: " + o);
	}

	private static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
          	Log.debug("Processamento de pedido interrompido");
        }
    }

	public static void pararProcessos() {
		processos.forEach(Thread::interrupt);
	}

}
