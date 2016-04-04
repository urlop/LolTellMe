package com.example.ruby.loltellme.models.item_model;

import java.util.Map;

/**
 * Created by Ruby on 24/02/2016.
 */
public class ResponseItem {
    Map<String, Item> data;

    public ResponseItem(Map<String, Item> data) {
        this.data = data;
    }

    public Map<String, Item> getData() {
        return data;
    }
}
