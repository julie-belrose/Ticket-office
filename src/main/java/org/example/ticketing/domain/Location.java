package org.example.ticketing.domain;

public class Location extends Address {
    // ─── EXTRA FIELD ─────────────────
    private int capacity;

    // ─── CONSTRUCTOR ─────────────────
    // Parameters: street, city, capacity
    // Call super(street, city)
    // Validate: capacity > 0

    // ─── GETTER AND SETTER ───────────

    // ─── toString() ──────────────────
    // Format: "street, city – capacity seats"
}
