package app;

import java.util.Scanner;
import model.Alien;
import model.FaceEater;
import model.FluffPuff;
import model.HTWRoom;
import model.Hero;
import model.Lecturer;

/**
 * Beinhaltet die Charaktere und Handlungsorte des Spiels
 * @author Leon Heinemann
 * @author Samira Reinhardt
 */
public class EscapeGame {
    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[8];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private Alien alien;
    

    private void initializeRooms() {
        Lecturer lecturer1 = new Lecturer("Herr Poeser");
        Lecturer lecturer2 = new Lecturer("Frau Safitri");
        Lecturer lecturer3 = new Lecturer("Frau Vaseva");
        Lecturer lecturer4 = new Lecturer("Frau Gärtner");
        Lecturer lecturer5 = new Lecturer("Herr Gnaoui");

        rooms[0] = new HTWRoom("A219", "Zwei Fenster sind offen, es zieht mörderisch... Die Kälte erzeugt einen schaurigen Nebel.", null);
        rooms[1] = new HTWRoom("A238", "Durch die großen Fenster kann man den außergewöhnlich blauvioletten Himmel sehen. Auf dem Pult steht eine große Kaffeetasse.", lecturer2);
        rooms[2] = new HTWRoom("A214", "Er wird von den leuchtenden Bildschirmen der Computer erhellt. Auf ihnen läuft ein Programm, das den Anschein macht, als würde jemand etwas hacken.", lecturer3);
        rooms[3] = new HTWRoom("A143", "Es riecht nach alten Socken und merkwürdige Schleimspuren zieren den Boden und die Schreibtische.", null);
        rooms[4] = new HTWRoom("A015", "Ein riesiger Raum. Die zugezogenen Vorhänge verbannen das Licht, als wäre man in einer geschlossenen Kiste.", lecturer4);
        rooms[5] = new HTWRoom("A142", "Die Fensterjalousien fahren unkontrolliert hoch und runter. Am anderen Ende des Raums stehen zahlreiche Staffeleien mit riesigen Gemälden.", lecturer1);
        rooms[6] = new HTWRoom("Lesesaal 1", "In der Mitte des Raums ist ein riesiger Haufen Bücher. Lose Seiten fliegen durch die Luft. Die Tische sind kurz- und kleingehauen.", null);
        rooms[7] = new HTWRoom("Treslounge", "Das gedimmte Licht erzeugt eine ruhige, friedliche Stimmung. Die schwarzen Ledersofas sehen gemütlicher aus denn je...", lecturer5);
    }
    /** 
    * Erstellt ein neues Escapegame mit einem neuen Hero
    */
    public EscapeGame(String heroName) {
        this.hero = new Hero(heroName);
        initializeRooms();
    }

    public EscapeGame() {
        this.hero = new Hero("Spieler");
    }

    /**
     * Gibt zurück, ob das Spiel läuft
     * @return true, wenn das Spiel läuft, sonst false
     */
    public boolean isGameRunning() {
        return gameRunning;
    }
    
    /**
     * Setzt den Status, ob das Spiel läuft
     * @param gameRunning
     */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }
   
    /**
     * Gibt zurück, ob das Spiel beendet ist
     * @return true, wenn das Spiel beendet ist, sonst false
     */
    public boolean isGameFinished() {
        return gameFinished;
    }
    
    /**
     * Setzt den Status, ob das Spiel beendet ist
     * @param gameFinished
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
     
     

    /**
     * Startet das Spiel
     */
    public void run() {
        System.out.println("Willkommen zu deinem Abenteuer, " + hero.getName() + "!\n");
          
        while (isGameRunning() && !isGameFinished()) {
            showGameMenu();
            String choice = readUserInput();
            handleUserInput(choice);
            System.out.println("====================");
        }
    }

    private void showGameMenu() {
        System.out.println("SPIELMENÜ\n");
        System.out.println("Was möchtest du tun?\n");
        System.out.println("(1) Hochschule erkunden");
        System.out.println("(2) Heldenstatus anzeigen");
        System.out.println("(3) Laufzettel anzeigen");
        System.out.println("(4) Verschnaufpause machen");
        System.out.println("(5) Spiel verlassen");
    }
    private String readUserInput() {
        Scanner scanner2 = new Scanner(System.in);
        String userInput = scanner2.nextLine();
        return userInput;
    }
    
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                String result = this.exploreHTW();
                System.out.println(result);
                break;
            case "2":
                System.out.println("Übersicht deines Helden:\n");
                System.out.println("Name: " + hero.getName());
                System.out.println("Lebenspunkte: " + hero.getHealthPoints());
                System.out.println("Erfahrungspunkte: " + hero.getExperiencePoints()); 
            break;
            case "3":
                System.out.println("Dein Laufzettel:\n");

                int signedCount = 0;
                for (int i = 0; i < rooms.length; i++) {
                    Lecturer lecturer = rooms[i].getLecturer();
                    if (lecturer == null) {
                        continue;
                    }
                    if (lecturer != null && lecturer.hasSigned() == true) {
                        signedCount++;
                    System.out.println(lecturer.getName()+" hat den Laufzettel unterschrieben.");
                    } else {
                    System.out.println(lecturer.getName()+" hat den Laufzettel noch nicht unterschrieben.");
                    }
            }
                int missingSignatures = 5 - signedCount;
                System.out.println("Fehlende Unterschriften: " + missingSignatures + "/5");
                System.out.println("Aktuelle Runde: " + currentRound + " von " + MAX_ROUNDS);
                break;
            case "4":
                System.out.println("Wähle deine Verschnaufpause:\n");
                System.out.println("(1) Kurze Verschnaufpause (+3 Lebenspunkte, aktuelle Runde fortsetzen)");
                System.out.println("(2) Lange Verschnaufpause (+10 Lebenspunkte, du kommst in die nächste Runde)");
                String restChoice = readUserInput();

               if (restChoice.equals("1")) {
                    hero.regenerate(false);
                    System.out.println("Du hast eine kurze Verschnaufpause gemacht und 3 Lebenspunkte erhalten. Deine aktuellen Lebenspunkte: " + hero.getHealthPoints());
                } else if (restChoice.equals("2")) {
                    hero.regenerate(true);
                    System.out.println("Du hast eine lange Verschnaufpause gemacht und 10 Lebenspunkte erhalten. Deine aktuellen Lebenspunkte: " + hero.getHealthPoints());
                    currentRound++;
                    System.out.println("Du kommst in die nächste Runde (Runde " + currentRound + ")");
                } else {
                    System.out.println("Ungültige Eingabe. Bitte wähle 1 oder 2!");
                }
                break;
            case "5":
                System.out.println("Zurück zum Hauptmenü...");
                setGameRunning(false);
                break;
            default:
                System.out.println("Ungültige Eingabe. Bitte wähle eine Zahl zwischen 1 und 5!");
                break;
        }
    }
   
    /**
     * holt sich den Helden des Spiels
     * @return der Held des Spiels 
     */
    public Hero getHero() {
        return hero;
    }

    private static final int MAX_ROUNDS = 24;
    private int currentRound = 1;

    private boolean isGameFinished = false;

    public String exploreHTW() {
        currentRound++;
        if (currentRound > MAX_ROUNDS) {
            isGameFinished = true;
            return "Deine Zeit ist um!\nProf. Majuntke steigt in ihr Raumschiff und fliegt zurück auf ihren Heimatplaneten, da sie in Wahrheit ein Alien ist!\nDas Spiel ist vorbei!";
        }
    
    int index = (int) (Math.random() * rooms.length);
    HTWRoom currentRoom = rooms[index];

    while (currentRoom == null) {
        index = (int) (Math.random() * rooms.length);
        currentRoom = rooms[index];
    }

    System.out.println("Du gehst in den Raum " + currentRoom.getIdentifier() + ". " + currentRoom.getDescription());
    
    double eventChance = Math.random();

    if (eventChance < 0.20) {
        return "Es passiert nichts Besonderes...";
    }
    
    if (eventChance < 0.20 + 0.52) {
        double alienChance = Math.random();
        Alien alien;
        if (alienChance < 0.60) {
            alien = FluffPuff.getRandomFluffPuff();
        } else {
            alien = FaceEater.getRandomFaceEater();
    }

        System.out.println(alien.getGreeting());

        if (alien.isFriendly()) {
            return "Das Alien " + alien.getName() + " lächelt dich freundlich an. Puh! - Keine Gefahr. Du kommst nun in die nächste Runde. Runde: " + currentRound;
        }
        System.out.println(alien.getName() + " ist ein feindliches Alien! Entscheide dich - möchtest du kämpfen oder fliehen? (K/F):");
        
        String battleChoice = readUserInput();

    if (battleChoice.equalsIgnoreCase("K")) {
            
        while (!alien.isDefeated() && hero.isOperational()) {
            int alienDamage = hero.attack();
            alien.takeDamage(alienDamage);

        if (alien.isDefeated()) {
            System.out.println("Du hast das Alien " + alien.getName() + " besiegt!");
            hero.addExperiencePoints(5);
            return "Du kommst nun in die nächste Runde. (Runde " + (currentRound + 1) + ")";
            }

            int heroDamage = (int) (Math.random() * 6);
            hero.takeDamage(heroDamage);
            System.out.println(alien.getName() + " greift dich an und verursacht " + heroDamage + " Schadenspunkte. Deine verbleibenden Lebenspunkte: " + hero.getHealthPoints());

        if (!hero.isOperational()) {
            isGameFinished = true;
            return "Du wurdest von " + alien.getName() + " besiegt. Das Spiel ist vorbei!";
            }
        }

    } else if (battleChoice.equalsIgnoreCase("F")) {
        if (hero.flee()) {
            return "Puh - die Flucht ist gerade so gelungen! Du kommst nun in die nächste Runde. (Runde " + (currentRound + 1) + ")";
        }
        System.out.println("Die Flucht ist misslungen! Du musst das Alien bekämpfen!");
        
        while (!alien.isDefeated() && hero.isOperational()) {
            int alienDamage = hero.attack();
            System.out.println("Du greifst " + alien.getName() + " an und verursachst " + alienDamage + " Schadenspunkte.");
            alien.takeDamage(alienDamage);
            System.out.println(alien.getName() + "s verbleibende Lebenspunkte: " + alien.getLifePoints());
        
        if (alien.isDefeated()) {
            System.out.println("Du hast das Alien " + alien.getName() + " besiegt!");
            hero.addExperiencePoints(5);
            return "Du kommst nun in die nächste Runde. (Runde " + (currentRound + 1) + ")";
            }

            int heroDamage = (int) (Math.random() * 3);
            System.out.println(alien.getName() + " greift dich an und verursacht " + heroDamage + " Schadenspunkte.");
            hero.takeDamage(heroDamage);
            System.out.println("Deine verbleibenden Lebenspunkte: " + hero.getHealthPoints());

        if (!hero.isOperational()) {
            isGameFinished = true;
            return "Du wurdest von " + alien.getName() + " besiegt. Das Spiel ist vorbei!";
            }
        } 
    } else {
        return "Ungültige Eingabe. PLATZHALTER";
    }
 }

 Lecturer lecturer = currentRoom.getLecturer();

    if (lecturer == null) {
        return "Es ist niemand im Raum.";
    }

    lecturer.meetHero();
    System.out.println("Du triffst auf die Übungsgruppenleitung " + lecturer.getName() + "!");

    if (lecturer.isReadyToSign()) {
        lecturer.sign();
        return "Die Übungsgruppenleitung unterschreibt deinen Laufzettel! Du kommst nun in die nächste Runde (Runde " + currentRound + ")";
    }
    return "Die Übungsgruppenleitung ist nicht bereit zu unterschreiben. Vielleicht musst du sie erst überzeugen.";
    }
}