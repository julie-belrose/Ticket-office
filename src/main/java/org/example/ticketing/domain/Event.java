package org.example.ticketing.domain;

import java.util.List;
import java.util.Date;

public class Event {
    // ─── FIELDS ──────────────────────
    private String id;                  // UUID
    private String name;
    private Location location;          // has capacity
    private Date date;
    private String time;                // optional: replace with LocalTime
    private int totalSeats;
    private List<Ticket> tickets;

    // ─── CONSTRUCTOR ─────────────────
    // Parameters: id, name, location, date, time, totalSeats
    // Validate: totalSeats > 0
    // Initialize empty ticket list

    // ─── GETTERS AND SETTERS ─────────

    // ─── BUSINESS METHODS ────────────
    // boolean hasAvailableSeats()
    // void addTicket(Ticket ticket)
    //   - If no seats available → throw CapacityFullException

    // ─── toString() ──────────────────
    // Example: [e45] Jazz Night @Paris on 2025-08-01 20:00 (12/50 seats)
}
