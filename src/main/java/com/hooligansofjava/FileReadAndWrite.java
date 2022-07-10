package com.hooligansofjava;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReadAndWrite {
    final static String FILENAME = "./backup/Party.json";

    public static void main(String[] args) {

    }

    public static String readFile() throws IOException {
        return Files.readString(Path.of(FILENAME), StandardCharsets.UTF_8);

    }

    public static void writeFile(Game gameData) throws IOException {
        BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(FILENAME));
        writer.write(gameData.generateJson());
        writer.close();
    }
}
