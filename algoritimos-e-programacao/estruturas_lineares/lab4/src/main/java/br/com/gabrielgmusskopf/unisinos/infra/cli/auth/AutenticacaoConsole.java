package br.com.gabrielgmusskopf.unisinos.infra.cli.auth;

import br.com.gabrielgmusskopf.unisinos.comando.BuscarUsuarioComando;
import br.com.gabrielgmusskopf.unisinos.comando.CadastrarUsuarioComando;
import br.com.gabrielgmusskopf.unisinos.dominio.execao.UsuarioException;
import br.com.gabrielgmusskopf.unisinos.infra.AutenticacaoContexto;
import br.com.gabrielgmusskopf.unisinos.infra.Contexto;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleAbstrato;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleManager;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;
import io.leego.banana.Ansi;
import io.leego.banana.BananaUtils;
import io.leego.banana.Font;

public class AutenticacaoConsole extends ConsoleAbstrato {

    public AutenticacaoConsole(ConsoleManager consoleManager) {
        super(consoleManager);
    }

    @Override
    public void exibir() {
        System.out.println(BananaUtils.bananansi("Unisinos KDS", Font.LARRY_3D, Ansi.BOLD, Ansi.PURPLE));
        var opcoes = exibirOpcoes(AuthComandoCli.values());

        var opcao = opcoes.get(buscarInteiro());
        if (opcao == null) {
            System.out.println("Opção não encontrada...\n");
            return;
        }

        switch (opcao) {
            case ENTRAR -> entrar();
            case CADASTRAR -> cadastrar();
			case SAIR -> Contexto.finalizar();
        }

    }

    private void entrar() {
        System.out.print("\nNome: ");
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
            System.out.print("\nNome: ");
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
