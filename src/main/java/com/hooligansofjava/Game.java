package com.hooligansofjava;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static ArrayList<Character> partyPlayer1 = new ArrayList<>();
    static ArrayList<Character> partyPlayer2 = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to the game of Hooligans of JAVA: ");
        Faker faker = new Faker();
        Scanner sc = new Scanner(System.in);
        int playerId= 0;
        int players = 0;
        while (players != 2) {
            int player = 0;
            if(playerId==0){
                player = ConsoleQuery.queryToConsole(sc, "Select a player to start the set up:", new String[]{"Player 1", "Player 2"}, 1, 2);
            }else if(playerId==1){
                player = 2;
            }else if (playerId==2){
                player = 1;
            }
            players++;
            playerId = player;
            System.out.println("Hello Player " + player);
            System.out.println("Now, you have to select the number of warriors and wizards.");
            int warriorCount = ConsoleQuery.queryToConsole(sc, "Now, you have to select the number of warriors.", 1, 10);
            generateCharacterLoop(faker, sc, TypeOfCharacter.WARRIOR, player, warriorCount);
            int wizardCount = ConsoleQuery.queryToConsole(sc, "Now, you have to select the number of wizards.", 1, 10);
            generateCharacterLoop(faker, sc, TypeOfCharacter.WIZARD, player, wizardCount);
        }


    }

    private static void generateCharacterLoop(Faker faker, Scanner sc, TypeOfCharacter type, int player, int count) {
        for (int i = 0; i < count; i++) {
            Character newCharacter;
            boolean customCharacter = ConsoleQuery.queryToConsole(sc, "Do you want to create a customized character? (Y/N)");
            if (customCharacter) {
                newCharacter = createCustomizedCharacter(sc, type);
            } else {
                newCharacter = createRandomCharacter(type, faker);
            }
            if (player == 1) {
                partyPlayer1.add(newCharacter);
            } else {
                partyPlayer2.add(newCharacter);
            }
        }
    }

    public static boolean checkValidNumber(String characterNumber) {
        int number ;
        if (characterNumber == null) {
            return false;
        }
        try {
            number = Integer.parseInt(characterNumber);

        } catch (NumberFormatException e) {
            return false;
        }
        return number >= 0 && number <= 50;
    }

    private static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt() % (max - min + 1);
    }

    private static Character createRandomCharacter(TypeOfCharacter type, Faker faker) {
        return CharacterFactory.getCharacter(type, generateRandomNumber(1, 50), faker.name().fullName(), generateRandomNumber(50, 100), generateRandomNumber(10, 100), generateRandomNumber(10, 100));
    }

    private static Character createCharacter(TypeOfCharacter type, String name, int hp, int classFirstAttribute, int classSecondAttribute) {
        // classFirstAttribute and classSecondAttribute corresponds to stamina and strength for Warrior and mana and intelligence for Wizard
        return CharacterFactory.getCharacter(type, generateRandomNumber(1, 50), name, hp, classFirstAttribute, classSecondAttribute);
    }

    private static Character createCustomizedCharacter(Scanner sc, TypeOfCharacter type) {
        // Character attributes to set
        String name;
        int health;
        Integer firstAttribute = null;
        Integer secondAttribute = null;

        switch (type) {
            case WARRIOR -> {
                firstAttribute = ConsoleQuery.queryToConsole(sc, "Define how much stamina do you want to set - (Choose a number between 10 - 50)", 10, 50);
                secondAttribute = ConsoleQuery.queryToConsole(sc, "Define how much strength do you want to set - (Choose a number between 10 - 50)", 10, 50);
            }
            case WIZARD -> {
                firstAttribute = ConsoleQuery.queryToConsole(sc, "Define how much mana do you want to set - (Choose a number between 10 - 50)", 10, 50);
                secondAttribute = ConsoleQuery.queryToConsole(sc, "Define how much intelligence do you want to set - (Choose a number between 10 - 50)", 1, 50);
            }
        }
        health = ConsoleQuery.queryToConsole(sc, " define ho much health do you want to set - (Choose a number between 1 - 100)", 1, 100);
        name = ConsoleQuery.queryToConsoleText(sc, "Finally, set a funny name for you Hero!" );
        System.out.println("Finally, set a funny name for you Hero!");
        return createCharacter(type, name, health, firstAttribute, secondAttribute);
    }
}
