package br.com.gabrielmusskopf.tamagotchi;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;

import br.com.gabrielmusskopf.tamagotchi.comunicacao.Comunicador;
import br.com.gabrielmusskopf.tamagotchi.desejo.Desejo;
import io.leego.banana.Ansi;
import io.leego.banana.BananaUtils;
import io.leego.banana.Font;
import lombok.Setter;

/**
 * Classe responsável por controlar as ações do jogo. Faz o intermédio do usuário com os desejos e ações tomadas.
 */
@Setter
public class Jogo {

	private static final Random RANDOM = new Random();
	private List<Desejo> desejos;
	private Comunicador comunicador;
	private Tamagotchi tamagotchi;
	private Desejo proximoDesejo;

	public Jogo(Consumer<Jogo> parametros){
		parametros.accept(this);
	}

	/**
	 * Método responsável por retornar, aleatóriamente, um desejo do Tamagotchi dos desejos existentes.
	 * @return Um desejo representado pela interface Desejo.
	 */
	public Desejo getAcao(){
		if (temProximoDesejo())
			return proximoDesejo;

		var numero = RANDOM.nextInt(desejos.size());
		return desejos.get(numero);
	}

	/**
	 * Executa o desejo gerado aleatoriamente, informando qual a ação do usuário. <br/>Caso a ação implique em demais ações,
	 * esse método é responsável por informar a classe Jogo que exite uma próxima ação a ser tomada, antes de gerar a próxima aleatoriamente.
	 * @param desejo Desejo gerado aleateriamente
	 * @param acaoUsuario Inteiro representando a ação escolhida pelo usuário
	 */
	public void executarAcao(Desejo desejo, int acaoUsuario){
		desejo.agir(tamagotchi, acaoUsuario)
				.ifPresentOrElse(proxDesejo -> this.proximoDesejo = proxDesejo, () -> proximoDesejo = null);
	}

	/**
	 * Exibe o início para o usuário. Os textos são formatados utilizando uma biblioteca externa.
	 * @see BananaUtils
	 * @param opcoes Map contendo as opções disponíveis para o usuário logo que inicia o jogo.
	 */
	public void iniciar(Map<String, String> opcoes) {
		comunicador.comunicar(BananaUtils.bananansi("Tamagotchi", Font.BIG_MONEY_NE, Ansi.YELLOW)+"\n\n");
		comunicador.comunicar(Imagem.penguim());
		comunicador.comunicar(BananaUtils.bananansi("Menu", Ansi.YELLOW));

		opcoes.forEach((opcao, valor) -> comunicador.comunicar("{0} - {1}", opcao, valor));
	}

	/**
	 * Método responsável por informar se o jogo deve reiniciar
	 * @return Boleano informando a resposta do jogador
	 */
	public boolean deveReiniciar() {
		comunicador.comunicar("\nDeseja jogar novamente?!\nS - Sim\nN - Não");

		String opcao = comunicador.lerLinha();

		while (!opcao.equalsIgnoreCase("S") && !opcao.equalsIgnoreCase("N")){
			opcao = comunicador.lerLinha();
		}

		return "S".equalsIgnoreCase(opcao) || !"N".equalsIgnoreCase(opcao);
	}

	/**
	 * Método responsável por encapsular a informação de se o tamagotchi está, ou não, vivo
	 * @return Boleano informando se ainda está vivo
	 */
	public boolean existe() {
		return tamagotchi.isVivo();
	}

	/**
	 * Método responsável por informar se o jogo tem um próximo desejo na fila
	 * @return Boleano informando se existe próximo desejo
	 */
	public boolean temProximoDesejo() {
		return existe() && Objects.nonNull(proximoDesejo);
	}
}
