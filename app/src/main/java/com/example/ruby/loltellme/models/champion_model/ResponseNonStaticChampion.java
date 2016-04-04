package com.example.ruby.loltellme.models.champion_model;

import java.util.List;
import java.util.Map;

/**
 * Created by Ruby on 24/02/2016.
 */
public class ResponseNonStaticChampion {
    List<ChampionNonStatic> champions;

    public ResponseNonStaticChampion(List<ChampionNonStatic> champions) {
        this.champions = champions;
    }

    public List<ChampionNonStatic> getChampions() {
        return champions;
    }
}
