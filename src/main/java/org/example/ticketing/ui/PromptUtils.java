package org.example.ticketing.ui;

import java.util.Scanner;

public final class PromptUtils {
    private PromptUtils() {}

    public static String ask(Scanner in, String msg) {
        System.out.print(msg);
        return in.nextLine().trim();
    }

    public static int askInt(Scanner in, String msg) {
        while (true) {
            try { return Integer.parseInt(ask(in, msg)); }
            catch (NumberFormatException e) { System.out.println("Enter a valid integer."); }
        }
    }
}

