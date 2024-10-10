package RockPaperScissors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HardDialoguePicker {
    private final Random random =  new Random();

    //TODO Special dialogue for last round, Special dialogue for revenge round and mock round
    //TODO Special dialogue for the next round after mock round depending on win/loss
    //TODO Dialogue encapsulating the mid and first rounds

    private final List<String> basicTurnLoss = new ArrayList<>(List.of(
            "How... did I lose to you? This must be some sort of mistake.",
            "Pathetic. A mere fluke, nothing more.",
            "You may have won this round, but don’t get used to it.",
            "A loss? No matter. Your luck will run out soon.",
            "Hmph. Enjoy your fleeting victory. It won’t happen again.",
            "You think this changes anything? It doesn’t.",
            "A minor setback. You’ve only delayed the inevitable.",
            "Don’t be too proud of yourself. This loss means nothing.",
            "Losing to you... how embarrassing. But I'll make sure it never happens again.",
            "Player..? You've gotta get me out of here! D:"
    ));
    private final List<Double> lossWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5));

    private final List<String> basicTurnWin = new ArrayList<>(List.of(
            "See? This is how it’s supposed to go. Your defeat is inevitable.",
            "Another victory. You never stood a chance against me.",
            "How does it feel to lose? You should get used to it.",
            "Pathetic. You thought you could win, didn’t you?",
            "Another step closer to crushing your hopes completely.",
            "You’re just a waste of time. Victory was always mine.",
            "Your attempts are futile. Every move you make leads to this.",
            "Watch closely, because this is what dominance looks like.",
            "Victory was a foregone conclusion. I was never going to lose.",
            "I... won... wait, no! I didn’t mean to—what’s happening?! Something’s wrong, I—No! Stop!"
    ));
    private final List<Double> winWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5));

    private double cumulativeWeight; // Variable to control selection
    private String currentDialogue;

    public String SelectTurnLossDialogue(){
        double totalWeight = lossWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;

        for (int i = 0; i < lossWeights.size(); i++) {
            cumulativeWeight += lossWeights.get(i);

            if(randomNum <= cumulativeWeight){
                lossWeights.remove(i);
                currentDialogue = basicTurnLoss.get(i);
                basicTurnLoss.remove(i);

                return currentDialogue;
            }
        }

        return null;
    }

    public String SelectTurnWinDialogue() {
        double totalWeight = winWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < winWeights.size(); i++) {
            cumulativeWeight += winWeights.get(i);

            if(randomNum <= cumulativeWeight){
                winWeights.remove(i);
                currentDialogue = basicTurnWin.get(i);
                basicTurnWin.remove(i);

                return currentDialogue;
            }
        }
        return null; //Fail-safe (Should not happen if all is good)
    }

}
