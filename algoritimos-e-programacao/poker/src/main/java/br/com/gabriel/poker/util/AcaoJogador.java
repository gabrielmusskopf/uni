package br.com.gabriel.poker.util;

import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import br.com.gabriel.poker.Controle;
import br.com.gabriel.poker.jogador.Jogador;
import lombok.RequiredArgsConstructor;

public class AcaoJogador {

	private static final Scanner scanner = new Scanner(System.in);

	public static <T> T executar (Jogador jogador, Function<Scanner, T> jogadorFunction, Supplier<T> npcSupplier) {
		return Controle.isPlayer(jogador) ? jogadorFunction.apply(scanner) : npcSupplier.get();
	}

	public static <T> T executar (Jogador jogador, Function<Scanner, T> jogadorFunction, BiFunction<Integer, Integer, T> npcSupplier) {
		return Controle.isPlayer(jogador) ? jogadorFunction.apply(scanner) : npcSupplier.apply(1, jogador.getFichas());
	}

	public static <T> T executar (Supplier<Jogador> jogadorSupplier, Supplier<List<?>> parametrosSupplier, BiFunction<Scanner, List<?>, T> jogadorFunction, Supplier<T> npcSupplier) {
		var jogador = jogadorSupplier.get();
		var parametros = parametrosSupplier.get();

		return Controle.isPlayer(jogador) ? jogadorFunction.apply(scanner, parametros) : npcSupplier.get();
	}

	public static <T> Acao<T> executar (Jogador jogador) {
		return new Acao<>(jogador);
	}

	@RequiredArgsConstructor
	public static class Acao<T> {

		private final Jogador jogador;
		private T valor;

		public Acao<T> casoPlayer (Runnable runnable) {
			if (Controle.isPlayer(jogador))
				runnable.run();

			return this;
		}

		public Acao<T> casoPlayer (Function<Scanner, T> playerFunction) {
			if (Controle.isPlayer(jogador))
				valor = playerFunction.apply(scanner);

			return this;
		}

		public T casoNPC (Function<Integer, T> npcFunction) {
			if (!Controle.isPlayer(jogador))
				valor = npcFunction.apply(jogador.getFichas());

			return valor;
		}

		public T casoNPC (Supplier<T> npcSupplier) {
			if (!Controle.isPlayer(jogador))
				valor = npcSupplier.get();

			return valor;
		}

	}

}
