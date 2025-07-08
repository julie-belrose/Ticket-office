package org.example.ticketing.ui;

import org.example.ticketing.domain.exception.CrudException;
import org.example.ticketing.domain.exception.NotFoundException;
import org.example.ticketing.repository.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Re-usable console CRUD menu.
 *
 * @param <T> entity type managed by this UI
 */
public final class Ihm<T> {

    private final Repository<T> repo;
    private final Supplier<T> creator;
    private final BiConsumer<T, Scanner> updater;
    private final Scanner in;
    private final String title;

    public Ihm(String title,
            Repository<T> repo,
            Supplier<T> creator,
            BiConsumer<T, Scanner> updater,
            Scanner in) {

        this.title = Objects.requireNonNull(title);
        this.repo = Objects.requireNonNull(repo);
        this.creator = Objects.requireNonNull(creator);
        this.updater = Objects.requireNonNull(updater);
        this.in = Objects.requireNonNull(in);
    }

    public void start() {
        while (true) {
            printMenu();
            switch (in.nextLine().trim()) {
                case "1" -> safe(this::listAll);
                case "2" -> safe(this::add);
                case "3" -> safe(this::edit);
                case "4" -> safe(this::delete);
                case "0" -> {
                    return;
                }
                default -> System.out.println("Unknown option");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== " + title + " ===");
        System.out.println("1 - List");
        System.out.println("2 - Add");
        System.out.println("3 - Edit");
        System.out.println("4 - Delete");
        System.out.println("0 - Back");
        System.out.print("> ");
    }

    /** Wraps an action and prints any {@link CrudException} message. */
    private void safe(Runnable action) {
        try {
            action.run();
        } catch (CrudException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void listAll() {
        List<T> data = repo.findAll();
        if (data.isEmpty()) {
            System.out.println("(no data)");
        } else {
            data.forEach(System.out::println);
        }
    }

    private void add() {
        T entity = creator.get();
        repo.save(entity);
        System.out.println("Saved.");
    }

    private void edit() {
        String id = prompt("ID? ");
        T entity = repo.find(id)
                .orElseThrow(() -> new NotFoundException(id));
        updater.accept(entity, in);
        repo.save(entity);
        System.out.println("Updated.");
    }

    private void delete() {
        String id = prompt("ID? ");
        repo.delete(id);
        System.out.println("Deleted.");
    }

    private String prompt(String msg) {
        System.out.print(msg);
        return in.nextLine().trim();
    }
}
