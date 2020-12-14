package HW04;

import java.util.Scanner;
import java.util.Random;
public class TicTacToe {


    public static void main(String[] args) { 
        Scanner scan = new Scanner(System.in);
        Board layout = new Board();
        int num = getNumberPlayers(scan);
        if (num == 1) {
            onePlayer(scan, layout);
        } else if (num == 2) {
            twoPlayer(scan, layout);
        }
        switch (layout.getGameState()) {
        case PLAYER1_WIN:
        	System.out.println("Player 1 wins!");
        	break;
        case PLAYER2_WIN:
            System.out.println("Player 2 wins!");
            break;
        default:
        	System.out.println("Its a Tie!");
        }
    }
    public static void onePlayer(Scanner scan, Board layout) {
        Random rand = new Random();
        Location loc = new Location(rand.nextInt(3), rand.nextInt(3));
        System.out.println(layout);
    	while (layout.getGameState() == GameState.ONGOING) {
            if (layout.placeLetter(getInput("1", scan), 'X') && layout.getGameState() == GameState.ONGOING) {
                System.out.println(layout);
                System.out.println("The Computer is making a move......");
                while (layout.placeLetter(loc, 'O') == false) {
                    loc = new Location(rand.nextInt(3), rand.nextInt(3));
                }
                System.out.println(layout);
                } else {
                    System.out.println(layout);
                }
        }
    }
    public static void twoPlayer(Scanner scan, Board layout) {
    	int turn = 1;
    	System.out.println(layout);
        while (layout.getGameState() == GameState.ONGOING) {
            if (turn == 1 && layout.placeLetter(getInput("1", scan), 'X')) {
                System.out.println(layout);
                turn++;
            } else if (turn == 2 && layout.placeLetter(getInput("2", scan), 'O')) {
                System.out.println(layout);
                turn--;
            }
        }
    }
    

    /********************************************************
     *                                                      *
     *      DO NOT MODIFY CODE BELOW THIS BOX               *
     *                                                      *
     ********************************************************/

   /**
     * Gets number of players from command line
     * @param sc        for reading from command-line
     * @return number of players (always 1 or 2)
     */
    private static int getNumberPlayers(Scanner sc) {
        boolean repeatPrompt = true;
        int numPlayers = 0;
        while (repeatPrompt) {
            System.out.print("How many players (1 or 2)? ");
            String input = sc.next();
            try {
                numPlayers = Integer.parseInt(input);
                if (numPlayers == 1 || numPlayers == 2) {
                    repeatPrompt = false;
                } else {
                    System.out.println("Enter 1 or 2 players.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please only enter a number.");
            }
        }
        return numPlayers;
    }

   /**
     * Handles scanner calls and user input
     * @param player    whose turn it is
     * @param sc        for reading from command-line
     * @return int[] holding row, column in that order
     */
    private static Location getInput(String player, Scanner sc) {
        boolean repeatPrompt = true;
        int row = -1;
        int col = -1;
        while (repeatPrompt) {
            System.out.print("Enter desired square for " + player + ": ");
            String input = sc.next();
            input = input.trim();
            String[] splitInput = input.split(",");
            try {
                row = Integer.parseInt(splitInput[0]);
                col = Integer.parseInt(splitInput[1]);
                repeatPrompt = false;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Please follow the format 'row,col'; for ex '1,2'");
            }
        }
        Location loc = new Location(row, col);
        return loc;
    }

}


