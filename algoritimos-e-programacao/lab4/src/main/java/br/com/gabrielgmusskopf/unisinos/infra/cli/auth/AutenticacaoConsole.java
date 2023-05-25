package br.com.gabrielgmusskopf.unisinos.infra.cli.auth;

import br.com.gabrielgmusskopf.unisinos.comando.BuscarUsuarioComando;
import br.com.gabrielgmusskopf.unisinos.comando.CadastrarUsuarioComando;
import br.com.gabrielgmusskopf.unisinos.dominio.execao.UsuarioException;
import br.com.gabrielgmusskopf.unisinos.infra.AutenticacaoContexto;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleAbstrato;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleManager;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;

import java.util.Arrays;
import java.util.Comparator;

public class AutenticacaoConsole extends ConsoleAbstrato {

    public AutenticacaoConsole(ConsoleManager consoleManager) {
        super(consoleManager);
    }

    @Override
    public void exibir() {
        System.out.println("=== Unisinos KDS ===");

        Arrays.stream(AuthComandoCli.values())
                .sorted(Comparator.comparing(AuthComandoCli::getNumero))
                .forEach(c -> System.out.printf("%d %s\n", c.getNumero(), c.getDescricao()));

        int escolha = buscarInteiro();
        var opcao = AuthComandoCli.numero(escolha);
        if (opcao == null) {
            System.out.println("Opção não encontrada...\n");
            return;
        }

        switch (opcao) {
            case ENTRAR -> entrar();
            case CADASTRAR -> cadastrar();
        }

    }

    private void entrar() {
        System.out.print("Nome: ");
        var nome = scanner.next();

        var usuario = new BuscarUsuarioComando(ContextoRepositorio.usuarioRepositorio()).buscar(nome);
        if (usuario.isEmpty()) {
            System.out.println("\nUsuário não encontrado\n");
            return;
        }
        var u = usuario.get();
        System.out.printf("\nBem vindo de volta, %s!\n", u.getNome());
        AutenticacaoContexto.autenticar(u);

        consoleManager.home();
    }

    private void cadastrar() {
        try {
            System.out.print("Nome: ");
            var n = scanner.next();
            var u = new CadastrarUsuarioComando(ContextoRepositorio.usuarioRepositorio())
                    .cadastrar(n);

            System.out.printf("\nBem vindo, %s!\n", u.getNome());
            AutenticacaoContexto.autenticar(u);

            consoleManager.home();
        } catch (UsuarioException ue) {
            System.out.printf("%n%s%n", ue.getMessage());
        }
    }

}
