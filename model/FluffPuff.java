package model;

public class FluffPuff extends Alien {
    private static final long serialVersionUID = 987654321098765432L;

    /**
     * Erstellt ein neues FluffPuff-Alien mit vordefinierten Eigenschaften
     */
    public FluffPuff(String name, int lifePoints, boolean friendly, String greeting) {
        super(name, lifePoints, friendly, greeting);

    }

    /**
     * Gibt ein zufälliges FluffPuff-Alien zurück
     * return eines der FluffPuff-Aliens
     */
    public static FluffPuff getRandomFluffPuff() {
        String[] names = {"Flauschella", "Fluffmann", "Knuddeline", "Knutschhold"};
        String[] greetings = {
            "\n\"Hey, Erdling! Warum so ernst? - Lass' dich knuddeln.\"",
            "\n\"Hallöchen! Du bist aber ein niedliches Wesen!\"",
            "\n\"Huhu! Nicht erschrecken, ich tu' dir nichts!\"",
            "\n\"Yippiiieee! Endlich jemand, den ich abknutschen kann!\""
        };

        int index = (int) (Math.random() * names.length);
        String name = names[index];
        String greeting = greetings[index];
        int lifePoints = 15 + (int) (Math.random() * 13);
        boolean friendly = true;

        return new FluffPuff(name, lifePoints, friendly, greeting);
    }
}