package PE05;

public class SmallBusiness extends Company {

	/**
	 * Constructor method that takes in number of employees, name, and building of the company.
	 * @param Employees the number of employees in the business
	 * @param name the name of the small business
	 * @param hq the building that the business is located in
	 **/
    public SmallBusiness(int Employees, String name, Building hq) {
        super(Employees, name, hq);
        if (getNumEmployees() > 25 || getNumEmployees() < 0) {
            super.setNumEmployees(25);
        }
    }

    /**
     * setter method that sets the number of employees to 25 if users try to set it over 25.
     * @param num takes in the number of the employees at which to set it at
     */
    public void setNumEmployees(int num) {
        if (num > 25 || num < 0) {
            super.setNumEmployees(25);
        } else {
            super.setNumEmployees(num);
        }
    }
}
