package com.hooligansofjava;

import com.google.gson.Gson;
import net.datafaker.Faker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    final ArrayList<Character> partyPlayer1 = new ArrayList<>();
    final ArrayList<Character> partyPlayer2 = new ArrayList<>();

    public Game() {

    }
    //generar 2 arrays (player 1 y player 2) con size n
    //llenar esos arrays de random characters
    public void randomParty() {
        Faker fc = new Faker();
        final CharacterFactory characterFactory = new CharacterFactory(fc);
        int index = generateRandomNumber(1, 100);
        for (int i = 0; i < index; i++) {
            partyPlayer1.add(characterFactory.createRandomCharacter());
            partyPlayer2.add(characterFactory.createRandomCharacter());
        }
    }

    public String generateJson() {
        Gson gson = new Gson();
        ArrayList<Wizard> player1Wizards;
        player1Wizards = partyPlayer1.stream().filter(x -> x instanceof Wizard).map(x -> (Wizard) x).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        ArrayList<Warrior> player1Warriors;
        player1Warriors = partyPlayer1.stream().filter(x -> x instanceof Warrior).map(x -> (Warrior) x).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        ArrayList<Wizard> player2Wizards;
        player2Wizards = partyPlayer2.stream().filter(x -> x instanceof Wizard).map(x -> (Wizard) x).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        ArrayList<Warrior> player2Warriors;
        player2Warriors = partyPlayer2.stream().filter(x -> x instanceof Warrior).map(x -> (Warrior) x).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return "%s Warriors::%s player2::%sWarriors::%s".formatted(gson.toJson(player1Wizards), gson.toJson(player1Warriors), gson.toJson(player2Wizards), gson.toJson(player2Warriors));

    }

    public void parseJson(String JSONText) {
        Gson gson = new Gson();
        String[] player = JSONText.split("player2::");
        String[] player1 = player[0].split("Warriors::");
        String[] player2 = player[1].split("Warriors::");

        String player1Wizards = player1[0];
        String player1Warriors = player1[1];
        String player2Wizards = player2[0];
        String player2Warriors = player2[1];

        Warrior[] loadedWarriors = gson.fromJson(player1Warriors, Warrior[].class);
        Wizard[] loadedWizards = gson.fromJson(player1Wizards, Wizard[].class);
        Warrior[] loadedWarriors2 = gson.fromJson(player2Warriors, Warrior[].class);
        Wizard[] loadedWizards2 = gson.fromJson(player2Wizards, Wizard[].class);

        partyPlayer1.addAll(Arrays.asList(loadedWarriors));
        partyPlayer1.addAll(Arrays.asList(loadedWizards));
        partyPlayer2.addAll(Arrays.asList(loadedWarriors2));
        partyPlayer2.addAll(Arrays.asList(loadedWizards2));


    }

    public Game startConsole() {
        System.out.println("Welcome to the game of Hooligans of JAVA: ");
        Scanner sc = new Scanner(System.in);
        int playerId = 0;
        int players = 0;
        boolean readPreviousParty = ConsoleQuery.queryToConsole(sc, "Read previous party?");
        if (readPreviousParty) {
            try {
                System.out.println("reading file");
                parseJson(FileReadAndWrite.readFile());
                players = 2;
            } catch (IOException e) {
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
            generateCharacterLoop(sc, TypeOfCharacter.WARRIOR, player, warriorCount);
            int wizardCount = ConsoleQuery.queryToConsole(sc, "Now, you have to select the number of wizards.", 1, 10);
            generateCharacterLoop(sc, TypeOfCharacter.WIZARD, player, wizardCount);
        }
        System.out.println("end reading data from terminal");

        return null;
    }

    private void generateCharacterLoop(Scanner sc, TypeOfCharacter type, int player, int count) {
        Faker fc = new Faker();
        final CharacterFactory characterFactory = new CharacterFactory(fc);
        for (int i = 0; i < count; i++) {
            Character newCharacter;
            boolean customCharacter = ConsoleQuery.queryToConsole(sc, "Do you want to create a customized character? (Y/N)");
            if (customCharacter) {
                newCharacter = createCustomizedCharacter(sc, type);
            } else {
                if (type == TypeOfCharacter.WARRIOR) {
                    newCharacter = characterFactory.createRandomWarrior();
                } else {
                    newCharacter = characterFactory.createRandomWizard();
                }
            }
            if (player == 1) {
                this.partyPlayer1.add(newCharacter);
            } else {
                this.partyPlayer2.add(newCharacter);
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
        return random.nextInt((max - min + 1) + min);
    }


      private static Character createCustomizedCharacter(Scanner sc, TypeOfCharacter type) {
        Faker fc = new Faker();
        final CharacterFactory characterFactory = new CharacterFactory(fc);
        String name;
        int health ;
        int firstAttribute ;
        int secondAttribute ;

        name = ConsoleQuery.queryToConsoleText(sc, "Finally, set a funny name for you Hero!");
        System.out.println("First, set a funny name for you Hero!");
        switch (type) {
            case WARRIOR -> {
                health = ConsoleQuery.queryToConsole(sc, " define ho much health do you want to set - (Choose a number between  %o - %o)".formatted(TypeOfCharacter.WARRIOR.HP_Min, TypeOfCharacter.WARRIOR.HP_Max), TypeOfCharacter.WARRIOR.HP_Min, TypeOfCharacter.WARRIOR.HP_Max);
                firstAttribute = ConsoleQuery.queryToConsole(sc, "Define how much stamina do you want to set - (Choose a number between %o - %o)".formatted(TypeOfCharacter.WARRIOR.firstParamMin, TypeOfCharacter.WARRIOR.firstParamMax), TypeOfCharacter.WARRIOR.firstParamMin, TypeOfCharacter.WARRIOR.firstParamMax);
                secondAttribute = ConsoleQuery.queryToConsole(sc, "Define how much strength do you want to set - (Choose a number between  %o - %o)".formatted(TypeOfCharacter.WARRIOR.secondParamMin, TypeOfCharacter.WARRIOR.secondParamMax), TypeOfCharacter.WARRIOR.secondParamMin, TypeOfCharacter.WARRIOR.secondParamMax);
                return characterFactory.createWarrior(name, health, firstAttribute, secondAttribute);
            }
            case WIZARD -> {
                health = ConsoleQuery.queryToConsole(sc, " define ho much health do you want to set - (Choose a number between %o - %o)".formatted(TypeOfCharacter.WIZARD.firstParamMin, TypeOfCharacter.WIZARD.firstParamMax), TypeOfCharacter.WIZARD.HP_Min, TypeOfCharacter.WIZARD.HP_Max);
                firstAttribute = ConsoleQuery.queryToConsole(sc, "Define how much mana do you want to set - (Choose a number between  %o - %o)".formatted(TypeOfCharacter.WIZARD.firstParamMin, TypeOfCharacter.WIZARD.firstParamMax), TypeOfCharacter.WIZARD.firstParamMin, TypeOfCharacter.WIZARD.secondParamMax);
                secondAttribute = ConsoleQuery.queryToConsole(sc, "Define how much intelligence do you want to set - (Choose a number between  %o - %o)".formatted(TypeOfCharacter.WIZARD.firstParamMin, TypeOfCharacter.WIZARD.firstParamMax), TypeOfCharacter.WIZARD.secondParamMin, TypeOfCharacter.WIZARD.secondParamMax);
                return characterFactory.createWizard(name, health, firstAttribute, secondAttribute);
            }
        }
        return null;
    }

    public void startGame() {
        Graveyard graveyard = new Graveyard();
        System.out.println("The game has started!");

        while (getAliveCharacters(partyPlayer1).size() > 0 && getAliveCharacters(partyPlayer2).size() > 0) {
            Character player1 = getAliveCharacters(partyPlayer1).get(0);
            Character player2 = getAliveCharacters(partyPlayer2).get(0);
            System.out.println("player1 " + player1.name + " (" + player1.getHp() + " ) Attacks " + "--> player2 " + player2.name + " (" + player2.getHp() + ")");
            System.out.println("player2 " + player2.name + " Attacks --> player1 " + player1.name);

            player2.receiveAttack(player1.attack());
            player1.receiveAttack(player2.attack());

            if (!player1.isAlive) {
                System.out.println("player1 " + player1.name + " is dead");
            }
            if (!player2.isAlive) {
                System.out.println("player2 " + player2.name + " is dead");
            }
        }
        if (getAliveCharacters(partyPlayer1).size() == 0) {
            System.out.println("player2 wins");
        } else {
            System.out.println("player1 wins");
        }

        sendDeathPlayersToTheGraveyard(partyPlayer1, graveyard, 1);
        sendDeathPlayersToTheGraveyard(partyPlayer2, graveyard, 2);

        graveyard.printGraveyard();
    }
    private void sendDeathPlayersToTheGraveyard(ArrayList<Character> partyPlayer, Graveyard graveyard, int party) {
        for(Character player : partyPlayer) {
            if (!player.isAlive) graveyard.sendCharacterToTheGraveyard(player, party);
        }
    }

    private ArrayList<Character> getAliveCharacters(ArrayList<Character> playerCharacters) {
        ArrayList<Character> aliveCharacters = new ArrayList<>();
        for (Character playerCharacter : playerCharacters) {
            if (playerCharacter.isAlive()) {
                aliveCharacters.add(playerCharacter);
            }
        }
        return aliveCharacters;
    }




}
