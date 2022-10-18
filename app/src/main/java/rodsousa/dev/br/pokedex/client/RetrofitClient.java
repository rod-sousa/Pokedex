package rodsousa.dev.br.pokedex.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit RETROFIT;

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    public static Retrofit getRETROFIT(){

        if (RETROFIT == null){
            RETROFIT = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    return RETROFIT;
    }
}
