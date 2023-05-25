package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.comando.App;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;

public class ConsoleManager implements App {

    private Console atual;
    private Console autenticacacao;
    private Console principal;
    private Console restaurante;

    public ConsoleManager(Console primeiro) {
        this.autenticacacao = new AutenticacaoConsole();
        this.principal = new MainConsole();
        this.atual = primeiro;
    }

    /*
    AUTENTICACAO -> MENU -> RESTAURANTE -> PERFIL
                      ^          |           |
                      |          |           |
                      ------------ <---------
     */

    public Console getAtual() {
        return atual;
    }

    @Override
    public void disponibilizar() {
        limparConsole();
        atual.exibir(this);
    }

    private void limparConsole() {
        //TODO
    }

    public void home(){
       atual = principal;
    }

    public void restaurante(Restaurante escolhido){
        atual = new RestauranteConsole(escolhido);
    }

}
