package br.com.gabrielgmusskopf.unisinos.dominio.execao;

public class RestauranteException extends RuntimeException {

    public RestauranteException(String msg) {
        super(msg);
    }

    public RestauranteException() {
    }

}
