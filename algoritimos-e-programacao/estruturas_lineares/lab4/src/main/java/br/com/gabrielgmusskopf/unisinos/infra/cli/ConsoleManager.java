package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.comando.App;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.cli.auth.AutenticacaoConsole;
import br.com.gabrielgmusskopf.unisinos.infra.cli.home.HomeConsole;
import br.com.gabrielgmusskopf.unisinos.infra.cli.restaurante.RestauranteConsole;

import java.util.Stack;

public class ConsoleManager implements App {

    private final Stack<Console> historicoConsole;
    private Console atual;
    private final Console autenticacacao;
    private final Console principal;

        public ConsoleManager() {
        this.autenticacacao = new AutenticacaoConsole(this);
        this.principal = new HomeConsole(this);
        this.historicoConsole = new Stack<>();
    }

    @Override
    public void disponibilizar() {
        limparConsole();
        atual.exibir();
    }

    private void limparConsole() {
        //TODO
    }

    public void login() {
        moverPara(autenticacacao);
    }

    public void home(){
        moverPara(principal);
    }

    public void restaurante(Restaurante escolhido){
        moverPara(new RestauranteConsole(escolhido, this));
    }

    public void voltar() {
        if (!historicoConsole.empty()){
            atual = historicoConsole.pop();
        }
    }

    private void moverPara(Console console) {
        historicoConsole.add(atual);
        atual = console;
    }

}
