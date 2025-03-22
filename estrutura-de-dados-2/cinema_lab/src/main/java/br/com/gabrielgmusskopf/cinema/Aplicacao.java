package br.com.gabrielgmusskopf.cinema;

public class Aplicacao {

    private static boolean executando;

    public static void iniciar() {
        executando = true;
    }

    public static void finalizar() {
        executando = false;
    }

    public static boolean isExecutando() {
        return executando;
    }

}
