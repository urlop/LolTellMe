package com.example.ruby.loltellme.models.champion_model;

import java.util.Map;

/**
 * Created by Ruby on 24/02/2016.
 */
public class ResponseChampion {
    Map<String, Champion> data;

    public ResponseChampion(Map<String, Champion> data) {
        this.data = data;
    }

    public Map<String, Champion> getData() {
        return data;
    }
}
