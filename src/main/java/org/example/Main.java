package org.example;


import java.util.Scanner;
import java.util.UUID;
import java.util.Date;

import org.example.ticketing.domain.*;
import org.example.ticketing.domain.exception.CrudException;
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

        /**
         * Prevent instantiation.
         */
        private Main() {
        }

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

        /* Seed demo data and keep back the generated IDs */
        SampleIds ids = preloadData(clientRepo, eventRepo, ticketSvc);

        /* Optional demo reservation with random IDs */
        demoScenario(ticketSvc, ids);

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

/* ---------- PRELOAD ----------------------------------------------- */

/** Holder for the random IDs we want to reuse in the demo. */
private record SampleIds(String clientId, String eventId) {
}

private static SampleIds preloadData(Repository<Client> clientRepo,
                Repository<Event> eventRepo, TicketService ticketSvc) {

        String clientId = generateId();
        String clientId2 = generateId();
        String eventId = generateId();
        String eventId2 = generateId();

        preloadDataClient(clientId, clientRepo);
        preloadDataEvent(eventId, eventRepo);
        preloadDataTicket(ticketSvc, clientId, eventId);

        return new SampleIds(clientId, eventId);
}

/** Generates a unique ID (UUID string). */
private static String generateId() {
        return UUID.randomUUID().toString();
}

private static void preloadDataEvent(String eventId, Repository<Event> eventRepo) {
        Location location = new Location("Opéra Garnier", "Paris", 3);
        Event event = new Event(eventId, "Concert Piano", location, new Date(), "21:00", 3);
        eventRepo.save(event);
}

private static void preloadDataClient(String clientId, Repository<Client> clientRepo) {
        Address address = new Address("7 rue du Paradis", "Paris");
        Client client = new Client(clientId, "Julie", "Belrose", address, 29, "+33600000001");
        clientRepo.save(client);
}

private static void preloadDataTicket(TicketService ticketSvc, String clientId, String eventId) {
        try {
                Ticket ticket = ticketSvc.reserve(clientId, eventId, 1, SeatType.GOLD);
                System.out.println("[DATA] Ticket booked : " + ticket);
        } catch (CrudException ex) {
                System.out.println("[DATA] Error booking ticket : " + ex.getMessage());
        }
}

private static void demoScenario(TicketService svc, SampleIds ids) {
        try {
                Ticket t = svc.reserve(ids.clientId, ids.eventId, 1, SeatType.VIP);
                System.out.println("[DEMO] Booked: " + t);
        } catch (CrudException ex) {
                System.out.println("[DEMO] " + ex.getMessage());
        }
}

}