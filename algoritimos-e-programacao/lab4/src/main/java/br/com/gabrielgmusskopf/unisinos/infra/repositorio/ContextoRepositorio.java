package br.com.gabrielgmusskopf.unisinos.infra.repositorio;

import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.ArquivoUsuarioRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.UsuarioRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.MemoriaUsuarioRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.ArquivoPedidoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.MemoriaPedidoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.pedido.PedidoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto.ArquivoProdutoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto.MemoriaProdutoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.produto.ProdutoRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.ArquivoRestauranteRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.MemoriaRestauranteRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.restaurante.RestauranteRepositorio;

import java.util.HashMap;
import java.util.Map;

public class ContextoRepositorio {

    private static TipoArmazenamento tipoArmazenamento;
    private static final Map<Class<?>, Repositorio<?,?>> cache = new HashMap<>();

    public static void setTipoArmazenamento(TipoArmazenamento armazenamento) {
        tipoArmazenamento = armazenamento;
    }

    public static UsuarioRepositorio usuarioRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> (UsuarioRepositorio) singleton(new ArquivoUsuarioRepositorio());
            case MEMORIA -> (UsuarioRepositorio) singleton(new MemoriaUsuarioRepositorio());
        };
    }

    public static RestauranteRepositorio restauranteRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> (RestauranteRepositorio) singleton(new ArquivoRestauranteRepositorio());
            case MEMORIA -> (RestauranteRepositorio) singleton(new MemoriaRestauranteRepositorio());
        };
    }

    public static PedidoRepositorio pedidoRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> (PedidoRepositorio) singleton(new ArquivoPedidoRepositorio());
            case MEMORIA ->  (PedidoRepositorio) singleton(new MemoriaPedidoRepositorio());
        };
    }

    public static ProdutoRepositorio produtoRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> (ProdutoRepositorio) singleton(new ArquivoProdutoRepositorio());
            case MEMORIA -> (ProdutoRepositorio) singleton(new MemoriaProdutoRepositorio());
        };
    }

    private static Repositorio<?,?> singleton(Repositorio<?,?> repositorio) {
        var r = cache.get(repositorio.getClass());
        if (r != null) {
            return r;
        }
        cache.put(repositorio.getClass(), repositorio);
        return repositorio;
    }

}
