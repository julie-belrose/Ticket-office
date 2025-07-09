package org.example.ticketing.domain.exception;

public class ValidationException extends UserMessageException {
    public ValidationException(String field, String hint) {
        super("Field '" + field + "' is not valid. " + hint);
    }
}
