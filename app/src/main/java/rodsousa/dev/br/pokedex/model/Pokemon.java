package rodsousa.dev.br.pokedex.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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


    public String getIdFormated() {
        return "#" + id;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public String getName() {
        return name.substring(0,1).toUpperCase().concat(name.substring(1));
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

    public int getStatHp(){
        return pokemonStrength.get(0).getBase_stat();
    }

    public int getStatAttack(){
        return pokemonStrength.get(1).getBase_stat();
    }

    public int getStatDefense(){
        return pokemonStrength.get(2).getBase_stat();
    }

    public int getStatSpAttack(){
        return pokemonStrength.get(3).getBase_stat();
    }

    public int getStatSpDefense(){
        return pokemonStrength.get(4).getBase_stat();
    }

    public int getStatSpeed(){
        return pokemonStrength.get(5).getBase_stat();
    }
}
