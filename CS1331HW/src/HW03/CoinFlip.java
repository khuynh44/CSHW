package HW03;

import java.util.Random;
import java.util.Scanner;

public class CoinFlip {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Random myRandom = new Random();
        boolean answer = true;
        while (answer) {
            System.out.println("Ready to run a coin flip simulation. Enter the number of sequences: ");
            int total = 0;
            int max = 0;
            int seq = scan.nextInt();
            System.out.println("How many heads should each sequence have?");
            int heads = scan.nextInt();
            System.out.println("How many tails should each sequence have?");
            int tails = scan.nextInt();
            for (int i = 1; i <= seq; i++) {
                int headcount = 0;
                int tailcount = 0;
                System.out.print(i + " - ");
                while (headcount < heads || tailcount < tails) {
                    if (myRandom.nextInt(2) == 0) {
                        headcount++;
                        System.out.print("H");
                    } else {
                        tailcount++;
                        System.out.print("T");
                    }
                }
                System.out.println();
                total = total + headcount + tailcount;
                if ((headcount + tailcount) > max) {
                    max = headcount + tailcount;
                }
            }
            System.out.printf("The average number of flips was %.2f and the maximum was "
                + max, (float)total / seq);
            System.out.println("Would you like to run another simulation? (y/n): ");
            String input = scan.nextLine();
            while (input.equals("y") == false) {
                if (input.equals("n")) {
                    answer = false;
                    input = "y";
                } else {
                    System.out.println("Would you like to run another simulation? (y/n)");
                    input = scan.nextLine();
                }
            }
        }
    }
}
