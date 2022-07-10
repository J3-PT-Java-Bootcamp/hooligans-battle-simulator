package com.hooligansofjava;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Game newGame = new Game();
        newGame.startConsole();
        System.out.println(Game.generateJson());
        FileReadAndWrite.writeFile(newGame);



    }


}