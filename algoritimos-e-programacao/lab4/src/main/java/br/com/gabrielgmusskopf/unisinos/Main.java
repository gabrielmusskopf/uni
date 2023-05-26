package br.com.gabrielgmusskopf.unisinos;

import br.com.gabrielgmusskopf.unisinos.infra.Contexto;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleFactory;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.TipoArmazenamento;

public class Main {

    public static void main(String[] args) {
        ContextoRepositorio.bootstrap(TipoArmazenamento.ARQUIVO);
        Contexto.setApp(ConsoleFactory.novo());

        Contexto.iniciar();
    }

    //TODO: Filtrar comandos por perfil
}
