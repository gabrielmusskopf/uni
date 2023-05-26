package br.com.gabrielgmusskopf.unisinos.dominio.execao;

public class IngredientesInsuficientesException extends RestauranteException {

    public IngredientesInsuficientesException(){
        super("Ingredientes insuficientes.");
    }

    public IngredientesInsuficientesException(String... ingredientes){
        super(String.format("Ingredientes insuficientes: %s", ingredientes));
    }

}
