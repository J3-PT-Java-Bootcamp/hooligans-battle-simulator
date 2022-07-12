package com.hooligansofjava;

import java.util.Scanner;

import static com.hooligansofjava.Game.checkValidNumber;


public class ConsoleQuery {

    public static int queryToConsole(Scanner sc, String text, String[] Options, int min, int max) {
        int result = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.println(text);
            for (int i = 1; i < Options.length + 1; i++) {
                System.out.println(i + ") " + Options[i - 1]);
            }
            System.out.println("Enter your choice: ");
            String choice = sc.nextLine();
            if (checkValidNumber(choice)) {
                result = Integer.parseInt(choice);
                if (result >= min && result <= max) {
                    isValid = true;
                }
            }

            if (result >= min && result <= max) {
                isValid = true;
            }
        }
        return result;
    }

    public static int queryToConsole(Scanner sc, String text, int min, int max) {
        int result = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.println(text);
            System.out.println("Enter number: ");
            String choice = sc.nextLine();
            if (checkValidNumber(choice)) {
                result = Integer.parseInt(choice);
                if (result >= min && result <= max) {
                    isValid = true;
                }
            }

            if (result >= min && result <= max) {
                isValid = true;
            }
        }
        return result;
    }

    public static String queryToConsoleText(Scanner sc, String text) {
        String result = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.println(text);
            String choice = sc.nextLine();
            if (choice.trim().length() > 0) {
                isValid = true;
            result = choice.trim().substring(0, 1).toUpperCase() + choice.trim().substring(1, choice.length()).toLowerCase();
            result = result.replace("/[-!$%^&*()_+|~=`{}\\[\\]:\";'<>?,.\\/]/","_");
            }
        }
        return result;
    }

    public static boolean queryToConsole(Scanner sc, String text) {
        boolean result = false;
        boolean isValid = false;
        while (!isValid) {
            System.out.println(text);
            System.out.println("Enter Y/N: ");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                result = true;
                isValid = true;
            } else if (choice.equalsIgnoreCase("n")) {
                isValid = true;
            }
        }
        return result;
    }
}
