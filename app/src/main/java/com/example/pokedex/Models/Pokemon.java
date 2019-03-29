package com.example.pokedex.Models;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private int mId;
    private String mImage = "";
    private String mName = "";
    private String mType = "";

    public Pokemon(int id,String name,String type,String Image) {
              mId = id;
              mName = name;
              mType = type;
              mImage = Image;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
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
}

