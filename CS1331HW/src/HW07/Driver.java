package HW07;

/**
 * Driver class for you to test out classes you write.  Feel free to change
 * whatever is in main; you won't be graded on it.
 * @author Kevin Huynh
 * @version 1.0
 */
public class Driver {

    /**
     * Executes the main execution of the program.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Doctor doctorWho = new Doctor("Mildred", 10, 10);
        Patient[] patientList = {
            new Patient("Rachna", 6, 8),
            new Patient("Tejas", 500, 100),
            new Patient("Will", 10, 10),
            new Patient("Aanya", 17, 11),
            new Patient("Julia", 10, 7)
        };
        System.out.println(doctorWho);
        printArray(patientList);
        doctorWho.performTreatment(patientList);
        printArray(patientList);
    }

    /**
     * Prints out a String representation of a given Patient array
     * @param arr the Patient array to print out
     */
    private static void printArray(Patient[] arr) {
        if (arr == null) {
            return;
        }
        System.out.print("[");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        if (arr.length > 0) {
            System.out.print(arr[arr.length - 1]);
        }
        System.out.println("]");
    }
}