package com.example.pokedex;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.pokedex.Models.Pokemon;
import com.example.pokedex.Utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Pokemon> pokemons = new ArrayList();
    PokemonAdapter adapter;
    RecyclerView rv_pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycler();

        for (int i = 1; i <= 807; i++) {

            new FetchPokemonTask().execute(Integer.toString(i).trim());
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void initRecycler() {

        rv_pokemon = findViewById(R.id.rv_pokemon_list);


        adapter = new PokemonAdapter(pokemons);

        rv_pokemon.setAdapter(adapter);
        rv_pokemon.setHasFixedSize(false);

        rv_pokemon.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setPokeInfo(String pokemonInfo) {
        try {
            String[] PokeTypes = {"", ""};
            String image = "";
            JSONObject json = new JSONObject(pokemonInfo);
            json.get("name");

            try {
                JSONObject sprites = new JSONObject(json.getString("sprites"));
                image = sprites.getString("front_default");

            } catch (JSONException e) {

            }

            try {
                JSONArray jsons = new JSONArray(json.getString("types"));

                Log.i("POKETYPE", jsons.getString(0));

                try {
                    JSONObject type1 = new JSONObject(jsons.getString(0));
                    try {
                        JSONObject type2 = new JSONObject(type1.getString("type"));
                        PokeTypes[0] = type2.getString("name");


                    } catch (JSONException e) {

                    }


                } catch (JSONException e) {

                }

                try {
                    JSONObject type12 = new JSONObject(jsons.getString(1));
                    try {
                        JSONObject type22 = new JSONObject(type12.getString("type"));
                        PokeTypes[1] = type22.getString("name");


                    } catch (JSONException e) {

                    }


                } catch (JSONException e) {

                }


            } catch (JSONException e) {
                Log.d("ERROR1", "Error");
            }

            String PokeTypeString = PokeTypes[0] + " " + PokeTypes[1];

            Log.d("POKENAME", json.getString("name"));
            pokemons.add(new Pokemon(json.getInt("id"), json.getString("name"), PokeTypeString, image));
            Log.d("POKEID", pokemons.get(json.getInt("id") - 1).getmName());
            adapter.notifyDataSetChanged();
            // initRecycler();
        } catch (JSONException e) {

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
                Log.i("VAPOREON", result);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String pokemonInfo) {

            if (pokemonInfo != null || !pokemonInfo.equals("")) {

                Log.i("DITTO", pokemonInfo);
                setPokeInfo(pokemonInfo);

            } else {

            }
        }
    }
}
