package org.example.ticketing.ui;

import org.example.ticketing.domain.*;
import org.example.ticketing.domain.exception.CrudException;
import org.example.ticketing.service.*;
import java.util.Scanner;

/**
 * Root console menu.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Delegate CRUD actions to {@link Ihm} instances for Client and Event.</li>
 *   <li>Handle the booking flow via {@link TicketService}.</li>
 *   <li>Centralise user prompts and basic validation.</li>
 * </ul>
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * Scanner sc = new Scanner(System.in);
 * MainMenu menu = new MainMenu(clientUi, eventUi, ticketService, sc);
 * menu.start();
 * }</pre>
 */
public class MainMenu {

    private final Ihm<Client> clientIhm;
    private final Ihm<Event> eventIhm;
    private final TicketService ticketService;
    private final Scanner in;

    public MainMenu(Ihm<Client> clientIhm,
            Ihm<Event> eventIhm,
            TicketService ticketService,
            Scanner in) {
        this.clientIhm = clientIhm;
        this.eventIhm = eventIhm;
        this.ticketService = ticketService;
        this.in = in;
    }

    /**
     * Displays the main loop until the user chooses "0 - Exit".
     */
    public void start() {
        while (true) {
            showMenu();
            switch (in.nextLine().trim()) {
                case "1" -> clientIhm.start();
                case "2" -> eventIhm.start();
                case "3" -> runSafely(this::reserveFlow);
                case "0" -> { return; }
                default  -> System.out.println("Unknown option");
            }
        }
    }

    /* Centralizes try/catch for all actions likely to fail */
    private void runSafely(Runnable action) {
        try {
            action.run();
        } catch (CrudException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void showMenu() {
        System.out.println("""
                === TICKETING MENU ===
                1 - Manage clients
                2 - Manage events
                3 - Reserve ticket
                0 - Exit
                """);
    }

    /**
     * Full interactive flow to reserve a ticket.
     *
     * <p>Prompts the user for event ID, client ID, seat number and seat type; then
     * Delegates to the {@link TicketService#reserve} method for booking.
     *
     * @implNote All {@link CrudException} subclasses are caught and their
     *           messages are printed to the console.
     */
    private void reserveFlow() {
        String eventId  = prompt("Event ID? ");
        String clientId = prompt("Client ID? ");
        int seatNumber  = readInt("Seat number? ");

        SeatType seatType = readSeatType("Seat type (STANDARD/GOLD/VIP)? ");
        Ticket ticket = ticketService.reserve(clientId, eventId, seatNumber, seatType);
        System.out.println("Reserved : " + ticket);
    }

    /**
     * Displays a message and reads user input.
     * @param msg the prompt message
     * @return trimmed user input
     */
    private String prompt(String msg) {
        System.out.print(msg);
        return in.nextLine().trim();
    }

    private int readInt(String msg) {
        while (true) {
            try { return Integer.parseInt(prompt(msg)); }
            catch (NumberFormatException ex) { System.out.println("Enter a valid integer."); }
        }
    }

    private SeatType readSeatType(String msg) {
        while (true) {
            try { return SeatType.valueOf(prompt(msg).toUpperCase()); }
            catch (IllegalArgumentException ex) {
                System.out.println("Invalid type. Options: STANDARD, GOLD, VIP.");
            }
        }
    }
}
