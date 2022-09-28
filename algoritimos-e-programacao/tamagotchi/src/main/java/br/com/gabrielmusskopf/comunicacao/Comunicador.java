package br.com.gabrielmusskopf.comunicacao;

import java.util.Map;
import java.util.function.Supplier;

import br.com.gabrielmusskopf.desejo.Informacao;

public interface Comunicador extends Informacao {

	void comunicar(Supplier<String> supplier);

	void comunicar(String mensagem);

	void comunicar(String padrao, Object... parametros);

	void comunicarFormatado(Map<Object, Object> informacoes);

	Integer lerInteiro();

	String lerLinha();
}
