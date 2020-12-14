package HW07;

/**
 * Represents a Doctor object with a certain name, 2 treatment objects,
 * and methods that act on those Treatment objects.
 * @author (your name here)
 * @version 1.0
 */
public class Doctor {
    private String name;
    private Treatment treatment;

    /**
     * Initialies a Doctor object with the given name and 1 additional
     * parameter.
     * After creating the EmergencyTreatment class, and creating an instance variable
     * of type Treatment above, in this constructor you should initialize an
     * instance of the **EmergencyTreatment** class, whose constructor will take
     * in the value of `emergencyHealAmount` This new object should be assigned
     * to an instance variable in this class.
     * @param  name                 the name of this doctor
     * @param  emergencyHealAmount  the amount of health points to pass in to the
     *                              EmergencyTreatment constructor
     */
    public Doctor(String name, int emergencyHealAmount) {
        this.name = name;
        treatment = new EmergencyTreatment(emergencyHealAmount);
    }

    /**
     * Initialies a Doctor object with the given name, and takes in 2 additional
     * parameters.
     * After creating the ScheduledTreatment class, and creating an instance variable
     * of type Treatment above, in this constructor you should initialize an
     * instance of the **ScheduledTreatment** class, whose constructor will take
     * in the value of `scheduledHealAmount` as well as `scheduledPatientID`.
     * This new object should be assigned to an instance variable in this class.
     * @param  name                 the name of this doctor
     * @param  scheduledHealAmount  the amount of health points to pass in to the
     *                              ScheduledTreatment constructor
     * @param  scheduledPatientID   the patientID to pass in to the
     *                              ScheduledTreatment constructor
     */
    public Doctor(String name, int scheduledHealAmount, int scheduledPatientID) {
        this.name = name;
        treatment = new ScheduledTreatment(scheduledHealAmount, scheduledPatientID);
    }

    /**
     * Sets the treatment to a different object.
     * @param t The treatment object it is set to.
     */
    public void setTreatment(Treatment t) {
        treatment = t;
    }

    /**
     * Allows the doctor to perform the treatment on all the patients of the array.
     * @param arr The array of patients.
     */
    public void performTreatment(Patient[] arr) {
        System.out.println(name + " goes to heal their patients");
        treatment.performHeal(arr);
    }

    @Override
    public String toString() {
        return String.format(
            "Doctor %s with treatment %s",
            name, treatment.toString()
        );
    }
}