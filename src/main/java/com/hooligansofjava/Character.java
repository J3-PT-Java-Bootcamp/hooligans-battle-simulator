package com.hooligansofjava;

public abstract class Character {
    protected int id;
    protected String name;
    protected int hp;
    protected boolean isAlive;



    public Character(int id, String name, int hp) {
        setId(id);
        setName(name);
        setHp(hp);
        setAlive(true);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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