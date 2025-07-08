package org.example.ticketing.domain.exception;

/**
 * Thrown when an entity cannot be located in the repository.
 * Uses a base message and lets you append which element was missing.
 */
public class NotFoundException extends CrudException {

    private static final String BASE_MSG = "Requested element not found";

    private final String fullMessage;

    // Constructor without details
    public NotFoundException() {
        super(BASE_MSG);
        this.fullMessage = BASE_MSG + ".";
    }

    // Constructor with element context (type + id)
    public NotFoundException(String elementRef) {
        super(BASE_MSG);
        this.fullMessage = BASE_MSG + ": " + elementRef + ".";
    }

    public String getFullMessage() { return fullMessage; }
}
