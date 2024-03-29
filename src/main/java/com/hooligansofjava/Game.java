package com.hooligansofjava;

import net.datafaker.Faker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;


public class Game {


    public Game() {

    }


    public void randomParty(GameData gameData) {
        Faker fc = new Faker();
        CharacterFactory factory = new CharacterFactory(fc);
        int index = Utils.getRandomNumber(1, 100);
        for (int i = 0; i < index; i++) {
            gameData.partyPlayer1.add(factory.createRandomCharacter());
            gameData.partyPlayer2.add(factory.createRandomCharacter());
        }
    }

    public void startCustomParty(GameData gameData) {
        Faker faker = new Faker();
        Scanner sc = new Scanner(System.in);
        int playerId = 0;
        int players = 0;
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
            generateCharacterLoop(faker, sc, TypeOfCharacter.WARRIOR, player, warriorCount, gameData);
            int wizardCount = ConsoleQuery.queryToConsole(sc, "Now, you have to select the number of wizards.", 1, 10);
            generateCharacterLoop(faker, sc, TypeOfCharacter.WIZARD, player, wizardCount, gameData);
        }
    }

    public GameData playLastParty() {

        try {
            System.out.println("reading file");
            return FileReadAndWrite.readFile();

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return null;
    }


    private void generateCharacterLoop(Faker fc, Scanner sc, TypeOfCharacter type, int player, int count, GameData gameData) {
        CharacterFactory factory = new CharacterFactory(fc);
        for (int i = 0; i < count; i++) {
            Character newCharacter;
            boolean customCharacter = ConsoleQuery.queryToConsole(sc, "Do you want to create a customized character? (Y/N)");
            if (customCharacter) {
                newCharacter = createCustomizedCharacter(sc, type, fc);
            } else {
                if (type == TypeOfCharacter.WARRIOR) {
                    newCharacter = factory.createRandomWarrior();
                } else {
                    newCharacter = factory.createRandomWizard();
                }
            }
            if (player == 1) {
                gameData.partyPlayer1.add(newCharacter);
            } else {
                gameData.partyPlayer2.add(newCharacter);
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
        return true;
    }


    private static Character createCustomizedCharacter(Scanner sc, TypeOfCharacter type, Faker fc) {
        final CharacterFactory characterFactory = new CharacterFactory(fc);
        String name;
        int health;
        int firstAttribute;
        int secondAttribute;

        name = ConsoleQuery.queryToConsoleText(sc, "First, set a funny name for you Hero!");
        switch (type) {
            case WARRIOR -> {
                health = ConsoleQuery.queryToConsole(sc, " define ho much health do you want to set - (Choose a number between  %s - %s)".formatted(TypeOfCharacter.WARRIOR.HP_Min, TypeOfCharacter.WARRIOR.HP_Max), TypeOfCharacter.WARRIOR.HP_Min, TypeOfCharacter.WARRIOR.HP_Max);
                firstAttribute = ConsoleQuery.queryToConsole(sc, "Define how much stamina do you want to set - (Choose a number between %s - %s)".formatted(TypeOfCharacter.WARRIOR.firstParamMin, TypeOfCharacter.WARRIOR.firstParamMax), TypeOfCharacter.WARRIOR.firstParamMin, TypeOfCharacter.WARRIOR.firstParamMax);
                secondAttribute = ConsoleQuery.queryToConsole(sc, "Define how much strength do you want to set - (Choose a number between  %s - %s)".formatted(TypeOfCharacter.WARRIOR.secondParamMin, TypeOfCharacter.WARRIOR.secondParamMax), TypeOfCharacter.WARRIOR.secondParamMin, TypeOfCharacter.WARRIOR.secondParamMax);
                return characterFactory.createWarrior(name, health, firstAttribute, secondAttribute);
            }
            case WIZARD -> {
                health = ConsoleQuery.queryToConsole(sc, " define ho much health do you want to set - (Choose a number between %s - %s)".formatted(TypeOfCharacter.WIZARD.HP_Min, TypeOfCharacter.WIZARD.HP_Max), TypeOfCharacter.WIZARD.HP_Min, TypeOfCharacter.WIZARD.HP_Max);
                firstAttribute = ConsoleQuery.queryToConsole(sc, "Define how much mana do you want to set - (Choose a number between  %s - %s)".formatted(TypeOfCharacter.WIZARD.firstParamMin, TypeOfCharacter.WIZARD.firstParamMax), TypeOfCharacter.WIZARD.firstParamMin, TypeOfCharacter.WIZARD.firstParamMax);
                secondAttribute = ConsoleQuery.queryToConsole(sc, "Define how much intelligence do you want to set - (Choose a number between  %s - %s)".formatted(TypeOfCharacter.WIZARD.firstParamMin, TypeOfCharacter.WIZARD.firstParamMax), TypeOfCharacter.WIZARD.secondParamMin, TypeOfCharacter.WIZARD.secondParamMax);
                return characterFactory.createWizard(name, health, firstAttribute, secondAttribute);
            }
        }
        return null;
    }

    public void startGame(Graveyard graveyard, GameData gameData) {
        System.out.println("The game has started!");
        try {
            while (getAliveCharacters(gameData.partyPlayer1).size() > 0 && getAliveCharacters(gameData.partyPlayer2).size() > 0) {
                Character player1 = getAliveCharacters(gameData.partyPlayer1).get(0);
                Character player2 = getAliveCharacters(gameData.partyPlayer2).get(0);
                int player1Dice = Utils.getRandomNumber(1, 20);
                int player2Dice = Utils.getRandomNumber(1, 20);
                sleep(400L);
                switch (player1Dice) {
                    case 1, 2 -> System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Player 1 has missed!");
                    case 20 -> {
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Player 1 has critically hit!");
                        player2.receiveAttack(player1.attack() * 2);
                    }
                    default -> {
                        Logger.colourLine(ConsoleColors.BLUE_BOLD_BRIGHT + "Player 1:  " + player1.name + " (" + player1.getHp() + ") Attacks " + ConsoleColors.YELLOW_BOLD_BRIGHT + " -->" + ConsoleColors.CYAN_BOLD_BRIGHT + " Player 2: " + player2.name + " (" + player2.getHp() + ")");
                        player2.receiveAttack(player1.attack());
                    }
                }
                switch (player2Dice) {
                    case 1, 2 -> System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Player 2 has missed!");
                    case 20 -> {
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Player 2 has critically hit!");
                        player1.receiveAttack(player2.attack() * 2);
                    }
                    default -> {
                        Logger.colourLine(ConsoleColors.CYAN_BOLD_BRIGHT + "Player 2:  " + player2.name + " (" + player2.getHp() + ") Attacks " + ConsoleColors.YELLOW_BOLD_BRIGHT + " -->" + ConsoleColors.BLUE_BOLD_BRIGHT + " Player 1: " + player1.name + " (" + player1.getHp() + ")");
                        player1.receiveAttack(player2.attack());

                    }
                }

                if (!player1.isAlive && !player2.isAlive) {
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "⚰️⚰️⚰️⚰️Both players have died!⚰️⚰️⚰️⚰️");
                } else if (!player1.isAlive) {
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "⚰️⚰️⚰️⚰️Player 1 has died!⚰️⚰️⚰️⚰️");
                } else if (!player2.isAlive) {
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "⚰️⚰️⚰️⚰️Player 2 has died!⚰️⚰️⚰️⚰️");
                }

            }

        } catch (Exception e) {


        }
        if(getAliveCharacters(gameData.partyPlayer1).size() == 0 && getAliveCharacters(gameData.partyPlayer2).size() == 0){
            Logger.colourLine(ConsoleColors.PURPLE_BOLD_BRIGHT + "Both players have died! Game Over!");

        }else if (getAliveCharacters(gameData.partyPlayer1).size() == 0) {
            Logger.colourLine(ConsoleColors.PURPLE_BOLD_BRIGHT + "Player 2 WINS!!!!");
        } else {
            Logger.colourLine(ConsoleColors.PURPLE_BOLD_BRIGHT + "Player 1 WINS!!!!");
        }

        sendDeathPlayersToTheGraveyard(gameData.partyPlayer1, graveyard, 1);
        sendDeathPlayersToTheGraveyard(gameData.partyPlayer2, graveyard, 2);

        graveyard.printGraveyard();
    }

    private void sendDeathPlayersToTheGraveyard(ArrayList<Character> partyPlayer, Graveyard graveyard, int party) {
        for (Character player : partyPlayer) {
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
