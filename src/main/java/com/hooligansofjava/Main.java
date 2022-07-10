package com.hooligansofjava;

import java.io.IOException;

public class Main {
    public static void main(String[] args)  {

        Game newGame = new Game();
        newGame.startConsole();

        newGame.startGame();
        /*System.out.println(newGame.generateJson());
        FileReadAndWrite.writeFile(newGame);
*/
    }


}