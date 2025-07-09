package org.example.ticketing.domain.exception;

public class DeleteException extends CrudException {
    public DeleteException(String details) {
        super("Unable to delete resource: " + details);
    }
}