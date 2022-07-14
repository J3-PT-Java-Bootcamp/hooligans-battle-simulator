package com.hooligansofjava;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in);
    private boolean exit = false;

    private int option;

    public static void menu() {

        int option;
        Scanner sc = new Scanner(System.in);
        Game game = new Game();
        GameData gameData = new GameData();
        Graveyard gy = new Graveyard();


        while (true) {
            // https://medium.com/@iliamsharipov_56660/handling-polymorphism-with-gson-f4a702014ffe
            // https://stackoverflow.com/questions/16800896/java-unmarshilling-json-data-containg-abstract-type
// https://stackoverflow.com/questions/19588020/gson-serialize-a-list-of-polymorphic-objects/19600090#19600090
            //

        option = ConsoleQuery.queryToConsole(sc,  ConsoleColors.BLACK_BACKGROUND + ConsoleColors.YELLOW_BOLD_BRIGHT + "WELLCOME TO THE GAME: HOLLIGANS OF JAVA: ", new String[]{"Play new custom party", "Play new random party", "Play last party", "Check the graveyard", "Read the readme file", "Exit"}, 1, 6);

            switch (option) {
                case 1:
                    game.startCustomParty(gameData);
                    FileReadAndWrite.writeFile(gameData);
                    game.startGame(gy, gameData);
                    break;
                case 2:
                    game.randomParty(gameData);
                    FileReadAndWrite.writeFile(gameData);
                    game.startGame(gy,gameData);
                    break;
                case 3:
                    gameData=  game.playLastParty();
                    game.startGame(gy,gameData);
                    break;
                case 4:
                    gy.printGraveyard();
                    break;
                case 5:
                    ReadMe.showReadMe();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choose the correct option.");
            }
        }
    }

}


