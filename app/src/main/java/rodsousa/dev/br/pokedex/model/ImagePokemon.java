package rodsousa.dev.br.pokedex.model;


import com.google.gson.annotations.SerializedName;

public class ImagePokemon {

    @SerializedName("other")
    private ImagePokemonOther imagePokemon;

    public ImagePokemonOther getImagePokemon() {
        return imagePokemon;
    }

    static class ImagePokemonOther{

        @SerializedName("official-artwork")
        private OfficialArtwork officialArt;

        public OfficialArtwork getDream_world() {
            return officialArt;
        }
    }

    static class OfficialArtwork {

        private String front_default;

        public String getFront_default() {
            return front_default;
        }
    }
}
