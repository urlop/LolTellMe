package com.example.ruby.loltellme.models;

import com.example.ruby.loltellme.utils.retrofit.Urls;

/**
 * Created by Ruby on 06/03/2016.
 */
public class Image {

    public static final int IMAGE_TYPE_CHAMPION = 1;
    public static final int IMAGE_TYPE_SPELL = 2;
    public static final int IMAGE_TYPE_ITEM = 3;

    String full;
    String sprite;
    String group; //champion

    public Image() {
    }

    public Image(String full, String sprite, String group) {
        this.full = full;
        this.sprite = sprite;
        this.group = group;
    }

    public String getFull(int type) {
        switch (type){
            case IMAGE_TYPE_CHAMPION:
                return Urls.IMAGE_CHAMPION_SQUARE + full;
            case IMAGE_TYPE_SPELL:
                return Urls.IMAGE_SPELL + full;
            case IMAGE_TYPE_ITEM:
                return Urls.IMAGE_ITEM + full;
            default:
                return full;
        }

    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
