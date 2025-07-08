package org.example;


import org.example.ticketing.domain.Client;
import org.example.ticketing.repository.MapRepository;
import org.example.ticketing.repository.Repository;
import org.example.ticketing.service.TicketService;
import org.example.ticketing.ui.Ihm;
import org.example.ticketing.domain.Event;
import org.example.ticketing.ui.MainMenu;

public class Main {
    public static void main(String[] args) {

        // ─── REPOSITORIES ─────────────────────────
        Repository<Client> clientRepo =
                new MapRepository<>(Client::getId);
        Repository<Event> eventRepo =
                new MapRepository<>(Event::getId);

        // ─── GENERIC IHMs (lambdas) ───────────────
        Ihm<Client> clientIhm = new Ihm<>(
                clientRepo,
                () -> { /* ask Scanner questions, build Client */ },
                (c, sc) -> { /* update Client fields */ }
        );

        Ihm<Event> eventIhm = new Ihm<>(
                eventRepo,
                () -> { /* build Event */ },
                (e, sc) -> { /* update Event */ }
        );

        // ─── SERVICE ─────────────────────────────
        TicketService ticketService = new TicketService(eventRepo, clientRepo);

        // ─── MENU ────────────────────────────────
        MainMenu menu = new MainMenu(clientIhm, eventIhm, ticketService);
        menu.start();
    }
}