package org.example;


import java.util.Scanner;

import org.example.ticketing.domain.Client;
import org.example.ticketing.domain.Event;
import org.example.ticketing.repository.MapRepository;
import org.example.ticketing.repository.Repository;
import org.example.ticketing.service.TicketService;
import org.example.ticketing.ui.EventPrompter;
import org.example.ticketing.ui.Ihm;
import org.example.ticketing.ui.MainMenu;
import org.example.ticketing.ui.ClientPrompter;

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

        Scanner in = new Scanner(System.in);

        Repository<Client> clientRepo = new MapRepository<>(Client::getId);
        Repository<Event> eventRepo = new MapRepository<>(Event::getId);

        TicketService ticketSvc = new TicketService(eventRepo, clientRepo);

        Ihm<Client> clientIhm = new Ihm<>(
                "CLIENTS", clientRepo,
                () -> ClientPrompter.create(in),
                ClientPrompter::update,
                in
        );

        Ihm<Event> eventIhm = new Ihm<>(
                "EVENTS", eventRepo,
                () -> EventPrompter.create(in),
                EventPrompter::update,
                in
        );

        new MainMenu(clientIhm, eventIhm, ticketSvc, in).start();
      }
    }