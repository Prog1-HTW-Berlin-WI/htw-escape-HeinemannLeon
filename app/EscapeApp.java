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
     * Gibt Eingaben auf der Konsole aus
     * @param args Argumente für main-Methode
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the HTW escape");
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
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");
        System.out.println("(1) Start new game");
        if (isGameRunning()){
            System.out.println("(2) Resume game");
        }
        if (hasSavedGame()){
            System.out.println("(3) Load game");
        } 
        if (isGameRunning()) {
            System.out.println("(4) Save game");
        }
        if (hasSavedGame()) {
            System.out.println("(5) Delete saved game");
            this.game = null;
        }
        System.out.println("(6) Quit");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 6: ");
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
                this.startGame();
                break;
            case "2":
                this.resumeGame();
                break;
            case "3":
                this.loadGame();
                this.resumeGame();
                break;
            case "4":
                this.saveGame();
                break;
            case "5":
                this.deleteGame();
                break;
            // ...
            case "6":
                System.out.println("See you soon!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                break;
        }
    }

    /**
     * Startet ein neues Spiel
     */
    private void startGame() {
        this.game = new EscapeGame();
        resumeGame();
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
            System.out.println("Game deleted!");
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
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
            return;
        }
        System.out.println("Game saved!");
    }

    /**
     * Lädt ein bestehendes Spiel
     */
    private void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (EscapeGame) ois.readObject();
            System.out.println("Game loaded!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while loading the game: " + ex.getMessage());
        }
    }

    /**
     * Prüft, ob das Spiel läuft
     * @return true, wenn es läuft; false wenn nicht
     */
    private boolean isGameRunning() {
        return game != null;
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
