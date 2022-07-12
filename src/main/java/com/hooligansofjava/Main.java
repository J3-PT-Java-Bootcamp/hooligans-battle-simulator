package com.hooligansofjava;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Game newGame = new Game();

        /*newGame.startConsole();*/
        newGame.randomParty();
        newGame.startGame();
        FileReadAndWrite.writeFile(newGame);
        /*
        System.out.println(newGame.generateJson());
        */
    }


}