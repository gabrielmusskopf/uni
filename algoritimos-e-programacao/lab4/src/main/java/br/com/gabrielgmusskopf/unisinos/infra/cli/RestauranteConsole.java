package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.comando.BuscarProdutosComando;
import br.com.gabrielgmusskopf.unisinos.comando.NovoPedidoComando;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.Contexto;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;

public class RestauranteConsole extends ConsoleAbstrato {

    private final Restaurante restaurante;

    protected RestauranteConsole(Restaurante restaurante, ContextoRepositorio ctxRepositorio, ConsoleManager consoleManager) {
        super(ctxRepositorio, consoleManager);
        this.restaurante = restaurante;
    }

    @Override
   public void exibir() {
        System.out.printf("---- %s ----%n", restaurante.getNome());
        exibirOpcoes(RestauranteComando.values());

        int escolha = buscarInteiro();
        var opcao = RestauranteComando.numero(escolha);
        if (opcao == null) {
            System.out.println("Opção não encontrada...\n");
            consoleManager.home();
            return;
        }

       switch (opcao) {
           case CANCELAR -> Contexto.finalizar();
           case NOVO_PEDIDO -> novoPedido();
       }
   }

    private void novoPedido() {
        System.out.print("Nome do cliente: ");
        var nome = scanner.next();

        var produtos = new BuscarProdutosComando(ctxRepositorio.produtoRepositorio()).buscar();
        var opcoes = montarOpcoes(produtos);
        if (opcoes.isEmpty()) {
            System.out.println("Sem produtos no momento...");
            return;
        }

        System.out.print("Produtos(para múltiplos, ponha os números separados por espaço): ");
        opcoes.forEach((k, v) -> System.out.printf("%d - %s", k, v));

        var produtosEscolhidos = selecionarOpcoes(opcoes);

        var input = new NovoPedidoComando.NovoPedidoInput(nome, produtosEscolhidos);
        new NovoPedidoComando(ctxRepositorio.pedidoRepositorio(), ctxRepositorio.clienteRepositorio())
                .criar(input);

        System.out.print("Pedido criado!\n\n");
    }

}
