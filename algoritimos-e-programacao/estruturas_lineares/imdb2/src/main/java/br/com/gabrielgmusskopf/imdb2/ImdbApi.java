package br.com.gabrielgmusskopf.imdb2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ImdbApi{

	private static final String OMDB_LINK  = "https://imdb-api.com/en/API/SearchTitle/k_6v3fap04/inception";

	public static void get() {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(OMDB_LINK)).build();
		try {
		        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		        if (response.statusCode()==200) {
		            System.out.println(response.body());
		        }
		    } catch (IOException | InterruptedException e) {
		        e.printStackTrace();
		}
	}
}
