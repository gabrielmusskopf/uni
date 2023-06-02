package br.com.gabrielgmusskopf.unisinos.infra.repositorio;

import br.com.gabrielgmusskopf.unisinos.infra.Log;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.ArquivoUsuarioRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.MemoriaUsuarioRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.UsuarioRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.estoque.ArquivoEstoqueRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.estoque.EstoqueRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.ArquivoPedidoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.MemoriaPedidoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.PedidoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto.ArquivoProdutoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto.MemoriaProdutoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto.ProdutoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.ArquivoRestauranteRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.MemoriaRestauranteRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextoRepositorio {

    private static final List<Thread> armazenamentoThreads = new ArrayList<>();
    private static TipoArmazenamento tipoArmazenamento;
    private static final Map<Class<?>, Repositorio<?,?>> cache = new HashMap<>();

    public static void bootstrap(TipoArmazenamento armazenamento) {
        tipoArmazenamento = armazenamento;
        usuarioRepositorio();
        produtoRepositorio();
        estoqueRepositorio();
        pedidoRepositorio();
        restauranteRepositorio();
    }

    public static UsuarioRepositorio usuarioRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> (UsuarioRepositorio) singleton(ArquivoUsuarioRepositorio.class);
            case MEMORIA -> (UsuarioRepositorio) singleton(MemoriaUsuarioRepositorio.class);
        };
    }

    public static RestauranteRepositorio restauranteRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> (RestauranteRepositorio) singleton(ArquivoRestauranteRepositorio.class);
            case MEMORIA -> (RestauranteRepositorio) singleton(MemoriaRestauranteRepositorio.class);
        };
    }

    public static PedidoRepositorio pedidoRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> (PedidoRepositorio) singleton(ArquivoPedidoRepositorio.class);
            case MEMORIA ->  (PedidoRepositorio) singleton(MemoriaPedidoRepositorio.class);
        };
    }

    public static ProdutoRepositorio produtoRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> (ProdutoRepositorio) singleton(ArquivoProdutoRepositorio.class);
            case MEMORIA -> (ProdutoRepositorio) singleton(MemoriaProdutoRepositorio.class);
        };
    }

    public static EstoqueRepositorio estoqueRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> (EstoqueRepositorio) singleton(ArquivoEstoqueRepositorio.class);
            case MEMORIA -> throw new ArmazenamentoNaoSuportadoException();
        };
    }

    private static Repositorio<?,?> singleton(Class<?> repositorio) {
        var r = cache.get(repositorio);
        if (r != null) {
            return r;
        }
        try {
            var repoInstancia = (Repositorio<?, ?>) repositorio.getDeclaredConstructor().newInstance();
            cache.put(repositorio, repoInstancia);
            return repoInstancia;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void adicionarThread(Thread thread) {
        armazenamentoThreads.add(thread);
        Runtime.getRuntime().addShutdownHook(thread);
    }

    public static void armazenar() {
        armazenamentoThreads.forEach(t -> {
            Log.debug("Armazenando dados em " + t.getName());
            t.start();
        });
    }

}
