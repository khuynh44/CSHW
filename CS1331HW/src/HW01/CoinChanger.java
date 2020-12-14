package HW01;
import java.util.Scanner;
public class CoinChanger {

	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	System.out.println("Pennies: "); 
	System.out.println("Nickels: ");
	System.out.println("Dimes: "); 
	System.out.println("Quarters: ");
	int p = input.nextInt();
	int n = input.nextInt();
	int d = input.nextInt(); 
	int q = input.nextInt(); 
	int total = q*25+d*10+n*5+p;
	int dollars = total/100;
	int cents = total%100;
	System.out.println("Total is " + dollars + " dollars and "+ cents + " cents. "); 
	
	}

}
