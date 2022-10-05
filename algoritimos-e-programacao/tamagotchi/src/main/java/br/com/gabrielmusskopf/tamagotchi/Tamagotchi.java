package br.com.gabrielmusskopf.tamagotchi;

import java.text.MessageFormat;

import br.com.gabrielmusskopf.tamagotchi.comunicacao.Comunicador;
import br.com.gabrielmusskopf.tamagotchi.comunicacao.Cor;
import br.com.gabrielmusskopf.tamagotchi.exception.TamagochiFaleceuException;
import lombok.Getter;

/**
 * Classe responsável por gerir o Tamagotchi em si. Contém ações de conhecimento do Tamagotchi e suas devidas implicações e regras
 */
@Getter
public class Tamagotchi {
	private static final int IDADE_MAXIMA = 15;

	private final Comunicador comunicador;
	private final String nome;
	private Integer diasDeVida;
	private Double peso;
	private int diasAcordado;
	private boolean isVivo;

	public Tamagotchi(String nome, Comunicador comunicador) {
		this.nome = nome;
		this.comunicador = comunicador;
		this.diasDeVida = 0;
		this.peso = 1.0;
		this.isVivo = true;
	}

	/**
	 * Responsável por comunicar o status do Tamagotchi
	 */
	public void comunicarStatus(){
		var mensagem =
				"\nStatus atuais:\n" + informar("Nome", nome) +
				informar("Idade", diasDeVida) +
				informar("Peso", peso) +
				informar("Dias acordados", diasAcordado);

		comunicador.comunicar(Cor.AZUL, mensagem);
	}

	/**
	 * Método auxiliar para formatar um atributo em específico.
	 * @param atributo Nome do campo que deseja exibir
	 * @param valor Valor do campo que deseja exibir
	 * @return Campo formatado
	 */
	private String informar(String atributo, Object valor) {
		return MessageFormat.format("{0}: {1}\n", atributo, valor) ;
	}

	/**
	 * Executa a regra de dormir e comunica o status ao usuário.
	 */
	public void dormir(){
		if (++diasDeVida >= IDADE_MAXIMA){
			isVivo = false;
			throw new TamagochiFaleceuException("{0} chegou a idade de ancião, por isso deve descansar eternamente. Paz!", nome);
		}

		diasAcordado = 0;
		comunicador.comunicar(Cor.VERDE, "{0} dormiu.", nome);

		comunicarStatus();
	}

	/**
	 * Executa a regra de permanecer acordado e comunica o status ao usuário.
	 */
	public void permanecerAcordado(){
		if (diasAcordado++ >= 5){
			comunicador.comunicar(Cor.VERDE, "{0} não aguentou mais, teve que descansar.", nome);
			dormir();
			return;
		}
		comunicador.comunicar(Cor.VERDE, "{0} permaneceu acordado, mesmo com sono...", nome);
		comunicarStatus();
	}

	/**
	 * Executa a regra de comer muito e comunica o status ao usuário.
	 */
	public void comerMuito(){
		engordar(5);
		comunicador.comunicar(Cor.VERDE, "{0} comeu de mais e cansou. Teve que dormir um pouquinho!", nome);
		dormir();
	}

	/**
	 * Executa a regra de comer pouco e comunica o status ao usuário.
	 */
	public void comerPouco(){
		comunicador.comunicar(Cor.VERDE, "{0} comeu só um pouquinho de nada.", nome);
		engordar(1);
		comunicarStatus();
	}

	/**
	 * Executa a regra de não comer e comunica o status ao usuário.
	 */
	public void naoComer(){
		comunicador.comunicar(Cor.VERDE, "{0} não vai comer, apesar de estar morto de fome :(", nome);
		emagrecer(2);
		comunicarStatus();
	}

	/**
	 * Executa a regra de correr e comunica o status ao usuário.
	 */
	public void correr() {
		emagrecer(4);
		comerMuito();
	}

	/**
	 * Executa a regra de caminhar e comunica o status ao usuário.
	 */
	public void caminhar() {
		emagrecer(1);
		comunicarStatus();
	}

	/**
	 * Método auxiliar para emagrecer o Tamagotchi
	 */
	private void emagrecer(int quantidade){
		peso -= quantidade;

		if (peso <= 0){
			isVivo = false;
			peso = 0.0;
			throw new TamagochiFaleceuException("{0} emagredeu de mais e sumiu :|", nome);
		}

		comunicador.comunicar(Cor.VERDE, "{0} emagreceu {1}kg.", nome, quantidade);
	}

	/**
	 * Método auxiliar para engordar o Tamagotchi
	 */
	private void engordar(int quantidade){
		peso += quantidade;

		if (peso > 20){
			isVivo = false;
			throw new TamagochiFaleceuException("{0} excedeu o limite e EXPLODIU!", nome);
		}

		comunicador.comunicar(Cor.VERDE, "{0} engordou {1}kg.", nome, quantidade);
	}

}
