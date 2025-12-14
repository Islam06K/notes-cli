package com.example;

import java.io.*;
import java.util.*;

public class NotesStore {
   private static final String FILE_PATH = "/app/data/notes.csv";

    public static List<String> readAll() {
        List<String> notes = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return notes;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                notes.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public static void add(String text) {
        List<String> notes = readAll();
        int id = notes.size() + 1;
        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            fw.write(id + ";" + text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
