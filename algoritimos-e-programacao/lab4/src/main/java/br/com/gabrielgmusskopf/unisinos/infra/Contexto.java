package br.com.gabrielgmusskopf.unisinos.infra;

import br.com.gabrielgmusskopf.unisinos.comando.App;
import br.com.gabrielgmusskopf.unisinos.comando.Comando;

import java.util.List;

public class Contexto {

    private static boolean executando;
    private static App app;

    public static void setApp(App a) {
        app = a;
    }

    public static void iniciar() {
        if (app == null) {
            throw new IllegalArgumentException();
        }
        executando = true;

       while (executando) {
           app.disponibilizar();
       }

    }

    public static void finalizar() {
        executando = false;
    }

    public static boolean executando() {
        return executando;
    }

}
