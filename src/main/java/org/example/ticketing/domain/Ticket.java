package org.example.ticketing.domain;

import java.util.Objects;

public class Ticket {
    private final String id;
    private final int seatNumber;
    private final Client client;
    private final Event event;
    private final SeatType seatType;

    public Ticket(String id, int seatNumber, Client client, Event event, SeatType seatType) {
        if (seatNumber <= 0)
            throw new IllegalArgumentException("Seat number must be positive");

        Objects.requireNonNull(id,        "id");
        Objects.requireNonNull(client,    "client");
        Objects.requireNonNull(event,     "event");
        Objects.requireNonNull(seatType,  "seatType");

        this.id = id;
        this.seatNumber = seatNumber;
        this.client = client;
        this.event = event;
        this.seatType = seatType;
    }

    public String getId() {
        return id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public Client getClient() {
        return client;
    }

    public Event getEvent() {
        return event;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + seatNumber + " – " + seatType + " – " + client.getFirstName() + " "
                + client.getLastName() + " – " + event.getName();
    }
}
