package swe.terminkalender.model;

import java.util.ArrayList;

/**
 * Privatnutzer erbt von der Abstrakten Klasse Benutzer. Privatnutzer haben einen Eigenen Kalender
 * den Sie mit Terminen befüllen können. Zudem haben sie die Möglichkeit Termine zu bearbeiten und zu löschen
 * und öffentliche Termine/Kalender in ihren Kalender zu adaptieren. 
 * @author Daniel Hanzer, Michael Schneider, Can Özkan
 * @version 1.0; 12/01/2016
 */
public class Privatnutzer extends Benutzer{

	/** Varibale um Benutzer Serializieren zu können */
	private static final long serialVersionUID = 1L;
	
	/** Eigener Kalender des Privatnutzers*/
	private Kalender kalender;
	
	/** ArrayList von Kalendern die von Veranstaltern per Einladung versand wurden */
	private ArrayList<Kalender> kalenderlist;
	
	/** ArrayList mit Terminen die von Veranstaltern per Einladung versand wurden */
	private ArrayList<Termin> terminlist;
	
	public Privatnutzer(String benutzername, String passwort, boolean status, int id){
		super(benutzername, passwort, status, id);
		
		
	}

	public Kalender getKalender() {
		return kalender;
	}

	public void setKalender(Kalender kalender) {
		this.kalender = kalender;
	}

	public ArrayList<Kalender> getKalenderlist() {
		return kalenderlist;
	}

	public void setKalenderlist(ArrayList<Kalender> kalenderlist) {
		this.kalenderlist = kalenderlist;
	}

	public ArrayList<Termin> getTerminlist() {
		return terminlist;
	}

	public void setTerminlist(ArrayList<Termin> terminlist) {
		this.terminlist = terminlist;
	}


}
