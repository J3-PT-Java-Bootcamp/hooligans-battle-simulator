package com.hooligansofjava;

public class Wizard extends Character implements Attacker {

    private int mana;
    private int intelligence;






    public Wizard( String name, int hp, int mana, int intelligence) {
        super( name, hp);
        setMana(mana);
        setIntelligence(intelligence);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getIntelligence() {
        return intelligence;
    }


    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public String toString() {
        return "Wizard{" + "mana=" + mana + ", intelligence=" + intelligence + ", id=" + id + ", name='" + name + '\'' + ", hp=" + hp + ", isAlive=" + isAlive + "} " + super.toString();
    }

    @Override
    public int attack() {
        if (mana >= 5) {
            mana -= 5;
            return intelligence;
        }
        mana++;
        return 2;
    }


}
