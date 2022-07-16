package com.hooligansofjava;

public class Logger {
    public static void colourLine(String text) {
        System.out.println(text + ConsoleColors.YELLOW_BOLD_BRIGHT);
    }
    public static void animatedLine(String text) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
