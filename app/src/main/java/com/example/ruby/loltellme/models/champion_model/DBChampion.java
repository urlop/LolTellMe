package com.example.ruby.loltellme.models.champion_model;

import com.example.ruby.loltellme.models.Image;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;

import java.lang.reflect.Type;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Ruby on 21/02/2016.
 */
public class DBChampion extends SugarRecord {
    int championId;
    String tags;
    String stats;
    String enemytips;
    String recommended;
    String image;
    String spells;
    String allytips;
    String info;
    String title;
    String name;
    String passive;
    String partype;
    String skins;

    boolean active = true;
    boolean freeToPlay = false;

    public DBChampion() {
    }

    public DBChampion(int championId, String tags, String stats, String enemytips, String recommended, String image, String spells, String allytips, String info, String title, String name, String passive, String partype, String skins) {
        this.championId = championId;
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

    public DBChampion(String tags, String stats, String enemytips, String recommended, String image, String spells, String allytips, String info, String title, String name, String passive, String partype, String skins) {
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

    public DBChampion(int championId, String tags, String image, String name) {
        this.championId = championId;
        this.tags = tags;
        this.image = image;
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public String getEnemytips() {
        return enemytips;
    }

    public void setEnemytips(String enemytips) {
        this.enemytips = enemytips;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSpells() {
        return spells;
    }

    public void setSpells(String spells) {
        this.spells = spells;
    }

    public String getAllytips() {
        return allytips;
    }

    public void setAllytips(String allytips) {
        this.allytips = allytips;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassive() {
        return passive;
    }

    public void setPassive(String passive) {
        this.passive = passive;
    }

    public String getPartype() {
        return partype;
    }

    public void setPartype(String partype) {
        this.partype = partype;
    }

    public String getSkins() {
        return skins;
    }

    public void setSkins(String skins) {
        this.skins = skins;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
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

    public Champion parseChampion() {
        Gson gson = new Gson();
        Type listString = new TypeToken<List<String>>() {}.getType();
        Type listRecommended = new TypeToken<List<Recommended>>() {}.getType();
        Type listSpell = new TypeToken<List<Spell>>() {}.getType();
        Type listSkin = new TypeToken<List<Skin>>() {}.getType();

        Champion champion = new Champion();
        champion.setId(championId);
        champion.setTags(gson.<List<String>>fromJson(tags, listString));
        champion.setStats(gson.fromJson(stats, Stat.class));
        champion.setEnemytips(gson.<List<String>>fromJson(enemytips, listString));
        champion.setRecommended(gson.<List<Recommended>>fromJson(recommended, listRecommended));
        champion.setImage(gson.fromJson(image, Image.class));
        champion.setSpells(gson.<List<Spell>>fromJson(spells, listSpell));
        champion.setAllytips(gson.<List<String>>fromJson(allytips, listString));
        champion.setInfo(gson.fromJson(info, Info.class));
        champion.setTitle(title);
        champion.setName(name);
        champion.setPassive(gson.fromJson(passive, Passive.class));
        champion.setPartype(partype);
        champion.setSkins(gson.<List<Skin>>fromJson(skins, listSkin));
        champion.setActive(active);
        champion.setFreeToPlay(freeToPlay);
        return champion;
    }

    public Champion parseImageChampion() {
        Gson gson = new Gson();
        Type listString = new TypeToken<List<String>>() {}.getType();

        Champion champion = new Champion();
        champion.setId(championId);
        champion.setTags(gson.<List<String>>fromJson(tags, listString));
        champion.setImage(gson.fromJson(image, Image.class));
        champion.setName(name);

        return champion;
    }

}


