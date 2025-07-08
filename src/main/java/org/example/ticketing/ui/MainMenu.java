package org.example.ticketing.ui;

import org.example.ticketing.domain.*;
import org.example.ticketing.repository.*;
import org.example.ticketing.service.*;
import java.util.Scanner;

/** Root menu that wires sub-IHMs and the booking flow. */
public class MainMenu {

    // ─── FIELDS ───────────────────────────────────
    private Ihm<Client> clientIhm;
    private Ihm<Event>  eventIhm;
    private TicketService ticketService;

    // ─── CONSTRUCTOR ─────────────────────────────
    // MainMenu(Ihm<Client>, Ihm<Event>, TicketService)

    // ─── MAIN LOOP ───────────────────────────────
    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("""
                === TICKETING MENU ===
                1 - Manage clients
                2 - Manage events
                3 - Reserve ticket
                0 - Exit
                """);
            switch (sc.nextLine().trim()) {
                case "1" -> clientIhm.start(sc);
                case "2" -> eventIhm.start(sc);
                case "3" -> reserveFlow(sc);   // ask eventId, clientId, seatType
                case "0" -> { return; }
                default  -> System.out.println("Unknown option");
            }
        }
    }

    // reserveFlow(Scanner sc)
    //   - prompt user inputs
    //   - call ticketService.reserve()
    //   - catch and print exceptions
}
