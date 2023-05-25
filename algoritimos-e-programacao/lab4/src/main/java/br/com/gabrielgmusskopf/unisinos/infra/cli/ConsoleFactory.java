package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.comando.App;

public class ConsoleFactory {

    public static App novo() {
        return new ConsoleManager(new AutenticacaoConsole());
    }

}
