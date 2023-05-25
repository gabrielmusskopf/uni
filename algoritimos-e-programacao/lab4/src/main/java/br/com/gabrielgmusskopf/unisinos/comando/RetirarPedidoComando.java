package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Restaurante;

public class RetirarPedidoComando {

    public void retirar(Restaurante restaurante){
        restaurante.retirarPedido();
    }

}
