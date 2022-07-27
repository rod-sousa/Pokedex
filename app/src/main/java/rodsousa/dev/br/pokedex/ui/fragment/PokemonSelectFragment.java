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

import rodsousa.dev.br.pokedex.R;
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
        if (pokemon != null) {
            binding.nomePokemon.setText(pokemon.getName());

            String iconMainTypeString = pokemon.getIconMainTypeString();
            int idResourceDrawable = getActivity().getResources().getIdentifier
                    (iconMainTypeString, "drawable", getActivity().getPackageName());
            binding.icType.setImageResource(idResourceDrawable);

            Picasso.get().load(pokemon.getImage()).into(binding.imagemPokemon);

            String backgroundType = pokemon.getBackgroundPokemonSelected();
            int idResourceBackground = getActivity().getResources().getIdentifier
                    (backgroundType, "drawable", getActivity().getPackageName());
            binding.backgorundType.setImageResource(idResourceBackground);

            binding.idPokemon.setText(pokemon.getIdFormated());

            String primaryType = pokemon.primaryType();
            binding.txtTypePrimary.setText(primaryType);
            int idColorPrimary = getResources().getIdentifier
                    (primaryType, "color", getActivity().getPackageName());
            binding.txtTypePrimary.setTextColor(getResources().getColor(idColorPrimary));

            String secondaryType = pokemon.secondaryType();
            if (!secondaryType.equals("")) {
                binding.txtTypeSecondary.setText(secondaryType);
                int idColorSecondary = getResources().getIdentifier
                        (secondaryType, "color", getActivity().getPackageName());
                binding.txtTypeSecondary.setTextColor(getResources().getColor(idColorSecondary));
            }

            binding.txtValueHeight.setText(pokemon.getHeight());
            binding.txtValueWeight.setText(pokemon.getWeight());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        viewModel = null;
    }
}
