package RockPaperScissors;

import java.util.Scanner;

public class Main {

    public static int PlayerSelection() {
        int keyNumber = 0;
        Scanner scanner = new Scanner(System.in);

        boolean loop = true;
        while (loop) {
            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "rock", "1":
                    keyNumber = 1;
                    System.out.println("You've selected Rock!");
                    loop = false;
                    break;
                case "paper", "2":
                    keyNumber = 2;
                    System.out.println("You've selected Paper!");
                    loop = false;
                    break;
                case "scissors", "3":
                    keyNumber = 3;
                    System.out.println("You've selected Scissors!");
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid input, try again.");
                    break;
            }
        }
        return keyNumber;
    }

    private static void PrintPaddedMessage(String border, String message) {
        int totalPadding = border.length() - message.length() - 2; // -2 for the side borders "|"
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding; // Ensures symmetry for odd lengths

        System.out.printf("|%" + leftPadding + "s%s%" + rightPadding + "s|%n", "", message, "");
    }

    private static boolean DifficultyRequest(String border) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(border);
        PrintPaddedMessage(border, "Please select your difficulty");
        PrintPaddedMessage(border, "Hard      OR      Normal");
        System.out.println(border);

        while (true) { // Loop to handle invalid inputs
            String difficultyChoice = scanner.nextLine();

            if (difficultyChoice.equalsIgnoreCase("hard")) {
                System.out.println(border);
                PrintPaddedMessage(border, "You've chosen Hard");
                PrintPaddedMessage(border, "Your opponent's choices will not be random!");
                System.out.println(border);
                return true;  // Return true for Hard
            } else if (difficultyChoice.equalsIgnoreCase("normal")) {
                System.out.println(border);
                PrintPaddedMessage(border, "You've chosen Normal");
                PrintPaddedMessage(border, "Your opponent's choices will be random!");
                System.out.println(border);
                return false;  // Return false for Normal
            } else {
                System.out.println("Invalid choice, please choose Hard or Normal");
            }
        }
    }

    public static void main(String[] args) {
        NormalMode normalMode = new NormalMode();
        String border = "_________________________________________________________________________________________________________________________";

        boolean difficulty = DifficultyRequest(border); // Hard = True | Normal = False

        PrintPaddedMessage(border, "When you are ready to make your choice simply type it in, or type in the number");
        PrintPaddedMessage(border, "|Rock - 1 | Paper - 2 | Scissors - 3|");
        System.out.println(border);

        if (!difficulty) {
            normalMode.NormalBot();
        }
    }
}
