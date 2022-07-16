package com.hooligansofjava;

import net.datafaker.Faker;

import static com.hooligansofjava.Utils.getRandomNumber;

public class CharacterFactory {
    Faker faker;

    public CharacterFactory(Faker faker) {
        this.faker = faker;
    }

    public Wizard createRandomWizard() {
        return new Wizard(faker.name().firstName(), getRandomNumber(TypeOfCharacter.WIZARD.HP_Min, TypeOfCharacter.WIZARD.HP_Max), getRandomNumber(TypeOfCharacter.WIZARD.firstParamMin, TypeOfCharacter.WIZARD.firstParamMax), getRandomNumber(TypeOfCharacter.WIZARD.secondParamMin, TypeOfCharacter.WIZARD.secondParamMax));
    }

    public Warrior createRandomWarrior() {
        return new Warrior(faker.name().firstName(), getRandomNumber(TypeOfCharacter.WARRIOR.HP_Min, TypeOfCharacter.WARRIOR.HP_Max), getRandomNumber(TypeOfCharacter.WARRIOR.firstParamMin, TypeOfCharacter.WARRIOR.firstParamMax), getRandomNumber(TypeOfCharacter.WARRIOR.secondParamMin, TypeOfCharacter.WARRIOR.secondParamMax));
    }


    public  Wizard createWizard(String name, int hp, int mana, int intelligence) {
        return new Wizard(name, hp, mana, intelligence);
    }

    public  Warrior createWarrior(String name, int hp, int stamina, int strength) {
        return new Warrior(name, hp, stamina, strength);
    }
    public Character createRandomCharacter() {
        int random = getRandomNumber(0, 1);
        if (random == 0) {
            return createRandomWizard();
        } else {
            return createRandomWarrior();
        }
    }

}
