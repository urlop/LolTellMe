package com.example.ruby.loltellme.models.champion_model;

import android.support.annotation.NonNull;

import com.example.ruby.loltellme.models.Image;
import com.example.ruby.loltellme.models.item_model.DBItem;
import com.example.ruby.loltellme.models.item_model.Item;
import com.example.ruby.loltellme.utils.retrofit.Urls;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruby on 21/02/2016.
 */
public class ChampionNonStatic{
    int id; //35,
    boolean active;
    boolean freeToPlay;

    public ChampionNonStatic() {
    }

    public ChampionNonStatic(int id, boolean active, boolean freeToPlay) {
        this.id = id;
        this.active = active;
        this.freeToPlay = freeToPlay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isFreeToPlay() {
        return freeToPlay;
    }

    public void setFreeToPlay(boolean freeToPlay) {
        this.freeToPlay = freeToPlay;
    }
}


