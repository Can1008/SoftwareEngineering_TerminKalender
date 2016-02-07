package swe.terminkalender.model;

import java.io.*;
import java.util.*;

/**
 * Jeder Privatnutzer hat einen Kalender. Jeder Veranstalter kann 0..* Kalender verwalten.
 * Kalender sind eindeutig jenen Benutzern zugeordnet. Sie beinhalten Termine.
 * @author Daniel Hanzer, Michael Schneider, Can Özkan
 * @version 1.0; 12/01/2016
 */
public class Kalender implements Serializable{
	
	/** Varibale um den Analytiker Serializieren zu können */
	private static final long serialVersionUID = 1L;
	
	/** ArrayListe von Terminen*/
	private ArrayList<Termin> terminlist;
	
	/** Namen des Kalenders */
	private String name;
	
	/** Kalender ist es möglich Kategorien zu haben */
	private Kategorie kategorie;
	
	/** Eindeutige Kalender ID */
	int kalenderId;
	
	public Kalender(String name, Kategorie kategorie, int kalenderId){
		this.name = name; this.kategorie = kategorie; this.kalenderId = kalenderId; this.terminlist = new ArrayList<Termin>();
	}

	public ArrayList<Termin> getTerminlist() {
		return terminlist;
	}

	public void setTerminlist(ArrayList<Termin> terminlist) {
		this.terminlist = terminlist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Kategorie getKategorie() {
		return kategorie;
	}

	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}
	
	public int getKalenderId(){
		return kalenderId;
	}
	
	public void setKalenderId(int kalenderId){
		this.kalenderId = kalenderId;
	}

}
