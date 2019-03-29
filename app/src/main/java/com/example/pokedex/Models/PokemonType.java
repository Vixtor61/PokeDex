package com.example.pokedex.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PokemonType implements Serializable {
    @SerializedName("slot")
    private String slot;
    @SerializedName("type")
    private PokeType type;

    public PokemonType(String slot, PokeType type) {
        this.slot = slot;
        this.type = type;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public PokeType getType() {
        return type;
    }

    public void setType(PokeType type) {
        this.type = type;
    }

    public class PokeType implements Serializable {
        @SerializedName("name")
        private String name;
        @SerializedName("url")
        private String url;

        public PokeType(String name, String url) {

            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
