package br.com.gabriel.poker.comunicacao;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Implementação de uma comunicação com o usuário. Utiliza System para exibição e Scanner para leitura
 *
 * @see System
 * @see Scanner
 */
public class ComunicacaoLog implements Comunicador {

	private final Scanner scanner = new Scanner(System.in);

	@Override
	public void comunicar(String mensagem) {
		System.out.println(mensagem);
	}

	@Override
	public void comunicar(Cor cor, String mensagem) {
		comunicarColorido(cor, mensagem);
	}

	@Override
	public void comunicar(String padrao, Object... parametros) {
		System.out.println(MessageFormat.format(padrao, parametros));
	}

	@Override
	public void comunicar(Cor cor, String padrao, Object... parametros) {
		var mensagem = MessageFormat.format(padrao, parametros);
		comunicarColorido(cor, mensagem);
	}

	private void comunicarColorido(Cor cor, String mensagem) {
		System.out.print(CorTerminal.getAscii(cor));
		System.out.println(mensagem);
		System.out.print(CorTerminal.RESET.getAscii());
	}

	@Override
	public String lerLinha() {
		return scanner.nextLine();
	}

	/**
	 * Representa as cores usadas no terminal
	 */
	@Getter
	@AllArgsConstructor
	private enum CorTerminal {
		RESET("\033[0m"),
		AZUL("\033[0;34m", Cor.AZUL),
		VERDE("\033[0;33m", Cor.VERDE),
		VERMELHO("\033[0;31m", Cor.VERMELHO),
		;

		private final String ascii;
		private Cor cor;

		CorTerminal(String ascii) {
			this.ascii = ascii;
		}

		public static String getAscii(Cor cor) {
			return Arrays.stream(CorTerminal.values())
					.filter(c -> Objects.nonNull(c.getCor()))
					.filter(c -> c.getCor().equals(cor))
					.findFirst()
					.map(CorTerminal::getAscii)
					.orElse(RESET.getAscii());
		}
	}

}
