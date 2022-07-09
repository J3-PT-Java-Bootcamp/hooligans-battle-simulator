package com.hooligansofjava;

import net.datafaker.Faker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static ArrayList<Character> partyPlayer1 = new ArrayList<>();
    static ArrayList<Character> partyPlayer2 = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Wellcome to the game of Holligans of JAVA: ");
        Faker faker = new Faker();
        Scanner sc = new Scanner(System.in);
        Character character = null;
        String player = "";
        while (!checkValidPlayer(player)) {
            System.out.println("""
                    Select a player to start the set up:
                    ______________
                    1 --> Player 1
                    2 --> Player 2
                    ______________
                    """);
            player = sc.nextLine();
        }
        System.out.println("Hello Player " + player);
        System.out.println("Now, you have to select the number of warriors and wizards.");
        String warriors = "-1";
        while (!checkValidNumber(warriors)) {
            System.out.println("Number of warriors: ");
            warriors = sc.nextLine();
            System.out.println(warriors);
        }
        int numberOfWarriors = Integer.parseInt(warriors);
        while (numberOfWarriors > 0) {
            createCustomizedCharacter(sc, TypeOfCharacter.WARRIOR);
            // preguntar si vol custom warrior
            // si custom warrior -> ask for warrior details
            // generate warrior with asked details
            // add warrior to player's party
            // decrease numberOfWarrior
            // start while again
            // else create Random warrior
            // add warrior to player's party
            // decrease numberOfWarrior
            // start while again
        }
        System.out.println("Now let's set up the wizards");

        String wizards = "-1";
        while (!checkValidNumber(wizards)) {
            System.out.println("Number of wizards: ");
            wizards = sc.nextLine();
            System.out.println(wizards);
        }
        var mainMenu = """
                Do you want customize them?
                ===============
                1. YES 
                2. NO 
                3. EXIT - close the application
                ===============
                """;

        System.out.println(mainMenu);
        String selection = "-1";

        while (!checkValidNumber(selection)) {
            System.out.println("Choose your option: ");
            selection = sc.nextLine();
        }
        switch (selection) {
            case "1":
                character = askCharacterInfo(sc);
                break;
            case "2":
                String typeOfCharacter = null;
                while (!checkValidNumber(typeOfCharacter)) {
                    System.out.println("""
                What type of character do you want to customize?
                ===============
                1. WARRIOR
                2. WIZARD
                3. EXIT - Close the application
                ===============
                """);
                    typeOfCharacter = sc.nextLine();
                }
                character = createRandomCharacter(typeOfCharacter, faker);
                break;
            case "3": //exit game
                break;
            default:
                System.out.println("Not valid number.");
        }

        if (character != null) {
            if (player.equals("1")) {
                partyPlayer1.add(character);
            } else {
                partyPlayer2.add(character);
            }
        }
    }

    public static boolean checkValidPlayer(String player1) {

        return player1.equals("1") || player1.equals("2");
    }

    public static boolean checkValidNumber(String characterNumber) {
        int number = 0;
        if(characterNumber == null) {
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

    private static Character createRandomCharacter(String type, Faker faker) {
        return CharacterFactory.getCharacter(type, generateRandomNumber(1, 50), faker.name().fullName(), generateRandomNumber(50, 100), generateRandomNumber(10, 100), generateRandomNumber(10, 100));
    }

    private static Character createCharacter(String type, String name, int hp, int classFirstAttribute, int classSecondAttribute) {
        // classFirstAttribute and classSecondAttribute corresponds to stamina and strength for Warrior and mana and intelligence for Wizard
        return CharacterFactory.getCharacter(type, generateRandomNumber(1, 50), name, hp, classFirstAttribute, classSecondAttribute);
    }

    private static Character createCustomizedCharacter(Scanner sc, TypeOfCharacter type) {
        // Character attributes to set
        String name;
        String health = null;
        String firstAttribute = null;
        String secondAttribuite = null;

        switch (type) {
            case TypeOfCharacter.WARRIOR:
                while (!checkValidNumber(firstAttribute)) {
                    System.out.println("Define how much stamina do you want to set - (Choose a number bewteen 10 - 50)");
                    firstAttribute = sc.nextLine();
                }
                while (!checkValidNumber(secondAttribuite)) {
                    System.out.println("Define how much strength do you want to set - (Choose a number bewteen 1 - 10)");
                    secondAttribuite = sc.nextLine();
                }
                break;
            case TypeOfCharacter.WIZARD:
                while(!checkValidNumber(firstAttribute)) {
                    System.out.println("Define how much mana do you want to set - (Choose a number between 10 - 50)");
                    firstAttribute = sc.nextLine();
                }
                while(!checkValidNumber(secondAttribuite)) {
                    System.out.println("Define how much intelligence do you want to set - (Choose a number between 1 - 50");
                    secondAttribuite = sc.nextLine();
                }
                break;
            case "3": //exit game
                break;
            default:
                System.out.println("Not valid number.");
        }
        while(!checkValidNumber(health)) {
            System.out.println("Define how much health do you want to set - (Choose a number between 1 - 100)");
            health = sc.nextLine();
        }
        System.out.println("Finally, set a funny name for you Hero!");
        name = sc.nextLine();

        return createCharacter(typeOfCharacter, name, Integer.parseInt(health), Integer.parseInt(firstAttribute), Integer.parseInt(secondAttribuite));
    }
}
