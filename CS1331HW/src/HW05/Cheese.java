package HW05;

/**
 * Program blueprints the types of cheeses.
 * @author Kevin Huynh
 *
 */
public class Cheese {
    /** The type of Cheese it is. */
    private String cheeseType;
    /** The price of the cheese. */
    private int price;
    /** Stores whether the cheese is sentient. */
    private boolean cheeseSentient;
    /** Counts how many times the cheese has been traded. */
    private int cheeseCount;
    /** Counts the total number of trades between all cheeses. */
    private static int totalCount;

    /**
     * Constructor taking in the price, type, and number of times it was traded.
     * @param price The price of the cheese.
     * @param type The type of cheese.
     * @param tradenum The number of times it was traded.
     */
    public Cheese(int price, String type, int tradenum) {
        this.price = price;
        cheeseType = type;
        cheeseCount = tradenum;
        if (cheeseCount >= 3) {
        	cheeseSentient = true;
        }
    }

    /**
     * overrides the previous method setting trade number to 0 and sentient to false.
     * @param price 
     * @param type
     */
    public Cheese(int price, String type) {
        this.price = price;
        cheeseType = type;
        cheeseCount = 0;
        cheeseSentient = false;
    }

    /**
     * Accessor method that retrieves the String version of the object.
     * @return Returns a String value of the object.
     */
    public String toString() {
        if (cheeseSentient) {
            return "I am a slice of " + cheeseType + " cheese that has been traded " +
                    cheeseCount + " times.";
        } else {
            return "This is a slice of " + cheeseType + " cheese that has been traded " +
                    cheeseCount + " times.";
        }
    }

    /**
     * Checks whether another Cheese object is equal to another one.
     * @param other Takes in another Cheese object.
     * @return Returns whether or not the objects are equivalent.
     */
    public boolean equals(Cheese other) {
    	return cheeseType.equals(other.cheeseType) && price == other.price && 
    		    cheeseSentient == other.cheeseSentient;
    }

    /**
     * Mutator method that changes the total trade count, object trade count, cheese sentience, and price.
     */
    public void trade() {
        cheeseCount++;
        totalCount++;
        if (cheeseCount == 3) {
        	cheeseSentient = true;
        	price = price * 2;
        	System.out.println("I'm ALIIIIIVE!");
        }
    }

    /**
     * Accessor method that retrieves the cheese type.
     * @return Returns the cheese type.
     */
    public String getCheese() {
        return cheeseType;
    }

    /**
     * Accessor method that retrieves the price of the cheese.
     * @return Returns the price of the object
     */
    public int getPrice() {
        return price;
    }

    /**
     * Accessor method that retrieves the sentience of the cheese.
     * @return Returns whether the cheese is sentient or not.
     */
    public boolean getSentience() {
        return cheeseSentient;
    }

    /**
     * Accessor method that retrieves the number of trades of the cheese.
     * @return Returns the number of the trades of the cheese.
     */
    public int getTradecount() {
        return cheeseCount;
    }

    /**
     * Accessor method that retrieves the total number of trades of the all cheeses.
     * @return Returns the total number of trades of all cheeses.
     */
    public int getTotalcount() {
        return totalCount;
    }
}
