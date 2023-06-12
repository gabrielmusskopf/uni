package br.com.gabrielgmusskopf.unisinos;

import br.com.gabrielgmusskopf.unisinos.infra.Contexto;
import br.com.gabrielgmusskopf.unisinos.infra.Log;
import br.com.gabrielgmusskopf.unisinos.infra.LogLevel;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleFactory;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.TipoArmazenamento;

public class Main {

    public static void main(String[] args) {
        TipoArmazenamento tipoArmazenamento = TipoArmazenamento.ARQUIVO;

        for (String s : args) {
            var arg = s.split(" ");
            if ("-d".equals(arg[0]) || "--debug".equals(arg[0])) Log.setLevel(LogLevel.DEBUG);
            if ("-a".equals(arg[0]) || "--armazenamento".equals(arg[0])) tipoArmazenamento = "arquivo".equalsIgnoreCase(arg[1])
                    ? TipoArmazenamento.ARQUIVO
                    : TipoArmazenamento.MEMORIA;
        }

        Log.debug("Tipo armazenamento: " + tipoArmazenamento);
        ContextoRepositorio.bootstrap(tipoArmazenamento);
        Contexto.iniciar(ConsoleFactory.novo());
    }

}
