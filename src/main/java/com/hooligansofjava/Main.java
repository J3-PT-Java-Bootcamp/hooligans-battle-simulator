package com.hooligansofjava;

import net.datafaker.Faker;
import java.util.Random;

public class Main {
    public static void main(String[] args) {


        Character p1 = new Warrior(123,"Spartan", 100,2,2);
        Faker faker = new Faker();

        Warrior p2 = new Warrior();
        Wizard p3 = new Wizard();


        for (int i = 0; i < 50; i++) {
            System.out.println(p3);
//            System.out.println(p2.attack());
            System.out.println(p3.receiveAttack(p3.attack()));
        }
    }

    private int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt() % (max -min + 1);
    }
    private Character createRandomCharacter(String type, Faker faker) {
        return CharacterFactory.getCharacter(type, generateRandomNumber(1, 50), faker.name().fullName(), generateRandomNumber(50, 100), generateRandomNumber(10, 100), generateRandomNumber(10, 100));
    }
    private Character createCharacter(String type, String name, int hp, int classFirstAttribute, int classSecondAttribute) {
        // classFirstAttribute and classSecondAttribute corresponds to stamina and strength for Warrior and mana and intelligence for Wizard
        return CharacterFactory.getCharacter(type, generateRandomNumber(1, 50), name, hp, classFirstAttribute, classSecondAttribute);
    }
}