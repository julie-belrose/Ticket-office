package org.example.ticketing.domain.exception;

public class UpdateException extends CrudException {
    public UpdateException(String details) {
        super("Unable to update resource: " + details);
    }
}