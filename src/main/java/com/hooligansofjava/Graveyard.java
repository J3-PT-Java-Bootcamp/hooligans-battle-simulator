package com.hooligansofjava;

import java.util.ArrayList;
import java.util.List;

public class Graveyard {

    private ArrayList<Character> graveyardPlayerOne;
    private ArrayList<Character> graveyardPlayerTwo;

    public Graveyard() {
        this.graveyardPlayerOne = new ArrayList<>();
        this.graveyardPlayerTwo = new ArrayList<>();
    }

    public ArrayList<Character> getGraveyardPlayerOne() {
        return graveyardPlayerOne;
    }

    public void setGraveyardPlayerOne(ArrayList<Character> graveyardPlayerOne) {
        this.graveyardPlayerOne = graveyardPlayerOne;
    }
    public ArrayList<Character> getGraveyardPlayerTwo() {
        return graveyardPlayerTwo;
    }
    public void setGraveyardPlayerTwo(ArrayList<Character> graveyardPlayerTwo) {
        this.graveyardPlayerTwo = graveyardPlayerTwo;
    }

    public void sendCharacterToTheGraveyard(Character character, int party) {
        if(party == 1) {
            getGraveyardPlayerOne().add(character);
        } else getGraveyardPlayerTwo().add(character);
    }

    public void printGraveyard() {
        List<List<String>> graveyard = new ArrayList<>();
        graveyard.add(new ArrayList<>());
        graveyard.add(new ArrayList<>());

        for (Character player : getGraveyardPlayerOne()) {
            graveyard.get(0).add(player.getName());
        }
        for (Character player : getGraveyardPlayerTwo()) {
            graveyard.get(1).add(player.getName());
        }

        System.out.println("""
                _______________________________________
                              Graveyard
                _______________________________________
                  Player 1
                _______________________________________
                """);

        printListOfDeadPlayersInAParty(graveyard, 0);
        System.out.println("""
                _______________________________________
                  Player 2
                _______________________________________
                """);
        printListOfDeadPlayersInAParty(graveyard, 1);
        System.out.println("""
                
                Thanks for playing with us! See you next time!
                
                """);
    }

    private void printListOfDeadPlayersInAParty(List<List<String>> graveyard, int party) {
        for (int i = 0; i < graveyard.get(party).size(); i++) {
            if(graveyard.get(party).size() < 1) {
                System.out.println("No player were dead in this party. Congrats!");
            } else {
                System.out.println(graveyard.get(party).get(i));
            }
        }
    }
}
