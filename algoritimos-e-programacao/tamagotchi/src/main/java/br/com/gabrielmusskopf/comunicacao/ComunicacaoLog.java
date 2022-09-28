package br.com.gabrielmusskopf.comunicacao;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class ComunicacaoLog implements Comunicador{

	private Scanner scanner = new Scanner(System.in);

	@Override
	public void comunicar(Supplier<String> supplier) {
		System.out.println(supplier.get());
	}

	@Override
	public void comunicar(String mensagem) {
		System.out.println(mensagem);
	}

	@Override
	public void comunicar(String padrao, Object... parametros) {
		System.out.println(MessageFormat.format(padrao, parametros));
	}

	@Override
	public void comunicarFormatado(Map<Object, Object> informacoes) {
		informacoes.forEach((chave, valor) -> System.out.println(formatar(chave, valor)));
	}

	@Override
	public Integer lerInteiro() {
		return scanner.nextInt();
	}

	@Override
	public String lerLinha() {
		return scanner.nextLine();
	}
}
