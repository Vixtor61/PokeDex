package com.example.pokedex;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.pokedex.Models.Pokemon;
import com.example.pokedex.Utils.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pokemon> pokemons = new ArrayList();
    PokemonAdapter mAdapter;
    RecyclerView mRv_pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycler();

        new FetchSpeciesTask().execute("1");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    public void initRecycler() {

        mRv_pokemon = findViewById(R.id.rv_pokemon_list);


        mAdapter = new PokemonAdapter(pokemons);

        mRv_pokemon.setAdapter(mAdapter);
        mRv_pokemon.setHasFixedSize(false);

        mRv_pokemon.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setPokeInfo(String pokemonInfo) {
        try {


            Gson mPokeJson = new Gson();
            Pokemon pokemon = mPokeJson.fromJson(pokemonInfo, Pokemon.class);
            pokemon.setmType();

            pokemons.add(pokemon);
            mAdapter.notifyDataSetChanged();

        } catch (Exception e) {

            Log.d("JSONEXCEPTION", "BAD1");
        }

    }

    private class FetchPokemonTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... pokemonNumbers) {
            if (pokemonNumbers.length == 0) {
                return null;
            }
            String ID = pokemonNumbers[0];
            URL pokeAPI = NetworkUtils.buildUrl(ID);

            try {
                String result = NetworkUtils.getResponseFromHttpUrl(pokeAPI);

                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String pokemonInfo) {

            if (pokemonInfo != null || !pokemonInfo.equals("")) {

                setPokeInfo(pokemonInfo);

            } else {

            }
        }

    }

    private class FetchSpeciesTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... pokemonNumbers) {
            if (pokemonNumbers.length == 0) {
                return null;
            }
            URL pokeAPI = NetworkUtils.buildUrl();

            try {
                String result = NetworkUtils.getResponseFromHttpUrl(pokeAPI);
                Log.i("VAPOREON", result);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String pokemonInfo) {

            Gson mPokeSpeciesJson = new Gson();
            PokeSpecies mPokeSpeciesObject = mPokeSpeciesJson.fromJson(pokemonInfo, PokeSpecies.class);
            for (int i = 1; i <= mPokeSpeciesObject.mCount; i++) {
                new FetchPokemonTask().execute(Integer.toString(i).trim());
            }
        }

    }

    public class PokeSpecies implements Serializable {
        @SerializedName("count")
        private int mCount;

        public PokeSpecies(int count) {
            this.mCount = count;
        }

        public int getCount() {
            return mCount;
        }

        public void setCount(int count) {
            this.mCount = count;
        }
    }
}
