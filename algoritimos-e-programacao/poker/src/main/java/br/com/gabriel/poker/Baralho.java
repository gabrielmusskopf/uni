package br.com.gabriel.poker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

import lombok.Getter;

public class Baralho {

	private final int totalCartas = 52;
	@Getter
	private Stack<Carta> cartas;

	public Baralho() {
		cartas = new Stack<>();
		montarCartas();
	}

	public void montarCartas() {
		var c = Arrays.stream(Naipe.values())
				.map(naipe -> IntStream.range(1, 14)
						.mapToObj(valor -> new Carta(valor, naipe))
						.toList())
				.flatMap(Collection::stream)
				.toList();

		cartas.addAll(c);
		embaralhar();
	}

	public void embaralhar() {
		embaralhar(this.cartas);
	}

	private void embaralhar(List<Carta> cartas) {
		Collections.shuffle(cartas);
	}

	public List<Carta> comprar(int quantidade) {
		return IntStream.range(0, quantidade)
				.mapToObj(i -> cartas.pop())
				.toList();
		// TODO: Montar novamente quando acabarem as cartas
	}

}
