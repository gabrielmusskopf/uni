package br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido;

import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.Pedido;
import br.com.gabrielgmusskopf.unisinos.infra.Log;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.RepositorioCSV;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArquivoPedidoRepositorio extends RepositorioCSV<Pedido> implements PedidoRepositorio {

    private final List<Pedido> pedidos;

    public ArquivoPedidoRepositorio() {
        pedidos = new ArrayList<>();
        inicializar();
        Log.debug("Repositório CSV de pedido criado");
    }

    @Override
    protected String cabecalho() {
        return "id,usuario,custo,produtos,estado";
    }

    @Override
    protected String caminhoData() {
        return "data/pedidos.csv";
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        buscarPorId(pedido.getId())
                .ifPresent(this::remover);

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
        var produtoIds = lerList(valores[3]);

        var usuario = ContextoRepositorio.usuarioRepositorio()
                .buscarPorId(usuarioId)
                .orElse(null);

        var produtos = produtoIds.stream()
                .map(i -> ContextoRepositorio.produtoRepositorio()
                .buscarPorId(i)
                .orElse(null))
                .toList();

        pedidos.add(Pedido.recuperar(id, usuario, custo, produtos, valores[4]));
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
