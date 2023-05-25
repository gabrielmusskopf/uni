package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.comando.BuscarProdutosComando;
import br.com.gabrielgmusskopf.unisinos.comando.NovoPedidoComando;
import br.com.gabrielgmusskopf.unisinos.comando.RestauranteComando;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.Contexto;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;

import java.util.Arrays;
import java.util.Comparator;

public class RestauranteConsole extends ConsoleAbstrato {

    private final Restaurante restaurante;

    public RestauranteConsole(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
   public void exibir(ConsoleManager manager) {
       Arrays.stream(RestauranteComando.values()).findFirst().stream()
               .sorted(Comparator.comparing(RestauranteComando::getNumero))
               .forEach(c -> System.out.printf("%d %s\n", c.getNumero(), c.getDescricao()));

       int escolha = buscarInteiro();
       var opcao = RestauranteComando.numero(escolha);
       if (opcao == null) {
           System.out.println("Opção não encontrada...\n");
           manager.home();
       }

       switch (opcao) {
           case CANCELAR -> Contexto.finalizar();
           case NOVO_PEDIDO -> novoPedido();
       }
   }

    private void novoPedido() {
        System.out.print("Nome do cliente: ");
        var nome = scanner.next();

        var produtos = new BuscarProdutosComando(ContextoRepositorio.produtoRepositorio()).buscar();
        var opcoes = montarOpcoes(produtos);

        System.out.print("Produtos(para múltiplos, ponha os números separados por espaço): ");
        opcoes.forEach((k, v) -> System.out.printf("%d - %s", k, v));

        var produtosEscolhidos = selecionarOpcoes(opcoes);

        var input = new NovoPedidoComando.NovoPedidoInput(nome, produtosEscolhidos);
        new NovoPedidoComando(ContextoRepositorio.pedidoRepositorio(), ContextoRepositorio.clienteRepositorio())
                .criar(input);

        System.out.print("Pedido criado!\n\n");
    }

}
