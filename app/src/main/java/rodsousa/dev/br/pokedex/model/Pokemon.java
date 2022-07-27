package rodsousa.dev.br.pokedex.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import rodsousa.dev.br.pokedex.R;

public class Pokemon {
    private int id;
    private String name;

    @SerializedName("sprites")
    private ImagePokemon imagePokemon;

    private int height;
    private int weight;

    @SerializedName("types")
    private ArrayList<TypePokemon> characteristic;

    @SerializedName("stats")
    private ArrayList<StatPokemon> pokemonStrength;


    public ArrayList<StatPokemon> getStats() {
        return pokemonStrength;
    }

    public String getIdFormated() {
        return "#" + id;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        double heightInMeter = height*0.1;
        String heightString = String.format("%.2f", heightInMeter);
        heightString = heightString.replace(".", ",");

        return heightString + "m";
    }

    public String getWeight() {
        double weightInKilograms = weight*0.1;
        String weightString = String.format("%.1f", weightInKilograms);
        weightString = weightString.replace(".", ",");

        return weightString + "kg";
    }

    public String getImage() {
        return imagePokemon.getImagePokemon().getDream_world().getFront_default();
    }

    public String getIconMainTypeString() {
        String baseCodeImage = "ic_type_";
        String primaryType = characteristic.get(0).getType().getName();
        return baseCodeImage + primaryType;
    }

    public String primaryType(){
        return characteristic.get(0).getType().getName();
    }

    public String secondaryType(){
        if (characteristic.size() > 1){
            return characteristic.get(1).getType().getName();
        }
        return "";
    }

    public String getBackgroundPokemonSelected(){
        String baseCodeImage = "background_";
        String backgroundType = characteristic.get(0).getType().getName();
        return baseCodeImage + backgroundType;
    }
}
