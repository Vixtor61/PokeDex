package com.example.pokedex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokedex.Models.Pokemon;
import com.example.pokedex.Utils.AppConstants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokemonActivity extends AppCompatActivity {
    private TextView name,type,id;
    private ImageView img;
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
            type.setText("Types: "+ pokemon.getmType());
            id.setText("Id: "+Integer.toString(pokemon.getmId()));
            AsyncLoadImage imageLoadTask=new AsyncLoadImage();

            imageLoadTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,pokemon.getmImage());



        }


}


    public void setContent(){
        name = findViewById(R.id.pk_name);
        type  = findViewById(R.id.pk_type);
        id = findViewById(R.id.pk_id);
        img = findViewById(R.id.image1);
    }
    private class AsyncLoadImage extends AsyncTask<String, String, Bitmap> {
        URL ImageUrl = null;
        Bitmap bmImg = null;
        InputStream is = null;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                ImageUrl = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) ImageUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);
            } catch (IOException e) {
                //Toast.makeText(PokemonActivity.this,"ASdf",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return bmImg;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(img!=null) {


                img.setImageBitmap(bitmap);
            }else {
                Toast.makeText(PokemonActivity.this,"Error",Toast.LENGTH_SHORT).show();

            }
        }
    }

}
