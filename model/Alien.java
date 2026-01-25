package model;

import java.io.Serializable;

/**
 * Hier werden die Gegner des Spielers definiert
 * @author Leon Heinemann
 * @author Samira Reinhardt
 */
public abstract class Alien implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 1729389822767173584L;

    private String name;
    private int lifePoints;
    private boolean friendly;
    private String greeting;

    /**
     * Erstellt ein neues Alien mit den angegebenen Eigenschaften
     * @param name Name des Aliens
     * @param lifePoints Lebenspunkte des Aliens
     * @param friendly Gibt an, ob das Alien freundlich ist
     * @param greeting Begrüßung des Aliens
     */
    public Alien(String name, int lifePoints, boolean friendly, String greeting) {
        this.name = name;
        this.lifePoints = lifePoints;
        if (this.lifePoints < 0) {
            this.lifePoints = 0;
        }
        this.friendly = friendly;
        this.greeting = greeting;
    }

    /**
     * berechnet den Schaden, den das Alien erleidet und gibt das Ergebnis aus
     * @param amount Schadenspunkte
     */
    public void takeDamage(int amount) {
        int damage = amount;

        if (damage < 0) {
            damage = 0;
        }

        int before = this.lifePoints;
        this.lifePoints -= damage;

        if (this.lifePoints < 0) {
            this.lifePoints = 0;
        }

    int actualDamage = before - this.lifePoints;
        
    System.out.println("Du greifst " + this.name + " an und es erleidet " + actualDamage + " Schadenspunkte. " + this.getName() + "s verbleibende Lebenspunkte: " + this.lifePoints);
    }

    /**
     * Überprüft, ob das Alien besiegt wurde
     * @return true, wenn die Lebenspunkte 0 (oder weniger) sind, sonst false
     */
    public boolean isDefeated() {
        return this.lifePoints <= 0;
    }

    public String getName() {
        return name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    /**
     * Überprüft, ob das Alien freundlich ist
     * @return true, wenn das Alien freundlich ist, false wenn feindlich
     */
    public boolean isFriendly() {
        return friendly;
    }

    public String getGreeting() {
        return greeting;
    }
}
    



