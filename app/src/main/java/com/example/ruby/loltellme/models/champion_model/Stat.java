package com.example.ruby.loltellme.models.champion_model;

/**
 * Created by Ruby on 05/03/2016.
 */
public class Stat {

    int attackrange;
    double mpperlevel;
    double mp;
    double attackdamage;
    double hp;
    double hpperlevel;
    double attackdamageperlevel;
    double armor;
    double mpregenperlevel;
    double hpregen;
    double critperlevel;
    double spellblockperlevel;
    double mpregen;
    double attackspeedperlevel;
    double spellblock;
    double movespeed;
    double attackspeedoffset;
    double crit;
    double hpregenperlevel;
    double armorperlevel;

    public Stat() {
    }

    public Stat(int attackrange, double mpperlevel, double mp, double attackdamage, double hp, double hpperlevel, double attackdamageperlevel, double armor, double mpregenperlevel, double hpregen, double critperlevel, double spellblockperlevel, double mpregen, double attackspeedperlevel, double spellblock, double movespeed, double attackspeedoffset, double crit, double hpregenperlevel, double armorperlevel) {
        this.attackrange = attackrange;
        this.mpperlevel = mpperlevel;
        this.mp = mp;
        this.attackdamage = attackdamage;
        this.hp = hp;
        this.hpperlevel = hpperlevel;
        this.attackdamageperlevel = attackdamageperlevel;
        this.armor = armor;
        this.mpregenperlevel = mpregenperlevel;
        this.hpregen = hpregen;
        this.critperlevel = critperlevel;
        this.spellblockperlevel = spellblockperlevel;
        this.mpregen = mpregen;
        this.attackspeedperlevel = attackspeedperlevel;
        this.spellblock = spellblock;
        this.movespeed = movespeed;
        this.attackspeedoffset = attackspeedoffset;
        this.crit = crit;
        this.hpregenperlevel = hpregenperlevel;
        this.armorperlevel = armorperlevel;
    }

    public int getAttackrange() {
        return attackrange;
    }

    public void setAttackrange(int attackrange) {
        this.attackrange = attackrange;
    }

    public double getMpperlevel() {
        return mpperlevel;
    }

    public void setMpperlevel(double mpperlevel) {
        this.mpperlevel = mpperlevel;
    }

    public double getMp() {
        return mp;
    }

    public void setMp(double mp) {
        this.mp = mp;
    }

    public double getAttackdamage() {
        return attackdamage;
    }

    public void setAttackdamage(double attackdamage) {
        this.attackdamage = attackdamage;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getHpperlevel() {
        return hpperlevel;
    }

    public void setHpperlevel(double hpperlevel) {
        this.hpperlevel = hpperlevel;
    }

    public double getAttackdamageperlevel() {
        return attackdamageperlevel;
    }

    public void setAttackdamageperlevel(double attackdamageperlevel) {
        this.attackdamageperlevel = attackdamageperlevel;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getMpregenperlevel() {
        return mpregenperlevel;
    }

    public void setMpregenperlevel(double mpregenperlevel) {
        this.mpregenperlevel = mpregenperlevel;
    }

    public double getHpregen() {
        return hpregen;
    }

    public void setHpregen(double hpregen) {
        this.hpregen = hpregen;
    }

    public double getCritperlevel() {
        return critperlevel;
    }

    public void setCritperlevel(double critperlevel) {
        this.critperlevel = critperlevel;
    }

    public double getSpellblockperlevel() {
        return spellblockperlevel;
    }

    public void setSpellblockperlevel(double spellblockperlevel) {
        this.spellblockperlevel = spellblockperlevel;
    }

    public double getMpregen() {
        return mpregen;
    }

    public void setMpregen(double mpregen) {
        this.mpregen = mpregen;
    }

    public double getAttackspeedperlevel() {
        return attackspeedperlevel;
    }

    public void setAttackspeedperlevel(double attackspeedperlevel) {
        this.attackspeedperlevel = attackspeedperlevel;
    }

    public double getSpellblock() {
        return spellblock;
    }

    public void setSpellblock(double spellblock) {
        this.spellblock = spellblock;
    }

    public double getMovespeed() {
        return movespeed;
    }

    public void setMovespeed(double movespeed) {
        this.movespeed = movespeed;
    }

    public double getAttackspeedoffset() {
        return attackspeedoffset;
    }

    public void setAttackspeedoffset(double attackspeedoffset) {
        this.attackspeedoffset = attackspeedoffset;
    }

    public double getCrit() {
        return crit;
    }

    public void setCrit(double crit) {
        this.crit = crit;
    }

    public double getHpregenperlevel() {
        return hpregenperlevel;
    }

    public void setHpregenperlevel(double hpregenperlevel) {
        this.hpregenperlevel = hpregenperlevel;
    }

    public double getArmorperlevel() {
        return armorperlevel;
    }

    public void setArmorperlevel(double armorperlevel) {
        this.armorperlevel = armorperlevel;
    }

    public double getAttackspeed() {
        return 1/(1.6 * (1 + attackspeedoffset));
    }
}
