package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.dominio.Cliente;
import br.com.gabrielgmusskopf.unisinos.infra.AutenticacaoContexto;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;

import java.util.Optional;

public class AutenticacaoConsole extends ConsoleAbstrato {

    @Override
    public void exibir(ConsoleManager manager) {
        System.out.println("--** Unisinos KDS **--\n");

        Optional<Cliente> cliente;
        do {
            System.out.println("Nome: ");
            var nome = scanner.next();

            cliente = ContextoRepositorio
                    .clienteRepositorio()
                    .buscarPorNome(nome);
        } while (cliente.isEmpty());

        System.out.println("Bem vindo!\n");
        AutenticacaoContexto.autenticar(cliente.get());
        manager.home();
    }

}
