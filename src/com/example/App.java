package com.example;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();

        for (String arg : args) {
            if (arg.startsWith("--")) {
                String[] p = arg.substring(2).split("=", 2);
                params.put(p[0], p.length > 1 ? p[1] : "");
            }
        }

        String cmd = params.get("cmd");

        if ("add".equals(cmd)) {
            String text = params.get("text");
            if (text == null || text.isEmpty()) {
                System.out.println("Text is empty");
                return;
            }
            NotesStore.add(text);

        } else if ("list".equals(cmd)) {
            List<String> notes = NotesStore.readAll();
            if (notes.isEmpty()) {
                System.out.println("(empty)");
            } else {
                for (String n : notes) {
                    System.out.println(n);
                }
            }

        } else {
            System.out.println("Unknown command");
        }
    }
}
