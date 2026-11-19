/**
 * ListEmptyException - my own exception for an empty list.
 *
 * Thrown by remove() and get() when the list has no items. A named
 * exception makes the problem clearer than a NullPointerException.
 *
 * It extends RuntimeException so callers are not forced to catch it.
 * ToDoList still catches it and prints a message instead.
 */
public class ListEmptyException extends RuntimeException {

    // Version number Java uses when saving exceptions; removes a compiler warning
    private static final long serialVersionUID = 1L;

    /**
     * Creates the exception with a message explaining what went wrong.
     *
     * @param message description of the error
     */
    public ListEmptyException(String message) {
        super(message);
    }
}
