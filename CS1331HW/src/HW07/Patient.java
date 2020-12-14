package HW07;

/**
 * Represents a Patient object with a particular name,
 * health attribute that will be always greater or equal to 0
 * but should never exceed maxHealth. (assume that whoever makes an instance of this
 * object does it in the correct way; you do not need to change the constructor.)
 * There are a few methods in here for you to implement, in addition to changing
 * the class header such that Patient properly implements the Comparable interface
 * @author Kevin Huynh
 * @version 1.0
 */
public class Patient implements Comparable<Patient> {
    /** The name of the patient */
    private final String name;

    /** The health and ID of the patient */
    private int health, patientID;

    /**
     * Compares two patients based on their patientID.
     * @param other The other patient.
     * @return returns the difference between patients ID's.
     */
    public int compareTo(Patient other) {
        return patientID - other.patientID;
    }

    /**
     * Allows patient's health to be increased by heal amount.
     * @param o The HealAbility object to obtain heal amount.
     */
    public void increaseHealth(HealAbility o) {
        health = health + o.getHealAmount();
        System.out.println("Patient " + name + " has been healed by " + o.getHealAmount()
            + " health points!");
    }

    /**
     * getter for patientID.
     * @return the value of patientID
     */
    public int getPatientID() {
        return patientID;
    }

    /**
     * getter for health.
     * @return the value of health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Initializes a Patient object with a parameter list corresponding
     * to all of its fields.
     * @param  name      name of this Patient
     * @param  health    current health of this Patient
     * @param  patientID the identification number for our Patient
     */
    public Patient(String name, int health, int patientID) {
        this.name = name;
        this.health = health;
        this.patientID = patientID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof Patient) {
            Patient p = (Patient) o;
            return name.equals(p.name) && health == p.health && patientID == p.patientID;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 17;
        result = prime * result + health;
        result = prime * result + patientID;
        result = prime * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("Patient '%s' with %d health and ID %d", name, health, patientID);
    }


}