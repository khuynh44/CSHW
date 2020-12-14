package HW07;

/**
 * A way for treatment to be performed in a sorted and orderly way.
 * @author Kevin Huynh
 * @version 1.0
 */
public class ScheduledTreatment extends Treatment {
    /** The ID of the treat patient. */
    private int treatPatientID;

    /**
     * Constructor the takes in heal amount and patient ID.
     * @param j The amount of heal.
     * @param k The treat patient ID.
     */
    public ScheduledTreatment(int j, int k) {
        super(j);
        treatPatientID = k;
    }

    /**
     * Performs heal amount on all patients in the sorted array.
     * @param arr The array of patients.
     */
    public void performHeal(Patient[] arr) {
        Patient key = new Patient("key", 0, treatPatientID);
        if (arr.length != 0) {
            HealAbility.sortPatients(arr);
            int target = binarySearch(arr, key);
            if (target != -1) {
                arr[target].increaseHealth(this);
            }
        }
    }

    /**
     * Searches the patient array passed in for the target value.
     * This list must be sorted in order for this method to work!
     * @see Patient
     * @param list the Patient array
     * @param target - the Patient element to search for
     * @return the index of the found element or -1 if not found
     */
    public static int binarySearch(Patient[] list, Patient target) {
        int min = 0;
        int max = list.length;
        int mid;
        boolean found = false;
        while (!found && min < max) {
            mid = (min + max) / 2;
            if (list[mid].compareTo(target) == 0) {
                found = true;
                return mid;
            } else if (target.compareTo(list[mid]) < 0) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }
}
