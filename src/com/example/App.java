package com.example;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: --cmd=add/list/count [--text=...] [--id=...]");
            return;
        }

        String cmd = null;
        String text = null;
        Integer id = null;

        for (String arg : args) {
            if (arg.startsWith("--cmd=")) {
                cmd = arg.substring(6);
            } else if (arg.startsWith("--text=")) {
                text = arg.substring(7);
            } else if (arg.startsWith("--id=")) {
                try {
                    id = Integer.parseInt(arg.substring(5));
                } catch (NumberFormatException ignored) { }
            }
        }

        NotesStore store = new NotesStore("data/notes.csv");

        if ("add".equals(cmd)) {
            if (text == null || text.trim().isEmpty()) {
                System.err.println("Error: --text is required for 'add'");
                return;
            }
            store.addNote(text);
            System.out.println("Note added.");
        } else if ("list".equals(cmd)) {
            var notes = store.getAllNotes();
            if (notes.isEmpty()) {
                System.out.println("(empty)");
            } else {
                for (String note : notes) {
                    System.out.println(note);
                }
            }
        } else if ("count".equals(cmd)) {
            System.out.println(store.getNoteCount());
        } else {
            System.err.println("Unknown command: " + cmd);
            System.err.println("Supported: add, list, count");
        }
    }
}