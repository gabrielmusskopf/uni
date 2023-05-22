package br.com.gabrielgmusskopf.imdb2;

public class Filme extends AudiovisualAbstrato {

    private final double duracao;

    public Filme(String nome, String codigo, int ano, double duracao, Genero genero) {
        super(nome, codigo, ano, genero);
        this.duracao = duracao;
    }

    @Override
    public TipoGravacao getTipoGravacao() {
        return TipoGravacao.FILME;
    }

}
