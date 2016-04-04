package com.example.ruby.loltellme.models.champion_model;

/**
 * Created by Ruby on 06/03/2016.
 */
public class Info {
    int defense;
    int magic;
    int difficulty;
    int attack;

    public Info() {
    }

    public Info(int defense, int magic, int difficulty, int attack) {
        this.defense = defense;
        this.magic = magic;
        this.difficulty = difficulty;
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
