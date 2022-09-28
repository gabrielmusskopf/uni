package br.com.gabrielmusskopf.desejo;

import java.text.MessageFormat;

public interface Informacao {

	default String formatar(Object titulo, Object informacao){
		return MessageFormat.format( "{0}: {1}", titulo, informacao);
	}

}
