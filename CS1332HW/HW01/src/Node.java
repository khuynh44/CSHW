/**
 * Object to hold different types of data for a list.
 * @author Kevin Huynh
 * @version 1.0
 * @param <T> Generic type of data.
 */
public class Node<T> {

    /** The data of the object. */
    private T data;

    /** The data the object points to. */
    private Node<T> next;

    /**
     * Constructor taking in data type and next data.
     * @param d The data it holds.
     * @param n The next data it points to.
     */
    public Node(T d, Node<T> n) {
        data = d;
        next = n;
    }

    /**
     * The 1 argument constructor only taking in data.
     * @param d The data it holds.
     */
    public Node(T d) {
        data = d;
        next = null;
    }

    /**
     * Accessor method that returns the data.
     * @return The data of the object.
     */
    public T getData() {
        return data;
    }

    /**
     * Returns the next data is points to.
     * @return the next data.
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets the next data to a different data.
     * @param n The next data it changes to.
     */
    public void setNext(Node<T> n) {
        next = n;
    }
}
