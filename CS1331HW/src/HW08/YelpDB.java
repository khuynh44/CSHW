package HW08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class of the project.
 * @author Kevin Huynh
 * @version 1.0
 */
public class YelpDB {

    /**
     * Main method that tests all the static methods.
     * @param args The main argument.
     */
    public static void main(String[] args) {
        File dataBase = new File("C:\\Users\\Kevin\\"
                + "IdeaProjects\\CSHWW\\src\\HW08\\database_restaurants");
        ArrayList<Restaurant> arr = load(dataBase);
        System.out.println(arr);
        System.out.println(countNumReview(arr));
        try {
            System.out.println(readReview(dataBase, arr, "Satto", "Neethu"));
        } catch (Exception e) {
            System.out.println("File is not found");
        }
    }

    /**
     * Loads the other helper method.
     * @param o The file of the database.
     * @return The list of restaurants in the database.
     */
    public static ArrayList<Restaurant> load(File o) {
        return loadHelper(o, new ArrayList<Restaurant>());
    }

    /**
     * Loads all of the restaurants found in the database and stores it in array list.
     * @param o The file of the database.
     * @param arr The array list of restaurants.
     * @return The array list of restaurants.
     */
    public static ArrayList<Restaurant> loadHelper(File o, ArrayList<Restaurant> arr) {
        File[] nest = o.listFiles();
        String check;
        try {
            check = nest[0].getName().substring(0, 10);
        } catch (Exception e) {
            check = nest[0].getName() + "          ";
        }
        if (!(check.equals("restaurant"))) {
            for (int i = 0; i < nest.length; i++) {
                loadHelper(nest[i], arr);
            }
        } else {
            for (int i = 0; i < nest.length; i++) {
                File[] reviews = nest[i].listFiles();
                String name = nest[i].getName().substring(10);
                arr.add(new Restaurant(name, reviews.length));
            }
        }
        return arr;
    }

    /**
     * Sums up the total number of reviews in each restaurant.
     * @param arr The array list of restaurants.
     * @return The total sum of reviews.
     */
    public static int countNumReview(ArrayList<Restaurant> arr) {
        int total = 0;
        for (Restaurant restaurant : arr) {
            total += restaurant.getNumReviews();
        }
        return total;
    }

    /**
     * Searches for and reads the rating of a specific
     * reviewer at a specific restaurant.
     * @param o The file location of the database.
     * @param arr The array of restaurants.
     * @param restaurantName The name of the restaurant for the rating.
     * @param reviewName The name of the reviewer.
     * @return The rating of the review found.
     * @throws FileNotFoundException If the file is not found in the database.
     * @throws ReviewNotFoundException If review is not found in the database.
     */
    public static double readReview(File o, ArrayList<Restaurant> arr, String restaurantName, String reviewName)
            throws FileNotFoundException, ReviewNotFoundException {
        Scanner scan;
        Restaurant other = new Restaurant(restaurantName, 0);
        String revName = "review" + reviewName + ".txt";
        String restName = "restaurant" + restaurantName;
        String parent = o.getParentFile().getName();
        double k;
        if (!arr.contains(other)) {
            throw new NotARestaurantException();
        }
        if (o.isDirectory()) {
            File[] nest = o.listFiles();
            for (int i = 0; i < nest.length; i++) {
                k = readReview(nest[i], arr, restaurantName, reviewName);
                if (k > -1.0) {
                    return k;
                }
            }
            return -1;
        } else if (parent.equals(restName) && o.getName().equals(revName)) {
            scan = new Scanner(o);
            scan.nextLine();
            scan.nextLine();
            String w = scan.nextLine().substring(8);
            return Double.parseDouble(w);
        } else {
            return -1;
        }
    }
}
