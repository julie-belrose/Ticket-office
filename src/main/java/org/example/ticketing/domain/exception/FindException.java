package org.example.ticketing.domain.exception;

public class FindException extends CrudException {
    public FindException(String details) {
        super("Unable to retrieve resource: " + details);
    }
}