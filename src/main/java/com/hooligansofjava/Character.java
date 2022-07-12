package com.hooligansofjava;

import net.datafaker.Faker;

import java.util.Random;
import java.util.UUID;

public abstract class Character implements Attacker {
    protected String id;
    protected String name;
    protected boolean isAlive;


    public Character(String name) {
        setId();
        setName(name);
        setAlive(true);
    }

    public String getId() {
        return id;
    }

    public void setId() {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }




}