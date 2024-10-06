package RockPaperScissors;

import java.util.Random;
import java.util.Scanner;

public class NormalMode {

    private String getBotChoice(int choice) {
        return switch (choice) {
            case 1 -> "Rock";
            case 2 -> "Paper";
            case 3 -> "Scissors";
            default -> "Unknown";
        };
    }

    private int TurnResult(int player, int bot) {

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

    public void NormalBot() {
        new Scanner(System.in);
        Random random = new Random();
        DialoguePicker dialogue = new DialoguePicker();

        int totalRounds = 0;
        int playerRoundsWon = 0;
        int botRoundsWon = 0;

        System.out.println();
        System.out.println("-Mr. RPS: Hello, I will be your opponent today :)");
        System.out.println("-Mr. RPS: Go ahead and make your move :D");
        System.out.println();

        while (totalRounds < 3 && playerRoundsWon < 2 && botRoundsWon < 2) {  // Loop for rounds, end if a player wins 2 rounds
            int playerPoints = 0;
            int botPoints = 0;

            System.out.printf("Round %d begins!%n", totalRounds + 1);
            totalRounds += 1;

            while (playerPoints < 3 && botPoints < 3) {  // Loop for turns in a single round
                int playerMove = Main.PlayerSelection();
                int botMove = random.nextInt(3) + 1;

                System.out.println("Mr. RPS has picked " + getBotChoice(botMove) + "!");

                int moveResult = TurnResult(playerMove, botMove);

                switch (moveResult) {
                    case 1:
                        playerPoints++;
                        dialogue.DialogueRPS("loss");
                        break;
                    case 2:
                        botPoints++;
                        dialogue.DialogueRPS("victory");
                        break;
                }
            }

            // After a round is complete
            if (playerPoints == 3) {
                playerRoundsWon++;
                System.out.printf("Congratulations, you won the round!%nRound score: You: %d | Mr. RPS: %d%n", playerPoints, botPoints);
                dialogue.DialogueRPS("roundVictory");
            } else if (botPoints == 3) {
                botRoundsWon++;
                System.out.printf("Mr. RPS won the round!%nRound score: You: %d | Mr. RPS: %d%n", playerPoints, botPoints);
                dialogue.DialogueRPS("roundVictory");
            }

            // Announce current standing
            System.out.printf("%nCurrent standing: You: %d rounds | Mr. RPS: %d rounds%n", playerRoundsWon, botRoundsWon);
        }

        // Final result after all rounds or if someone wins 2 rounds
        if (playerRoundsWon > botRoundsWon) {
            System.out.printf("Congratulations, you won the game! Final rounds: You: %d | Mr. RPS: %d%n", playerRoundsWon, botRoundsWon);
            dialogue.DialogueRPS("gameLoss");
        } else {
            System.out.printf("Mr. RPS won the game! Final rounds: You: %d | Mr. RPS: %d%n", playerRoundsWon, botRoundsWon);
            dialogue.DialogueRPS("gameVictory");
        }
    }

}