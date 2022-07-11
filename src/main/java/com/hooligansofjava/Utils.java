package com.hooligansofjava;

public class Utils {
    public static int getRandomNumber(int min, int max) {
        double response = (Math.random() * (max - min) + min);
        return (int) response;
    }
}
