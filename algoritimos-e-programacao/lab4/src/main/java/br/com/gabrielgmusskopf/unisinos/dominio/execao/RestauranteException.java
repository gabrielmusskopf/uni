package br.com.gabrielgmusskopf.unisinos.dominio.execao;

public class RestauranteException extends RuntimeException {

    private static final long serialVersionUID = -6344919147717731846L;

    public RestauranteException(String msg) {
        super(msg);
    }

}
