package org.example.ticketing.domain;

public class Ticket {
    // ─── FIELDS ──────────────────────
    private String id;              // UUID string
    private int seatNumber;
    private Client client;          // reference
    private Event event;            // reference
    private SeatType seatType;

    // ─── CONSTRUCTOR ─────────────────
    // Parameters: id, seatNumber, client, event, seatType

    // ─── GETTERS AND SETTERS ─────────

    // ─── toString() ──────────────────
    // Example: [t12] Seat 3 – VIP – Alice – Jazz Night
}
