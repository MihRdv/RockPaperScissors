package RockPaperScissors;

import java.util.Random;

public class BotAlgorithm {

    private final Random random = new Random();

    int randomNum =  random.nextInt(3) + 1;

    /*ROUND 1
     -MR. RPS will select one of the three key numbers and stick with that choice until an IF is triggered
     -IF the player score = 2 && MR RPS score = 0, MR RPS will make the score equal
     -IF player score = 2 && MR RPS score = 1, MR RPS will counter the player, again making the score equal
     -IF player score = 2 && MR RPS score = 2, MR RPS will pick the counter of the LEAST played thing by the player OR
     the MOST played
    */

    private int CounterPlayer() {
        return switch (HardMode.playerMove) {
            case 1 -> 2; // If the player's last move was Rock, counter with Paper
            case 2 -> 3; // If the player's last move was Paper, counter with Scissors
            case 3 -> 1; // If the player's last move was Scissors, counter with Rock
            default -> -1;
        };
    }

    private int CounterMostUsedMove() {
        // Find the most used move and counter it
        if (Main.rockUses >= Main.paperUses && Main.rockUses >= Main.scissorsUses) {
            return 2; // Counter rock with paper
        } else if (Main.paperUses >= Main.rockUses && Main.paperUses >= Main.scissorsUses) {
            return 3; // Counter paper with scissors
        } else {
            return 1; // Counter scissors with rock
        }
    }

    private int CounterLeastUsedMove() {
        // Find the least used move and counter it
        if (Main.rockUses <= Main.paperUses && Main.rockUses <= Main.scissorsUses) {
            return 2; // Counter rock with paper
        } else if (Main.paperUses <= Main.rockUses && Main.paperUses <= Main.scissorsUses) {
            return 3; // Counter paper with scissors
        } else {
            return 1; // Counter scissors with rock
        }
    }

    public int Round1() {

        // If the player leads 2-0
        if (HardMode.botPoints == 0 && HardMode.playerPoints == 2) {
            HardMode.botPoints += 2;

            boolean coinFlip = random.nextBoolean(); // true for most used, false for least used

            if (coinFlip) {
                // Pick counter of the most picked move by the player
                return CounterMostUsedMove();
            } else {
                // Pick counter of the least picked move by the player
                return CounterLeastUsedMove();
            }
        }
        // If 2 - 2
        if (HardMode.botPoints == 2 && HardMode.playerPoints == 2){
            boolean coinFlip = random.nextBoolean(); // true for most used, false for least used

            if (coinFlip) {
                // Pick counter of the most picked move by the player
                return CounterMostUsedMove();
            } else {
                // Pick counter of the least picked move by the player
                return CounterLeastUsedMove();
            }
        }
        if (HardMode.botPoints == 1 && HardMode.playerPoints == 2){
            return CounterPlayer();
        }
        // Default case: Random move
        else {
            return randomNum; // Random move if the score is not 2-0
        }
    }



}
