package model;

import java.io.Serializable;

/**
 * Definiert die Lehrpersonen, die dem Spieler helfen
 * @author Leon Heinemann
 * @author Samira Reinhardt
 */

public class Lecturer implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 540082607047283589L;

    private String name;
    private boolean hasSigned;
    private boolean hasMetHero;

    /**
     * Erstellt einen neuen Übungsleiter mit dem angegebenen Namen
     * @param name Name des Übungsleiters
     */
    public Lecturer (String name) {
        this.name = name;
        this.hasSigned = false;
        this.hasMetHero = false;
    }
    /**
     * Markiert den Lecturer als getroffen
     */
    public void meetHero() {
        this.hasMetHero = true;
    }

    /**
     * Überprüft, ob der Übungsleiter bereit ist zu unterschreiben
     * @return true, wenn noch nicht unterschrieben wurde und er den Helden getroffen hat, sonst false
     */
    public boolean isReadyToSign() {
        if (!hasSigned && hasMetHero) {
            return true;
        } else {
            return false;
        }  
    }

    /**
     * Markiert den Lecturer als erledigt, indem er auf dem Laufzettel unterschreibt
     */
    public void sign() {
        this.hasSigned = true;
    }

    public String getName() {
        return name;
    }

    public boolean hasSigned() {
        return this.hasSigned;
    }
}