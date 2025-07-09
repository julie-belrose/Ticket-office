package org.example.ticketing.domain.exception;

/**
 * <h2>User-friendly root exception</h2>
 *
 * <p>Base class for <em>all</em> application / domain errors that must be
 * displayed to the end-user with a clear, understandable sentence.<br>
 * The technical stack-trace still contains the same text because we forward the
 * message to {@link RuntimeException#RuntimeException(String)}.</p>
 *
 * <p><b>Usage</b> – extend this class when:</p>
 * <ul>
 *   <li>the error is meant to be caught by the UI or a REST layer,</li>
 *   <li>you want a single call to {@code ex.getMessage()} to be enough.</li>
 * </ul>
 */
public abstract class UserMessageException extends RuntimeException {

    private final String userMessage;

    /**
     * @param userMessage a ready-to-display sentence for the UI
     */
    protected UserMessageException(String userMessage) {
        super(userMessage);          // still visible in stack trace
        this.userMessage = userMessage;
    }

    /** @return the user-facing message (UI / console). */
    @Override
    public String getMessage() {
        return userMessage;
    }
}
