package com.example.ruby.loltellme.models.item_model;

import com.example.ruby.loltellme.models.Image;
import com.example.ruby.loltellme.models.champion_model.DBChampion;
import com.example.ruby.loltellme.models.champion_model.Recommended;
import com.example.ruby.loltellme.models.champion_model.Skin;
import com.example.ruby.loltellme.models.champion_model.Spell;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

/**
 * Created by Ruby on 06/03/2016.
 */
public class Item{
    int id;
    String name;
    String group; //JungleItems
    String description; //"<stats>+40% Attack Speed<br>+30 Magic Damage on Hit</stats><br><br><unique>UNIQUE Passive - Devouring Spirit:</unique> Takedowns on large monsters and Champions increase the magic damage of this item by +1. Takedowns on Rift Scuttlers increase the magic damage of this item by +2. Takedowns on epic monsters increase the magic damage of this item by +5. At 30 Stacks, your Devourer becomes Sated, granting extra on Hit effects.",
    int depth;
    List<String> from;
    List<String> into;
    //boolean hideFromAll;
    Map<String, Boolean> maps; /* "maps":{"10":false, "1":false, "8":false, "14":false, "11":true, "12":false}*/
    Image image;
    Map<String,Object> gold; /*"gold":{"base":450,"total":2450,"sell":1715,"purchasable":true}*/
    Map<String,Double> stats; //{"PercentAttackSpeedMod":0.4}
    /*
    FlatArmorMod	double
    FlatAttackSpeedMod	double
    FlatBlockMod	double
    FlatCritChanceMod	double
    FlatCritDamageMod	double
    FlatEXPBonus	double
    FlatEnergyPoolMod	double
    FlatEnergyRegenMod	double
    FlatHPPoolMod	double
    FlatHPRegenMod	double
    FlatMPPoolMod	double
    FlatMPRegenMod	double
    FlatMagicDamageMod	double
    FlatMovementSpeedMod	double
    FlatPhysicalDamageMod	double
    FlatSpellBlockMod	double
    PercentArmorMod	double
    PercentAttackSpeedMod	double
    PercentBlockMod	double
    PercentCritChanceMod	double
    PercentCritDamageMod	double
    PercentDodgeMod	double
    PercentEXPBonus	double
    PercentHPPoolMod	double
    PercentHPRegenMod	double
    PercentLifeStealMod	double
    PercentMPPoolMod	double
    PercentMPRegenMod	double
    PercentMagicDamageMod	double
    PercentMovementSpeedMod	double
    PercentPhysicalDamageMod	double
    PercentSpellBlockMod	double
    PercentSpellVampMod	double
    rFlatArmorModPerLevel	double
    rFlatArmorPenetrationMod	double
    rFlatArmorPenetrationModPerLevel	double
    rFlatCritChanceModPerLevel	double
    rFlatCritDamageModPerLevel	double
    rFlatDodgeMod	double
    rFlatDodgeModPerLevel	double
    rFlatEnergyModPerLevel	double
    rFlatEnergyRegenModPerLevel	double
    rFlatGoldPer10Mod	double
    rFlatHPModPerLevel	double
    rFlatHPRegenModPerLevel	double
    rFlatMPModPerLevel	double
    rFlatMPRegenModPerLevel	double
    rFlatMagicDamageModPerLevel	double
    rFlatMagicPenetrationMod	double
    rFlatMagicPenetrationModPerLevel	double
    rFlatMovementSpeedModPerLevel	double
    rFlatPhysicalDamageModPerLevel	double
    rFlatSpellBlockModPerLevel	double
    rFlatTimeDeadMod	double
    rFlatTimeDeadModPerLevel	double
    rPercentArmorPenetrationMod	double
    rPercentArmorPenetrationModPerLevel	double
    rPercentAttackSpeedModPerLevel	double
    rPercentCooldownMod	double
    rPercentCooldownModPerLevel	double
    rPercentMagicPenetrationMod	double
    rPercentMagicPenetrationModPerLevel	double
    rPercentMovementSpeedModPerLevel	double
    rPercentTimeDeadMod	double
    rPercentTimeDeadModPerLevel	double
     */

    public Item() {
    }

    public Item(int id, String name, String group, String description, int depth, List<String> from, List<String> into, Map<String, Boolean> maps, Image image, Map<String, Object> gold, Map<String, Double> stats) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.description = description;
        this.depth = depth;
        this.from = from;
        this.into = into;
        this.maps = maps;
        this.image = image;
        this.gold = gold;
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public List<String> getFrom() {
        return from;
    }

    public void setFrom(List<String> from) {
        this.from = from;
    }

    public List<String> getInto() {
        return into;
    }

    public void setInto(List<String> into) {
        this.into = into;
    }

    public Map<String, Boolean> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Boolean> maps) {
        this.maps = maps;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Map<String, Object> getGold() {
        return gold;
    }

    public void setGold(Map<String, Object> gold) {
        this.gold = gold;
    }

    public Map<String, Double> getStats() {
        return stats;
    }

    public void setStats(Map<String, Double> stats) {
        this.stats = stats;
    }

    public DBItem parseItem(){
        Gson gson = new Gson();

        return new DBItem(
                id,
                name,
                group,
                description,
                depth,
                gson.toJson(from),
                gson.toJson(into),
                gson.toJson(maps),
                gson.toJson(image),
                gson.toJson(gold),
                gson.toJson(stats)
        );
    }

}
