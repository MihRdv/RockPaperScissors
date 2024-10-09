package RockPaperScissors;

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
    private boolean lastRoundDialogue = true;

    private final BotAlgorithm Algorithm = new BotAlgorithm();

    private void SelectAlgorithm(){

        if (botRoundsWon == 0 && playerRoundsWon == 2) {
            if(playerPoints == 0 && botPoints == 0){
                System.out.println("Mr. RPS: ...");
                System.out.println("CONSOLE: GRANTING MR RPS ACCESS TO PLAYER MOVES");
            } // Revenge dialogue
            botMove = Algorithm.RevengeRound();
        } else if (playerRoundsWon >= 2) {
            if(lastRoundDialogue){
                System.out.println("Mr. RPS: You will NOT win.");
                System.out.println("CONSOLE: $*(!#^#*())(^@#!");
                lastRoundDialogue = false;
            } // Last round dialogue
            botMove = Algorithm.LastRound();
        } else if (botRoundsWon == 3 && playerRoundsWon == 0) {
            System.out.println("Mr. RPS: As expected, losing 3 - 0, and I thought humans were supposed to be better than AI");
            System.out.println("Mr. RPS: If that is truly the case, you are no more than a mere bug, here, you may win this round.");
            botMove = Algorithm.MockRound();
        } else if (totalRounds == 0) {
            botMove = Algorithm.Round1();
        } else {
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

    public void HardBot() {
        playerRoundsWon = 0;
        botRoundsWon = 0;

        System.out.println("Mr. RPS: I will be your opponent today, get ready.");
        System.out.println("Mr. RPS: Go ahead and make your move.");
        System.out.println();

        // Loop for rounds, stops when the player wins 5 rounds or Mr. RPS wins 3 rounds
        while (playerRoundsWon < 5 && botRoundsWon < 3) {

            startRound();

            // Loop for turns within the round, stops when someone scores 3 points
            boolean roundLoop = playerPoints < 3 && botPoints < 3;
            while (roundLoop) {
                if(botPoints == 0 && playerPoints == 2 && totalRounds == 0){
                    System.out.println();
                    System.out.println("Mr. RPS: I won't go down that easy the first round.");
                    System.out.println("CONSOLE: GRANTING MR RPS 2 POINTS");
                    System.out.println();
                }
                if(botPoints == 2 && playerPoints == 2 && totalRounds == 0){
                    System.out.println();
                    System.out.println("Mr. RPS?: Now we're equal. What shall my next move be, I wonder?");
                    System.out.println();
                }

                    playerMove = Main.PlayerSelection();

                //To choose the bot move
                SelectAlgorithm();

                System.out.println("Mr. RPS has picked " + getBotChoice(botMove) + "!");

                result = TurnResult(playerMove, botMove);

                switch (result) {
                    case 1 : { // Player wins the turn
                        playerPoints++;
                        // TODO: Add dialogue for bot losing the turn
                        break;
                    }
                    case 2 : {  // Bot wins the turn
                        botPoints++;
                        // TODO: Add dialogue for bot winning the turn
                        break;
                    }
                }
                System.out.printf("Current points: You - %d | Mr. RPS - %d%n",playerPoints,botPoints);
                if (changeRoundScore){
                    roundLoop = playerPoints < 5 && botPoints < 5;
                } else {
                    roundLoop = playerPoints < 3 && botPoints < 3;
                }
            }

            // Handle the round winner
            if (playerPoints == 3) {
                playerRoundsWon++;
                System.out.printf("Congratulations, you won the round!%nRound score: You: %d | Mr. RPS: %d%n", playerPoints, botPoints);
                // TODO: Add dialogue for bot losing the round
            } else if (botPoints == 3) {
                botRoundsWon++;
                System.out.printf("Mr. RPS won the round!%nRound score: You: %d | Mr. RPS: %d%n", playerPoints, botPoints);
                // TODO: Add dialogue for bot winning the round
            }

            // Display current standing after each round
            System.out.printf("%nCurrent standing: You: %d rounds | Mr. RPS: %d rounds%n", playerRoundsWon, botRoundsWon);
        }

        // Final game result
        if (playerRoundsWon >= 5) {
            System.out.printf("Congratulations, you won the game! Final rounds: You: %d | Mr. RPS: %d%n", playerRoundsWon, botRoundsWon);
            // TODO: Add dialogue for bot losing the game
        } else {
            System.out.printf("Mr. RPS won the game! Final rounds: You: %d | Mr. RPS: %d%n", playerRoundsWon, botRoundsWon);
            // TODO: Add dialogue for bot winning the game
        }
    }
}
