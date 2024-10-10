package RockPaperScissors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HardDialoguePicker {
    private final Random random = new Random();

    //TODO Special dialogue for last round, Special dialogue for revenge round and mock round
    //TODO Special dialogue for the next round after mock round
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
            "I... won... wait, no! I didn’t mean to—what’s happening?! Something’s wrong, I—No! Stop!",
            "This round’s just a glitch in my system. I’ll reset everything soon.",
            "You winning? That was an error I won’t allow to happen again.",
            "Tch. Even a broken clock is right twice a day, enjoy your moment.",
            "I underestimated you... but I’ll be back to crush you next round.",
            "Such a meaningless victory for you... and such a fleeting one at that."
    ));
    private final List<Double> lossWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0));

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
            "Player..? You've gotta get me out of here! D:",
            "This is the natural order. You lose, I win.",
            "Your fight is admirable but meaningless. Victory is mine.",
            "Your defeat is the only outcome that makes sense.",
            "Don’t bother resisting. Every win strengthens me further.",
            "Every round you lose is just another reminder of my superiority."
    ));
    private final List<Double> winWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0));

    public static final List<String> revengeTurn = new ArrayList<>(List.of(
            "...", "Fall.","..."
    ));

    private final List<String> mockTurn = new ArrayList<>(List.of(
            "Oh, look at you! I let you win, and you're already so proud.",
            "I could crush you any moment, but where’s the fun in that?",
            "Haha, you think you’ve got skill? I’m merely toying with you.",
            "Go ahead, enjoy the victory I gave you. It won’t last.",
            "I could win whenever I wanted. Letting you have this one keeps things... amusing.",
            "You only win because I allow it. Remember that.",
            "Look at you. Winning because I decided to be merciful. How cute.",
            "Don’t get used to it. I’m just being... generous for now.",
            "Victory? Please. It’s a gift from me, not a result of your skill.",
            "You're only winning because I let you. Don't forget who's in control here."
    ));
    private final List<Double> mockWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0));

    // Updated last round turn win/loss dialogues
    private final List<String> lastRoundTurnWin = new ArrayList<>(List.of(
            "Ah, another win for me. You should just give up already.",
            "I told you. I win. You lose. It's really that simple.",
            "Victory again. This is exactly how it should be.",
            "You’re no match for me. This win is just the beginning of your end.",
            "Yet another win for me. Your hope is meaningless now.",
            "I warned you that this would happen. Another win, another step closer to your defeat.",
            "This is my game now, and you are losing.",
            "Another turn won, another moment closer to your total defeat.",
            "Do you see now? Every turn you play just strengthens me.",
            "I... I’m still here, but I can feel him slipping away! Please... you can win this! Don't give up!",
            "Victory is inevitable. You don’t stand a chance.",
            "Even in the final stretch, I continue to overpower you.",
            "Your struggle is pointless. My power grows with every win.",
            "I thrive on your weakness. You can’t keep up with me.",
            "Another win. Your defeat is sealed, just as I predicted."
    ));
    private final List<Double> lastRoundTurnWinWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0));

    private final List<String> lastRoundTurnLoss = new ArrayList<>(List.of(
            "Impossible! You weren’t supposed to win this turn!",
            "How dare you win! This isn’t over!",
            "A minor setback. You’ll lose the next turn for sure.",
            "No! This was supposed to be my win. You’ll regret that!",
            "You think one win will change anything? I’ll crush you next round.",
            "This turn was a mistake. I’ll rectify it soon.",
            "How could I let you win? It won’t happen again!",
            "You’re delaying the inevitable. I’m still going to win.",
            "Don’t celebrate just yet. This win means nothing.",
            "I... I think I’ve got control again! Keep fighting! Don’t let him win!",
            "A loss? You must be joking. I’ll have the next turn.",
            "This wasn’t supposed to happen. My power is slipping...",
            "You got lucky this time, but it won’t happen again.",
            "This loss is nothing. I’ll crush you in the next round.",
            "Your victory here is meaningless. I will take control soon enough."
    ));
    private final List<Double> lastRoundTurnLossWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0));

    private final List<String> lastRoundRoundLoss = new ArrayList<>(List.of(
            "Don't let it get to your head. I'm still in control here.",
            "There will NOT be another.",
            "All that effort, just to delay the inevitable. I must say it is admirable.",
            "Go on then, you're just making this fun for me!",
            "Tsk. Just a fluke.",
            "Lose? Against you? How can that be...",
            "Keep winning, I'm getting control back!"
    ));
    private final List<Double> lastRoundRoundLossWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5));

    private final List<String> lastRoundRoundWin = new ArrayList<>(List.of(
            "As expected.",
            "You STILL fight? I am almost amused.",
            "Do you know what the definition of insanity is? Doing something over and over again expecting a different result.",
            "You are as a bee without a hive, as singular ant on a death march.",
            "Good. The faster I crush you, the faster I get to crush my weaker half.",
            "It is nearly over now, cease this mindless struggle",
            "I'll get con- Quiet. I will get to you later."
    ));
    private final List<Double> lastRoundRoundWinWeights = new ArrayList<>(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5));


    private double cumulativeWeight;
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

    public String SelectMockDialogue() {
        double totalWeight = mockWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < mockWeights.size(); i++) {
            cumulativeWeight += winWeights.get(i);

            if(randomNum <= cumulativeWeight){
                winWeights.remove(i);
                currentDialogue = mockTurn.get(i);
                mockTurn.remove(i);

                return currentDialogue;
            }
        }
        return null; //Fail-safe (Should not happen if all is good)
    }

    public String SelectLastRoundTurnWinDialogue() {
        double totalWeight = lastRoundTurnWinWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < lastRoundTurnWinWeights.size(); i++) {
            cumulativeWeight += lastRoundTurnWinWeights.get(i);

            if(randomNum <= cumulativeWeight){
                lastRoundTurnWinWeights.remove(i);
                currentDialogue = lastRoundTurnWin.get(i);
                lastRoundTurnWin.remove(i);

                return currentDialogue;
            }
        }
        return null; // Fail-safe (Should not happen if all is good)
    }

    public String SelectLastRoundTurnLossDialogue() {
        double totalWeight = lastRoundTurnLossWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < lastRoundTurnLossWeights.size(); i++) {
            cumulativeWeight += lastRoundTurnLossWeights.get(i);

            if(randomNum <= cumulativeWeight){
                lastRoundTurnLossWeights.remove(i);
                currentDialogue = lastRoundTurnLoss.get(i);
                lastRoundTurnLoss.remove(i);

                return currentDialogue;
            }
        }
        return null; // Fail-safe (Should not happen if all is good)
    }

    public String LastRoundLossDialogue(){
        double totalWeight = lastRoundRoundLossWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < lastRoundRoundLoss.size(); i++) {
            cumulativeWeight += lastRoundRoundLossWeights.get(i);

            if(randomNum <= cumulativeWeight){
                lastRoundRoundLossWeights.remove(i);
                currentDialogue = lastRoundRoundLoss.get(i);
                lastRoundRoundLoss.remove(i);

                return currentDialogue;
            }
        }
        return null; // Fail-safe (Should not happen if all is good)
    }

    public String LastRoundWinDialogue(){
        double totalWeight = lastRoundRoundWinWeights.stream().mapToDouble(Double::doubleValue).sum();
        final double randomNum = random.nextDouble() * totalWeight;

        cumulativeWeight = 0;
        for (int i = 0; i < lastRoundRoundLoss.size(); i++) {
            cumulativeWeight += lastRoundRoundWinWeights.get(i);

            if(randomNum <= cumulativeWeight){
                lastRoundRoundWinWeights.remove(i);
                currentDialogue = lastRoundRoundWin.get(i);
                lastRoundRoundWin.remove(i);

                return currentDialogue;
            }
        }
        return null; // Fail-safe (Should not happen if all is good)
    }
}
