package RockPaperScissors;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int PlayerSelection(String input) {
        int keyNumber = 0;

        boolean loop = true;
        while (loop) { // Switch case to handle all different selections
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

    private static String getBotChoice(int choice) {
        return switch (choice) {
            case 1 -> "Rock";
            case 2 -> "Paper";
            case 3 -> "Scissors";
            default -> "Unknown";
        };
    }

    private static int TurnResult(int player, int bot) {

        if (player == bot) {
            System.out.println("It's a tie!");
            return 0;
        } else if ((player == 1 && bot == 3) ||
                (player == 2 && bot == 1) ||
                (player == 3 && bot == 2)) {
            System.out.println("Player wins!");
            return 1;
        } else {
            System.out.println("Mr. RPS wins!");
            return 2;
        }
    }

    private static void DialogueRPS(String state) {
        //TODO
    }

    private static void NormalBot() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int playerPoints = 0;
        int botPoints = 0;

        System.out.println();
        System.out.println("-Mr. RPS: Hello, I will be your opponent today :)");
        System.out.println("-Mr. RPS: Go ahead and make your move :D");
        System.out.println();

        while (playerPoints < 6 || botPoints < 6) {
            int playerMove = PlayerSelection(scanner.nextLine());
            int botMove = random.nextInt(3) + 1;

            System.out.println("Mr. RPS has picked " + getBotChoice(botMove) + "!");

            int moveResult = TurnResult(playerMove, botMove);

            switch (moveResult) {
                case 1:
                    playerPoints++;
                    DialogueRPS("loss");
                    break;
                case 2:
                    botPoints++;
                    DialogueRPS("victory");
                    break;
            }
        }
    }

    public static void main(String[] args) {

        String border = "_________________________________________________________________________________________________________________________";

        boolean difficulty = DifficultyRequest(border); //Hard = True | Normal = False

        PrintPaddedMessage(border, "When you are ready to make your choice simply type it in, or type in the number");
        PrintPaddedMessage(border, "|Rock - 1 | Paper - 2 | Scissors - 3|");
        System.out.println(border);

        if (!difficulty) {
            NormalBot();
        }
    }
}
