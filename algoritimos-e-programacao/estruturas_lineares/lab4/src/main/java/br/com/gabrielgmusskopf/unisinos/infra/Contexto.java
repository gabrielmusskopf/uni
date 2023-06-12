package br.com.gabrielgmusskopf.unisinos.infra;

import br.com.gabrielgmusskopf.unisinos.comando.App;
import br.com.gabrielgmusskopf.unisinos.dominio.PedidoProcessador;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;

import java.util.concurrent.atomic.AtomicBoolean;

public class Contexto {

    private static final AtomicBoolean executando = new AtomicBoolean();

    public static void iniciar(App app) {
        if (app == null) {
            throw new IllegalArgumentException("Um App deve ser informado");
        }

        executando.set(true);

        while (executando.get()) {
            app.disponibilizar();
        }

    }

    public static void finalizar() {
        ContextoRepositorio.armazenar();
        PedidoProcessador.pararProcessos();
        executando.set(false);
    }

    private Contexto(){}

}
