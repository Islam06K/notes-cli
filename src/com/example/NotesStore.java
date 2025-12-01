package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NotesStore {
    private static final String DATA_FILE = "data/notes.csv";

    public void addNote(String text) throws IOException {
        List<String> notes = loadNotes();
        int nextId = notes.isEmpty() ? 1 : notes.size() + 1;
        String newNote = nextId + ";" + text;

        try (FileWriter fw = new FileWriter(DATA_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(newNote);
            bw.newLine();
        }
    }

    public List<String> listNotes() throws IOException {
        return loadNotes();
    }

    private List<String> loadNotes() throws IOException {
        List<String> notes = new ArrayList<>();
        File file = new File(DATA_FILE);
        if (!file.exists()) return notes;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    notes.add(line);
                }
            }
        }
        return notes;
    }
}