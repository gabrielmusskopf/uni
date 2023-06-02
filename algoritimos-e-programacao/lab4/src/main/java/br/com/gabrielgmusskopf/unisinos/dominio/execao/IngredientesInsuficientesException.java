package br.com.gabrielgmusskopf.unisinos.dominio.execao;

public class IngredientesInsuficientesException extends RestauranteException {

    private static final long serialVersionUID = -2143892105249764995L;

    public IngredientesInsuficientesException(){
        super("Ingredientes insuficientes.");
    }

}
