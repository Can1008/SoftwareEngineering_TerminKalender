package swe.terminkalender.model;

import java.util.*;

/**
 * Veranstalter erbt von der Abstrakten Klasse Benutzer. Veranstalter können mehrere öffentliche
 * Kalender erstellen, sowie mit Terminen befüllen. Zudem können sie diese per Einladung an Privatnutzer
 * versenden. Außerdem ist es ihnen mögliche neue Kalender/Termine zu erstellen sowie zu löschen. Termine
 * können im Nachhinien bearbeitet werden.
 * @author Daniel Hanzer, Michael Schneider, Can Özkan
 * @version 1.0; 12/01/2016
 */
public class Veranstalter extends Benutzer{

	/** Varibale um Benutzer Serializieren zu können */
	private static final long serialVersionUID = 1L;
	
	/** Firma des Veranstalters */
	private String firma;
	
	/** E-Mail Adresse des Veranstalters */
	private String email;
	
	/** ArrayList von Kalendern, die der Veranstalter verwaltet */
	private ArrayList<Kalender> kalenderListe;
	
	/** ArrayList von Privatnutzer, um Termin/Kalender Einladungen an jene zu versenden */
	private ArrayList<Privatnutzer> benutzerListe;
	
	public Veranstalter(String benutzername, String passwort, boolean status, int id, String firma, String email, ArrayList<Kalender> kalenderListe){
		super(benutzername, passwort, status, id);
		this.firma = firma; this.email = email; this.kalenderListe = new ArrayList<Kalender>();
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Kalender> getKalenderListe() {
		return kalenderListe;
	}

	public void setKalenderListe(ArrayList<Kalender> kalenderListe) {
		this.kalenderListe = kalenderListe;
	}

	public ArrayList<Privatnutzer> getBenutzerListe() {
		return benutzerListe;
	}

	public void setBenutzerListe(ArrayList<Privatnutzer> benutzerListe) {
		this.benutzerListe = benutzerListe;
	}
}
