package com.hooligansofjava;

import net.datafaker.Faker;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Wellcome to the game of Holligans of JAVA: ");
        String player = "";
        while (!checkValidPlayer(player)){
            System.out.println("Please, select: \n 1 --> player 1\n 2 --> player 2");
            player = sc.nextLine();
        }
        System.out.println("Hello player " + player);
        System.out.println("Now, you have to select the number of warriors and wizards.");
        String warriors = "-1";
        while (!checkValidNumber(warriors)){
            System.out.println("Number of warriors: ");
            warriors = sc.nextLine();
            System.out.println(warriors);
        }

        String wizards = "-1";
        while (!checkValidNumber(wizards)){
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
        while (!checkValidNumber(selection)){
            System.out.println("Choose your option: ");
            selection = sc.nextLine();
        }
        switch (selection){
            case "1": askCharacterInfo();
            break;
            case "2": createRandomCharacter();
            break;
            case "3" : //exit game
                break;
            default:
                System.out.println("Not valid number.");
        }






    }
    public static boolean checkValidPlayer(String player1) {

        return player1.equals("1") || player1.equals("2");
    }

    public static boolean checkValidNumber(String characterNumber){
        int number = 0;
        try {
            number = Integer.parseInt(characterNumber);

        }catch (NumberFormatException e){
            return false;
        }

        if( number >= 0 && number <= 50){
            return true;
        }
        return false;
    }

    private static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt() % (max -min + 1);
    }
    private static Character createRandomCharacter(String type, Faker faker) {
        return CharacterFactory.getCharacter(type, generateRandomNumber(1, 50), faker.name().fullName(), generateRandomNumber(50, 100), generateRandomNumber(10, 100), generateRandomNumber(10, 100));
    }
    private static Character createCharacter(String type, String name, int hp, int classFirstAttribute, int classSecondAttribute) {
        // classFirstAttribute and classSecondAttribute corresponds to stamina and strength for Warrior and mana and intelligence for Wizard
        return CharacterFactory.getCharacter(type, generateRandomNumber(1, 50), name, hp, classFirstAttribute, classSecondAttribute);
    }

    private static void askCharacterInfo(Scanner sc){
        var mainMenu = """
                    What type of character do you want to customize?
                    ===============
                    1. WARRIOR
                    2. WIZARD
                                       
                    3. EXIT - Close the application
                    ===============
                    
                    """;
        System.out.println(mainMenu);
        String selection = "-1";
        while (!checkValidNumber(selection)){
            System.out.println("Choose your option: ");
            selection = sc.nextLine();
        }
        switch (selection){
            case "1":
                System.out.println("Cuanta estamina? Elige un número entre X - Y.");
                String stamina = sc.nextLine();
                break;
            case "2":
                System.out.println("Cuanta maná? Elige un número entre X - Y.");
                String mana = sc.nextLine();
                break;
            case "3" : //exit game
                break;
            default:
                System.out.println("Not valid number.");
        }
    }
