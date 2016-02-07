package swe.terminkalender.model;

import java.io.*;

/**
 * Benutzer ist eine abstrakte Klasse und bietet die Grundlage für sämtliche Benutzer
 * (von einer abstrakten Klasse kann kein Objekt erzeugt werden!)
 * @author Daniel Hanzer, Michael Schneider, Can Özkan
 * @version 1.0; 12/01/2016
 */
public abstract class Benutzer implements Serializable{
	
	/** Benutzername des Users */
	private String benutzername;
	
	/** Passwort zur Kontrolle zur Sicherheitskontrolle */
	private String passwort;
	
	/** Status auf true ist notwendig um als Analytiker bzw. Veranstalter sich einloggen zukönnen.
	 * Nur der Administrator kann diesen Wert ändern
	 * */
	private boolean status;
	
	/** Eindeutige ID */
	private int id;
	
	/** Varibale um Benutzer Serializieren zu können */
	private static final long serialVersionUID = 1L;
	
	public Benutzer(String benutzername, String passwort, boolean status, int id){
		this.benutzername = benutzername; this.passwort = passwort; this.passwort = passwort; this.id = id;
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
