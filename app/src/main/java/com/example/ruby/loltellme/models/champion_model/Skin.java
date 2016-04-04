package com.example.ruby.loltellme.models.champion_model;

import com.example.ruby.loltellme.utils.retrofit.Urls;

/**
 * Created by Ruby on 06/03/2016.
 */
public class Skin {
    int num; //0
    String name; //"default"

    public Skin() {
    }

    public Skin(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
