package com.example.ruby.loltellme.models.champion_model;

import com.example.ruby.loltellme.models.Image;

import java.util.List;

/**
 * Created by Ruby on 06/03/2016.
 *
 * Uses tooltip as description
 */
public class Spell {

    Image image;
    String sanitizedDescription;
    String description; //"description":"Shaco creates an illusion of himself near him, which can attack nearby enemies. (Deals half damage to turrets.) Upon death, it explodes, dealing damage to nearby enemies. ",
    String name;

    List<Double> range; //[1200,1200,1200]
    //LevelTip leveltip
    String resource; //{{ cost }} Mana
    int maxrank;
    List<String> effectBurn;
    List<Double> cooldown; //[100,90,80]
    List<Integer> cost;
    List<Var> vars; //[{"link":"bonusattackdamage","coeff":[1]}]
    String rangeBurn; //"1200"
    //String costType;  //"Mana"
    List<List<Double>> effect; //[null,[50,90,130,170,210]]
    String cooldownBurn;//"100/90/80"
    //String sanitizedTooltip;
    //String key;
    String costBurn;
    String tooltip; //"tooltip":"Shaco vanishes briefly and reappears with a clone that lasts up to 18 seconds. The clone deals 75% of Shaco's damage and receives {{ e2 }}% increased damage.<br><br>On death, the clone detonates, dealing {{ e1 }} <span class=\"color99FF99\">(+{{ a1 }})<\/span> magic damage to nearby enemies.<br><br><span class=\"color99FF99\">The clone can be controlled by holding the alt key and using the right mouse button or by reactivating this ability.<\/span>"

    public Spell() {
    }

    public Spell(Image image, String sanitizedDescription, String description, String name, List<Double> range, String resource, int maxrank, List<String> effectBurn, List<Double> cooldown, List<Integer> cost, List<Var> vars, String rangeBurn, List<List<Double>> effect, String cooldownBurn, String costBurn, String tooltip) {
        this.image = image;
        this.sanitizedDescription = sanitizedDescription;
        this.description = description;
        this.name = name;
        this.range = range;
        this.resource = resource;
        this.maxrank = maxrank;
        this.effectBurn = effectBurn;
        this.cooldown = cooldown;
        this.cost = cost;
        this.vars = vars;
        this.rangeBurn = rangeBurn;
        this.effect = effect;
        this.cooldownBurn = cooldownBurn;
        this.costBurn = costBurn;
        this.tooltip = tooltip;
    }

    public Image getImage() {
        return image;
    }

    public String getSanitizedDescription() {
        return sanitizedDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public List<Double> getRange() {
        return range;
    }

    public String getResource() {
        return resource;
    }

    public int getMaxrank() {
        return maxrank;
    }

    public List<String> getEffectBurn() {
        return effectBurn;
    }

    public List<Double> getCooldown() {
        return cooldown;
    }

    public List<Integer> getCost() {
        return cost;
    }

    public List<Var> getVars() {
        return vars;
    }

    public String getRangeBurn() {
        return rangeBurn;
    }

    public List<List<Double>> getEffect() {
        return effect;
    }

    public String getCooldownBurn() {
        return cooldownBurn;
    }

    public String getCostBurn() {
        return costBurn;
    }

    public String getTooltip() {
        return tooltip;
    }

    public class Var{
        String link; //bonusattackdamage
        List<Double> coeff; //[1]

        public Var() {
        }

        public Var(String link, List<Double> coeff) {
            this.link = link;
            this.coeff = coeff;
        }

        public String getLink() {
            return link;
        }

        public List<Double> getCoeff() {
            return coeff;
        }
    }
}
