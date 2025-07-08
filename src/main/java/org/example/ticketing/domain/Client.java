package org.example.ticketing.domain;

import java.util.List;

public class Client {
    // ─── FIELDS ──────────────────────
    private String id;                  // e.g. UUID string
    private String firstName;
    private String lastName;
    private Address address;
    private int age;
    private String phoneNumber;
    private List<Ticket> tickets;

    // ─── CONSTRUCTOR ─────────────────
    // Parameters: id, firstName, lastName, address, age, phoneNumber
    // Initialize tickets as empty list
    // Validate: age must be >= 0
    // Optionally validate phoneNumber using regex

    // ─── GETTERS AND SETTERS ─────────
    // One for each field, except setId (ID is immutable)

    // ─── BUSINESS METHODS ────────────
    // addTicket(Ticket ticket)
    // removeTicketById(String ticketId) - optional

    // ─── toString() ──────────────────
    // Example output: [c123] Alice Smith – 28 yrs – 0600000000 – Paris
}
