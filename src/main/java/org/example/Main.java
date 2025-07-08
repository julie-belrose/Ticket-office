package org.example;


import java.util.Scanner;
import java.util.UUID;

import org.example.ticketing.domain.Address;
import org.example.ticketing.domain.Client;
import org.example.ticketing.repository.MapRepository;
import org.example.ticketing.repository.Repository;
import org.example.ticketing.service.TicketService;
import org.example.ticketing.ui.Ihm;
import org.example.ticketing.domain.Event;
import org.example.ticketing.ui.MainMenu;

/**
 * Entry-point of the console ticketing application.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Wire concrete repositories and services.</li>
 *   <li>Create generic IHMs for {@code Client} and {@code Event} using lambdas.</li>
 *   <li>Launch the {@link MainMenu} root loop.</li>
 * </ul>
 *
 * <p>Run with:<br>{@code mvn clean compile exec:java}</p>
 */
public final class Main {

    /** Prevent instantiation. */
    private Main() { }

    /**
     * Bootstraps the application.
     *
     * @param args CLI arguments (unused)
     */
    public static void main(String[] args) {

        /* ── 1. SHARED SCANNER ─────────────────────────────────────── */
        Scanner in = new Scanner(System.in);

        /* ── 2. REPOSITORIES (in-memory HashMap) ───────────────────── */
        Repository<Client> clientRepo = new MapRepository<>(Client::getId);
        Repository<Event>  eventRepo  = new MapRepository<>(Event::getId);

        /* ── (Optionnel) Données de démonstration ──────────────────── */
        Address addr   = new Address("221B Baker St", "London");
        Client  sample = new Client("CLI-1", "Alice", "Smith", addr, 28, "+33612345678");
        clientRepo.save(sample);

        /* ── 3. SERVICE MÉTIER ─────────────────────────────────────── */
        TicketService ticketService = new TicketService(eventRepo, clientRepo);

        /* ── 4. IHM CLIENT ─────────────────────────────────────────── */
        Ihm<Client> clientIhm = new Ihm<>(
                "CLIENTS",
                clientRepo,
                () -> {
                    System.out.print("First name: ");
                    String first = in.nextLine().trim();
                    System.out.print("Last name: ");
                    String last  = in.nextLine().trim();
                    System.out.print("Street: ");
                    String street = in.nextLine().trim();
                    System.out.print("City: ");
                    String city   = in.nextLine().trim();
                    System.out.print("Age: ");
                    int age = Integer.parseInt(in.nextLine().trim());
                    System.out.print("Phone: ");
                    String phone = in.nextLine().trim();

                    return new Client(
                            UUID.randomUUID().toString(),
                            first, last,
                            new Address(street, city),
                            age, phone
                    );
                },
                (c, sc) -> {
                    System.out.print("New phone (" + c.getPhoneNumber() + "): ");
                    c.setPhoneNumber(sc.nextLine().trim());
                },
                in
        );

        /* ── 5. IHM EVENT ──────────────────────────────────────────── */
        Ihm<Event> eventIhm = new Ihm<>(
                "EVENTS",
                eventRepo,
                () -> {
                    System.out.print("Name: ");
                    String name = in.nextLine().trim();
                    System.out.print("Street: ");
                    String street = in.nextLine().trim();
                    System.out.print("City: ");
                    String city   = in.nextLine().trim();
                    System.out.print("Date (YYYY-MM-DD): ");
                    String date   = in.nextLine().trim();
                    System.out.print("Time (HH:MM): ");
                    String time   = in.nextLine().trim();
                    System.out.print("Capacity: ");
                    int capacity  = Integer.parseInt(in.nextLine().trim());

                    Location loc = new Location(street, city, capacity);
                    return new Event(
                            UUID.randomUUID().toString(),
                            name, loc,
                            java.sql.Date.valueOf(date),
                            time,
                            capacity
                    );
                },
                (e, sc) -> {
                    System.out.print("New name (" + e.getName() + "): ");
                    e.setName(sc.nextLine().trim());
                },
                in
        );
}