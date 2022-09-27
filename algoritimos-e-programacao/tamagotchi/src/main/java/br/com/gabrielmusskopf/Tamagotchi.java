package br.com.gabrielmusskopf;

import java.text.MessageFormat;

import lombok.Getter;

@Getter
public class Tamagotchi {
	private static final int IDADE_MAXIMA = 15;

	private String nome;
	private Integer diasDeVida;
	private Double peso;
	private int diasAcordado;
	private boolean isVivo;
	private Comunicador comunicador;

	public Tamagotchi(String nome, Comunicador comunicador) {
		this.nome = nome;
		this.comunicador = comunicador;
		this.diasDeVida = 0;
		this.peso = 1.0;
		this.isVivo = true;
	}

	public void comunicarStatus(){
		comunicador.comunicar(() ->  new StringBuilder()
				.append("Nome: " + nome)
				.append("\nIdade: " + diasDeVida)
				.append("\nPeso: " + peso)
				.append("\nDias acordado: " + diasAcordado)
				.toString());
	}

	public void dormir(){
		if (!estaVivo()) {
			comunicador.comunicar("Infelizmente {0} não está mais entre nós :(", nome);
			return;
		}

		diasAcordado = 0;
		diasDeVida++;
	}

	public void permanecerAcordado(){
		if (!estaVivo()){
			comunicador.comunicar("Infelizmente {0} não está mais entre nós :(\n", nome);
			return;
		}

		if (diasAcordado++ >= 5){
			comunicador.comunicar("{0} não aguentou mais, teve que descansar zZzZ.\n", nome);
			dormir();
		}
	}

	private boolean estaVivo(){
		return diasDeVida <= IDADE_MAXIMA && isVivo;
	}

}
