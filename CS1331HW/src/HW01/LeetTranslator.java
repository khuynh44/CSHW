package HW01;
import java.util.Scanner; 
public class LeetTranslator {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter string to translate: ");
		String input = scan.nextLine();
		input = input.toLowerCase();
		input = input.replace('a', '@');
		input = input.replace('e', '3');
		input = input.replace('i', '1');
		input = input.replace('s', '$');
		input = input.replace('o', '0');
		System.out.println(input);
	}

}
