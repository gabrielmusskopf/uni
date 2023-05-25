package br.com.gabrielgmusskopf.unisinos.infra.cli.auth;

import br.com.gabrielgmusskopf.unisinos.comando.BuscarClienteComando;
import br.com.gabrielgmusskopf.unisinos.dominio.Cliente;
import br.com.gabrielgmusskopf.unisinos.infra.AutenticacaoContexto;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleAbstrato;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleManager;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;

import java.util.Optional;

public class AutenticacaoConsole extends ConsoleAbstrato {

    public AutenticacaoConsole(ConsoleManager consoleManager) {
        super(consoleManager);
    }

    @Override
    public void exibir() {
        System.out.println("**** Unisinos KDS ****");

        Optional<Cliente> cliente;
        do {
            System.out.println("Nome: ");
            var nome = scanner.next();

            cliente = new BuscarClienteComando(ContextoRepositorio.clienteRepositorio()).buscar(nome);
        } while (cliente.isEmpty());

        var c = cliente.get();
        System.out.printf("\nBem vindo de volta, %s!\n", c.getNome());
        AutenticacaoContexto.autenticar(c);

        consoleManager.home();
    }

}
