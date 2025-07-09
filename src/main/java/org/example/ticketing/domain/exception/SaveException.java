package org.example.ticketing.domain.exception;

public class SaveException extends CrudException {
    public SaveException(String details) {
        super("Unable to save resource: " + details);
    }
}
