package br.com.gabrielgmusskopf.unisinos.pedido;

import br.com.gabrielgmusskopf.unisinos.Cliente;
import br.com.gabrielgmusskopf.unisinos.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class Pedido {

    private final Cliente cliente;
    private final Double custo;
    private List<Produto> produtos;
    private EstadoPedido estado;

    public Pedido(Cliente cliente, Produto... produtos) {
        this(cliente, List.of(produtos));
    }

    public Pedido(Cliente cliente, List<Produto> produtos) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.custo = calcularCustoTotal(produtos);
        this.estado = new PedidoCriado(this);
    }

    private double calcularCustoTotal(List<Produto> produtos) {
        return produtos.stream()
                .map(Produto::getValor)
                .reduce(Double::sum)
                .orElse(0D);
    }

    public void processarPagamento() {
        estado.processarPagamento();
    }

    public void aprovar() {
        estado.aprovar();
    }

    public void entregar() {
        estado.entregar();
    }

    public void cancelar() {
        estado.cancelar();
    }

    public void finalizar() {
        estado.finalizar();
    }

    public void alterarEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public String toString() {
        var p = produtos.stream()
                .map(Produto::getNome)
                .collect(Collectors.joining(", "));

        return """
                %s | %s | %f | %s
                """.formatted(cliente, estado, custo, p);
    }

}
