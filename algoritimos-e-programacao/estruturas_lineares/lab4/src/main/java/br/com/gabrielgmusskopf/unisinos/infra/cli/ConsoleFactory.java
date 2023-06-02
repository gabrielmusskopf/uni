package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.comando.App;

public class ConsoleFactory {

    public static App novo() {
        var manager = new ConsoleManager();
        manager.login();
        return manager;
    }

}
