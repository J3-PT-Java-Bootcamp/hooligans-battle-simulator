package com.hooligansofjava;

import java.util.ArrayList;

public class GameData {
    public ArrayList<Character> partyPlayer1 = new ArrayList<>();
    public ArrayList<Character> partyPlayer2 = new ArrayList<>();
    public GameData(ArrayList<Character> partyPlayer1, ArrayList<Character> partyPlayer2) {
        this.partyPlayer1 = partyPlayer1;
        this.partyPlayer2 = partyPlayer2;
    }
    public GameData() {

    }
}
