package com.example.ruby.loltellme.models.map_model;

/**
 * Created by Ruby on 06/03/2016.
 */
public class Map {
    int mapId;
    String mapName; //NewTwistedTreeline, SummonersRift, WIPUpdate, CrystalScar, SummonersRiftNew, ProvingGroundsNew

    public Map() {
    }

    public Map(int mapId, String mapName) {
        this.mapId = mapId;
        this.mapName = mapName;
    }

    public int getMapId() {
        return mapId;
    }

    public String getMapName() {
        return mapName;
    }
}
