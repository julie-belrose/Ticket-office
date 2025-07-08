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

        /** Prevent instantiation. */
        private Main() {
        }

    /**
     * Bootstraps the application.
     *
     * @param args CLI arguments (unused)
     */
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            Repos repos = initRepositories();
            TicketService svc = new TicketService(repos.eventRepo(), repos.clientRepo());

            SampleIds ids = preloadData(repos.clientRepo(), repos.eventRepo(), svc);
            demoScript(svc, ids);

            Ihm<Client> clientIhm = buildClientIhm(repos.clientRepo(), in);
            Ihm<Event>  eventIhm  = buildEventIhm (repos.eventRepo(),  in);

            new MainMenu(clientIhm, eventIhm, svc, in).start();
        }
    }

    private record Repos(Repository<Client> clientRepo,
                         Repository<Event>  eventRepo) { }

    private static Repos initRepositories() {
        return new Repos(
                new MapRepository<>(Client::getId),
                new MapRepository<>(Event::getId)
        );
    }

    private static Ihm<Client> buildClientIhm(Repository<Client> repo, Scanner in) {
        return new Ihm<>(
                "CLIENTS", repo,
                () -> ClientPrompter.create(in),
                ClientPrompter::update,
                in
        );
    }

    private static Ihm<Event> buildEventIhm(Repository<Event> repo, Scanner in) {
        return new Ihm<>(
                "EVENTS", repo,
                () -> EventPrompter.create(in),
                EventPrompter::update,
                in
        );
    }

    /**
     * Small DTO to track IDs used during seeding.
     * These IDs can be reused in other demo steps.
     */
    private record SampleIds(String clientId, String eventId) {
    }

    /**
     * Seeds a default client, event and initial reservation for testing purposes.
     *
     * @param clientRepo the repository where to save the sample client
     * @param eventRepo  the repository where to save the sample event
     * @param ticketSvc  service used to simulate a booking
     * @return the IDs of the created client and event
     */
    private static SampleIds preloadData(
                    Repository<Client> clientRepo,
                    Repository<Event> eventRepo,
                    TicketService ticketSvc) {

        String clientId = generateId();
        String eventId = generateId();

        preloadDataClient(clientId, clientRepo);
        preloadDataEvent(eventId, eventRepo);
        preloadDataTicket(ticketSvc, clientId, eventId);

        return new SampleIds(clientId, eventId);
}

    /**
     * Generates a new unique ID using {@link UUID}.
     *
     * @return a random UUID as a string
     */
    private static String generateId() {
        return UUID.randomUUID().toString();
}

/**
 * Creates and saves a sample event in the given repository.
 *
 * @param eventId   the ID to assign to the event
 * @param eventRepo the target event repository
 */
    private static void preloadDataEvent(String eventId, Repository<Event> eventRepo) {
        Location location = new Location("Opéra Garnier", "Paris", 3);
        Event event = new Event(eventId, "Concert Piano", location, new Date(), "21:00", 3);
        eventRepo.save(event);
}

/**
 * Creates and saves a sample client in the given repository.
 *
 * @param clientId   the ID to assign to the client
 * @param clientRepo the target client repository
 */
    private static void preloadDataClient(String clientId, Repository<Client> clientRepo) {
        Address address = new Address("7 rue du Paradis", "Paris");
        Client client = new Client(clientId, "Julie", "Belrose", address, 29, "+33600000001");
        clientRepo.save(client);
}

/**
 * Simulates a successful ticket booking using the given service.
 *
 * @param ticketSvc the service to process the booking
 * @param clientId  the ID of the client booking
 * @param eventId   the ID of the event
 */
    private static void preloadDataTicket(TicketService ticketSvc, String clientId, String eventId) {
        try {
                Ticket ticket = ticketSvc.reserve(clientId, eventId, 1, SeatType.GOLD);
                System.out.println("[DATA] Ticket booked : " + ticket);
        } catch (CrudException ex) {
                System.out.println("[DATA] Error booking ticket : " + ex.getMessage());
        }
}

/**
 * Simulates a second ticket booking for demo purposes.
 *
 * @param svc the ticket service
 * @param ids previously generated client/event IDs
 */
private static void demoScript(TicketService svc, SampleIds ids) {
        try {
                Ticket t = svc.reserve(ids.clientId, ids.eventId, 1, SeatType.VIP);
                System.out.println("[DEMO] Booked: " + t);
        } catch (CrudException ex) {
                System.out.println("[DEMO] " + ex.getMessage());
        }
}
}
