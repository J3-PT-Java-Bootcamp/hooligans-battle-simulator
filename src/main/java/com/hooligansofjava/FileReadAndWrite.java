package com.hooligansofjava;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hooligansofjava.Gson.CustomDeserializer;
import com.hooligansofjava.Gson.CustomSerializer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReadAndWrite {
    final static String FILENAME = "./backup/Party.json";

    public static void main(String[] args) {

    }

    public static GameData readFile() throws IOException {
        GsonBuilder gb = new GsonBuilder();
        ArrayList<Character> partyPlayer1 = new ArrayList<>();
        ArrayList<Character> partyPlayer2 = new ArrayList<>();

        gb.registerTypeAdapter(partyPlayer2.getClass(), new CustomDeserializer());
        Gson gson = gb.create();
        String data = Files.readString(Path.of(FILENAME), StandardCharsets.UTF_8);
        String player1 = data.split("\n")[0];
        String player2 = data.split("\n")[1];
        partyPlayer1 = gson.fromJson(player1,partyPlayer1.getClass());
        partyPlayer2 = gson.fromJson(player2,partyPlayer2.getClass());

        GameData gameData = new GameData(partyPlayer1,partyPlayer2);
        return gameData;

    }

    public static void writeFile(GameData gameData) {
        GsonBuilder gb = new GsonBuilder();
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(FILENAME));
            List<Character> al = new ArrayList<Character>();

            gb.registerTypeAdapter(al.getClass(), new CustomSerializer());
            Gson gson = gb.create();
            writer.write(gson.toJson(gameData.partyPlayer1)+"\n"+gson.toJson((gameData.partyPlayer2)));
            writer.close();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
}
