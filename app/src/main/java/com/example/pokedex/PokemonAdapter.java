package com.example.pokedex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pokedex.Models.Pokemon;
import com.example.pokedex.Utils.AppConstants;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    private ArrayList<Pokemon> items;

    PokemonAdapter(ArrayList<Pokemon> list) {
        items = list;
    }

    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element_pokemon, parent, false);

        return new ViewHolder(parent.getContext(), view);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(PokemonAdapter.ViewHolder viewHolder, int position) {
        viewHolder.bind(items.get(position));

    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextName, mTextType, mTextId;
        public LinearLayout mLinear;
        Context mContext;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;
            mLinear = itemView.findViewById(R.id.Linear);
            mTextId = itemView.findViewById(R.id.count_element);
            mTextName = itemView.findViewById(R.id.tv_pokemon_name);
            mTextType = itemView.findViewById(R.id.tv_pokemon_type);
            itemView.setOnClickListener(this);

        }

        public void bind(final Pokemon Item) {
            mTextId.setText("Id: " + Integer.toString(Item.getmId()));

            mTextName.setText(Item.getmName());
            mTextType.setText(Item.getmType());

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {

                Pokemon poke = items.get(position);
                Intent intent = new Intent(mContext, PokemonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(AppConstants.TEXT_KEY, poke);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        }

    }
}
