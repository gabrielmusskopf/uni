package br.com.gabrielgmusskopf.cinema.opcao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Grafico;

public class OpcoesFactory {

	private final Cinema cinema;
	private final Grafico grafico;

	public OpcoesFactory(Cinema cinema, Grafico grafico) {
		this.cinema = cinema;
		this.grafico = grafico;
	}

	public List<Opcao> construir(TipoOpcao... opcoes) {
        var ops = Arrays.asList(opcoes);

        return IntStream.range(0, ops.size())
            .mapToObj(i -> construir(i + 1, ops.get(i)))
            .collect(Collectors.toList());
	}

	public Opcao construir(int numero, TipoOpcao opcao){
		return switch (opcao) {
			case RESERVAR -> new ReservarOpcao(numero, cinema, grafico);
			case RESERVAR_MULTIPLOS -> new ReservarMultiplosOpcao(numero, cinema, grafico);
			case RESERVAR_MULTIPLOS_COLUNA_OU_FILA -> new ReservarMultiplosColunaOuFilaOpcao(numero, cinema, grafico);
			case CANCELAR_RESERVA -> new CancelarReservaOpcao(numero, cinema, grafico);
			case OCUPAR -> new OcuparOpcao(numero, cinema, grafico);
			case EXIBIR_POSICOES -> new ExibirOpcoesOpcao(numero, cinema, grafico);
			case PARAR -> new PararProgramaOpcao(numero);
		};
	}

}
