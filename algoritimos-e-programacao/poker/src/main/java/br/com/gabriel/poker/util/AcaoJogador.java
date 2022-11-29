package br.com.gabriel.poker.util;

import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import br.com.gabriel.poker.Controle;
import br.com.gabriel.poker.jogador.Jogador;

public class AcaoJogador {

	private static final Scanner scanner = new Scanner(System.in);

	public static <T> T executar (Jogador jogador, Function<Scanner, T> jogadorFunction, Supplier<T> npcSupplier) {
		return Controle.isPlayer(jogador) ? jogadorFunction.apply(scanner) : npcSupplier.get();
	}


	public static <T> T executar (Supplier<Jogador> jogadorSupplier, Supplier<List<?>> parametrosSupplier, BiFunction<Scanner, List<?>, T> jogadorFunction, Supplier<T> npcSupplier) {
		var jogador = jogadorSupplier.get();
		var parametros = parametrosSupplier.get();

		return Controle.isPlayer(jogador) ? jogadorFunction.apply(scanner, parametros) : npcSupplier.get();
	}

}
