package RockPaperScissors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DialoguePicker {

    private final Random random = new Random();
    //Different lines of dialogue for victory/loss respectively, the "weights" list representing the chance that
    //Specific one gets picket (The lower the number the lower the chance)

    private List<String> winDialogue = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
    private List<String> lossDialogue = new ArrayList<>(List.of("11", "12", "13", "14", "15", "16", "17", "18", "19", "20"));

    private List<Double> lossWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5));
    private List<Double> winWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5));

    private double cumulativeWeight; // Variable to control selection

    String currentDialogue;


    private String SelectLossDialogue() {
        double totalLossWeight = lossWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNumLoss = random.nextDouble() * totalLossWeight;

        cumulativeWeight = 0;

        for (int i = 0; i < lossWeights.size(); i++) {
            cumulativeWeight += lossWeights.get(i);

            if(randomNumLoss <= cumulativeWeight){
                lossWeights.remove(i);
                currentDialogue = lossDialogue.get(i);
                lossDialogue.remove(i);

                return currentDialogue;
            }
        }
        return null; //Fail-safe (Should not happen if all is good)
    }

    private String SelectVictoryDialogue() {
        double totalWinWeight = winWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNumWin = random.nextDouble() * totalWinWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < winWeights.size(); i++) {
            cumulativeWeight += winWeights.get(i);

            if(randomNumWin <= cumulativeWeight){
                winWeights.remove(i);
                currentDialogue = winDialogue.get(i);
                winDialogue.remove(i);

                return currentDialogue;
            }
        }
        return null; //Fail-safe (Should not happen if all is good)
    }

    public void DialogueRPS(String state) {
        // TODO: Add dialogue interaction based on the game state.

        switch (state) {
            case "victory": {
                System.out.println(SelectVictoryDialogue());
                break;
            }
            case "loss": {
                System.out.println(SelectLossDialogue());
                break;
            }
        }
    }
}
