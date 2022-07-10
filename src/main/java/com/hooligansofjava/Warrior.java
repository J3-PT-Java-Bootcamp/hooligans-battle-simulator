package com.hooligansofjava;

public class Warrior extends Character implements Attacker {

    private int stamina;
    private int strength;


    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, hp);
        setStamina(stamina);
        setStrength(strength);
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }


    @Override
    public String toString() {
        return "Warrior{" +
                "id= " + id +
                " name= '" + name + '\'' +
                " hp= " + hp +
                " isAlive= " + isAlive +
                " stamina = " + stamina +
                " strength = " + strength;
    }

    @Override
    public int attack() {
        if (this.stamina >= 5) {
            this.stamina -= 5;
            return this.strength;
        }
        this.stamina++;

        return this.strength / 2;
    }

}
