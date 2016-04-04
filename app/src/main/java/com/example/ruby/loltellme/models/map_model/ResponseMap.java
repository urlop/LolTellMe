package com.example.ruby.loltellme.models.map_model;

import java.util.Map;

/**
 * Created by Ruby on 24/02/2016.
 */
public class ResponseMap {
    Map<String, com.example.ruby.loltellme.models.map_model.Map> data;

    public ResponseMap(Map<String, com.example.ruby.loltellme.models.map_model.Map> data) {
        this.data = data;
    }

    public Map<String, com.example.ruby.loltellme.models.map_model.Map> getData() {
        return data;
    }
}
