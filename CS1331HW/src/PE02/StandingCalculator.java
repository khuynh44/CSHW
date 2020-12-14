package PE02;
import java.util.Scanner;
public class StandingCalculator {

	public static void main(String[] args) {
		int credits;
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Credit hours taken:");
		credits = myScanner.nextInt();
		if(credits>=0 && credits<=29) {
			System.out.println("Freshmen");
		} else if(credits>=30 && credits<=59) {
			System.out.println("Sophmores");
		}else if(credits>=60 && credits<=89) {
			System.out.println("Juniors");
		}else if(credits>=90) {
			System.out.println("Seniors");
		}else  
			System.out.println("Invalid");
	}

}
