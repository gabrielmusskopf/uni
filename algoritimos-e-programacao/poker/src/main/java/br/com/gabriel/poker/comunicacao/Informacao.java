package br.com.gabriel.poker.comunicacao;

import java.text.MessageFormat;

/**
 * Representa uma informação para o jogador.<br/>
 */
public interface Informacao {

	/**
	 * Como a informação deve ser exibida
	 *
	 * @param titulo     Título da informação
	 * @param informacao A informação em si
	 * @return Informação formatada
	 */
	default String formatar(Object titulo, Object informacao) {
		return MessageFormat.format("{0}: {1}", titulo, informacao);
	}

}
