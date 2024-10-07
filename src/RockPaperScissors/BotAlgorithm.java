package RockPaperScissors;

import java.util.Random;

public class BotAlgorithm {

    private final Random random = new Random();

    private final int randomNum =  random.nextInt(3) + 1;
    private boolean coinFlip;

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
    
    private int CounterLastPlayerMove(){
        return switch (Main.playerLastMove){
            case 1: yield 2;
            case 2: yield 3;
            case 3: yield 1;

            default:
                yield -1;
        };
    }

    private int CounterLastBotMove(){
        return switch(HardMode.botLastMove){
            case 1: yield 2;
            case 2: yield 3;
            case 3: yield 1;

            default:
                yield -1;
        };
    }

    private int RepeatLastMove(){
        return switch(HardMode.botLastMove){
            case 1: yield 1;
            case 2: yield 2;
            case 3: yield 3;
            default: yield -1;
        };
    }

    private int flipCoin(int firstCondition, int secondCondition) {
        coinFlip = random.nextBoolean();
        return coinFlip ? firstCondition : secondCondition;
    }

    /*ROUND 1
     -MR. RPS will select one of the three key numbers and stick with that choice until an IF is triggered
     -IF the player score = 2 && MR RPS score = 0, MR RPS will make the score equal
     -IF player score = 2 && MR RPS score = 1, MR RPS will counter the player, again making the score equal
     -IF player score = 2 && MR RPS score = 2, MR RPS will pick the counter of the LEAST played thing by the player OR
     the MOST played
    */
    public int Round1() {

        // If the player leads 2-0
        if (HardMode.botPoints == 0 && HardMode.playerPoints == 2) {
            HardMode.botPoints += 2;

            coinFlip = random.nextBoolean(); // true for most used, false for least used

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
            return flipCoin(CounterMostUsedMove(), CounterLeastUsedMove());
        }
        // If 2-1 Player lead
        if (HardMode.botPoints == 1 && HardMode.playerPoints == 2){
            return CounterPlayer();
        }
        // Default case: Random move
        else {
            return randomNum; // Random move if the score is not 2-0
        }
    }


    /*MID ROUNDS
    -MR RPS on his all other turns, will pick the counter of the LEAST/MOST played item by the player

    -IF MR RPS loses first turn (0-1), he will pick the counter of player's LAST move
    -IF MR RPS wins first turn (1-0), he will repeat move

    -IF (2-0) lead for MR RPS, he will pick the counter of his LAST move
    -IF (1-1), MR RPS will REPEAT move

    -IF (1-2), MR RPS will either COUNTER or REPEAT move
    -IF (2-2), MR RPS will counter LEAST used

    -IF (0-2) MR RPS 80% chance to counter 20% chance to REPEAT move
     */
    public int MidRounds() {
        int state = HardMode.botPoints * 10 + HardMode.playerPoints;

        return switch (state) {
            case 1 -> // Bot 0, Player 1
                    CounterLastPlayerMove();
            case 10 -> // Bot 1, Player 0
                    RepeatLastMove();
            case 20 -> // Bot 2, Player 0
                    CounterLastBotMove();
            case 11 -> // Bot 1, Player 1
                    RepeatLastMove();
            case 12 -> // Bot 1, Player 2
                    flipCoin(CounterPlayer(), RepeatLastMove());
            case 22 -> // Bot 2, Player 2
                    CounterLeastUsedMove();
            case 2 -> {
                int chance = random.nextInt(100);
                yield chance < 80 ? CounterPlayer() : RepeatLastMove(); // Bot 0, Player 2
            }
            default -> // Default case if no other conditions match
                    flipCoin(CounterMostUsedMove(), CounterLeastUsedMove());
        };
    }


}
