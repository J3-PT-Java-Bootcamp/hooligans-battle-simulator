package com.hooligansofjava;

public class Warrior extends Character implements Attacker{

    private int stamina;
    private int strength;

    private static String [] nameWarriorsArray = {"Ragnar","Marco Aurelio","Svenhylde","Hrigjold"};

    public Warrior(){
        super( randomNumber(1000,1), randomName(),randomNumber(100,100));
        setStamina(randomNumber(40,10));
        setStrength(randomNumber(9,1));
    }

    private static int randomNumber(int x, int x1) {
        int number = (int)(Math.random()* x + x1);
        return number;
    }

    private static String randomName() {
        String name = nameWarriorsArray[(int) Math.random()*nameWarriorsArray.length];
        return name;
    }


    public Warrior(int id, String name, int hp, int stamina, int strength) {
        super(id, name, hp);
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
                " strength = "+ strength;
    }

    @Override
    public int attack(){
        if(stamina>=5) {
            stamina -= 5;
            return strength;
        }
        stamina++;
        return strength/2;
    }

}
