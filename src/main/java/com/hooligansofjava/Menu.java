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

        option = ConsoleQuery.queryToConsole(sc, "WELLCOME TO THE GAME: HOLLIGANS OF JAVA: \n Choose your option: ", new String[]{"Play new custom party", "Play new random party", "Play last party", "Check the graveyard", "Read the readme file", "Exit"}, 1, 6);

        switch (option) {
            case 1:
                game.startCustomParty();
                FileReadAndWrite.writeFile(game);
                game.startGame();
                break;
            case 2:
                game.randomParty();
                FileReadAndWrite.writeFile(game);
                game.startGame();
                break;
            case 3:
                game.playLastParty();
                game.startGame();
                break;
            case 4:
                //check the graveyard
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


