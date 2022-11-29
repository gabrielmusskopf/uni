package br.com.gabriel.poker;

import java.util.function.Consumer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Configuracoes {

	private long segundosDelayEntreRodadas = 3;
	private long segundosDelayAposRodada = 6;
	private int quantidadeDeJogadores = 5;

	public Configuracoes (Consumer<Configuracoes> consumer) {
		consumer.accept(this);
	}

}
