package br.com.gabrielmusskopf.tamagotchi.exception;

import java.text.MessageFormat;

/**
 * Exception para encerrar o fluxo de ações, caso o Tamagotchi morra.
 */
public class TamagochiFaleceuException extends RuntimeException{

	public TamagochiFaleceuException(String formato, Object... parametros) {
		super(MessageFormat.format(formato, parametros));
	}
}
