package RockPaperScissors;

import java.util.Scanner;

public class Main {

    private static final String border =
 "_________________________________________________________________________________________________________________________";
    public static Scanner scanner = new Scanner(System.in);

    private static boolean difficulty;
    public static int rockUses = 0;
    public static int paperUses = 0;
    public static int scissorsUses = 0;
    public static int playerLastMove;

    public static int PlayerSelection() {
        int keyNumber = 0;

        boolean loop = true;
        while (loop) {
            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "rock", "1":
                    keyNumber = 1;
                    System.out.println("You've selected Rock!");
                    rockUses++;
                    playerLastMove = 1;
                    loop = false;
                    break;
                case "paper", "2":
                    keyNumber = 2;
                    System.out.println("You've selected Paper!");
                    paperUses++;
                    playerLastMove = 2;
                    loop = false;
                    break;
                case "scissors", "3":
                    keyNumber = 3;
                    System.out.println("You've selected Scissors!");
                    scissorsUses++;
                    playerLastMove = 3;
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid input, try again.");
                    break;
            }
        }
        return keyNumber;
    }

    public static void PrintPaddedMessage(String message) {
        int totalPadding = border.length() - message.length() - 2; // -2 for the side borders "|"
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding; // Ensures symmetry for odd lengths

        System.out.printf("|%" + leftPadding + "s%s%" + rightPadding + "s|%n", "", message, "");
    }

    private static void DifficultyRequest() {

        System.out.println(border);
        PrintPaddedMessage("Please select your difficulty");
        PrintPaddedMessage("Hard      OR      Normal");
        System.out.println(border);

        while (true) { // Loop to handle invalid inputs
            String difficultyChoice = scanner.nextLine();

            if (difficultyChoice.equalsIgnoreCase("hard")) {
                System.out.println(border);
                PrintPaddedMessage("You've chosen Hard");
                PrintPaddedMessage("Game ends when you win 5 times or when your opponent wins 3 times! Good luck!");
                System.out.println(border);
                difficulty = true;  // Return true for Hard
                break;
            } else if (difficultyChoice.equalsIgnoreCase("normal")) {
                System.out.println(border);
                PrintPaddedMessage("You've chosen Normal");
                PrintPaddedMessage("First to 2 wins!");
                System.out.println(border);
                difficulty = false;  // Return false for Normal
                break;
            } else {
                System.out.println("Invalid choice, please choose Hard or Normal");
            }
        }
    }

    public static void main(String[] args) {
        NormalMode normalMode = new NormalMode();
        HardMode hardMode = new HardMode();

        DifficultyRequest();

        PrintPaddedMessage("When you are ready to make your choice simply type it in, or type in the number");
        PrintPaddedMessage("|Rock - 1 | Paper - 2 | Scissors - 3|");
        System.out.println(border);

        if (difficulty) {
            hardMode.HardBot(); // 3 Total rounds - First to 2 wins
        } else {
            normalMode.NormalBot(); // 9 Total rounds - First to 5 wins
        }
    }
}
