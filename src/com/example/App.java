package com.example;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: --cmd=add --text=\"...\" OR --cmd=list");
            return;
        }

        String cmd = "";
        String text = "";
        int id = -1;

        for (int i = 0; i < args.length; i++) {
            if ("--cmd".equals(args[i]) && i + 1 < args.length) {
                cmd = args[i + 1];
            } else if ("--text".equals(args[i]) && i + 1 < args.length) {
                text = args[i + 1];
            } else if ("--id".equals(args[i]) && i + 1 < args.length) {
                try {
                    id = Integer.parseInt(args[i + 1]);
                } catch (NumberFormatException ignored) {}
            }
        }

        NotesStore store = new NotesStore();

        switch (cmd) {
            case "add":
                if (!text.isEmpty()) {
                    store.addNote(text);
                    System.out.println("✓ Note added");
                } else {
                    System.err.println("Error: --text is required for 'add'");
                }
                break;

            case "list":
                var notes = store.listNotes();
                if (notes.isEmpty()) {
                    System.out.println("(empty)");
                } else {
                    for (String note : notes) {
                        System.out.println(note);
                    }
                }
                break;

            default:
                System.err.println("Unknown command: " + cmd);
        }
    }
}