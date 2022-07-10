package com.hooligansofjava;

import com.google.gson.Gson;
import net.datafaker.Faker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static ArrayList<Character> partyPlayer1 = new ArrayList<>();
    static ArrayList<Character> partyPlayer2 = new ArrayList<>();

    public Game(ArrayList<Character> partyPlayer1, ArrayList<Character> partyPlayer2) {
        Game.partyPlayer1 = partyPlayer1;
        Game.partyPlayer2 = partyPlayer2;

    }

    public Game() {

    }

    public static String generateJson() {
        Gson gson = new Gson();
        return "{\"game\":[{\"player1\":" + gson.toJson(partyPlayer1) + "},".concat("{\"player2\":" + gson.toJson(partyPlayer2) + "}]}");

    }

    public static void parseJson(String JSONText) {


    }

    public Game startConsole(){
        System.out.println("Welcome to the game of Hooligans of JAVA: ");
        Faker faker = new Faker();
        Scanner sc = new Scanner(System.in);
        int playerId = 0;
        int players = 0;
        boolean readPreviousParty = ConsoleQuery.queryToConsole(sc, "Read previous party?");
        if (readPreviousParty) {
            try {
            parseJson(FileReadAndWrite.readFile());
            players = 2;
            }catch (IOException e){
                System.out.println("error reading file, Initiating normal game...");
            }
        }
        while (players != 2) {
            int player = 0;
            if (playerId == 0) {
                player = ConsoleQuery.queryToConsole(sc, "Select a player to start the set up:", new String[]{"Player 1", "Player 2"}, 1, 2);
            } else if (playerId == 1) {
                player = 2;
            } else if (playerId == 2) {
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
        System.out.println("end reading data from terminal");
        for (Character character : partyPlayer1) {
            System.out.println(character);

        }
        for (Character character : partyPlayer2) {
            System.out.println(character);

        }

        return null;
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
            System.out.println(newCharacter);
            if (player == 1) {
                partyPlayer1.add(newCharacter);
            } else {
                partyPlayer2.add(newCharacter);
            }
        }
    }

    public static boolean checkValidNumber(String characterNumber) {
        int number;
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
        String name;
        int health;
        Integer firstAttribute = null;
        Integer secondAttribute = null;

        switch (type) {
            case WARRIOR -> {
                firstAttribute = ConsoleQuery.queryToConsole(sc, "Define how much stamina do you want to set - (Choose a number between 10 - 50)", TypeOfCharacter.WARRIOR.firstParamMin, TypeOfCharacter.WARRIOR.firstParamMax);
                secondAttribute = ConsoleQuery.queryToConsole(sc, "Define how much strength do you want to set - (Choose a number between 10 - 50)", TypeOfCharacter.WARRIOR.secondParamMin, TypeOfCharacter.WARRIOR.secondParamMax);
            }
            case WIZARD -> {
                firstAttribute = ConsoleQuery.queryToConsole(sc, "Define how much mana do you want to set - (Choose a number between 10 - 50)", TypeOfCharacter.WIZARD.firstParamMin, TypeOfCharacter.WIZARD.secondParamMax);
                secondAttribute = ConsoleQuery.queryToConsole(sc, "Define how much intelligence do you want to set - (Choose a number between 10 - 50)", TypeOfCharacter.WIZARD.secondParamMin, TypeOfCharacter.WIZARD.secondParamMax);
            }
        }
        health = ConsoleQuery.queryToConsole(sc, " define ho much health do you want to set - (Choose a number between 1 - 100)", 1, 100);
        name = ConsoleQuery.queryToConsoleText(sc, "Finally, set a funny name for you Hero!");
        System.out.println("Finally, set a funny name for you Hero!");
        return createCharacter(type, name, health, firstAttribute, secondAttribute);
    }
}
