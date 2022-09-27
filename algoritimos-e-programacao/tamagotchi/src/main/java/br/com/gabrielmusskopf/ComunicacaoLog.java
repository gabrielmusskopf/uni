package br.com.gabrielmusskopf;

import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.function.Supplier;

public class ComunicacaoLog implements Comunicador{
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
}
