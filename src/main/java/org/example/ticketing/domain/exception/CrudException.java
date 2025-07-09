package org.example.ticketing.domain.exception;

/**
 * Parent for errors raised <em>during CRUD operations</em>
 * on repositories: <strong>save, find, update, delete</strong>.
 */
public abstract class CrudException extends UserMessageException {
    protected CrudException(String userMessage) {
        super(userMessage);
    }
}
