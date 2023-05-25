package br.com.gabrielgmusskopf.unisinos.infra.cli.restaurante;

import br.com.gabrielgmusskopf.unisinos.comando.NovoPedidoComando;
import br.com.gabrielgmusskopf.unisinos.comando.RetirarPedidoComando;
import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.dominio.execao.RestauranteException;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;
import br.com.gabrielgmusskopf.unisinos.infra.AutenticacaoContexto;
import br.com.gabrielgmusskopf.unisinos.infra.Contexto;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleAbstrato;
import br.com.gabrielgmusskopf.unisinos.infra.cli.ConsoleManager;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;

import java.util.stream.Collectors;

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
           case VER_PRODUTOS -> exbirProdutos();
           case NOVO_PEDIDO -> novoPedido();
           case FILA_PEDIDOS -> exibirFilaPedidos();
           case RETIRAR_PEDIO -> retirarPedido();
           case VOLTAR -> consoleManager.voltar();
       }
   }

    private void exbirProdutos() {
        var opcoes = montarOpcoes(restaurante.getProdutos());
        if (opcoes.isEmpty()) {
            System.out.println("\nSem produtos no momento...");
            return;
        }

        System.out.println("\tCardápio\t");
        opcoes.forEach((i, p) -> System.out.printf("%d) %s - R$%.2f\n", i, p.getNome(), p.getValor()));
    }

    private void novoPedido() {
        var opcoes = montarOpcoes(restaurante.getProdutos());
        if (opcoes.isEmpty()) {
            System.out.println("\nSem produtos no momento...");
            return;
        }

        System.out.print("\nProdutos(para múltiplos, ponha os números separados por espaço): \n");
        opcoes.forEach((i, p) -> System.out.printf("%d) %s - R$%.2f\n", i, p.getNome(), p.getValor()));

        var produtosEscolhidos = selecionarOpcoes(opcoes);

        new NovoPedidoComando(ContextoRepositorio.pedidoRepositorio())
                .criar(AutenticacaoContexto.getAutenticado(), restaurante, produtosEscolhidos);

        System.out.print("\nPedido criado!\n");
    }

    private void exibirFilaPedidos() {
        if (restaurante.getPedidos().isEmpty()) {
            System.out.println("\nNenhum pedido na fila!");
            return;
        }

        System.out.println("\tPedidos\t");
        for(Pedido p : restaurante.getPedidos()) {
            var itens = p.getProdutos().stream()
                    .map(Produto::getNome)
                    .collect(Collectors.joining(" | "));
            System.out.printf("[%s] Itens: %s%n", p.getEstado(), itens);
        }
    }

    private void retirarPedido() {
        try {
            new RetirarPedidoComando().retirar(restaurante);
            System.out.println("Pedido retirado!");
        } catch (RestauranteException re) {
            System.out.printf("%n%s%n", re.getMessage());
        }
    }

}
