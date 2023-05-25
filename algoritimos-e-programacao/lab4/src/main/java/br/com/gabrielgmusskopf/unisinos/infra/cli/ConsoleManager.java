package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.comando.App;
import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.ContextoRepositorio;

public class ConsoleManager implements App {

    private Console atual;
    private Console autenticacacao;
    private Console principal;
    private Console restaurante;
    private ContextoRepositorio ctxRepositorio;

    public ConsoleManager(ContextoRepositorio ctxRepositorio) {
        this.autenticacacao = new AutenticacaoConsole(ctxRepositorio, this);
        this.principal = new MainConsole(ctxRepositorio, this);
        this.ctxRepositorio = ctxRepositorio;
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
        atual.exibir();
    }

    private void limparConsole() {
        //TODO
    }

    public void login() {
        atual = autenticacacao;
    }

    public void home(){
       atual = principal;
    }

    public void restaurante(Restaurante escolhido){
        atual = new RestauranteConsole(escolhido, ctxRepositorio, this);
    }

}
