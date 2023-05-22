package br.com.gabrielgmusskopf.imdb2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Streaming {

    private final String nome;
    private Map<Categoria, List<Audiovisual>> audiovisuais;

    public Streaming(final String nome) {
        this.nome = nome;
    }

    public List<Audiovisual> buscar(Categoria categoria) {
        return audiovisuais.get(categoria);
    }

    public boolean adicionarAudiovisual(final Map<Categoria, List<Audiovisual>> novos) {
        if (novos == null) {
            return false;
        }

        audiovisuais.forEach((c, atuais) -> {
            final var novo = novos.get(c);
            if (novo != null) {
                atuais.addAll(novo);
            }
        });

        return true;
    }

    public boolean adicionarAudiovisual(final Categoria categoria, final Audiovisual... audiovisuaisNovos) {
        final var novos = Arrays.asList(audiovisuaisNovos);
        audiovisuais.compute(categoria, (c, a) -> {
            if (a == null) {
                return novos;
            }

            a.addAll(novos);
            return a;
        });

        return true;
    }

    public List<Audiovisual> buscarSeries() {
        return audiovisuais.values()
            .stream()
            .flatMap(t -> t.stream())
            .filter(a -> TipoGravacao.SERIE.equals(a.getTipoGravacao()))
            .toList();
    }

    public List<Audiovisual> buscarFilmes() {
        return audiovisuais.values()
            .stream()
            .flatMap(t -> t.stream())
            .filter(a -> TipoGravacao.FILME.equals(a.getTipoGravacao()))
            .toList();
    }


}
