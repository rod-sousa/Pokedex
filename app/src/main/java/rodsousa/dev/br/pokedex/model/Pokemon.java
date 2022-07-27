package rodsousa.dev.br.pokedex.model;

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

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getImage() {
        return imagePokemon.getImagePokemon().getDream_world().getFront_default();
    }

    public int getIconMainType() {
        if(characteristic.get(0).getType().getName().equals("normal")){
            return R.drawable.ic_type_normal;
        }
        if(characteristic.get(0).getType().getName().equals("fighting")){
            return R.drawable.ic_type_fighting;
        }
        if(characteristic.get(0).getType().getName().equals("flying")){
            return R.drawable.ic_type_flying;
        }
        if(characteristic.get(0).getType().getName().equals("poison")){
            return R.drawable.ic_type_poison;
        }
        if(characteristic.get(0).getType().getName().equals("ground")){
            return R.drawable.ic_type_ground;
        }
        if(characteristic.get(0).getType().getName().equals("rock")){
            return R.drawable.ic_type_rock;
        }
        if(characteristic.get(0).getType().getName().equals("bug")){
            return R.drawable.ic_type_bug;
        }
        if(characteristic.get(0).getType().getName().equals("ghost")){
            return R.drawable.ic_type_ghost;
        }
        if(characteristic.get(0).getType().getName().equals("steel")){
            return R.drawable.ic_type_steel;
        }
        if(characteristic.get(0).getType().getName().equals("fire")){
            return R.drawable.ic_type_fire;
        }
        if(characteristic.get(0).getType().getName().equals("water")){
            return R.drawable.ic_type_water;
        }
        if(characteristic.get(0).getType().getName().equals("grass")){
            return R.drawable.ic_type_grass;
        }
        if(characteristic.get(0).getType().getName().equals("electric")){
            return R.drawable.ic_type_electric;
        }
        if(characteristic.get(0).getType().getName().equals("psychic")){
            return R.drawable.ic_type_psychic;
        }
        if(characteristic.get(0).getType().getName().equals("ice")){
            return R.drawable.ic_type_ice;
        }
        if(characteristic.get(0).getType().getName().equals("dragon")){
            return R.drawable.ic_type_dragon;
        }
        if(characteristic.get(0).getType().getName().equals("dark")){
            return R.drawable.ic_type_dark;
        }
        if(characteristic.get(0).getType().getName().equals("fairy")){
            return R.drawable.ic_type_fairy;
        }
        return 0;
    }
}
