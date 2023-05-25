package br.com.gabrielgmusskopf.unisinos.infra.repositorio;

import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.ArquivoClienteRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.ClienteRepositorio;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.MemoriaClienteRepositorio;
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

    private final TipoArmazenamento tipoArmazenamento;
    private Map<Tipo, Repositorio<?,?>> cache = new HashMap<>();

    public ContextoRepositorio(TipoArmazenamento armazenamento) {
        tipoArmazenamento = armazenamento;
    }

    private enum Tipo {
        RESTAURANTE, CLIENTE;
    }

    //TODO: Corrigir repositórios
    public ClienteRepositorio clienteRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> (ClienteRepositorio) singleton(Tipo.CLIENTE, new ArquivoClienteRepositorio());
            case MEMORIA -> (ClienteRepositorio) singleton(Tipo.CLIENTE, new MemoriaClienteRepositorio());
        };
    }

    public RestauranteRepositorio restauranteRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> (RestauranteRepositorio) singleton(Tipo.RESTAURANTE, new ArquivoRestauranteRepositorio());
            case MEMORIA -> (RestauranteRepositorio) singleton(Tipo.RESTAURANTE, new MemoriaRestauranteRepositorio());
        };
    }

    public PedidoRepositorio pedidoRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> new ArquivoPedidoRepositorio();
            case MEMORIA ->  new MemoriaPedidoRepositorio();
        };
    }

    public ProdutoRepositorio produtoRepositorio(){
        return switch (tipoArmazenamento) {
            case ARQUIVO -> new ArquivoProdutoRepositorio();
            case MEMORIA -> new MemoriaProdutoRepositorio();
        };
    }

    private <ID, T> Repositorio<ID,T> singleton(Tipo tipo, Repositorio<ID, T> repositorio) {
        var r = cache.get(tipo);
        if (r != null) {
            return (Repositorio<ID, T>) r;
        }
        cache.put(tipo, repositorio);
        return repositorio;
    }

}
