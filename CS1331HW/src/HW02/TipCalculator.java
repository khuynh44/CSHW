package HW02;
import java.util.Scanner;
public class TipCalculator {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Tip Calculator");
		System.out.println("Enter the prices of your items. Enter -1 to enter tip percentage. ");
		int count = 1;
		double total = 0; 
		double tip;
		System.out.println("Item 1: ");
		double input = scan.nextDouble();
		while(input != -1) { 
			total = total + input;
			count++;
			System.out.println("Item " + count + ": ");
			input = scan.nextDouble();
		}
		System.out.println("Enter the tip percentage: ");
		tip = scan.nextDouble()*total;
		System.out.format("Subtotal: $%.2f", total); 
		System.out.println();
		System.out.format("Tip: $%.2f", tip);
		System.out.println();
		System.out.format("Total: $%.2f", (total + tip));
	

}
}