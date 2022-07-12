package com.hooligansofjava;

public class Wizard extends Character {

    private int mana;
    private int hp;
    private int intelligence;


    public Wizard(String name, int hp, int mana, int intelligence) {
        super(name);
        setMana(mana);
        setIntelligence(intelligence);
        setHp(hp);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if(hp <TypeOfCharacter.WIZARD.HP_Min) {
            this.hp = TypeOfCharacter.WIZARD.HP_Min;
        } else if(hp > TypeOfCharacter.WIZARD.HP_Max) {
            this.hp = TypeOfCharacter.WIZARD.HP_Max;
        } else {
            this.hp = hp;
        }
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < TypeOfCharacter.WIZARD.firstParamMin) {
            this.mana = TypeOfCharacter.WIZARD.firstParamMin;
        } else if (mana > TypeOfCharacter.WIZARD.firstParamMax) {
            this.mana = TypeOfCharacter.WIZARD.firstParamMax;
        } else {
            this.mana = mana;
        }
    }

    public int getIntelligence() {
        return intelligence;
    }


    public void setIntelligence(int intelligence) {
        if (intelligence < TypeOfCharacter.WIZARD.secondParamMin) {
            this.intelligence = TypeOfCharacter.WIZARD.secondParamMin;
        } else if (intelligence > TypeOfCharacter.WIZARD.secondParamMax) {
            this.intelligence = TypeOfCharacter.WIZARD.secondParamMax;
        } else {
            this.intelligence = intelligence;
        }
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


    @Override
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
