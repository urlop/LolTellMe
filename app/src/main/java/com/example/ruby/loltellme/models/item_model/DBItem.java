package com.example.ruby.loltellme.models.item_model;

import com.example.ruby.loltellme.models.Image;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by Ruby on 06/03/2016.
 */
public class DBItem extends SugarRecord{
    int itemId;
    String name;
    String itemGroup; //JungleItems
    String description; //"<stats>+40% Attack Speed<br>+30 Magic Damage on Hit</stats><br><br><unique>UNIQUE Passive - Devouring Spirit:</unique> Takedowns on large monsters and Champions increase the magic damage of this item by +1. Takedowns on Rift Scuttlers increase the magic damage of this item by +2. Takedowns on epic monsters increase the magic damage of this item by +5. At 30 Stacks, your Devourer becomes Sated, granting extra on Hit effects.",
    int depth;
    String itemFrom;
    String itemInto;
    //boolean hideFromAll;
    String maps; /* "maps":{"10":false, "1":false, "8":false, "14":false, "11":true, "12":false}*/
    String image;
    String gold; /*"gold":{"base":450,"total":2450,"sell":1715,"purchasable":true}*/
    String stats; //{"PercentAttackSpeedMod":0.4}

    public DBItem() {
    }

    public DBItem(int itemId, String name, String itemGroup, String description, int depth, String itemFrom, String itemInto, String maps, String image, String gold, String stats) {
        this.itemId = itemId;
        this.name = name;
        this.itemGroup = itemGroup;
        this.description = description;
        this.depth = depth;
        this.itemFrom = itemFrom;
        this.itemInto = itemInto;
        this.maps = maps;
        this.image = image;
        this.gold = gold;
        this.stats = stats;
    }

    public Item parseItem(){
        Gson gson = new Gson();
        Type listString = new TypeToken<List<String>>() {}.getType();
        Type mapMaps = new TypeToken<Map<String, Boolean>>() {}.getType();
        Type mapGold = new TypeToken<Map<String,Object>>() {}.getType();
        Type mapStats = new TypeToken<Map<String,Double>>() {}.getType();

        Item item = new Item();
        item.setId(itemId);
        item.setName(name);
        item.setGroup(itemGroup);
        item.setDescription(description);
        item.setDepth(depth);
        item.setFrom(gson.<List<String>>fromJson(itemFrom, listString));
        item.setInto(gson.<List<String>>fromJson(itemInto, listString));
        item.setMaps(gson.<Map<String, Boolean>>fromJson(maps, mapMaps));
        item.setImage(gson.fromJson(image, Image.class));
        item.setGold(gson.<Map<String,Object>>fromJson(gold, mapGold));
        item.setStats(gson.<Map<String,Double>>fromJson(stats, mapStats));

        return item;
    }
}
