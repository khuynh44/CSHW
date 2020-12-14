package HW06;

/**
 * Main class the drives the adventure classes.
 * @author Kevin
 * @version 1.0
 */
public class DungeonDriver {

    /**
     * Main method that drives everything.
     * @param args takes in the argument.
     */
    public static void main(String[] args) {
        Knight adven1 = new Knight("Galahad", 7, 6, true);
        Knight adven2 = new Knight("Lancelot", 30, 5, false);
        Archer adven3 = new Archer("Archie", 10, 2);
        adven3.attack(adven2);
        System.out.println(adven3);
        System.out.println(adven2);
        System.out.println();
        adven1.attack(adven3);
        System.out.println(adven1);
        System.out.println(adven3);
        System.out.println();
        adven3.attack(adven1);
        System.out.println(adven3);
        System.out.println(adven1);
        System.out.println();
        adven2.attack(adven1);
        System.out.println(adven2);
        System.out.println(adven1);
        System.out.println();
        adven2.challenge(adven1);
        System.out.println(adven1);
        System.out.println(adven2);
    }
}
