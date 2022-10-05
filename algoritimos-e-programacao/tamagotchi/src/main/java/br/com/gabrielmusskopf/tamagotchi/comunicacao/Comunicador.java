package br.com.gabrielmusskopf.tamagotchi.comunicacao;

import br.com.gabrielmusskopf.tamagotchi.desejo.Informacao;

/**
 * Representa a comunicação com o usuário. A implementação padrão é utilizando o console.
 * @see ComunicacaoLog
 */
public interface Comunicador extends Informacao {

	/**
	 * Comunicar uma mensagem
	 * @param mensagem Texto da mensagem
	 */
	void comunicar(String mensagem);

	/**
	 * Comunicar uma mensagem colorida
	 * @param cor Cor da mensagem
	 * @param mensagem Texto da mensagem
	 */
	void comunicar(Cor cor, String mensagem);

	/**
	 * Comunicar uma mensagem com parâmetros. A implementação padrão utiliza MessageFormat.
	 * @see java.text.MessageFormat
	 * @param padrao Texto esperando parâmetros. Os parâmetros devem ser esperados no formato '{0}', sendo o número a posição do elemento que deseja exibir
	 * @param parametros N parâmetros para serem exibidos na mensagem.
	 */
	void comunicar(String padrao, Object... parametros);

	/**
	 * Comunicar uma mensagem com parâmetros. A implementação padrão utiliza MessageFormat.
	 * @see java.text.MessageFormat
	 * @param cor Cor da mensagem
	 * @param padrao Texto esperando parâmetros. Os parâmetros devem ser esperados no formato '{0}', sendo o número a posição do elemento que deseja exibir
	 * @param parametros N parâmetros para serem exibidos na mensagem.
	 */
	void comunicar(Cor cor, String padrao, Object... parametros);

	/**
	 * Lê a String inserida pelo usuário.
	 * @return
	 */
	String lerLinha();
}
