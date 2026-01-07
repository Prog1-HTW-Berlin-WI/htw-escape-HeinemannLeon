package app;

import model.HTWRoom;
import model.Hero;
import java.io.Serializable;

/**
 * Beinhaltet die Charaktere und Handlungsorte des Spiels
 * @author Leon Heinemann
 */
public class EscapeGame implements Serializable {
    // serialVersionUID hinzufügen, damit Objekte stabil serialisiert werden können
    private static final long serialVersionUID = 1L;

    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[3];
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    public EscapeGame() {
        this.hero = new Hero();
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void run() {
        System.out.println("The game has started. Or not?");
    }

    public Hero getHero() {
        return hero;
    }
}
