package com.hooligansofjava;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Wellcome to the game of Holligans de JAVA: ");
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
        System.out.println("Do you want customize them?");
        String wizards = "-1";
        while (!checkValidNumber(wizards)){
            System.out.println("Number of wizards: ");
            wizards = sc.nextLine();
            System.out.println(wizards);
        }
        System.out.println("Do you want customize them?");

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


}
