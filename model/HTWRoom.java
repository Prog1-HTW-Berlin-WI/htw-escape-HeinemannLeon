package model;

import java.io.Serializable;

/**
 * Definiert die Räume der HTW als Handlungsorte des Spiels
 * @author Leon Heinemann
 * @author Samira Reinhardt
 */

public class HTWRoom implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 9065680017147292999L;

    private String identifier;
    private String description;
    private Lecturer lecturer;

    /**
     * Erstellt einen neuen Raum mit der angegebenen Bezeichnung und Beschreibung
     * @param identifier Rambezeichnung
     * @param description Beschreibung des Raums
     * @param lecturer Übungsleiter im Raum
     */
    public HTWRoom(String identifier, String description, Lecturer lecturer) {
        this.identifier = identifier;
        this.description = description;
        this.lecturer = lecturer;
    }

    /**
     * Gibt die Raumbezeichnung zurück
     * @return Raumbezeichnung
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Gibt die Beschreibung des Raums zurück
     * @return Beschreibung des Raums
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gibt den Übungsleiter im Raum zurück
     * @return Übungsleiter im Raum
     */
    public Lecturer getLecturer() {
        return lecturer;
    }
}