package HW05;

/**
 * Program that stores the Cheese Connoisseurs.
 * @author Kevin Huynh
 *
 */
public class CheeseConnoisseur {
    /** The name of the connoisseur. */
    private String name;
    /** The type of cheese he/she owns. */
    private Cheese cheese;
    /** The amount of money the connoisseur has. */
    private int money;

    /**
     * Constructor that takes in the connoisseur's name, amount of money, and type of cheese owned.
     * @param name The name of the connoisseur.
     * @param money The amount of money the connoisseur has.
     * @param cheese The type of cheese the connoisseur owns.
     */
    public CheeseConnoisseur(String name, int money, Cheese cheese) {
        this.name = name;
        this.money = money;
        this.cheese = cheese;
    }

    /**
     * Constructor without cheese type.
     * @param name name of connoisseur.
     * @param money amount of money of connoisseur.
     */
    public CheeseConnoisseur(String name, int money) {
        this(name, money, null);
    }

    /**
     * Constructor with only name.
     * @param name name of connoisseur.
     */
    public CheeseConnoisseur(String name) {
        this(name, 0, null);
    }

    /**
     * constructor with out parameters.
     */
    public CheeseConnoisseur() {
        this("Louis Pasteur", 20, null);
    }

    /**
     * Method that allows connoisseurs to trade for other cheeses.
     * @param other The other cheese object he/she would like to trade.
     */
    public void trade(Cheese other) {
    	cheese.trade();
        cheese = other;
        cheese.trade();
    }

    /**
     * Overrides the previous method to allow for connoisseurs to trade with other connoisseurs.
     * @param other The other connoisseur he/she would like to trade with.
     */
    public void trade(CheeseConnoisseur other) {
        int difference = Math.abs(other.cheese.getPrice() - cheese.getPrice());
        if ((cheese.equals(other.cheese) == false) && (cheese.getPrice() < other.cheese.getPrice())) {
            if (difference <= money) {
                money = money - difference;
                other.money = other.money + difference;
                Cheese temp = cheese;
                trade(other.cheese);
                other.cheese = temp;
            }
        } else if (difference <= other.money) {
            other.money = other.money - difference;
            money = money + difference;
            Cheese temp = cheese;
            trade(other.cheese);
            other.cheese = temp;
            }
        }

    /**
     * Accessor method that retrieves the name of the connoisseur.
     * @return Returns the name of the connoisseur.
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method the retrieves the cheese owned by the connoisseur.
     * @return Returns the type of cheese owned by the connoisseur.
     */
    public Cheese getCheese() {
    	return cheese;
    }

    /**
     * Mutator method the change of type of cheese owned by the connoisseur.
     * @param c Takes in the type of cheese to set to.
     */
    public void setCheese(Cheese c) {
        cheese = c;
    }

    /**
     * Accessor method the retrieves the total amount of money the connoisseur has.
     * @return Returns the total amount of money the connoisseur has.
     */
    public int getMoney() {
        return money;
    }
}
