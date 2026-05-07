package api_pokemon;

import modelo.Pokemon;
import servicio.ApiService;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clase principal — consume la PokéAPI: https://pokeapi.co
 */
public class API_Pokemonn {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApiService apiService = new ApiService();

        System.out.println("=== Buscador de Pokémon ===");
        System.out.print("Ingresa el nombre o ID del Pokémon: ");
        String entrada = scanner.nextLine().trim().toLowerCase();

        System.out.println("\nBuscando: " + entrada + "...");

        try {
            Pokemon pokemon;

            // Si el usuario ingresó un número, buscar por ID; si no, por nombre
            if (entrada.matches("\\d+")) {
                pokemon = apiService.getPokemon(Integer.parseInt(entrada));
            } else {
                pokemon = apiService.getPokemon(entrada);
            }

            if (pokemon != null) {
                System.out.println("\n¡Pokémon encontrado!");
                System.out.println("------------------------");
                System.out.println("ID          : " + pokemon.getId());
                System.out.println("Nombre      : " + pokemon.getName());
                System.out.println("Altura      : " + pokemon.getHeight() + " dm");
                System.out.println("Peso        : " + pokemon.getWeight() + " hg");
                System.out.println("Exp. base   : " + pokemon.getBaseExperience());
                System.out.println("Tipos       : " + pokemon.getTypes());
                System.out.println("Habilidades : " + pokemon.getAbilities());
                System.out.println("------------------------");
            } else {
                System.out.println("No se encontró el Pokémon. Verifica el nombre o ID.");
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al conectar con la PokéAPI:");
            e.printStackTrace();
        }

        scanner.close();
    }
}