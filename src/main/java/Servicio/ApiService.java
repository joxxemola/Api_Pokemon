package servicio;

import modelo.Pokemon;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 * Servicio para consumir la PokéAPI: https://pokeapi.co
 */
public class ApiService {

    // Cliente HTTP reutilizable (HTTP/2)
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    // URL base de la PokéAPI
    private static final String API_URL = "https://pokeapi.co/api/v2/pokemon/";

    /**
     * Obtiene un Pokémon por su ID o nombre.
     * Ejemplos: getPokemon(1) → bulbasaur | getPokemon("pikachu")
     */
    public Pokemon getPokemon(int id) throws IOException, InterruptedException {
        return fetchPokemon(String.valueOf(id));
    }

    public Pokemon getPokemon(String name) throws IOException, InterruptedException {
        return fetchPokemon(name.toLowerCase());
    }

    // Método interno que realiza la petición HTTP y mapea el JSON
    private Pokemon fetchPokemon(String idOrName) throws IOException, InterruptedException {

        // Construir la petición
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(API_URL + idOrName))
                .header("Accept", "application/json")
                .build();

        // Enviar la petición
        HttpResponse<String> response = httpClient.send(
                request, HttpResponse.BodyHandlers.ofString()
        );

        // Verificar el estado HTTP
        if (response.statusCode() != 200) {
            System.out.println("Error HTTP: " + response.statusCode());
            return null;
        }

        // Parsear el JSON y construir el objeto Pokemon
        JSONObject jsonObject = new JSONObject(response.body());
        return new Pokemon(jsonObject);
    }
}