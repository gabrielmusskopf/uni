package br.com.gabrielgmusskopf.unisinos.infra.cli.restaurante;

import br.com.gabrielgmusskopf.unisinos.NovoProdutoComando;
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

import java.util.Arrays;
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
        var opcoes = exibirOpcoes(RestauranteComandoCli.values());

        var opcao = opcoes.get(buscarInteiro());
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
           case ADICIONAR_PRODUTOS -> adicionarProdutos();
           case VER_ESTOQUE -> verEstoque();
           case ABASTECER_ESTOQUE -> abastecerEstoque();
           case VOLTAR -> consoleManager.voltar();
           default -> {
           }
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

        try {
            new NovoPedidoComando(ContextoRepositorio.pedidoRepositorio())
                    .criar(AutenticacaoContexto.getAutenticado(), restaurante, produtosEscolhidos);
            System.out.print("\nPedido criado!\n");
        }catch (RestauranteException re) {
            System.out.printf("%n%s%n", re.getMessage());
        }

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

    private void adicionarProdutos() {
        System.out.print("Nome do produto: ");
        var n = scanner.next();

        System.out.print("Preço: R$");
        var preco = Double.parseDouble(scanner.next());

        System.out.print("Ingredientes (separados por espaço): ");
        var ingredientes = Arrays.stream(scanner.next().trim().split(" "))
                .toList();

        //Confirmação

        new NovoProdutoComando(ContextoRepositorio.restauranteRepositorio(), ContextoRepositorio.produtoRepositorio())
                .adicionar(restaurante, new Produto(n, preco, ingredientes));

        System.out.printf("%n%s criado!%n", n);
    }

    private void verEstoque() {
        System.out.println("\tEstoque:\t");
        var estoque = restaurante.getEstoque().getIngredientes();
        if (estoque.isEmpty()) {
            System.out.println("\nNão há ingredientes.");
            return;
        }

        estoque.forEach((ingrediente, quantidade) -> System.out.printf("%s - %d%n", ingrediente, quantidade));
    }

    private void abastecerEstoque() {
        // mudar para "Item quantidade" por linha
        System.out.println("\nDigite os ingredientes para inserir ao estoque, separados por espaço:");
        var ingredientes = scanner.next().trim().split(" ");
        restaurante.getEstoque().abastecer(ingredientes);
    }

}
