package rodsousa.dev.br.pokedex.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import rodsousa.dev.br.pokedex.R;
import rodsousa.dev.br.pokedex.databinding.ActivityListaPokemonBinding;
import rodsousa.dev.br.pokedex.model.Pokemon;
import rodsousa.dev.br.pokedex.ui.adapter.ListAdapter;
import rodsousa.dev.br.pokedex.ui.fragment.PokemonSelectFragment;
import rodsousa.dev.br.pokedex.ui.viewmodel.PokemonViewModel;

public class ListaPokemonActivity extends AppCompatActivity {

    private ActivityListaPokemonBinding binding;
    private PokemonViewModel viewModel;
    private ListAdapter adapterList;
    private GridLayoutManager layoutManager;
    private boolean instanceIsSearch = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaPokemonBinding.inflate(getLayoutInflater());

        setRecycler();
        generateViewModelAndPokemonList();

        setTheme(R.style.Theme_Pokedex);
        setContentView(binding.getRoot());

        refresh();

        buttonSearch();

        scrollLastItemAndLoadMore();

        buttonLoadMore();

    }

    private void buttonSearch() {
        binding.icSearch.setOnClickListener(view -> {
            String edtText = binding.edtSearch.getText().toString().trim();
            if (!edtText.equals("")) {
                instanceIsSearch = true;
                viewModel.searchPokemon(edtText);
                binding.buttonLoadMore.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void generateViewModelAndPokemonList() {
        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        viewModel.retrievePokemonList();
        observerReturnEndpointAndSetListAdapter();
    }

    private void setRecycler() {
        layoutManager = new GridLayoutManager(this, 2);
        binding.recyclerView.setLayoutManager(layoutManager);
    }

    private void setListOnAdapter(ArrayList<Pokemon> pokemonList) {
        if (adapterList == null) {
            adapterList = new ListAdapter(this, pokemonList, pokemon -> {
                viewModel.pokemonSelect(pokemon);
                showDialog();
            });
            binding.recyclerView.setAdapter(adapterList);
        } else {
            adapterList.updateListAdapter(pokemonList);
        }
    }

    private void refresh() {
        binding.swiperefresh.setOnRefreshListener(() -> {
            consumeAgainEndpointPokemonList();
            instanceIsSearch = false;
            binding.swiperefresh.setRefreshing(false);
        });
    }

    private void observerReturnEndpointAndSetListAdapter() {
        viewModel.getListPokemon().observe(this, this::setListOnAdapter);
    }

    private void consumeAgainEndpointPokemonList() {
        viewModel.resetPokemonList();
        viewModel.retrievePokemonList();
    }

    private void scrollLastItemAndLoadMore() {
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private boolean loading = true;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (!instanceIsSearch) {
                    if (dy > 0) {
                        if (loading) {
                            int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                            int lastPositionItem = layoutManager.getItemCount() - 1;

                            if ((lastCompletelyVisibleItemPosition == lastPositionItem)) {
                                loading = false;
                                binding.buttonLoadMore.setVisibility(View.VISIBLE);
                                loading = true;
                            } else {
                                binding.buttonLoadMore.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                }
            }
        });
    }

    private void buttonLoadMore() {
        final Handler handler = new Handler();
        binding.buttonLoadMore.setOnClickListener(view -> {
            binding.loadingAnimation.setVisibility(View.VISIBLE);
            binding.buttonLoadMore.setVisibility(View.INVISIBLE);

            viewModel.loadMorePokemon();

            handler.postDelayed(() -> binding.loadingAnimation.setVisibility(View.GONE), 1000);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.resetPokemonList();
    }

    private void showDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        PokemonSelectFragment dialogFragmentPokemon = new PokemonSelectFragment();
        dialogFragmentPokemon.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppDialogTheme);
        dialogFragmentPokemon.show(fragmentManager, "customDialog");
    }
}