package com.hooligansofjava;
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
        {

        return stamina;
    }
    }

    public void setStamina(int stamina) {
            if (stamina < TypeOfCharacter.WARRIOR.firstParamMin){
                this.stamina = TypeOfCharacter.WARRIOR.firstParamMin;
            } else if (stamina > TypeOfCharacter.WARRIOR.firstParamMax) {
                this.stamina = TypeOfCharacter.WARRIOR.firstParamMax;
            } else {
                this.stamina = stamina;
            }

    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if (strength < TypeOfCharacter.WARRIOR.secondParamMin){
            this.strength = TypeOfCharacter.WARRIOR.secondParamMin;
        } else if (strength > TypeOfCharacter.WARRIOR.secondParamMax) {
            this.strength = TypeOfCharacter.WARRIOR.secondParamMax;
        } else {
            this.strength = strength;
        }
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
