package br.com.gabrielgmusskopf.imdb2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

class StreamingTest {

    private Audiovisual filmeComedia;
    private Audiovisual filmeDrama;
    private Audiovisual serieComedia;
    private Audiovisual serieTerror;

    @BeforeEach
    void setUp() {
        filmeDrama = new Filme("Muito Drama 2", "123", 2023, 180, Genero.DRAMA);
        filmeComedia= new Filme("Rindo Muito", "345", 2001, 190, Genero.COMEDIA);
        serieComedia = new Seriado("Muito Drama Especial de Natal", "4234", 2023, Genero.COMEDIA);
        serieTerror = new Seriado("Morrendo de Medo", "3245", 2008, Genero.TERROR, List.of(new Temporada(1, 10)));
    }

    @Test
    void deveCriarStreamingEAdicionarFilme() {
        var streaming = new Streaming("SeuFilmeJa");
        streaming.adicionarAudiovisual(Categoria.MARATONA, filmeDrama, serieComedia);

        assertThat(streaming.buscar(Categoria.MARATONA)).hasSize(2);
    }
}
