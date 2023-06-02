package br.com.gabrielgmusskopf.unisinos.dominio.execao;

public class UsuarioException extends RuntimeException {

    private static final long serialVersionUID = 2887193928461048326L;

    public UsuarioException(String msg) {
        super(msg);
    }

}
