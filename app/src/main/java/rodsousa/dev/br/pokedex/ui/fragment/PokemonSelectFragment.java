package rodsousa.dev.br.pokedex.ui.fragment;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
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



            ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(getContext(), idColorPrimary));

            binding.progressHp.setBackgroundTintList(colorStateList);
            binding.progressAttack.setBackgroundTintList(colorStateList);
            binding.progressDefense.setBackgroundTintList(colorStateList);
            binding.progressSpAttack.setBackgroundTintList(colorStateList);
            binding.progressSpDefense.setBackgroundTintList(colorStateList);
            binding.progressSpeed.setBackgroundTintList(colorStateList);

            binding.backgroundProgressHp.setBackgroundTintList(colorStateList);
            binding.backgroundProgressAttack.setBackgroundTintList(colorStateList);
            binding.backgroundProgressDefense.setBackgroundTintList(colorStateList);
            binding.backgroundProgressSpAttack.setBackgroundTintList(colorStateList);
            binding.backgroundProgressSpDefense.setBackgroundTintList(colorStateList);
            binding.backgroundProgressSpeed.setBackgroundTintList(colorStateList);

            int statHp = pokemon.getStatHp();
            int statAttack = pokemon.getStatAttack();
            int statDefense = pokemon.getStatDefense();
            int statSpAttack = pokemon.getStatSpAttack();
            int statSpDefense = pokemon.getStatSpDefense();
            int statSpeed = pokemon.getStatSpeed();

            Log.i("TAG", "" + pokemon.getStatSpAttack());

            binding.progressHp.setLayoutParams(percentSizeStats(statHp, binding.progressHp));
            binding.progressAttack.setLayoutParams(percentSizeStats(statAttack, binding.progressAttack));
            binding.progressDefense.setLayoutParams(percentSizeStats(statDefense, binding.progressDefense));
            binding.progressSpAttack.setLayoutParams(percentSizeStats(statSpAttack, binding.progressSpAttack));
            binding.progressSpDefense.setLayoutParams(percentSizeStats(statSpDefense, binding.progressSpDefense));
            binding.progressSpeed.setLayoutParams(percentSizeStats(statSpeed, binding.progressSpeed));
        }
    }

    public ViewGroup.LayoutParams percentSizeStats(int value, ImageView imageView){
        //TODO util e definir constants maximo
        int valueFormatedDp = (220*value)/130;

        if (valueFormatedDp > 220){
            valueFormatedDp = 220;
        }

        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) imageView.getLayoutParams();
        params.width = (int) (valueFormatedDp * getContext().getResources().getDisplayMetrics().density);
        return params;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        viewModel = null;
    }
}
