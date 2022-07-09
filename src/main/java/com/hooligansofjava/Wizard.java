package com.hooligansofjava;

public class Wizard extends Character implements Attacker {

    private int mana;
    private int intelligence;

    final static String[] nameWizardsArray = {"Urhan", "Ejamar", "Ataz", "Ugovras"};

    public Wizard() {
        super(randomNumber(1000, 1), randomName(), randomNumber(100, 100));
        setIntelligence(randomNumber(49, 1));
        setMana(randomNumber(40, 10));
    }

    private static int randomNumber(int x, int x1) {
      return (int) (Math.random() * x + x1);

    }

    private static String randomName() {
        return nameWizardsArray[(int) (Math.random() * nameWizardsArray.length)];

    }


    public Wizard(int id, String name, int hp, int mana, int intelligence) {
        super(id, name, hp);
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
