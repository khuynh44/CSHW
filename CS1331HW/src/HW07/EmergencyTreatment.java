package HW07;

/**
 * A way to treat patients with the lowest amount of health.
 * @author Kevin Huynh
 * @version 1.0
 */
public class EmergencyTreatment extends Treatment {

    /**
     * Constructor taking in the amount of heal value.
     * @param k The amount of heal.
     */
    public EmergencyTreatment(int k) {
        super(k);
    }

    /**
     * Allows patients with least health to be healed first.
     * @param arr The array of patients that needs to be healed.
     */
    public void performHeal(Patient[] arr) {
        Patient p;
        if (arr.length != 0) {
            p = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i].getHealth() < p.getHealth()) {
                    p = arr[i];
                }
            }
            p.increaseHealth(this);
        }
    }
}
