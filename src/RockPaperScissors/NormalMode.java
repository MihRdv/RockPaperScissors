package RockPaperScissors;

import java.util.Random;
import java.util.Scanner;

public class NormalMode {

    public String getBotChoice(int choice) {
        return switch (choice) {
            case 1 -> "Rock";
            case 2 -> "Paper";
            case 3 -> "Scissors";
            default -> "Unknown";
        };
    }

    public int TurnResult(int player, int bot) {

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

    public void DialogueRPS(String state) {
        // TODO: Add dialogue interaction based on the game state.
    }

    public void NormalBot() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int playerPoints = 0;
        int botPoints = 0;

        System.out.println();
        System.out.println("-Mr. RPS: Hello, I will be your opponent today :)");
        System.out.println("-Mr. RPS: Go ahead and make your move :D");
        System.out.println();

        while (playerPoints < 6 && botPoints < 6) {  // Loop until either player or bot gets 6 points
            int playerMove = Main.PlayerSelection();
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
}
