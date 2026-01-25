package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Beinhaltet die Auswahlmöglichkeiten des Spielers im Hauptmenü, z.B. Speichern, Fortsetzen...
 * @author Leon Heinemann
 * @author Samira Reinhardt
 */
public class EscapeApp {

    /**
    * Bezeichnung der Speicherdatei des Spiels
    * @return Name der Datei
    */
    public static final String SAVE_FILE_NAME = "save";
    /**
     * Das Spiel selbst
     */
    private EscapeGame game;
    
    /**
     * Prüft, ob das Spiel läuft
     * @return true, wenn das Spiel läuft; false, wenn nicht
     */
    private boolean gameRunning = true;

    /**
     * Prüft, ob das Spiel bereits gestartet wurde
     * @return true, wenn das Spiel gestartet wurde; false, wenn nicht
     */
    private boolean hasStartedGame = false;

    /**
     * Gibt Eingaben auf der Konsole aus
     * @param args Argumente für main-Methode
     */
    public static void main(String[] args) {
        System.out.println("THE HTW ESCAPE");
        System.out.println("========================================\n");

        /**
         * Konstruktor für die App
         */
        EscapeApp app = new EscapeApp();

        while (true) {
            app.showMainMenu();
            String choice = app.readUserInput();
            app.handleUserInput(choice);
            System.out.println("====================");
        }
    }

    /**
     * Gibt das Hauptmenü auf der Konsole aus
     */
    private void showMainMenu() {
        System.out.println("HAUPTMENÜ\n");
        System.out.println("Was möchtest du tun?\n");
        System.out.println("(1) Neues Spiel starten");
        if (isGameRunning() && !isGameFinished() && hasStartedGame) {
            System.out.println("(2) Spiel fortsetzen");
        }
        if (hasSavedGame() && !isGameRunning() && !isGameFinished() && hasStartedGame) {
            System.out.println("(3) Spiel laden");
        } 
        if (isGameRunning() && !isGameFinished() && hasStartedGame) {
            System.out.println("(4) Spiel speichern");
        }
        if (hasSavedGame() && !isGameFinished() && hasStartedGame) {
            System.out.println("(5) Bestehendes Spiel löschen");
        }
        System.out.println("(6) Spiel beenden");
        System.out.println("");
        System.out.print("Gib eine Nummer zwischen 1 und 6 ein: ");
    }

    /**
     * Liest die Nutzereingabe ein
     * @return Eingabe des Spielers
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        // TBD
        return userInput;
    }

    /**
     * Verarbeitet die Nutzereingabe
     * @param input Eingabe des Spielers
     */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                this.hasStartedGame = true;
                this.startGame();
                break;
            case "2":
                if (!isGameRunning()) {
                    System.out.println("Du hast noch kein Spiel, das du fortsetzen kannst!");
                    break;
                }
                this.resumeGame();
                break;
            case "3":
                if (!hasSavedGame()) {
                    System.out.println("Es gibt kein gespeichertes Spiel zum Laden!");
                    break;
                }
                this.loadGame();
                this.resumeGame();
                break;
            case "4":
                if (!isGameRunning() || isGameFinished()) {
                    System.out.println("Du hast noch kein Spiel, das du speichern kannst!");
                    break;
                } 
                this.saveGame();
                break;
            case "5":
                if (!hasSavedGame()) {
                    System.out.println("Es gibt kein gespeichertes Spiel zum Löschen!");
                    break;
                }
                this.deleteGame();
                this.game = null;
                this.hasStartedGame = false;
                break;
            case "6":
                System.out.println("Bis bald!");
                System.exit(0);
                break;
            default:
                System.out.println("Ungültige Eingabe. Bitte wähle eine Zahl zwischen 1 und 6!");
                break;
        }
    }

    /**
     * Startet ein neues Spiel
     */
    private void startGame() {
       System.out.print("Gib deinen Heldennamen ein: ");
        String heroName = readUserInput();
        this.game = new EscapeGame(heroName);
        this.resumeGame();
    }

    /**
     * Setzt ein pausiertes Spiel fort
     */
    private void resumeGame() {
        this.game.setGameRunning(true);
        this.game.run();
    }

    /**
     * Löscht ein bestehendes Spiel
     */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Spiel gelöscht!");
        }
    }

    /**
     * Speichert das bestehende Spiel
     */
    private void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
        } catch (Exception ex) {
            System.err.println("Fehler beim Speichern des Spiels: " + ex.getMessage());
            return;
        }
        System.out.println("Spiel gespeichert!");
    }

    /**
     * Lädt ein bestehendes Spiel
     */
    private void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (EscapeGame) ois.readObject();
            System.out.println("Spiel geladen!");
        } catch (Exception ex) {
            System.err.println("Fehler beim Laden des Spiels: " + ex.getMessage());
        }
    }

    /**
     * Prüft, ob das Spiel läuft
     * @return true, wenn es läuft; false wenn nicht
     */
    private boolean isGameRunning() {
        return this.gameRunning;
    }

    /**
     * Prüft, ob das Spiel beendet ist
     * @return true, wenn es beendet ist; false wenn nicht
     */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
     * Prüft, ob das Spiel gespeichert wurde
     * @return true, wenn Speicherdatei besteht; false wenn nicht
     */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }
}

