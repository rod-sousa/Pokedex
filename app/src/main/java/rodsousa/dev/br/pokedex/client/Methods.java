package rodsousa.dev.br.pokedex.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rodsousa.dev.br.pokedex.model.Pokemon;

public interface Methods {

    @GET("pokemon/{id}")
    Call <Pokemon> searchPokemon(@Path("id")String id);
}
