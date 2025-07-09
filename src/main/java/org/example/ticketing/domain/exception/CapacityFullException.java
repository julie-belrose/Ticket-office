package org.example.ticketing.domain.exception;

public class CapacityFullException extends UserMessageException {
    public CapacityFullException(String eventRef) {
        super("Event '" + eventRef + "' is full.");
    }
}

