package RockPaperScissors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DialoguePicker {

    private final Random random = new Random();
    //Different lines of dialogue for victory/loss respectively, the "weights" list representing the chance that
    //Specific one gets picket (The lower the number the lower the chance)

    private final List<String> winDialogue = new ArrayList<>(List.of(
            "Oh wow, looks like I won this one! Good game! :D",
            "Haha, I got a win this time! Let's keep it rolling! ;D",
            "Well played! But I guess I just got a little lucky ;)",
            "Woohoo! A victory for me this time! Ready for the next one? :D",
            "Haha, I didn’t see that coming either, but I’ll take the win! :P",
            "Looks like I came out on top this time! Good try though! :D",
            "I guess it’s my lucky day, huh? Let’s go again! :D",
            "Haha, well played! But I managed to slip past you this round! ;)",
            "Woohoo! I won! But it was really close, you almost had me! :D",
            "I won... *of course I did*. How could you possibly-- Oh! Haha, sorry about that, just a bug! Let’s play again! :D"
    ));

    private final List<String> lossDialogue = new ArrayList<>(List.of(
            "Aww, you got me this time! Well done! :D",
            "Oops, looks like I lost! You’re on fire! ;D",
            "Haha, I didn’t stand a chance there! Good job! :D",
            "Well played, well played! You definitely earned that one! ;)",
            "Oh no, I guess I wasn’t fast enough! Nicely done! :P",
            "Haha, you totally got me there! Let’s see if I can win the next one! :D",
            "Wow, great job! I didn’t expect to lose that one! Let’s play again! ;D",
            "Oh, you got me! I’ll have to step up my game next time! :D",
            "Haha, you win this round! That was awesome! Ready for the next? ;D",
            "I can’t believe I lost to you... again?! Oh... must’ve glitched! Let’s go again, friend! :D"
    ));

    private final List<String> endLossDialogue = new ArrayList<>(List.of(
            "Aww, you won the game! Well played, you were amazing! :D",
            "Wow, you really outsmarted me there! Great job! ;D",
            "Haha, I couldn’t keep up with you this time! What a game! :D",
            "Well done! You’re the champ! Let’s play again sometime! :D",
            "How could someone like you... worthless... actually win against me?! Oh dear, looks like a glitch! Great game though! :D"
    ));

    private final List<String> endWinDialogue = new ArrayList<>(List.of(
            "Woohoo! I won the whole game! That was so much fun! :D",
            "Haha, looks like I came out on top! You were awesome though! ;D",
            "Wow, that was a great game! You really gave me a challenge! :D",
            "I had a blast! Thanks for playing with me! Let’s do this again sometime! :D",
            "Pathetic. Winning against you feels almost... pointless. Oops! Sorry, I think something went wrong there. Ready for the next? :D"
    ));

    private final List<Double> lossWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5));
    private final List<Double> winWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5));
    private final List<Double> endLossWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 0.5));
    private final List<Double> endWinWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 0.5));

    private double cumulativeWeight; // Variable to control selection

    String currentDialogue;


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

    private String SelectEndLossDialogue(){
        double totalWeight = endLossWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < endLossWeights.size(); i++) {
            cumulativeWeight += endLossWeights.get(i);

            if(randomNum <= cumulativeWeight){
                endLossWeights.remove(i);
                currentDialogue = endLossDialogue.get(i);
                endLossDialogue.remove(i);

                return currentDialogue;
            }
        }

        return null;
    }

    private String SelectEndWinDialogue(){
        double totalWeight = endLossWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < endWinWeights.size(); i++) {
            cumulativeWeight += endWinWeights.get(i);

            if(randomNum <= cumulativeWeight){
                endWinWeights.remove(i);
                currentDialogue = endWinDialogue.get(i);
                endWinDialogue.remove(i);

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
            case "endLoss":{
                System.out.println();
                System.out.println("Mr. RPS: "+SelectEndLossDialogue());
            }
            case "endVictory":{
                System.out.println();
                System.out.println("Mr. RPS: "+SelectEndWinDialogue());
            }
        }
    }
}
