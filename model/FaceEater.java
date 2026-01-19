package model;

public class FaceEater extends Alien {
    private static final long serialVersionUID = 1234567890123456789L;

    /**
     * Erstellt ein neues FaceEater-Alien mit vordefinierten Eigenschaften
     */
    public FaceEater(String name, int lifePoints, boolean friendly, String greeting) {
        super(name, lifePoints, friendly, greeting);

    }

    /**
     * Gibt ein zufälliges FaceEater-Alien zurück
     * return eines der FaceEater-Aliens
     */
    public static FaceEater getRandomFaceEater() {
        String[] names = {"Knabberina", "Beißhard", "Schmatzbert", "Schluckrich"};
        String[] greetings = {
            "\"Mmhhh... Dein Gesicht sieht lecker aus!\"",
            "\"Du kommst genau richtig zu meiner Mittagszeit!\"",
            "\"Dein Gesicht wird mein Abendessen sein!\"",
            "\"Ich liebe den Geschmack von ängstlichen Gesichtern!\""
        };

        int index = (int) (Math.random() * names.length);
        String name = names[index];
        String greeting = greetings[index];
        int lifePoints = 10 + (int) (Math.random() * 9);
        boolean friendly = false;

        return new FaceEater(name, lifePoints, friendly, greeting);
    }
}