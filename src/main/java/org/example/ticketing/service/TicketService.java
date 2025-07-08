package org.example.ticketing.service;

import org.example.ticketing.domain.*;
import org.example.ticketing.domain.exception.*;
import org.example.ticketing.repository.Repository;
import org.example.ticketing.domain.Event;

/**
 * Business logic that crosses aggregates:
 *   - reserves a seat,
 *   - links Ticket to Client and Event,
 *   - enforces capacity rule.
 */
public class TicketService {

    // ─── FIELDS ───────────────────────────────────
    private Repository<Event>  eventRepo;
    private Repository<Client> clientRepo;

    // ─── CONSTRUCTOR ─────────────────────────────
    // TicketService(Repository<Event>, Repository<Client>)

    // ─── BUSINESS METHOD ─────────────────────────
    public Ticket reserve(String eventId,
                          String clientId,
                          SeatType seatType)
            throws NotFoundException, CapacityFullException {
        // TODO: fetch entities, validate, create Ticket, update both aggregates, save
        return; /* new Ticket */
    }
}
