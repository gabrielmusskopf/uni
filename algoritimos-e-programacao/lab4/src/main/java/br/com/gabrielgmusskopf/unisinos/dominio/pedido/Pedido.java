package br.com.gabrielgmusskopf.unisinos.dominio.pedido;

import br.com.gabrielgmusskopf.unisinos.dominio.Dominio;
import br.com.gabrielgmusskopf.unisinos.dominio.Produto;
import br.com.gabrielgmusskopf.unisinos.dominio.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Pedido implements Dominio, Serializable {

    private static final long serialVersionUID = -7097901326783065464L;

    private final UUID id;
    private final Usuario usuario;
    private final Double custo;
    private List<Produto> produtos;
    private EstadoPedido estado;


    public static Pedido recuperar(String id, Usuario usuario, double custo, List<Produto> produtos) {
        //TODO implementar com EstadoPedido
        return new Pedido(id, usuario, custo, produtos);
    }

    public Pedido(Usuario usuario, Produto... produtos) {
        this(usuario, List.of(produtos));
    }

    public Pedido(Usuario usuario, List<Produto> produtos) {
        this.id = UUID.randomUUID();
        this.usuario = usuario;
        this.produtos = produtos;
        this.custo = calcularCustoTotal(produtos);
        this.estado = new PedidoCriado(this);
    }

    private Pedido(String id, Usuario usuario, double custo, List<Produto> produtos) {
        this.id = UUID.fromString(id);
        this.usuario = usuario;
        this.produtos = produtos;
        this.custo = custo;
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

    public void preparar() {
        estado.preparar();
    }

    public boolean isAguardandoCliente(){
        return estado instanceof PedidoAguardandoCliente;
    }

    public void aguardandoCliente() {
        estado.aguardandoCliente();
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

    public Usuario getCliente() {
        return usuario;
    }

    public String getId() {
        return id.toString();
    }

    public Double getCusto() {
        return custo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void atualizarElemento(Produto produto) {
        produtos.add(produto);
    }

    public String toString() {
        var p = produtos.stream()
                .map(Produto::getNome)
                .collect(Collectors.joining(", "));

        return """
                %s | %s | %f | %s
                """.formatted(usuario, estado, custo, p);
    }

}
