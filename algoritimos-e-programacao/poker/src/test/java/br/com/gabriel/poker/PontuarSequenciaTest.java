package br.com.gabriel.poker;


import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;

public class PontuarSequenciaTest {

	private final PontuarSequencia servico = new PontuarSequencia();

	@Test
	public void deveValidarRoyalFlush () {
		var cartas = List.of(
				new Carta(1, Naipe.OURO),
				new Carta(10, Naipe.OURO),
				new Carta(11, Naipe.OURO),
				new Carta(12, Naipe.OURO),
				new Carta(13, Naipe.OURO)
		);

		var mao = servico.pontuar(cartas);

		assertEquals(Mao.ROYAL_FLUSH, mao);
	}

	@Test
	public void deveValidarStraigthFlush () {
		var cartas = List.of(
				new Carta(9, Naipe.OURO),
				new Carta(10, Naipe.OURO),
				new Carta(11, Naipe.OURO),
				new Carta(12, Naipe.OURO),
				new Carta(13, Naipe.OURO)
		);

		var mao = servico.pontuar(cartas);

		assertEquals(Mao.STRAIGTH_FLUSH, mao);
	}

	@Test
	public void deveValidarQuadra () {
		var cartas = List.of(
				new Carta(9, Naipe.OURO),
				new Carta(9, Naipe.COPAS),
				new Carta(9, Naipe.ESPADA),
				new Carta(9, Naipe.OURO),
				new Carta(13, Naipe.OURO)
		);

		var mao = servico.pontuar(cartas);

		assertEquals(Mao.QUADRA, mao);
	}

	@Test
	public void deveValidarFullHouse () {
		var cartas = List.of(
				new Carta(9, Naipe.OURO),
				new Carta(9, Naipe.COPAS),
				new Carta(9, Naipe.ESPADA),
				new Carta(13, Naipe.OURO),
				new Carta(13, Naipe.OURO)
		);

		var mao = servico.pontuar(cartas);

		assertEquals(Mao.FULL_HOUSE, mao);
	}

	@Test
	public void deveValidarFlush () {
		var cartas = List.of(
				new Carta(1, Naipe.OURO),
				new Carta(9, Naipe.OURO),
				new Carta(7, Naipe.OURO),
				new Carta(13, Naipe.OURO),
				new Carta(12, Naipe.OURO)
		);

		var mao = servico.pontuar(cartas);

		assertEquals(Mao.FLUSH, mao);
	}

	@Test
	public void deveValidarStraigthSequencia () {
		var cartas = List.of(
				new Carta(9, Naipe.PAUS),
				new Carta(10, Naipe.OURO),
				new Carta(11, Naipe.ESPADA),
				new Carta(12, Naipe.COPAS),
				new Carta(13, Naipe.OURO)
		);

		var mao = servico.pontuar(cartas);

		assertEquals(Mao.SEQUENCIA, mao);
	}

	@Test
	public void deveValidarTrinca () {
		var cartas = List.of(
				new Carta(9, Naipe.OURO),
				new Carta(9, Naipe.COPAS),
				new Carta(9, Naipe.ESPADA),
				new Carta(1, Naipe.OURO),
				new Carta(13, Naipe.OURO)
		);

		var mao = servico.pontuar(cartas);

		assertEquals(Mao.TRINCA, mao);
	}

	@Test
	public void deveValidarDoisPares () {
		var cartas = List.of(
				new Carta(9, Naipe.OURO),
				new Carta(9, Naipe.COPAS),
				new Carta(1, Naipe.ESPADA),
				new Carta(1, Naipe.OURO),
				new Carta(13, Naipe.OURO)
		);

		var mao = servico.pontuar(cartas);

		assertEquals(Mao.DOIS_PARES, mao);
	}

	@Test
	public void deveValidarUmPar () {
		var cartas = List.of(
				new Carta(9, Naipe.OURO),
				new Carta(9, Naipe.COPAS),
				new Carta(7, Naipe.ESPADA),
				new Carta(1, Naipe.OURO),
				new Carta(13, Naipe.OURO)
		);

		var mao = servico.pontuar(cartas);

		assertEquals(Mao.UM_PAR, mao);
	}

}