package br.com.gabrielgmusskopf.unisinos;

import br.com.gabrielgmusskopf.unisinos.comando.Comando;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleAbstrato;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleFactory;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.TipoArmazenamento;
import br.com.gabrielgmusskopf.unisinos.infra.cli.MainConsole;
import br.com.gabrielgmusskopf.unisinos.infra.Contexto;

public class Main {

    public static void main(String[] args) {
        //Injeção de dependências
        ContextoRepositorio.setTipoArmazenamento(TipoArmazenamento.MEMORIA);
        Contexto.setApp(ConsoleFactory.novo());

        Contexto.iniciar();
    }
}
