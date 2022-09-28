package br.com.gabrielmusskopf;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;

import br.com.gabrielmusskopf.comunicacao.Comunicador;
import br.com.gabrielmusskopf.desejo.Desejo;
import lombok.Setter;

@Setter
public class Jogo {

	private List<Desejo> desejos;
	private Comunicador comunicador;
	private Tamagotchi tamagotchi;
	private Desejo proximoDesejo;

	public Jogo(Consumer<Jogo> parametros){
		parametros.accept(this);
	}

	public Desejo getAcao(){
		if (temProximoDesejo())
			return proximoDesejo;

		proximoDesejo = null;

		var numero = new Random().nextInt(desejos.size());
		return desejos.get(numero);
	}

	public void executarAcao(Desejo desejo, int acaoUsuario){
		desejo.agir(tamagotchi, acaoUsuario)
				.ifPresent(proxDesejo -> this.proximoDesejo = proxDesejo);

	}

	public void iniciar() {
		comunicador.comunicar("\nBem vindo!\n");
	}

	public boolean existe() {
		return tamagotchi.isVivo();
	}

	public boolean temProximoDesejo() {
		return existe() && Objects.nonNull(proximoDesejo);
	}
}
