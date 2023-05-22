package br.com.gabrielgmusskopf.imdb2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AudiovisualAbstrato implements Audiovisual {

    private final String nome;
    private final String codigo;
    private final int ano;
    private List<Ator> principaisAtores;
    protected final Genero genero;

    public AudiovisualAbstrato(String nome, String codigo, int ano, Genero genero) {
        this(nome, codigo, ano, new ArrayList<>(), genero);
    }

    public AudiovisualAbstrato(String nome, String codigo, int ano, List<Ator> principaisAtores, Genero genero) {
        this.principaisAtores = Objects.requireNonNullElse(principaisAtores, new ArrayList<>());
        this.codigo = codigo;
        this.genero = genero;
        this.nome = nome;
        this.ano = ano;
    }

    public boolean adicionarAtores(Ator... atores){
       return adicionarAtores(List.of(atores));
    }

    public boolean adicionarAtores(List<Ator> atores){
        principaisAtores = atores;
        return true;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getAno() {
        return ano;
    }

    public List<Ator> getPrincipaisAtores() {
        return principaisAtores;
    }

    public void setPrincipaisAtores(List<Ator> principaisAtores) {
        this.principaisAtores = principaisAtores;
    }

}
