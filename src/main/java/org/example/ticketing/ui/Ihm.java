package org.example.ticketing.ui;

import org.example.ticketing.repository.Repository;
import java.util.Scanner;
import java.util.function.*;

/**
 * Generic console CRUD UI.
 * @param <T> entity type managed by this UI
 */
public class Ihm<T> {

    // ─── FIELDS ───────────────────────────────────
    private Repository<T> repo;
    private Supplier<T>   creator;          // builds a new T from user input
    private BiConsumer<T,Scanner> updater;  // edits an existing T

    // ─── CONSTRUCTOR ─────────────────────────────
    // Ihm(Repository<T>, Supplier<T>, BiConsumer<T,Scanner>)

    // ─── MENU LOOP ───────────────────────────────
    public void start(Scanner sc) {
        // while-loop displaying options:
        // 1-List  2-Add  3-Edit  4-Delete  0-Back
        // Call private helpers listAll(), add(), edit(sc), delete(sc)
    }

    // listAll(), add(), edit(Scanner), delete(Scanner)
    //  -> use repo + lambdas, handle NotFoundException, print friendly messages
}
