package com.example.ruby.loltellme.models.champion_model;

import com.example.ruby.loltellme.models.Image;

/**
 * Created by Ruby on 06/03/2016.
 *
 * Uses description as description
 */
public class Passive {

    Image image;
    String sanitizedDescription;
    String description; //"description":"Shaco creates an illusion of himself near him, which can attack nearby enemies. (Deals half damage to turrets.) Upon death, it explodes, dealing damage to nearby enemies. ",
    String name;

    public Passive() {
    }

    public Passive(Image image, String sanitizedDescription, String description, String name) {
        this.image = image;
        this.sanitizedDescription = sanitizedDescription;
        this.description = description;
        this.name = name;
    }
}
