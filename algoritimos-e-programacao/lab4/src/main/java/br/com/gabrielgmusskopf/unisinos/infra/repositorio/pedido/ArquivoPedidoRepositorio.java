package br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.RepositorioArquivos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArquivoPedidoRepositorio extends RepositorioArquivos<Pedido> implements PedidoRepositorio {

    private final List<Pedido> pedidos;

    public ArquivoPedidoRepositorio() {
        pedidos = new ArrayList<>();
        carregar(pedidos);
        escreverAoFinal("id,usuario,custo,produtos,estado");
    }

    @Override
    protected String caminhoData() {
        return "data/pedidos.csv";
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        pedidos.add(pedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> buscar(Pedido pedido) {
        return pedidos.stream()
                .filter(p -> p.getId().equals(pedido.getId()))
                .findFirst();
    }

    @Override
    public Optional<Pedido> buscarPorId(String s) {
        return pedidos.stream()
                .filter(p -> p.getId().equals(s))
                .findFirst();
    }

    @Override
    public void remover(Pedido pedido) {
        pedidos.remove(pedido);
    }

    @Override
    public List<Pedido> buscarTodos() {
        return pedidos;
    }

    @Override
    protected void recuperarElemento(String[] valores) {
        var id = valores[0];
        var usuarioId = valores[1];
        var custo = Double.parseDouble(valores[2]);
        var produtoIds = stringToList(valores, 3);

        var usuario = ContextoRepositorio.usuarioRepositorio()
                .buscarPorId(usuarioId)
                .orElse(null);

        var produtos = produtoIds.stream()
                .map(i ->ContextoRepositorio.produtoRepositorio()
                .buscarPorId(i)
                .orElse(null))
                .toList();

        pedidos.add(Pedido.recuperar(id, usuario, custo, produtos));
    }

    @Override
    protected List<?> escreverValores(Pedido pedido) {
        return List.of(pedido.getId(), pedido.getUsuario(), pedido.getCusto(), pedido.getProdutos(), pedido.getEstado());
    }

    @Override
    public List<Pedido> buscarParaCliente(Usuario usuario) {
        return pedidos.stream()
                .filter((p -> p.getCliente().getNome().equals(usuario.getNome()))) //TODO: implementar equals Usuario
                .toList();
    }

}
