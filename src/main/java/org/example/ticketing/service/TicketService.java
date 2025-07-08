package org.example.ticketing.service;

import org.example.ticketing.domain.*;
import org.example.ticketing.domain.exception.*;
import org.example.ticketing.repository.Repository;
import java.util.UUID;

/**
 * Business logic that crosses aggregates:
 *   - reserves a seat,
 *   - links Ticket to Client and Event,
 *   - enforces capacity rule.
 */
public class TicketService {

    // ─── FIELDS ───────────────────────────────────
    private final Repository<Event>  eventRepo;
    private final Repository<Client> clientRepo;

    public TicketService(Repository<Event> eventRepo, Repository<Client> clientRepo) {
        this.eventRepo = eventRepo;
        this.clientRepo = clientRepo;
    }

    /**
     * Reserves a seat for a client on a given event.
     *
     * @param clientId   Unique identifier of the client requesting the ticket.
     * @param seatNumber Seat number to reserve (must be positive and available).
     * @param eventId    Unique identifier of the target event.
     * @param seatType   Requested seat category (STANDARD, GOLD, VIP).
     *
     * @return {@link Ticket}  Newly created ticket, fully linked to the client and the event.
     *
     * @throws NotFoundException     If the client or the event cannot be found in their repositories.
     * @throws CapacityFullException If the event has reached its maximum capacity.
     *
     * <p><b>Example :</b></p>
     * <pre>{@code
     * TicketService service = new TicketService(eventRepo, clientRepo);
     * Ticket t = service.reserve("CLI1", 12, "EVT7", SeatType.VIP);
     * System.out.println("Booked: " + t);
     * }</pre>
     */
    public Ticket reserve(String clientId,
                          String eventId,
                          int seatNumber,
                          SeatType seatType)
            throws NotFoundException, CapacityFullException {

        Event  event = eventRepo.find(eventId)
                .orElseThrow(() -> new NotFoundException("Event " + eventId + " not found"));

        Client client = clientRepo.find(clientId)
                .orElseThrow(() -> new NotFoundException("Client " + clientId + " not found"));

        if (!event.hasAvailableSeats()) {
            throw new CapacityFullException(event.getName());
        }

        String ticketId = UUID.randomUUID().toString(); // ID unique
        Ticket ticket = new Ticket(ticketId, seatNumber, client, event, seatType);

        event.addTicket(ticket);
        client.addTicket(ticket);

        eventRepo.save(event);
        clientRepo.save(client);

        return ticket;
    }
}
