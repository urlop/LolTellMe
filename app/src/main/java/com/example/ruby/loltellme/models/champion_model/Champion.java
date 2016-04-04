package com.example.ruby.loltellme.models.champion_model;

import android.support.annotation.NonNull;

import com.example.ruby.loltellme.models.Image;
import com.example.ruby.loltellme.models.item_model.DBItem;
import com.example.ruby.loltellme.models.item_model.Item;
import com.example.ruby.loltellme.utils.retrofit.Urls;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Ruby on 21/02/2016.
 */
public class Champion implements Comparable<Champion>{
    int id; //35,
    List<String> tags;
    Stat stats;
    List<String> enemytips;
    List<Recommended> recommended;
    Image image;   //"sprite"
    List<Spell> spells;
    List<String> allytips;
    Info info;
    String title;
    String name;
    Passive passive;
    String partype; //"partype":"MP"
    List<Skin> skins;

    boolean active = true;
    boolean freeToPlay = false;

    public Champion() {
    }

    public Champion(int id, List<String> tags, Stat stats, List<String> enemytips, List<Recommended> recommended, Image image, List<Spell> spells, List<String> allytips, Info info, String title, String name, Passive passive, String partype, List<Skin> skins) {
        this.id = id;
        this.tags = tags;
        this.stats = stats;
        this.enemytips = enemytips;
        this.recommended = recommended;
        this.image = image;
        this.spells = spells;
        this.allytips = allytips;
        this.info = info;
        this.title = title;
        this.name = name;
        this.passive = passive;
        this.partype = partype;
        this.skins = skins;
    }

    public Champion(List<String> tags, Stat stats, List<String> enemytips, List<Recommended> recommended, Image image, List<Spell> spells, List<String> allytips, Info info, String title, String name, Passive passive, String partype, List<Skin> skins) {
        this.tags = tags;
        this.stats = stats;
        this.enemytips = enemytips;
        this.recommended = recommended;
        this.image = image;
        this.spells = spells;
        this.allytips = allytips;
        this.info = info;
        this.title = title;
        this.name = name;
        this.passive = passive;
        this.partype = partype;
        this.skins = skins;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getTags() {
        return tags;
    }

    public Stat getStats() {
        return stats;
    }

    public List<String> getEnemytips() {
        return enemytips;
    }

    public List<Recommended> getRecommended() {
        return recommended;
    }

    public Image getImage() {
        return image;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public List<String> getAllytips() {
        return allytips;
    }

    public Info getInfo() {
        return info;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public Passive getPassive() {
        return passive;
    }

    public String getPartype() {
        return partype;
    }

    public List<Skin> getSkins() {
        return skins;
    }

    public String getSplashImage(){
        return Urls.IMAGE_CHAMPION_SPLASH + name + Urls.IMAGE_CHAMPION_SPLASH_END;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setStats(Stat stats) {
        this.stats = stats;
    }

    public void setEnemytips(List<String> enemytips) {
        this.enemytips = enemytips;
    }

    public void setRecommended(List<Recommended> recommended) {
        this.recommended = recommended;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public void setAllytips(List<String> allytips) {
        this.allytips = allytips;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassive(Passive passive) {
        this.passive = passive;
    }

    public void setPartype(String partype) {
        this.partype = partype;
    }

    public void setSkins(List<Skin> skins) {
        this.skins = skins;
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

    public String getSkinUrl(int position){
        return Urls.IMAGE_CHAMPION_SPLASH + name + "_" + skins.get(position).getNum() + ".jpg";
    }

    public String getSkinName(int position){
        return skins.get(position).getName();
    }

    public List<Item> getItems(){
        List<Item> itemList = new ArrayList<>();
        List<String> itemIds = new ArrayList<>();

        for (Recommended r : recommended) {

            if (r.map.equals("SR")) {
                for (Recommended.Block b : r.blocks){
                    for (Recommended.Block.BItem bi : b.items) {
                        itemIds.add(String.valueOf(bi.getId()));
                    }
                }
                /*for (Recommended.Block.BItem bi : r.blocks.get(0).items) {
                    itemIds.add(String.valueOf(bi.getId()));
                }*/
                break;
            }
        }

        String[] strIds = itemIds.toArray(new String[itemIds.size()]);
        String strId = Arrays.toString(strIds);
        String strWhereId = strId.replace("[", "(").replace("]",")");
        List<DBItem> dbItem = DBItem.findWithQuery(DBItem.class, "SELECT * FROM DB_ITEM WHERE item_id IN "+strWhereId+" AND '1' LIKE ?", String.valueOf(1));
        for (DBItem dbi : dbItem) {
            itemList.add(dbi.parseItem());
        }

        return itemList;
    }

    public DBChampion parseChampion(){
        Gson gson = new Gson();

        return new DBChampion(
                id,
                gson.toJson(tags),
                gson.toJson(stats),
                gson.toJson(enemytips),
                gson.toJson(recommended),
                gson.toJson(image),
                gson.toJson(spells),
                gson.toJson(allytips),
                gson.toJson(info),
                title,
                name,
                gson.toJson(passive),
                partype,
                gson.toJson(skins)
                );
    }

    @Override
    public int compareTo(@NonNull Champion another) {
        return getName().compareTo(another.getName());
    }
}


