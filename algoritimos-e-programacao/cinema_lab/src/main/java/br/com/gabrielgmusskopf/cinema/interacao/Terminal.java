package br.com.gabrielgmusskopf.cinema.interacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Terminal implements Interacao {

	private final static Scanner SCANNER = new Scanner(System.in).useDelimiter("\n");

	@Override
	public String in() {
		return SCANNER.next();
	}

	@Override
	public Integer inInt() {
		try {
			return SCANNER.nextInt();
		}catch (InputMismatchException e){
			return  null;
		}
	}

	@Override
	public void outln(String mensagem) {
		System.out.println(mensagem);
	}

	@Override
	public void outln() {
		System.out.println();
	}

	@Override
	public void out(String mensagem) {
		System.out.print(mensagem);
	}

	@Override
	public void err(String mensagem) {
		comunicarColorido(Cor.VERMELHO, mensagem);
	}

	@Override
	public void info(String mensagem) {
		comunicarColorido(Cor.AZUL, mensagem);
	}

	private void comunicarColorido(Cor cor, String mensagem) {
		System.out.print(CorTerminal.getAscii(cor));
		System.out.println(mensagem);
		System.out.print(CorTerminal.RESET.getAscii());
	}

	private enum CorTerminal {
		RESET("\033[0m"),
		AZUL("\033[0;34m", Cor.AZUL),
		VERDE("\033[0;33m", Cor.VERDE),
		VERMELHO("\033[0;31m", Cor.VERMELHO),
		;

		private final String ascii;
		private Cor cor;

		CorTerminal(String ascii, Cor cor) {
			this.ascii = ascii;
			this.cor = cor;
		}

		CorTerminal(String ascii) {
			this.ascii = ascii;
		}

		public String getAscii() {
			return ascii;
		}

		public static String getAscii(Cor cor){
			return Arrays.stream(CorTerminal.values())
					.filter(c -> Objects.nonNull(c.cor))
					.filter(c -> c.cor.equals(cor))
					.findFirst()
					.map(CorTerminal::getAscii)
					.orElse(RESET.getAscii());
		}
	}
}
