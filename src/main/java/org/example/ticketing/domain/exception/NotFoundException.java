package org.example.ticketing.domain.exception;

public class NotFoundException extends CrudException {
    public NotFoundException(String id) {
        super("Resource with ID '" + id + "' not found.");
    }
}
