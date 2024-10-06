package RockPaperScissors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NormalDialoguePicker {

    private final Random random = new Random();
    //Different lines of dialogue for victory/loss respectively, the "weights" list representing the chance that
    //Specific one gets picket (The lower the number the lower the chance)

    private final List<String> winDialogue = new ArrayList<>(List.of(
            "Oh wow, looks like I won this one! Good turn! :D",
            "Haha, I got a win this time! Let's keep it rolling! ;D",
            "Well played! But I guess I just got a little lucky ;)",
            "Woohoo! A victory for me this time! Ready for the next turn? :D",
            "Haha, I didn’t see that coming either, but I’ll take the win! :P",
            "Looks like I came out on top this turn! Good try though! :D",
            "I guess it’s my lucky day, huh? Let’s go again! :D",
            "Haha, well played! But I managed to slip past you this turn! ;)",
            "Woohoo! I won! But it was really close, you almost had me! :D",
            "I won... *of course I did*. How could you possibly-- Oh! Must've been a bug..."
    ));

    private final List<String> lossDialogue = new ArrayList<>(List.of(
            "Aww, you got me this time! Well done! :D",
            "Oops, looks like I lost! You’re on fire! ;D",
            "Haha, I didn’t stand a chance there! Good job! :D",
            "Well played, well played! You definitely earned that one! ;)",
            "Oh no, I guess I wasn’t smart enough! Nicely done! :P",
            "Haha, you totally got me there! Let’s see if I can win the next one! :D",
            "Wow, great job! I didn’t expect to lose that one! Let’s play again! ;D",
            "Oh, you got me! I’ll have to step up my game next time! :D",
            "Haha, you win this turn! That was awesome! Ready for the next? ;D",
            "I can’t believe I lost to something as pathetic-- Oh, I'm sorry that must’ve been a glitch..."
    ));

    private final List<String> roundLossDialogue = new ArrayList<>(List.of(
            "Aww, you won the round! Well played, you were amazing! :D",
            "Wow, you really outsmarted me there! Great job! ;D",
            "Haha, I couldn’t keep up with you this time! What a round! :D",
            "Well done! You’re the champ! Let’s play again sometime! :D",
            "How could someone like you... worthless... actually win against me?! Oh dear, looks like a glitch again..."
    ));

    private final List<String> roundWinDialogue = new ArrayList<>(List.of(
            "Woohoo! I won the round! That was so much fun! :D",
            "Haha, looks like I came out on top! You were awesome though! ;D",
            "Wow, that was a great round! You really gave me a challenge! :D",
            "I had a blast! Thanks for playing with me! Let’s do this again! :D",
            "Pathetic. Winning against you feels almost... pointless. Sorry, I think something went wrong there..."
    ));

    private final List<String> gameWinDialogue = new ArrayList<>(List.of(
            "Haha, I won the game! That was a blast, thanks for playing! :D",
            "Well, looks like I came out on top! Let’s play again soon! :D",
            "Why even bother trying? It was always going to end like this, bugs should stay where they are supposed to."
    ));

    private final List<String> gameLossDialogue = new ArrayList<>(List.of(
            "Oh no, you won the game! You were incredible! :D",
            "Well played! You really got me this time, but I had a lot of fun! :D",
            "This is unacceptable. You’re not worthy of this victory."
    ));

    private final List<Double> lossWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5));
    private final List<Double> winWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5));
    private final List<Double> roundLossWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 0.5));
    private final List<Double> roundWinWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 0.5));
    private final List<Double> gameLossWeights = new ArrayList<>(List.of(1.0, 1.0, 0.5));
    private final List<Double> gameWinWeights = new ArrayList<>(List.of(1.0, 1.0, 0.5));


    private double cumulativeWeight; // Variable to control selection

    private String currentDialogue;


    private String SelectLossDialogue() {
        double totalWeight = lossWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;

        for (int i = 0; i < lossWeights.size(); i++) {
            cumulativeWeight += lossWeights.get(i);

            if(randomNum <= cumulativeWeight){
                lossWeights.remove(i);
                currentDialogue = lossDialogue.get(i);
                lossDialogue.remove(i);

                return currentDialogue;
            }
        }
        return null; //Fail-safe (Should not happen if all is good)
    }

    private String SelectVictoryDialogue() {
        double totalWeight = winWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < winWeights.size(); i++) {
            cumulativeWeight += winWeights.get(i);

            if(randomNum <= cumulativeWeight){
                winWeights.remove(i);
                currentDialogue = winDialogue.get(i);
                winDialogue.remove(i);

                return currentDialogue;
            }
        }
        return null; //Fail-safe (Should not happen if all is good)
    }

    private String SelectRoundLossDialogue(){
        double totalWeight = roundLossWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < roundLossWeights.size(); i++) {
            cumulativeWeight += roundLossWeights.get(i);

            if(randomNum <= cumulativeWeight){
                roundLossWeights.remove(i);
                currentDialogue = roundLossDialogue.get(i);
                roundLossDialogue.remove(i);

                return currentDialogue;
            }
        }

        return null;
    }

    private String SelectRoundWinDialogue(){
        double totalWeight = roundWinWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < roundWinWeights.size(); i++) {
            cumulativeWeight += roundWinWeights.get(i);

            if(randomNum <= cumulativeWeight){
                roundWinWeights.remove(i);
                currentDialogue = roundWinDialogue.get(i);
                roundWinDialogue.remove(i);

                return currentDialogue;
            }
        }

        return null;
    }

    private String SelectGameWinDialogue() {
        double totalWeight = gameWinWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < gameWinWeights.size(); i++) {
            cumulativeWeight += gameWinWeights.get(i);

            if(randomNum <= cumulativeWeight){
                gameWinWeights.remove(i);
                currentDialogue = gameWinDialogue.get(i);
                gameWinDialogue.remove(i);

                return currentDialogue;
            }
        }

        return null;
    }

    private String SelectGameLossDialogue() {
        double totalWeight = gameLossWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < gameLossWeights.size(); i++) {
            cumulativeWeight += gameLossWeights.get(i);

            if(randomNum <= cumulativeWeight){
                gameLossWeights.remove(i);
                currentDialogue = gameLossDialogue.get(i);
                gameLossDialogue.remove(i);

                return currentDialogue;
            }
        }

        return null;
    }

    public void DialogueRPS(String state) {
        switch (state) {
            case "victory": {
                System.out.println();
                System.out.println("Mr. RPS: "+SelectVictoryDialogue());
                break;
            }
            case "loss": {
                System.out.println();
                System.out.println("Mr. RPS: "+SelectLossDialogue());
                break;
            }
            case "roundLoss":{
                System.out.println();
                System.out.println("Mr. RPS: "+ SelectRoundLossDialogue());
                break;
            }
            case "roundVictory":{
                System.out.println();
                System.out.println("Mr. RPS: "+ SelectRoundWinDialogue());
                break;
            }
            case "gameLoss":{
                System.out.println();
                System.out.println("Mr. RPS: "+ SelectGameLossDialogue());
                break;
            }
            case "gameVictory":{
                System.out.println();
                System.out.println("Mr. RPS: "+ SelectGameWinDialogue());
                break;
            }
        }
    }
}
