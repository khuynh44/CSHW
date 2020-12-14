package PE05;
/**
 * A class representing a company.
 * @author Jack Kelly
 * @version 1
 **/
public class Company {

    private int numEmployees;
    private String name;
    private Building headQuarters;

    /**
     * Method that checks if two objects of the same class are equal.
     * @param other another company object
     * @return returns whether the objects are equals or not
     **/
    public boolean equals(Object other) {
    	if (other == this) {
            return true;
        } else if (other instanceof Company) {
            Company temp = (Company) other;
            return numEmployees == temp.numEmployees
                && name.equals(temp.name)
                && headQuarters.equals(temp.headQuarters);
        }
        return false;
    }

    /**
     * Method that returns the string value of the object.
     * @return the string value of the method
     */
    public String toString() {
        return "The {" + name + "} company has {" + numEmployees + "} employees and their"
                + " headquarters is in the {" + headQuarters + "} building.";
    }

    /* ********************************
     * Do not edit below this comment *
     * ********************************/

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 17;
        result = prime * result + numEmployees;
        result = prime * result + name.hashCode();
        result = prime * result + headQuarters.hashCode();
        return result;
    }

    /**
     * A constructor for the Company class.
     * @param numEmployees value to set numEmployees to
     * @param name String to set name equal to
     * @param hq Building to set hq equal to
     **/
    public Company(int numEmployees, String name, Building hq) {
        this.numEmployees = numEmployees;
        this.name = name;
        this.headQuarters = hq;
    }

    /**
     * getter for headQuarters.
     * @return the value of headQuarters
     **/
    public Building getHeadQuarters() {
        return headQuarters;
    }

    /**
     * setter for headQuarters.
     * @param value the value to set it to
     **/
    public void setHeadQuarters(Building value) {
        headQuarters = value;
    }

    /**
     * getter for name.
     * @return the value of name
     **/
    public String getName() {
        return name;
    }

    /**
     * setter for name.
     * @param value the value to set it to
     **/
    public void setName(String value) {
        name = value;
    }

    /**
     * getter for numEmployees.
     * @return the value of numEmployees
     **/
    public int getNumEmployees() {
        return numEmployees;
    }

    /**
     * setter for numEmployees.
     * @param value the value to set it to
     **/
    public void setNumEmployees(int value) {
        numEmployees = value;
    }
}
