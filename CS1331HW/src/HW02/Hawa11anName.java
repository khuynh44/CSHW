package HW02;
import java.util.Scanner;
public class Hawa11anName {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Hawa11! Let's check if your name is Hawa11an enough."); 
		String input;
		String input1;
		boolean name = true;
		while(name) {
			System.out.println("Enter your name: ");
			input = scan.nextLine();
			input1 = input.toLowerCase();
		for(int i = 0; i<input1.length(); i++) { 
			switch(input1.charAt(i)) { 
			case 'a':
				break;
			case 'e':
				break; 
			case 'i':
				break;
			case 'o':
				break;
			case 'u':
				break;
			case 'k':
				break;
			case 'l':
				break;
			case 'h':
				break;
			case 'm':
				break;
			case 'n':
				break;
			default: 
				input1 = input1.substring(0,i)+input1.substring(i+1);
				name = false;
			}
		}
		if(name == false) { 
			System.out.println("Sorry " + input + ", you aren't Hawa11an enough to come!");
			System.out.println("Let's make your name... " + input1 + "!");
		} else {
			System.out.println("Aloha, " + input);
		}
		System.out.println("Would you like to try again? (y/n)");
		input = scan.nextLine();
		if(input.equals("y"))
			name = true;
		else 
			name=false;
		}
	}

}
