package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.comando.BuscarRestaurantesComando;
import br.com.gabrielgmusskopf.unisinos.comando.NovoRestauranteComando;
import br.com.gabrielgmusskopf.unisinos.infra.Contexto;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;

import java.util.Arrays;
import java.util.Comparator;

public class MainConsole extends ConsoleAbstrato {

    protected MainConsole(ContextoRepositorio ctxRepositorio, ConsoleManager consoleManager) {
        super(ctxRepositorio, consoleManager);
    }

    @Override
    public void exibir() {
        System.out.println("----Menu----");
        Arrays.stream(HomeComando.values())
                .sorted(Comparator.comparing(HomeComando::getNumero))
                .forEach(c -> System.out.printf("%d %s\n", c.getNumero(), c.getDescricao()));

        int escolha = buscarInteiro();
        var opcao = HomeComando.numero(escolha);
        if (opcao == null) {
            System.out.println("Opção não encontrada...\n");
            consoleManager.home();
            return;
        }

        switch (opcao) {
            case NOVO_RESTAURANTE -> novoRestaurante();
            case ESCOLHER_RESTAURANTE -> exibirRestaurantes();
            case CANCELAR -> Contexto.finalizar();
        }
    }

    private void novoRestaurante() {
        System.out.print("Nome do restaurante: ");
        var n = scanner.next();
        var r = new NovoRestauranteComando.NovoRestauranteInput(n.trim());
        var s = new NovoRestauranteComando(ctxRepositorio.restauranteRepositorio()).criar(r);

        System.out.printf("Restaurante %s criado!\n\n", s.getNome());
    }

    private void exibirRestaurantes() {

        var restaurantes = new BuscarRestaurantesComando(ctxRepositorio.restauranteRepositorio()).buscar();

        if (restaurantes.isEmpty()) {
            System.out.println("Sem restaurantes...");
            return;
        }

        var opcoes = montarOpcoes(restaurantes);

        System.out.println("Restaurantes:");
        opcoes.forEach((k, v) -> System.out.printf("%d - %s%n", k, v.getNome()));

        var escolhido = selecionarOpcao(opcoes);
        consoleManager.restaurante(escolhido);
    }

}
