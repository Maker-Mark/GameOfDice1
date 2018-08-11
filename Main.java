import java.util.Scanner;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintStream ps = new PrintStream("output.txt");
        int     die1    = 0,
                die2    = 0,
                die1Mod = 0,
                die2Mod = 0,
                winCountMod   = 0,
                drawCountMod  = 0,
                loseCountMod  = 0,
                winCountNorm  = 0,
                drawCountNorm = 0,
                loseCountNorm = 0,
                winCountPair  = 0,
                drawCountPair = 0,
                loseCountPair = 0;
        boolean diceValid = false,
                specialCombo = false,
                notSpecialCombo = !specialCombo,
                win = false,
                draw = true,
                lose = false;

        ps.println("To exit the game enter two separate integers, \"0\" and \"0\".");
        do {
            ps.println("Type in two integers from 1 to 6:");
            die1 = sc.nextInt();
            die2 = sc.nextInt();
            diceValid = (valid(die1) && valid(die2));
            specialCombo = (die1 == 0 && die2 == 0);
            notSpecialCombo = !specialCombo;
            win  = (outcome(die1, die2) == 2);
            draw = (outcome(die1, die2) == 1);
            lose = (outcome(die1, die2) == 0);
            if (specialCombo) {
                ps.println("\n/===================================\\");
                ps.println("Special combination entered, exiting.");
                ps.println("\\===================================/\n");
            } else if (diceValid) {
                ps.println("\n/===================================\\");
                ps.println("Normal dice values are:");
                ps.printf("Die 1: %d %nDie 2: %d %n", die1, die2);
                if (win) {
                    ps.println("This normal roll is a winner.\n");
                    winCountNorm++;
                } else if (draw) {
                    ps.println("This normal roll is a draw.\n");
                    drawCountNorm++;
                } else if (lose) {
                    ps.println("This normal roll is a loser.\n");
                    loseCountNorm++;
                }
                // Calculate alternative dice values:
                die1Mod = die1 + 2;
                die2Mod = die2 + 3;
                if (die1Mod > 6) {
                    die1Mod -= 6;
                }
                if (die2Mod > 6) {
                    die2Mod -= 6;
                }
                if (win && (outcome(die1Mod, die2Mod) == 2)) {
                    winCountPair++;
                } else if (draw && (outcome(die1Mod, die2Mod) == 1)) {
                    drawCountPair++;
                } else if (lose && (outcome(die1Mod, die2Mod) == 0)) {
                    loseCountPair++;
                }
                // Call outcome on the two new dice values:
                win  = (outcome(die1Mod, die2Mod) == 2);
                draw = (outcome(die1Mod, die2Mod) == 1);
                lose = (outcome(die1Mod, die2Mod) == 0);
                ps.println("Modified dice values are now:");
                ps.printf("Die 1: %d %nDie 2: %d %n", die1Mod, die2Mod);
                if (win) {
                    ps.println("This modified roll is a winner.");
                    winCountMod++;
                } else if (draw) {
                    ps.println("This modified roll is a draw.");
                    drawCountMod++;
                } else if (lose) {
                    ps.println("This modified roll is a loser.");
                    loseCountMod++;
                }
                ps.println("\\===================================/\n");
            } else {
                ps.println("\n/===================================\\");
                ps.println("Wrong input entered, try again.");
                ps.println("\\===================================/\n");
            }
        } while (notSpecialCombo);
        ps.println("\n\n=======USER-INPUT DICE ROLLS=======");
        ps.printf("Total winning dice rolls: %d %n", winCountNorm);
        ps.printf("Total draw dice rolls: %d %n", drawCountNorm);
        ps.printf("Total losing dice rolls: %d %n", loseCountNorm);
        ps.println("===================================\n");
        ps.println("========MODIFIED DICE ROLLS========");
        ps.printf("Total winning dice rolls: %d %n", winCountMod);
        ps.printf("Total draw dice rolls: %d %n", drawCountMod);
        ps.printf("Total losing dice rolls: %d %n", loseCountMod);
        ps.println("===================================\n");
        ps.println("=========PAIRED DICE ROLLS=========");
        ps.printf("Total double winners: %d %n", winCountPair);
        ps.printf("Total double draws: %d %n", drawCountPair);
        ps.printf("Total double losers: %d %n", loseCountPair);
        ps.println("===================================");
    }

    public static boolean valid(int die) {
        if (die <= 6 && die >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public static int outcome(int die1, int die2) {
        int sum = die1 + die2;
        boolean win = (sum == 7 || sum == 5 || sum == 12);
        boolean lose = (sum == 2 || sum == 4 || sum == 11);
        boolean draw = (sum == 8 || sum == 9 || sum == 10 || sum == 3 || sum == 6);

        if (win) {
            return 2;
        } else if (draw) {
            return 1;
        } else if (lose) {
            return 0;
        } else {
            // On error
            return -1;
        }
    }
}
