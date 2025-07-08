package org.example.ticketing.domain.exception;

/**
 * Thrown when an Event has no seats left.
 * Provides a fixed base message and an optional constructor
 * that embeds the event identifier.
 */
public class CapacityFullException extends CrudException {

    private static final String BASE_MSG = "Event capacity reached";

    private final String fullMessage;

    public CapacityFullException() {
        super(BASE_MSG);
        this.fullMessage = BASE_MSG + ".";
    }

    public CapacityFullException(String eventRef) {
        super(BASE_MSG);
        this.fullMessage = BASE_MSG + " for event '" + eventRef + "'.";
    }

    public String getFullMessage() { return fullMessage; }
}
