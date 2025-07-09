package org.example.ticketing.domain;

import java.util.List;

import org.example.ticketing.domain.exception.CapacityFullException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Event {
    private final String id;
    private String name;
    private final Location location;
    private final Date date;
    private String time;
    private final int totalSeats;
    private final List<Ticket> tickets;

    public Event(String id, String name, Location location, Date date, String time, int totalSeats) {
        validateNotNull(id, "id");
        validateNotNull(name, "name");
        validateNotNull(location, "location");
        validateNotNull(date, "date");
        validateNotNull(time, "time");
        validateTotalSeats(totalSeats);
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
        this.totalSeats = totalSeats;
        this.tickets = new ArrayList<>();
    }

    private void validateNotNull(Object obj, String field) {
        if (obj == null)
            throw new IllegalArgumentException(field + " must not be null");
    }

    private void validateTotalSeats(int totalSeats) {
        if (totalSeats <= 0)
            throw new IllegalArgumentException("Total seats must be > 0");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    public void setName(String name) {
        validateNotNull(name, "name");
        this.name = name;
    }

    public boolean hasAvailableSeats() {
        return tickets.size() < totalSeats;
    }

    public void addTicket(Ticket ticket) {
        if (!hasAvailableSeats()) {
            throw new CapacityFullException(id);
        }
        if (!this.equals(ticket.getEvent())) {
            throw new IllegalArgumentException("Ticket does not belong to this event");
        }
        tickets.add(ticket);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name + " @" + location + " on " + date + " " + time + " (" + tickets.size() + "/"
                + totalSeats + " seats)";
    }
}
