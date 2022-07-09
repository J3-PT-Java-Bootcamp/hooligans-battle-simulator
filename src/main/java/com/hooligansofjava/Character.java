package com.hooligansofjava;

import net.datafaker.Faker;

import java.util.Random;
import java.util.UUID;

public abstract class Character {
    protected String id;
    protected String name;
    protected int hp;
    protected boolean isAlive;



    public Character( String name, int hp) {
        setId();
        setName(name);
        setHp(hp);
        setAlive(true);
    }

    public String getId() {
        return id;
    }
    public void setId( ) {
        UUID uuid = UUID.randomUUID();

        this.id = uuid.toString();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", isAlive=" + isAlive +
                '}';
    }

    public int receiveAttack(int damage){
        if(damage >= getHp()){
            setAlive(false);
            setHp(0);
        }else {
            setHp(getHp()-damage);
        }
        return getHp();
    }



}