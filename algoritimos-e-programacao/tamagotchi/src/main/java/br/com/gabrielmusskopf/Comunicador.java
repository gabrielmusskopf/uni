package br.com.gabrielmusskopf;

import java.util.function.Supplier;

public interface Comunicador {

	void comunicar(Supplier<String> supplier);

	void comunicar(String mensagem);

	void comunicar(String padrao, Object... parametros);
}
