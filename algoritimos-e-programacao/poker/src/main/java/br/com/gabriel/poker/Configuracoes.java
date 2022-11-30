package br.com.gabriel.poker;

import java.util.Scanner;
import java.util.function.Consumer;

import br.com.gabriel.poker.comunicacao.ComunicacaoLog;
import br.com.gabriel.poker.comunicacao.Comunicador;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Configuracoes {

	public static final Comunicador COMUNICADOR = new ComunicacaoLog();
	public static final Scanner SCANNER = new Scanner(System.in);
	private long segundosDelayEntreRodadas = 3;
	private long segundosDelayAposRodada = 6;
	private int quantidadeDeJogadores = 5;

	public Configuracoes (Consumer<Configuracoes> consumer) {
		consumer.accept(this);
	}

}
