package rodsousa.dev.br.pokedex.ui.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodsousa.dev.br.pokedex.client.Methods;
import rodsousa.dev.br.pokedex.client.RetrofitClient;
import rodsousa.dev.br.pokedex.model.Pokemon;

public class PokemonViewModel extends ViewModel {

    private static final ArrayList<Pokemon> listPokemon = new ArrayList<>();
    private static final MutableLiveData<ArrayList<Pokemon>> _listPokemon = new MutableLiveData<>();
    private Call<Pokemon> call;

    private static final MutableLiveData<Pokemon> _pokemonSelect = new MutableLiveData<>();

    private static final int POKEMON_QUERY_SIZE = 10;

    private int startPokemonList = 1;
    private int endPokemonList = startPokemonList + POKEMON_QUERY_SIZE;

    public void searchPokemon(String pokemon) {
        listPokemon.clear();

        Methods methods = RetrofitClient.getRETROFIT().create(Methods.class);
        call = methods.searchPokemon(pokemon);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    listPokemon.add(response.body());
                    _listPokemon.setValue(listPokemon);
                } else {
                    //TODO
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                //TODO
            }
        });
    }

    public void retrievePokemonList() {
        Methods methods = RetrofitClient.getRETROFIT().create(Methods.class);
        for (int i = startPokemonList; i < endPokemonList; i++) {
            call = methods.searchPokemon(String.valueOf(i));
            call.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(@NonNull Call<Pokemon> call, @NonNull Response<Pokemon> response) {
                    if (response.isSuccessful()) {
                        listPokemon.add(response.body());
                        if (listPokemon.size() >= endPokemonList-1){
                            _listPokemon.setValue(listPokemon);
                        }

                    } else {
                        //TODO
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Pokemon> call, @NonNull Throwable t) {
                    //TODO
                }
            });
        }
    }

    public void resetPokemonList(){
        listPokemon.clear();
        startPokemonList = 1;
        endPokemonList = startPokemonList + POKEMON_QUERY_SIZE;
    }

    public void loadMorePokemon(){
        startPokemonList += POKEMON_QUERY_SIZE;
        endPokemonList += POKEMON_QUERY_SIZE;
        retrievePokemonList();
    }

    public void pokemonSelect(Pokemon pokemon){
        _pokemonSelect.setValue(pokemon);
    }

    public LiveData<Pokemon> getPokemonSelected(){
        return _pokemonSelect;
    }

    public LiveData<ArrayList<Pokemon>> getListPokemon() {
        return _listPokemon;
    }
}
