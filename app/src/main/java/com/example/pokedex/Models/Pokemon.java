package com.example.pokedex.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {

    @SerializedName("types")
    @Expose
    private ArrayList<PokemonType> types;
    @SerializedName("id")
    private int mId;

    private String mImage = "";
    @SerializedName("name")
    @Expose
    private String mName = "";
    @SerializedName("sprites")
    @Expose
    private PokeSprites sprite;
    private String mType = "";

    public Pokemon(ArrayList<PokemonType> types, int mId, String mImage, String mName, String mType) {
        this.types = types;
        this.mId = mId;
        this.mImage = mImage;
        this.mName = mName;

    }

    public ArrayList<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<PokemonType> types) {
        this.types = types;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public PokeSprites getSprite() {
        return sprite;
    }

    public void setSprite(PokeSprites sprite) {
        this.sprite = sprite;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {

        this.mType = mType;
    }

    public void setmType() {
        try {
            this.mType = types.get(1).getType().getName() + " " + types.get(0).getType().getName();
        } catch (Exception e) {
            this.mType = types.get(0).getType().getName();

        }
    }

    public class PokeSprites implements Serializable {
        @SerializedName("front_default")
        @Expose
        private String front_default;

        public PokeSprites(String front_default) {
            this.front_default = front_default;
        }

        public String getFront_default() {
            return front_default;
        }

        public void setFront_default(String front_default) {
            this.front_default = front_default;
        }
    }
}
