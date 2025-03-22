package br.com.gabriel.poker;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.gabriel.poker.jogador.Jogador;

public class PontuarSequencia {

	public static Mao pontuar (List<Carta> cartas) {

		final var cartasOrdenadas = cartas.stream()
				.sorted(Comparator.comparingInt(Carta::getNumero))
				.toList();

		if (isRoyalFlush(cartasOrdenadas))
			return Mao.ROYAL_FLUSH;

		if (isStraigthFlush(cartasOrdenadas))
			return Mao.STRAIGTH_FLUSH;

		if (isQuadra(cartas))
			return Mao.QUADRA;

		if (isFullHouse(cartas))
			return Mao.FULL_HOUSE;

		if (isFlush(cartas))
			return Mao.FLUSH;

		if (isSequencia(cartas))
			return Mao.SEQUENCIA;

		if (isTrinca(cartas))
			return Mao.TRINCA;

		if (isDoisPares(cartas))
			return Mao.DOIS_PARES;

		if (isUmPar(cartas))
			return Mao.UM_PAR;

		return Mao.NENHUM;
	}

	public static Jogador buscarCartaMaisAlta (Map<Jogador, List<Carta>> todasCartas) {
		return todasCartas.entrySet().stream()
				.peek(entry -> converter1Para14(entry.getValue()))
				.max(Comparator.comparingInt(e -> buscarCartaMaisAlta(e.getValue()).getNumero()))
				.map(Map.Entry::getKey)
				.orElse(null);
	}

	public static Carta buscarCartaMaisAlta (List<Carta> todasCartas) {
		return todasCartas.stream()
				.max(Comparator.comparing(Carta::getNumero))
				.orElse(null);
	}

	private static boolean isRoyalFlush (List<Carta> cartas) {

		var cartasAMapeado = converter1Para14(cartas).stream()
				.sorted(Comparator.comparingInt(Carta::getNumero))
				.toList();

		return isSequenciaDe(cartasAMapeado, 5) &&
				isMesmoNaipe(cartas) &&
				Integer.valueOf(14).equals(cartasAMapeado.get(cartasAMapeado.size() - 1).getNumero());
	}

	private static List<Carta> converter1Para14 (List<Carta> cartas) {
		return cartas.stream()
				.peek(carta -> {
					if (carta.getNumero().equals(1)) carta.setNumero(14);
				})
				.toList();
	}

	private static boolean isStraigthFlush (List<Carta> cartas) {
		return isSequenciaDe(cartas, 5) && isMesmoNaipe(cartas);
	}

	private static boolean isQuadra (List<Carta> cartas) {
		return isCartasIguais(cartas, 4);
	}

	private static boolean isFullHouse (List<Carta> cartas) {
		return isCartasIguais(cartas, 3, 2);
	}

	private static boolean isFlush (List<Carta> cartas) {
		return isMesmoNaipe(cartas);
	}

	private static boolean isSequencia (List<Carta> cartas) {
		return isSequenciaDe(cartas, 5);
	}

	private static boolean isTrinca (List<Carta> cartas) {
		return isCartasIguais(cartas, 3);
	}

	private static boolean isDoisPares (List<Carta> cartas) {
		return isCartasIguais(cartas, 2, 2);
	}

	private static boolean isUmPar (List<Carta> cartas) {
		return isCartasIguais(cartas, 2);
	}

	private static boolean isCartasIguais (List<Carta> cartas, int quantidadeIguais) {
		var numeros = cartas.stream()
				.map(Carta::getNumero)
				.toList();

		return numeros.stream()
				.anyMatch(numero -> Collections.frequency(numeros, numero) >= quantidadeIguais);
	}

	private static boolean isCartasIguais (List<Carta> cartas, int quantidadeIguais1, int quantidadeIguais2) {
		var numeros = cartas.stream()
				.map(Carta::getNumero)
				.toList();

		var numerosAgrupados = numeros.stream()
				.collect(Collectors.groupingBy(n -> n));

		var quantidade1Validada = numerosAgrupados.values().stream()
				.anyMatch(valores -> valores.size() == quantidadeIguais1);

		var valorParaRemover = numerosAgrupados.values().stream()
				.filter(valores -> valores.size() == quantidadeIguais1)
				.flatMap(Collection::stream)
				.findFirst()
				.orElse(null);

		numerosAgrupados.remove(valorParaRemover);

		var quantidade2Validada = numerosAgrupados.values().stream()
				.anyMatch(valores -> valores.size() == quantidadeIguais2);

		return quantidade1Validada && quantidade2Validada;
	}

	private static boolean isSequenciaDe (List<Carta> cartas, int quantidadeSequencia) {

		int numerosEmSequencia = 1;
		for (int i = 0; i < cartas.size() - 1; i++) {
			var cartaAtual = cartas.get(i);
			var proximaCarta = cartas.get(i + 1);

			if (proximaCarta.getNumero() - cartaAtual.getNumero() != 1)
				return false;

			numerosEmSequencia++;
		}

		return numerosEmSequencia == quantidadeSequencia;
	}

	private static boolean isMesmoNaipe (List<Carta> cartas) {

		var primeiraCarta = cartas.get(0);

		for (int i = 1; i < cartas.size(); i++) {
			var cartaAtual = cartas.get(i);

			if (!cartaAtual.getNaipe().equals(primeiraCarta.getNaipe()))
				return false;

		}

		return true;
	}

}
