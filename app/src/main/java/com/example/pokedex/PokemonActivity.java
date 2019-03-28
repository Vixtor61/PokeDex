package com.example.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokedex.Models.Pokemon;
import com.example.pokedex.Utils.AppConstants;

public class PokemonActivity extends AppCompatActivity {
    private TextView name,type,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        Intent mIntent = getIntent();
        setContent();
        if (mIntent!=null) {
            Bundle bundle = mIntent.getExtras();
            Pokemon pokemon = (Pokemon) bundle.getSerializable(AppConstants.TEXT_KEY);
           // Toast.makeText(this,pokemon.getmName(),Toast.LENGTH_SHORT).show();
            //name.setText(pokemon.getmName());
            name.setText(pokemon.getmName());
            type.setText(pokemon.getmType());
            id.setText(Integer.toString(pokemon.getmId()));
            //type.setText(pokemon.getmType());

        }


}

    public void setContent(){
        name = findViewById(R.id.pk_name);
        type  = findViewById(R.id.pk_type);
        id = findViewById(R.id.pk_id);
    }
}
