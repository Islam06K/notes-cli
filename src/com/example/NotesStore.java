package com.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NotesStore {
    private final String filePath;

    public NotesStore(String filePath) {
        this.filePath = filePath;
        try {
            Path dir = Paths.get(filePath).getParent();
            if (dir != null) {
                Files.createDirectories(dir);
            }
            if (!Files.exists(Paths.get(filePath))) {
                Files.createFile(Paths.get(filePath));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize notes file", e);
        }
    }

    public List<String> loadNotes() {
        List<String> notes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    notes.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read notes", e);
        }
        return notes;
    }

    private void saveNotes(List<String> notes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String note : notes) {
                writer.write(note);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write notes", e);
        }
    }

    public void addNote(String text) {
        List<String> notes = loadNotes();
        int nextId = notes.isEmpty() ? 1 : parseId(notes.get(notes.size() - 1)) + 1;
        notes.add(nextId + ";" + text);
        saveNotes(notes);
    }

    public List<String> getAllNotes() {
        return loadNotes();
    }

    public int getNoteCount() {
        return loadNotes().size();
    }

    private int parseId(String line) {
        int semicolon = line.indexOf(';');
        if (semicolon == -1) return 0;
        try {
            return Integer.parseInt(line.substring(0, semicolon));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}