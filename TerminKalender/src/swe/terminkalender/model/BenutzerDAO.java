package swe.terminkalender.model;

import java.util.ArrayList;

/**
 * BenutzerDAO interface für die Serializierung.
 * @author Daniel Hanzer, Michael Schneider, Can Özkan
 * @version 1.0; 12/01/2016
 */
public interface BenutzerDAO {
	
	/** 
	 * Liste von allen Gespeicherten Benutzern
	 * 
	 * @return ArrayList von allen Benutzern
	 */
	ArrayList<Benutzer> ListOfBenutzer();
	
	/** 
	 * Funktion zum Speichern von Benutzern 
	 * @param benutzer
	 * 			Der zu Speichernde Benutzer
	 */
	void speichereBenutzer(Benutzer benutzer);
	
	/** 
	 * Funktion zum löschen eines Benutzer
	 * @param id
	 * 			Benutzer Id des zu löschenden Benutzers
	 */
	void loescheBenutzer(int id);
}
