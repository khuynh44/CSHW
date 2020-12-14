package HW08;

/**
 * Unchecked exception checking if a specific restaurant exist in the database.
 */
public class NotARestaurantException extends RuntimeException {

    /**
     * Constructor that delivers message to the console.
     */
    NotARestaurantException() {
        super("That is not a restaurant we know!");
    }
}
