package org.example.ticketing.domain.exception;

public class ValidationException extends CrudException {
    private static final String BASE_MSG = "Data validation failed";

    private final String fullMessage;

    // ─── Constructor without hint ───────────────────
    public ValidationException(String field) {
        super(BASE_MSG);
        this.fullMessage = "Field '" + field + "' is not valid.";
    }

    // ─── Constructor with hint ──────────────────────
    public ValidationException(String field, String hint) {
        super(BASE_MSG);
        this.fullMessage = "Field '" + field + "' is not valid. " + hint;
    }

    public String getFullMessage() {
        return fullMessage;
    }
}
