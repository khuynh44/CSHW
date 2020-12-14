package HW08;

/**
 * The layout of each restaurant object.
 * @author Kevin Huynh
 * @version 1.0
 */
public class Restaurant {
    /** The name of the restaurant. */
    private String name;

    /** The number of reviews it has. */
    private int numReviews;

    /**
     * Constructor taking in the name and number of reviews of the restaurant.
     * @param n The name of the restaurant.
     * @param nr The number of reviews it has.
     */
    public Restaurant(String n, int nr) {
        name = n;
        numReviews = nr;
    }

    @Override
    public String toString() {
        return "Restaurent: " + name + ", reviews: " + numReviews;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Restaurant) {
            Restaurant other = (Restaurant) o;
            return name.equals(other.name);
        } else {
            return false;
        }
    }

    /**
     * Accessor method that obtains the name of the restaurant.
     * @return The name of the restaurant.
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method that obtains the number of reviews for the restaurant.
     * @return The number of reviews for the restaurant.
     */
    public int getNumReviews() {
        return numReviews;
    }
}
