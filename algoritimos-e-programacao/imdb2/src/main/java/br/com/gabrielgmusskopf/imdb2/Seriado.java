package br.com.gabrielgmusskopf.imdb2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Seriado extends AudiovisualAbstrato {

    private List<Temporada> temporadas;

    public Seriado(String nome, String codigo, int ano, Genero genero) {
        this(nome, codigo, ano, genero, new ArrayList<>());
    }

    public Seriado(String nome, String codigo, int ano, Genero genero, List<Temporada> temporadas) {
        super(nome, codigo, ano, genero);
        this.temporadas = Objects.requireNonNullElse(temporadas, new ArrayList<>());
    }

    public void adicionarTemporada(Temporada temporada) {
        temporadas.add(temporada);
    }

    @Override
    public TipoGravacao getTipoGravacao() {
        return TipoGravacao.SERIE;
    }

}
