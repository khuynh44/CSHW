//I worked on the homework assignment alone, using only course materials.

package HW05;

/**
 * Program that sets up and creates all of the cheese and connoisseur objects.
 * @author Kevin Huynh
 *
 */
public class CheeseDriver {

	public static void main(String[] args) {
        Cheese cheese1 = new Cheese(3, "Parmesian");
        Cheese cheese2 = new Cheese(2, "Mozzarella");
        Cheese cheese3 = new Cheese(4, "American", 1);
        Cheese cheese4 = new Cheese(1, "Gouda", 2);
        CheeseConnoisseur conn1 = new CheeseConnoisseur("Kevin", 10, cheese1);
        CheeseConnoisseur conn2 = new CheeseConnoisseur("John", 20);
        conn2.setCheese(cheese3);
        conn1.trade(cheese2);
        conn2.trade(cheese1);
        conn1.trade(cheese3);
        conn2.trade(cheese4);
        conn1.trade(conn2);
        System.out.println(conn1.getCheese());
        System.out.println(conn2.getCheese());
        System.out.println(cheese1);
	}

}
