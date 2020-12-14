package HW06;

/**
 * Program for character dungeon game.
 * astract class the is used as extension blueprint for other character classes.
 * @author Kevin
 * @version 1.0
 */
public abstract class Adventurer {

    /** The name of the Adventurer. */
    private String name;
    /** The attack value of the adventurer */
    private int att;
    /** The health value of the adventurer */
    private int health;

    /**
     * Constructor taking in name, health, and attack values.
     * @param nam The name of the adventurer.
     * @param heal The health value of the adventurer.
     * @param attack The attack value of the adventurer.
     */
    public Adventurer(String nam, int heal, int attack) {
        name = nam;
        health = heal;
        att = attack;
    }

    /**
     * Allows other adventurers to attack each other.
     * @param other The other adventurer.
     */
    public abstract void attack(Adventurer other);

    /**
     * Allows adventurers to restore their health.
     */
    public void restore() {
        health += 15;
    }

    /**
     * Checks if two adventurers are equal.
     * @param other The other adventurer.
     * @return returns if they are equal or not.
     */
    public boolean equals(Adventurer other) {
        return name.equals(other.name) && health == other.health && att == other.att;
    }

    /**
     * returns the string value of the object.
     * @return String value of the object.
     */
    public String toString() {
        return "Name: " + name + ", Health: " + health + ", Attack: " + att;
    }

    /**
     * Changes the health of the adventurer.
     * @param h The health that is set.
     */
    public void setHealth(int h) {
        health = h;
    }

    /**
     * Accesses the attack value of the adventurer.
     * @return the attack value of the adventurer.
     */
    public int getAttack() {
        return att;
    }

    /**
     * Accesses the health value of the adventurer.
     * @return The health value of the adventurer.
     */
    public int getHealth() {
        return health;
    }
}