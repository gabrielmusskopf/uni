package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.comando.BuscarRestaurantesComando;
import br.com.gabrielgmusskopf.unisinos.comando.Comando;
import br.com.gabrielgmusskopf.unisinos.comando.NovoRestauranteComando;
import br.com.gabrielgmusskopf.unisinos.infra.Contexto;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class MainConsole extends ConsoleAbstrato {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void exibir(ConsoleManager manager) {
        System.out.println("|\tMenu\t|");
        Arrays.stream(Comando.values())
                .sorted(Comparator.comparing(Comando::getNumero))
                .forEach(c -> System.out.printf("%d %s\n", c.getNumero(), c.getDescricao()));

        int escolha = buscarInteiro();
        var opcao = Comando.numero(escolha);
        if (opcao == null) {
            System.out.println("Opção não encontrada...\n");
        }

        switch (opcao) {
            case NOVO_RESTAURANTE -> novoRestaurante();
            case ESCOLHER_RESTAURANTE -> exibirRestaurantes(manager);
            case CANCELAR -> Contexto.finalizar();
        }
    }

    private void novoRestaurante() {
        System.out.print("Nome do restaurante: ");
        var n = scanner.next();
        var r = new NovoRestauranteComando.NovoRestauranteInput(n.trim());
        var s = new NovoRestauranteComando(ContextoRepositorio.restauranteRepositorio()).criar(r);

        System.out.printf("Restaurante %s criado!\n\n", s.getNome());
    }

    private void exibirRestaurantes(ConsoleManager manager) {

        var restaurantes = new BuscarRestaurantesComando(ContextoRepositorio.restauranteRepositorio()).buscar();

        if (restaurantes.isEmpty()) {
            System.out.println("Sem restaurantes...");
            return;
        }

        var opcoes = montarOpcoes(restaurantes);

        System.out.println("Restaurantes:");
        opcoes.forEach((k, v) -> System.out.printf("%d - %s", k, v.getNome()));

        var escolhido = selecionarOpcao(opcoes);
        manager.restaurante(escolhido);
    }

}
