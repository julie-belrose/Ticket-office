package org.example.ticketing.domain.exception;

/**
 * Exception levée lorsqu’un {@link org.example.ticketing.domain.Event} a
 * atteint sa capacité maximale.
 *
 * <p>
 * This class provides a complete personalized message via
 * {@link #getMessage()},
 * based on the event identifier concerned.
 * It inherits from {@link CrudException} for uniform management in the
 * application.
 * </p>
 *
 * <p>
 * Example of usage :
 * </p>
 * 
 * <pre>{@code
 * if (!event.hasAvailableSeats()) {
 *     throw new CapacityFullException(event.getId());
 * }
 * }</pre>
 *
 * <p>
 * And on the display side, in the IHM or a service :
 * </p>
 * 
 * <pre>{@code
 * catch (CrudException ex) {
 *     System.out.println(ex.getMessage());
 * }
 * }</pre>
 */
public class CapacityFullException extends CrudException {

    private final String fullMessage;

    /**
     * Constructs an exception with a detailed message including the event
     * identifier.
     *
     * @param eventRef the complete event identifier (or name)
     */
    public CapacityFullException(String eventRef) {
        super("Event capacity reached");
        this.fullMessage = "Event '" + eventRef + "' is full.";
    }

    /**
     * Returns the complete message to display to the user.
     *
     * @return the complete message describing the error
     */
    @Override
    public String getMessage() {
        return fullMessage;
    }
}

