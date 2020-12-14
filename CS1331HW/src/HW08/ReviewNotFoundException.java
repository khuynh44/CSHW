package HW08;

/**
 * Checked exception that checks if a review is found in a database.
 * @author Kevin Huynh
 * @version 1.0
 */
public class ReviewNotFoundException extends Exception {

    /**
     * Constructor for the check exception.
     * @param message The message delivered to the console when exception is thrown.
     */
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
