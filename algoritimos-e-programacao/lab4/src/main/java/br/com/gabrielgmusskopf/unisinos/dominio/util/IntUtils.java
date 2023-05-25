package br.com.gabrielgmusskopf.unisinos.dominio.util;

public class IntUtils {

    public static Response converter(String s) {
        try {
            return new Response(Integer.parseInt(s) , true);
        } catch (NumberFormatException nfe) {
            return new Response(null , false);
        }
    }

    public record Response(Integer num, Boolean res){}

}
