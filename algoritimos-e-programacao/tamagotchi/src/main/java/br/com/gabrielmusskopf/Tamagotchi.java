package br.com.gabrielmusskopf;

import java.util.Map;

import br.com.gabrielmusskopf.comunicacao.Comunicador;
import lombok.Getter;

@Getter
public class Tamagotchi {
	private static final int IDADE_MAXIMA = 15;

	private Comunicador comunicador;
	private String nome;
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

	public void comunicarStatus(){
		comunicador.comunicarFormatado(Map.of("Nome", nome, "Idade", diasDeVida, "Peso", peso, "Dias acordado", diasAcordado));
	}

	public void dormir(){
		if (!estaVivo()) return;

		diasAcordado = 0;
		diasDeVida++;
		comunicador.comunicar("{0} dormiu. Agora está com {1} dias de vida.", nome, diasDeVida);
	}

	public void permanecerAcordado(){
		if (!estaVivo()) return;

		if (diasAcordado++ >= 5){
			comunicador.comunicar("{0} não aguentou mais, teve que descansar.", nome);
			dormir();
		}
	}

	public void comerMuito(){
		if (!estaVivo()) return;

		engordar(5);

		comunicador.comunicar("{0} comeu de mais e cansou. Teve que dormir um pouquinho!", nome);
		dormir();
	}

	public void comerPouco(){
		if (!estaVivo()) return;

		comunicador.comunicar("{0} comeu só um pouquinho de nada.", nome);
		engordar(1);
		comunicarStatus();
	}

	public void naoComer(){
		if (!estaVivo()) return;

		comunicador.comunicar("{0} não vai comer, apesar de estar morto de fome :(", nome);
		emagrecer(2);
	}

	public void correr() {
		if (!estaVivo()) return;

		emagrecer(4);
		comerMuito();
	}

	public void caminhar() {
		if (!estaVivo()) return;

		emagrecer(1);
	}

	private void emagrecer(int quantidade){
		if (!estaVivo()) return;

		peso -= quantidade;

		if (peso <= 0){
			comunicador.comunicar("{0} emagredeu de mais e sumiu :|", nome);
			isVivo = false;
			return;
		}

		comunicador.comunicar("{0} emagreceu {1}kg.", nome, quantidade);
	}

	private void engordar(int quantidade){
		if (!estaVivo()) return;

		peso += quantidade;

		if (peso > 20){
			comunicador.comunicar("{0} excedeu o limite de peso e EXPLODIU! :O", nome);
			isVivo = false;
			return;
		}

		comunicador.comunicar("{0} engordou {1}kg.", nome, quantidade);
	}

	private boolean estaVivo(){
		return diasDeVida <= IDADE_MAXIMA && isVivo;
	}
}
