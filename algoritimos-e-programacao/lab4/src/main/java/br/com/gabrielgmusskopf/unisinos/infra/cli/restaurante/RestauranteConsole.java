package br.com.gabrielgmusskopf.unisinos.infra.cli.restaurante;

import br.com.gabrielgmusskopf.unisinos.comando.BuscarProdutosComando;
import br.com.gabrielgmusskopf.unisinos.comando.NovoPedidoComando;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.Contexto;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleAbstrato;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleManager;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;

public class RestauranteConsole extends ConsoleAbstrato {

    private final Restaurante restaurante;

    public RestauranteConsole(Restaurante restaurante, ConsoleManager consoleManager) {
        super(consoleManager);
        this.restaurante = restaurante;
    }

    @Override
   public void exibir() {
        System.out.printf("\n---- %s ----%n", restaurante.getNome());
        exibirOpcoes(RestauranteComandoCli.values());

        int escolha = buscarInteiro();
        var opcao = RestauranteComandoCli.numero(escolha);
        if (opcao == null) {
            System.out.println("Opção não encontrada...\n");
            consoleManager.home();
            return;
        }

       switch (opcao) {
           case CANCELAR -> Contexto.finalizar();
           case NOVO_PEDIDO -> novoPedido();
           case VOLTAR -> consoleManager.voltar();
       }
   }

    private void novoPedido() {
        var produtos = new BuscarProdutosComando(ContextoRepositorio.produtoRepositorio()).buscar();
        var opcoes = montarOpcoes(produtos);
        if (opcoes.isEmpty()) {
            System.out.println("\nSem produtos no momento...");
            return;
        }

        System.out.print("\nNome do cliente: ");
        var nome = scanner.next();

        System.out.print("Produtos(para múltiplos, ponha os números separados por espaço): ");
        opcoes.forEach((k, v) -> System.out.printf("%d - %s", k, v));

        var produtosEscolhidos = selecionarOpcoes(opcoes);

        var input = new NovoPedidoComando.NovoPedidoInput(nome, produtosEscolhidos);
        new NovoPedidoComando(ContextoRepositorio.pedidoRepositorio(), ContextoRepositorio.clienteRepositorio())
                .criar(input);

        System.out.print("Pedido criado!\n\n");
    }

}
