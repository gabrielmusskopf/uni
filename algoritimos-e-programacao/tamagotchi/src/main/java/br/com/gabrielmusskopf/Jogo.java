package br.com.gabrielmusskopf;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import lombok.Setter;

@Setter
public class Jogo {

	private List<Desejo> acoes;
	private Comunicador comunicador;
	private Tamagotchi tamagotchi;

	public Jogo(Consumer<Jogo> parametros){
		parametros.accept(this);
	}

	public Desejo getAcao(){
		var numero = new Random().nextInt(acoes.size());
		return acoes.get(numero);
	}

	public void executarAcao(Desejo desejo, int acaoUsuario){
		desejo.agir(tamagotchi, acaoUsuario);
	}

	public void iniciar() {
		comunicador.comunicar(() -> "Bem vindo!\n");
	}
}
