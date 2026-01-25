package model;

import java.io.Serializable;

/**
 * Definiert den Spielercharakter
 * @author Leon Heinemann
 * @author Samira Reinhardt
 */
public class Hero implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;

    /**
     * Erstellt einen neuen Helden mit Standardwerten
     * @param name Name des Helden
     * @return Der erstellte Held
     */
    private String name;
    private int healthPoints;
    private int experiencePoints;
    private Lecturer[] signedExerciseLeaders;

    /**
     * Erstellt einen neuen Helden mit dem angegebenen Namen
     * @param name Name des Helden
     */
    public Hero(String name) {
        this.name = name;
        this.healthPoints = 50;
        this.experiencePoints = 0;
        this.signedExerciseLeaders = new Lecturer[5];
    }

    /**
     * Der Held erleidet Schaden
     * @param amount Schadenspunkte
     */
    public void takeDamage(int amount) {
        this.healthPoints -= amount;
        if (this.healthPoints < 0) {
            this.healthPoints = 0;
        }
    }

    /**
     * Der Held regeneriert Gesundheitspunkte
     * @param longRest true für lange Regeneration, false für kurze
     */
    public void regenerate(boolean longRest) {
        if (longRest) {
            this.healthPoints += 10;
        } else {
            this.healthPoints += 3;
        }
        if (this.healthPoints > 50) {
            this.healthPoints = 50;
        }
    }

    /**
     * Der Held versucht zu fliehen
     * @return true bei Erfolg, sonst false
     */
    public boolean flee() {
        double randomValue = Math.random();

        if (randomValue < 0.42) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * bestimmt den Schaden des Heldenangriffs
     * @return Schadenspunkte 
     */
    public int attack() {
        double baseDamage = experiencePoints * 2.3 + 1;
        double randomValue = Math.random();

        if (randomValue < 0.13) {
            baseDamage = 0; 
        System.out.println("Fehlschlag! Dein Angriff verfehlt das Alien.");
        } else if (randomValue < 0.13 + 0.12) {
            baseDamage = baseDamage * 2;
        System.out.println("Sehr guter Treffer! Das Alien erleidet doppelten Schaden.");
        }

        return (int) baseDamage;
    }

    /**
     * Der Held lässt einen Übungsleiter unterschreiben
     * @param lecturer Übungsleiter
     */
    public void signExerciseLeader(Lecturer lecturer) {

            boolean alreadySigned = false;

            for (int i = 0; i < signedExerciseLeaders.length; i++) {
                if (signedExerciseLeaders[i] != null && signedExerciseLeaders[i] == lecturer) {
                    alreadySigned = true;
                    break;
                }
            }
        
            for (int i = 0; i < signedExerciseLeaders.length; i++) {
                 if (signedExerciseLeaders[i] == null) {
                    signedExerciseLeaders[i] = lecturer;
                    break;
                    }
                }
            }
        
    /**
     * Gibt die aktuellen Erfahrungspunkte des Helden zurück
     * @return aktuellen Erfahrungspunkte
     */
    public int getExperiencePoints() {
        return this.experiencePoints;
    }

    /**
     * Fügt dem Helden Erfahrungspunkte hinzu
     * @param experiencePoints Erfahrungspunkte
     */
    public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints += experiencePoints;
    }

    /**
     * Überprüft, ob der Held noch einsatzfähig ist
     * @return true, wenn der Held einsatzfähig ist, sonst false
     */
    public boolean isOperational() {
        if (healthPoints > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gibt den Namen des Helden zurück
     * @return Name des Helden
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gibt die Gesundheitspunkte des Helden zurück
     * @return Gesundheitspunkte
     */
    public int getHealthPoints() {
        return this.healthPoints;
    }

    /**
     * Gibt die Liste der unterschriebenen Übungsleiter zurück
     * @return Liste der unterschriebenen Übungsleiter
     */
    public Lecturer[] getSignedExerciseLeaders() {
        return this.signedExerciseLeaders;
    }
}