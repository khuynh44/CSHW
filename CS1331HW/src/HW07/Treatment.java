package HW07;

/**
 * The layout for which Treatment is executed for each patient.
 * @author Kevin Huynh
 * @version 1.0
 */
public abstract class Treatment implements HealAbility {
    /** the amount of heal */
    private int heal;

    @Override
    public int getHealAmount() {
        return heal;
    }

    /**
     * Constructor taking the amount of heal value.
     * @param h the amount of heal value.
     */
    public Treatment(int h) {
        heal = h;
    }

    @Override
    public String toString() {
        return "Treatment with " + heal + " heal";
    }
}
