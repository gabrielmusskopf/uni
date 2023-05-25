package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.comando.App;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.TipoArmazenamento;

public class ConsoleFactory {

    public static App novo(TipoArmazenamento armazenamento) {
        var manager = new ConsoleManager(new ContextoRepositorio(armazenamento));
        manager.login();
        return manager;
    }

}
