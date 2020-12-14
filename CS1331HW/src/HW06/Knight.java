package HW06;

/**
 * The knight adventurer.
 * @author Kevin
 * @version 1.0
 */
public class Knight extends Adventurer {
    /** If the adventurer has a squire or not. */
    private boolean hasSquire;

    /**
     * Constructor taking in name, health, attack, and if they have a squire.
     * @param nam Name of the knight.
     * @param heal Health of the knight.
     * @param attack Attack value of the knight.
     * @param squire If the knight has a squire.
     */
    public Knight(String nam, int heal, int attack, boolean squire) {
        super(nam, heal, attack);
        hasSquire = squire;
    }

    /**
     * Constructor taking in name and defaulting health, attack, and squire value.
     * @param nam Name of the knight.
     */
    public Knight(String nam) {
        super(nam, 100, 15);
        hasSquire = true;
    }

    /**
     * Allows the knight to attack other adventurers.
     * @param other The other adventurer.
     */
    public void attack(Adventurer other) {
        if (other == null) {
        } else if (getHealth() > 0) {
            if (hasSquire) {
                other.setHealth(other.getHealth() - getAttack() * 2);
            } else {
                other.setHealth(other.getHealth() - getAttack());
            }
            if (other.getHealth() < 0) {
                other.setHealth(0);
            }
        }
    }

    /**
     * Allows knight to challenge another knight for their squire.
     * @param other The other knight.
     */
    public void challenge(Knight other) {
        if (other == null) {
        } else if (getHealth() > 0 && !(hasSquire) && other.hasSquire) {
            attack(other);
            if (other.getHealth() == 0) {
                hasSquire = true;
                other.hasSquire = false;
            } else {
                other.attack(this);
            }
        }
    }

    /**
     * Checks if two knights are equal or not.
     * @param other The other knight.
     * @return whether the knights are equal or not.
     */
    public boolean equals(Knight other) {
        return super.equals(other) && hasSquire == other.hasSquire;
    }

    /**
     * returns the String value of the object.
     * @return the String value of the object.
     */
    public String toString() {
        return super.toString() + ", Squire: " + hasSquire;
    }
}