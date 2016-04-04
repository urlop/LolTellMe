package com.example.ruby.loltellme.models.champion_model;

import java.util.List;

/**
 * Created by Ruby on 05/03/2016.
 */
public class Recommended {
    String title;   //Beginner
    String map; // SR: Summoners Rift, HA: Howling Abyss, TT: Twisted Treeline
    List<Block> blocks;
    //String type //riot
    //Sring mode //INTRO

    public class Block {
        //boolean recMath //false
        List<BItem> items;
        //String type; //beginner_starter

        public class BItem {
            int id; //TODO change
            int count;

            public int getId() {
                return id;
            }

            public int getCount() {
                return count;
            }
        }
    }

}
