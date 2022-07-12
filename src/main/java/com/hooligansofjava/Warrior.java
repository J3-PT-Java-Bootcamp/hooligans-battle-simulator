package com.hooligansofjava;

import net.datafaker.Faker;

import static com.hooligansofjava.Utils.getRandomNumber;

public class Warrior extends Character {

    private int stamina;
    private int hp;
    private int strength;


    public Warrior(String name, int hp, int stamina, int strength) {
        super(name);
        setStamina(stamina);
        setStrength(strength);
        setHp(hp);
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        if (hp < TypeOfCharacter.WARRIOR.HP_Min) {
            this.hp = TypeOfCharacter.WARRIOR.HP_Min;
        } else if (hp > TypeOfCharacter.WARRIOR.HP_Max) {
            this.hp = TypeOfCharacter.WARRIOR.HP_Max;
        } else {
            this.hp = hp;
        }
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

    @Override
    public int receiveAttack(int damage) {
        if (damage >= getHp()) {
            setAlive(false);
            setHp(0);
        } else {
            setHp(getHp() - damage);
        }
        return getHp();
    }

}
