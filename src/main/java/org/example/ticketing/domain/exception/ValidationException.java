package org.example.ticketing.domain.exception;

public class ValidationException extends CrudException {
    private static final String BASE_MSG = "Data validation failed";

    private final String fullMessage;

    public ValidationException(String field) {
        super(BASE_MSG);
        this.fullMessage = "Field '" + field + "' is not valid.";
    }

    public ValidationException(String field, String hint) {
        super(BASE_MSG);
        this.fullMessage = "Field '" + field + "' is not valid. " + hint;
    }

    public String getFullMessage() {
        return fullMessage;
    }
}
