package swe.terminkalender.model;

import java.io.*;
import java.util.*;

/**
 * Jeder Kalender besteht aus 0..* Terminen. Termine werden in der Klasse Kalender als ArrayList
 * gespeichert und sind daher existenzabhängig von ihnen, sowie Kalender abhängig von Privatnutzer/Veranstalter
 * sind.
 * @author Daniel Hanzer, Michael Schneider, Can Özkan
 * @version 1.0; 12/01/2016
 */
public class Termin implements Serializable{
	
	/** Varibale um Benutzer Serializieren zu können */
	private static final long serialVersionUID = 1L;
	
	/** Name des Termins */
	String name;
	
	/** Ort an dem der Termin statt findet */
	String ort;
	
	/** Beschreibung/Notizen zum Termin */
	String notiz;
	
	/** Beginndatum des Termins */
	Calendar beginndatum;
	
	/** Enddatum des Termins */
	Calendar enddatum;
	
	/** Zugeordnete Kategorie */
	Kategorie kategorie;
	
	/** Eindeutige Termin Id */
	int terminId;
	
	/** Anzahl wie oft der Termin geteilt wurde */
	int anzahlGeteilt;
	
	/** Anzahl an Tagen, ab wann eine Benachrichtung erfolgen soll */
	int benachrichtigungTage;
	
	public Termin(String name, String ort, String notiz, Calendar beginndatum, Calendar enddatum, Kategorie kategorie, int terminId){
		this.name = name; this.ort = ort; this.notiz = notiz; this.beginndatum = beginndatum; this.enddatum = enddatum; this.terminId = terminId;
		this.kategorie = kategorie;
	}
	public Termin(String name, String ort, String notiz, Calendar beginndatum, Calendar enddatum, int terminId){
		this.name = name; this.ort = ort; this.notiz = notiz; this.beginndatum = beginndatum; this.enddatum = enddatum; this.terminId = terminId;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public String getNotiz() {
		return notiz;
	}

	public void setNotiz(String notiz) {
		this.notiz = notiz;
	}
	
	public Calendar getBeginndatum() {
		return beginndatum;
	}

	public void setBeginndatum(Calendar beginndatum) {
		this.beginndatum = beginndatum;
	}
	
	public Calendar getEnddatum() {
		return enddatum;
	}

	public void setEnddatum(Calendar enddatum) {
		this.enddatum = enddatum;
	}
	
	public Kategorie getKategorie() {
		return kategorie;
	}

	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}
	
	public int getTerminId(){
		return terminId;
	}
	
	public void setTerminId(int terminId){
		this.terminId = terminId;
	}
	
	public int getAnzahlGeteilt(){
		return anzahlGeteilt;
	};
	public void setAnzahlGeteilt(int anzahlGeteilt){
		this.anzahlGeteilt = anzahlGeteilt;
	}
	
	public int getBenachrichtigungTage(){
		return benachrichtigungTage;
	}
	
	public void setBenachrichtigungTage(int benachrichtigungTage){
		this.benachrichtigungTage = benachrichtigungTage;
	}
	
}
