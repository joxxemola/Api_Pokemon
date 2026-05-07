package modelo;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Modelo que representa un Pokémon obtenido desde la PokéAPI
 */
public class Pokemon {

    private int id;
    private String name;
    private int height;
    private int weight;
    private int baseExperience;
    private String types;
    private String abilities;

    // Constructor que parsea directamente el JSON de la PokéAPI
    public Pokemon(JSONObject json) {
        this.id             = json.getInt("id");
        this.name           = json.getString("name");
        this.height         = json.getInt("height");
        this.weight         = json.getInt("weight");
        this.baseExperience = json.optInt("base_experience", 0);

        // Extraer tipos (puede haber varios, ej: "fire, flying")
        JSONArray typesArray = json.getJSONArray("types");
        StringBuilder typesBuilder = new StringBuilder();
        for (int i = 0; i < typesArray.length(); i++) {
            if (i > 0) typesBuilder.append(", ");
            typesBuilder.append(
                typesArray.getJSONObject(i).getJSONObject("type").getString("name")
            );
        }
        this.types = typesBuilder.toString();

        // Extraer habilidades
        JSONArray abilitiesArray = json.getJSONArray("abilities");
        StringBuilder abilitiesBuilder = new StringBuilder();
        for (int i = 0; i < abilitiesArray.length(); i++) {
            if (i > 0) abilitiesBuilder.append(", ");
            abilitiesBuilder.append(
                abilitiesArray.getJSONObject(i).getJSONObject("ability").getString("name")
            );
        }
        this.abilities = abilitiesBuilder.toString();
    }

    // Getters y Setters
    public int getId()                        { return id; }
    public void setId(int id)                 { this.id = id; }

    public String getName()                   { return name; }
    public void setName(String name)          { this.name = name; }

    public int getHeight()                    { return height; }
    public void setHeight(int height)         { this.height = height; }

    public int getWeight()                    { return weight; }
    public void setWeight(int weight)         { this.weight = weight; }

    public int getBaseExperience()            { return baseExperience; }
    public void setBaseExperience(int be)     { this.baseExperience = be; }

    public String getTypes()                  { return types; }
    public void setTypes(String types)        { this.types = types; }

    public String getAbilities()              { return abilities; }
    public void setAbilities(String ab)       { this.abilities = ab; }

    @Override
    public String toString() {
        return "Pokemon{"
            + "id=" + id
            + ", name='" + name + '\''
            + ", height=" + height
            + ", weight=" + weight
            + ", baseExperience=" + baseExperience
            + ", types='" + types + '\''
            + ", abilities='" + abilities + '\''
            + '}';
    }
}