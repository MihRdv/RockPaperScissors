package RockPaperScissors;

import java.util.Random;

public class HardMode {

    public static int playerPoints;
    public static int botPoints;
    public static int playerRoundsWon;
    public static int botRoundsWon;
    public static int playerMove;
    public static int botMove;
    public static int botLastMove;
    public static int result;
    public static int totalRounds;
    public static boolean changeRoundScore = false;
    private boolean lastRoundOpeningDialogue = true;
    private boolean isLastRound = false;
    private boolean isMock = false;
    private boolean isRevenge = false;


    private final BotAlgorithm Algorithm = new BotAlgorithm();
    private final HardDialoguePicker dialogue = new HardDialoguePicker();

    private void SelectAlgorithm() {
        if (botRoundsWon == 0 && playerRoundsWon == 2) {
            if (playerPoints == 0 && botPoints == 0) {
                System.out.println("Mr. RPS: ...");
                System.out.println("CONSOLE: GRANTING MR RPS ACCESS TO PLAYER MOVES");
            } // Revenge dialogue
            isRevenge = true;
            isMock = false;
            isLastRound = false;
            botMove = Algorithm.RevengeRound();
        } else if (playerRoundsWon >= 3) {
            if (lastRoundOpeningDialogue) {
                System.out.println("Mr. RPS: You will NOT win.");
                System.out.println("CONSOLE: $*(!#^#*())(^@#!");
                lastRoundOpeningDialogue = false;
            } // Last round dialogue
            isRevenge = false;
            isMock = false;
            isLastRound = true;
            botMove = Algorithm.LastRound();
        } else if (botRoundsWon == 2 && playerRoundsWon == 0) {
            isRevenge = false;
            isMock = true;
            isLastRound = false;
            System.out.println("Mr. RPS: As expected, losing 2 - 0, and I thought humans were supposed to be better than AI");
            System.out.println("Mr. RPS: If that is truly the case, you are no more than a mere bug, here, you may win this round.");
            botMove = Algorithm.MockRound();
        } else if (totalRounds == 0) {
            botMove = Algorithm.Round1();
        } else {
            isRevenge = false;
            isMock = false;
            isLastRound = false;
            botMove = Algorithm.MidRounds();
        }
    }

    public String getBotChoice(int botMove) {
        return switch (botMove) {
            case 1 -> {
                botLastMove = 1;
                yield "Rock";
            }
            case 2 -> {
                botLastMove = 2;
                yield "Paper";
            }
            case 3 -> {
                botLastMove = 3;
                yield "Scissors";
            }
            default -> "Unknown";
        };
    }

    private int TurnResult(int playerMove, int botMove) {
        if (playerMove == botMove) {
            System.out.println("It's a tie!");
            return 0; // Tie
        } else if ((playerMove == 1 && botMove == 3) ||  // Player wins: Rock beats Scissors
                (playerMove == 2 && botMove == 1) ||  // Player wins: Paper beats Rock
                (playerMove == 3 && botMove == 2)) {  // Player wins: Scissors beat Paper
            System.out.println("Player wins this turn!");
            return 1; // Player wins
        } else {
            System.out.println("Mr. RPS wins this turn!");
            return 2; // Bot wins
        }
    }

    private void startRound() {
        playerPoints = 0;
        botPoints = 0;
        totalRounds = playerRoundsWon + botRoundsWon;
        System.out.printf("Round %d begins!%n", totalRounds + 1);
    }

    private void turnDialogue(String state) {
        Random random = new Random();
        switch (state) {
            case "win": {
                if (isRevenge) {
                    System.out.printf("%nMr.RPS: %s%n", HardDialoguePicker.revengeTurn.get(random.nextInt(3) + 1));
                } else if (isLastRound) {
                    System.out.printf("%nMr.RPS: %s%n", dialogue.SelectLastRoundTurnWinDialogue());
                } else {
                    System.out.printf("%nMr.RPS: %s%n", dialogue.SelectTurnWinDialogue());
                }
                break;
            }
            case "loss": {
                if (isLastRound) {
                    System.out.printf("%nMr.RPS: %s%n", dialogue.SelectLastRoundTurnLossDialogue());
                } else if (isMock) {
                    System.out.printf("%nMr.RPS: %s%n", dialogue.SelectMockDialogue());
                } else {
                    System.out.printf("%nMr.RPS: %s%n", dialogue.SelectTurnLossDialogue());
                }
                break;
            }
        }
    }

    private void roundDialogue(String state) {
            switch (state) {
                case "win":{
                    if(isLastRound){
                        System.out.printf("%nMr. RPS: %s%n",dialogue.LastRoundWinDialogue());
                    } else {
                        System.out.printf("%nMr. RPS: %s%n",dialogue.RoundWinDialogue());
                    }
                    break;
                }
                case "loss":{
                    if(isLastRound){
                        System.out.printf("%nMr. RPS: %s%n",dialogue.LastRoundLossDialogue());
                    } else {
                        System.out.printf("%nMr. RPS: %s%n",dialogue.RoundLossDialogue());
                    }
                    break;
                }
            }
    }

    public void HardBot() {
        playerRoundsWon = 0;
        botRoundsWon = 0;

        System.out.println("Mr. RPS: I will be your opponent today, get ready.");
        System.out.println("Mr. RPS: Go ahead and make your move.");
        System.out.println();

        // Loop for rounds, stops when the player wins 5 rounds or Mr. RPS wins 3 rounds
        // Loop for rounds, stops when the player wins 5 rounds or Mr. RPS wins 3 rounds
        while (playerRoundsWon < 5 && botRoundsWon < 3) {
            startRound();

            // Update the roundLoop condition based on the last round
            boolean roundLoop = isLastRound ? playerPoints < 5 && botPoints < 5 : playerPoints < 3 && botPoints < 3;
            while (roundLoop) {
                // Check for specific conditions here...

                playerMove = Main.PlayerSelection();

                // To choose the bot move
                SelectAlgorithm();

                System.out.println("Mr. RPS has picked " + getBotChoice(botMove) + "!");

                result = TurnResult(playerMove, botMove);

                switch (result) {
                    case 1: { // Player wins the turn
                        playerPoints++;
                        turnDialogue("loss");
                        break;
                    }
                    case 2: {  // Bot wins the turn
                        botPoints++;
                        turnDialogue("win");
                        break;
                    }
                }
                System.out.printf("Current points: You - %d | Mr. RPS - %d%n", playerPoints, botPoints);

                // Update the roundLoop condition based on whether it's the last round
                roundLoop = isLastRound ? playerPoints < 5 && botPoints < 5 : playerPoints < 3 && botPoints < 3;
            }

            // Handle the round winner
            if (playerPoints == 3) {
                playerRoundsWon++;
                System.out.printf("Congratulations, you won the round!%nRound score: You: %d | Mr. RPS: %d%n", playerPoints, botPoints);
                roundDialogue("loss");
            } else if (botPoints == 3) {
                botRoundsWon++;
                System.out.printf("Mr. RPS won the round!%nRound score: You: %d | Mr. RPS: %d%n", playerPoints, botPoints);
                roundDialogue("win");
            }

            // Display current standing after each round
            System.out.printf("%nCurrent standing: You: %d rounds | Mr. RPS: %d rounds%n", playerRoundsWon, botRoundsWon);
        }
    }
}
