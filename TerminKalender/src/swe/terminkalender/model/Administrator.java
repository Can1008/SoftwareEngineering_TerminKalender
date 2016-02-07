package swe.terminkalender.model;

/**
 * Der Administrator kann Benutzer sperren/freischalten bzw. löschen.
 * @author Daniel Hanzer, Michael Schneider, Can Özkan
 * @version 1.0; 12/01/2016
 */
public class Administrator extends Benutzer{
	
	/** Varibale um den Analytiker Serializieren zu können */
	private static final long serialVersionUID = 1L;
	
	public Administrator(String benutzername, String passwort, boolean status, int id){
		super(benutzername, passwort, status, id);
	}
}


