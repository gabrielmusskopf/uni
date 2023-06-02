package br.com.gabrielgmusskopf.unisinos;

import br.com.gabrielgmusskopf.unisinos.infra.Contexto;
import br.com.gabrielgmusskopf.unisinos.infra.Log;
import br.com.gabrielgmusskopf.unisinos.infra.LogLevel;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleFactory;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.TipoArmazenamento;

public class Main {

    public static void main(String[] args) {
		for (String arg : args) {
            if ("-d".equals(arg) || "--debug".equals(arg)) Log.setLevel(LogLevel.DEBUG);
		}

        ContextoRepositorio.bootstrap(TipoArmazenamento.ARQUIVO);
        Contexto.iniciar(ConsoleFactory.novo());
    }

}
