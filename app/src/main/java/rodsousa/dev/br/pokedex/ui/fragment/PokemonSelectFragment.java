package rodsousa.dev.br.pokedex.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import rodsousa.dev.br.pokedex.databinding.FragmentPokemonSelectBinding;
import rodsousa.dev.br.pokedex.model.Pokemon;
import rodsousa.dev.br.pokedex.ui.viewmodel.PokemonViewModel;

public class PokemonSelectFragment extends DialogFragment {

    private FragmentPokemonSelectBinding binding = null;
    private PokemonViewModel viewModel = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPokemonSelectBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        viewModel.getPokemonSelected().observe(this, pokemon -> {
            bindsFields(pokemon);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void bindsFields(Pokemon pokemon) {
        if (pokemon != null){
            binding.nomePokemon.setText(pokemon.getName());
            binding.icType.setImageResource(pokemon.getIconMainType());
            Picasso.get().load(pokemon.getImage()).into(binding.imagemPokemon);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        viewModel = null;
    }
}
