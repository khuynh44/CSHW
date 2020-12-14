package HW06;

/**
 * The Archer adventurer.
 * @author Kevin
 * @version 1.0
 */
public class Archer extends Adventurer {
    /** The number of arrows the archer has set to 10. */
    private int arrows = 10;

    /**
     * Constructor taking in name, health, and attack value.
     * @param nam Name of archer.
     * @param heal Health value of archer.
     * @param attack Attack value of archer.
     */
    public Archer(String nam, int heal, int attack) {
        super(nam, heal, attack);
    }

    /**
     * Constructor taking in name and defaulting health and attack value.
     * @param nam Name of the archer.
     */
    public Archer(String nam) {
        super(nam, 75, 40);
    }

    /**
     * Allows archer to attack other adventurers.
     */
    public void attack(Adventurer other) {
        if (other == null) {
        } else if (arrows >= 1 && getHealth() > 0) {
            other.setHealth(other.getHealth() - getAttack());
            arrows -= 1;
            if (other.getHealth() < 0) {
                other.setHealth(0);
            }
        }
    }

    /**
     * Checks of tw0 archers are equal or not.
     * @param other The other archer.
     * @return whether they are equal or not.
     */
    public boolean equals(Archer other) {
        return super.equals(other);
    }

    /**
     * Returns the string value of the object.
     * @return the string value of the method.
     */
    public String toString() {
        return super.toString() + ", Arrows: " + arrows;
    }
}
