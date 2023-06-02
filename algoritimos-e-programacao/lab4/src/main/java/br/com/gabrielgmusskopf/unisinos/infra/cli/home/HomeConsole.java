package br.com.gabrielgmusskopf.unisinos.infra.cli.home;

import br.com.gabrielgmusskopf.unisinos.comando.BuscarPedidosComando;
import br.com.gabrielgmusskopf.unisinos.comando.BuscarRestaurantesComando;
import br.com.gabrielgmusskopf.unisinos.comando.NovoRestauranteComando;
import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.infra.AutenticacaoContexto;
import br.com.gabrielgmusskopf.unisinos.infra.Contexto;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleAbstrato;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleManager;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;

import java.util.stream.Collectors;

public class HomeConsole extends ConsoleAbstrato {

    public HomeConsole(ConsoleManager consoleManager) {
        super(consoleManager);
    }

    @Override
    public void exibir() {
        System.out.println("\n----Menu----");
        var opcoes = exibirOpcoes(HomeComandoCli.values());

        var opcao = opcoes.get(buscarInteiro());
        if (opcao == null) {
            System.out.println("Opção não encontrada...\n");
            consoleManager.home();
            return;
        }

        switch (opcao) {
            case NOVO_RESTAURANTE -> novoRestaurante();
            case ESCOLHER_RESTAURANTE -> exibirRestaurantes();
            case MEUS_PEDIDOS -> exibirPedidos();
            case CANCELAR -> Contexto.finalizar();
        }
    }

    private void novoRestaurante() {
        System.out.print("\nNome do restaurante: ");
        var n = scanner.next();
        var s = new NovoRestauranteComando(ContextoRepositorio.restauranteRepositorio(), ContextoRepositorio.estoqueRepositorio())
                .criar(n.trim());

        System.out.printf("Restaurante %s criado!%n", s.getNome());
    }

    private void exibirRestaurantes() {
        var restaurantes = new BuscarRestaurantesComando(ContextoRepositorio.restauranteRepositorio()).buscar();
        if (restaurantes.isEmpty()) {
            System.out.println("\nSem restaurantes...");
            return;
        }

        var opcoes = montarOpcoes(restaurantes);

        System.out.println("\nRestaurantes:");
        opcoes.forEach((k, v) -> System.out.printf("%d - %s%n", k, v.getNome()));

        var escolhido = selecionarOpcao(opcoes);
        consoleManager.restaurante(escolhido);
    }

    private void exibirPedidos() {
        var pedidos = new BuscarPedidosComando(ContextoRepositorio.pedidoRepositorio())
                .buscar(AutenticacaoContexto.getAutenticado());
        if (pedidos.isEmpty()){
            System.out.println("\nSem pedidos por enquanto.");
            return;
        }

        System.out.println("\tPedidos\t");
        pedidos.forEach(p -> {
            var itens = p.getProdutos().stream()
                    .map(Produto::getNome)
                    .map(nome -> String.format("- %s\n", nome))
                    .collect(Collectors.joining());

            System.out.printf("[%s] - R$%.2f\n%s\n", p.getEstado(), p.getCusto(), itens);
        });
    }

}
