package app;

import model.HTWRoom;
import model.Hero;

/**
 * Beinhaltet die Charaktere und Handlungsorte des Spiels
 * @author Leon Heinemann
 * @author Samira Reinhardt
 */
public class EscapeGame {
    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[3];
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    /** 
    * Erstellt ein neues Escapegame mit einem neuen Hero
    */
    public EscapeGame() {
        this.hero = new Hero();
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
        System.out.println("The game has started. Or not?");
    }
    /**
     * Gibt den Helden des Spiels zurück
     * @return
     */
    public Hero getHero() {
        return hero;
    }
}
