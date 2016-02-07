package swe.terminkalender.model;

import java.util.*;


/**
 * Benutzer ist eine abstrakte Klasse und bietet die Grundlage für sämtliche Benutzer
 * (von einer abstrakten Klasse kann kein Objekt erzeugt werden!)
 * * @author Daniel Hanzer, Michael Schneider, Can �zkan
 * @version 1.0; 12/01/2016
 */
public class BenutzerManagement{
	
	/** Objekt der Klasse SerializedBenutzerDAO, damit die Funktionen der Klasse
	 * aufgerufen werden können
	 * */
	SerializedBenutzerDAO a = new SerializedBenutzerDAO();
	
	/** 
	 * Funktion zum finden von Terminen innerhalb eines Benachrichtigungsfensters
	 * und Sortierung nach neuestem Termin
	 * @param benutzer
	 * 		  �bergebener Privatnutzer mit den Terminen 	
	 * @return
	 * 		Termine, die im Benachrichtigungsfenster sind.	 * 		
	 */
	
	public ArrayList<Termin> getBenachrichtigungsTermine(Privatnutzer benutzer)
	{
		ArrayList<Termin> benutzerTermine = benutzer.getKalender().getTerminlist();	
		ArrayList<Termin> benachrTermine = new ArrayList<Termin>();
			for(Termin t: benutzerTermine)
			{
				int tage = t.getBenachrichtigungTage();
				if(tage>0)
				{
					Date start = t.getBeginndatum().getTime();
					Date ende = t.getEnddatum().getTime();
					Date heute = Calendar.getInstance().getTime();
					double diff = start.getTime() - heute.getTime();
					double days2 = (diff/(1000*60*60*24))+1;
					int day = (int) days2;
					if(day<= tage && heute.before(ende))
					{
						benachrTermine.add(t);
					}
				}				
			}
		Collections.sort(benachrTermine, new Comparator<Termin>() {
			@Override
			public int compare(final Termin object1, final Termin object2) {
				
				return object1.getBeginndatum().compareTo(object2.getBeginndatum());
			}
		});
		return benachrTermine;
	}
	
	/** 
	 * Um die Gr��e der Benachrichtigungen als Zahl zu kriegen.
	 * @param benutzer
	 * 		Benutzer f�r die Benachrichtigungen
	 * @return
	 * 		Zahl der Benachrichtigungen
	 */
	
	public int sizeOfBenachrichtigungen(Privatnutzer benutzer)
	{
		ArrayList<Termin> benachrListe = getBenachrichtigungsTermine(benutzer);
		int i = benachrListe.size();
		return i;
	}
	
	
	/** 
	 * Funktion, die die Tage bis zum Termin zur�ckgibt. Gibt auch vergangene Termine (Negativwert) zur�ck.
	 * @param t
	 * 		Termin f�r die Berechnung der Tage
	 * @return
	 * 		Tage bis zum Termin
	 */
	
	public int tageBisTermin(Termin t)
	{
		Date start = t.getBeginndatum().getTime();
		Date heute = Calendar.getInstance().getTime();
		double diff = start.getTime() - heute.getTime();
		double days2 = (diff/(1000*60*60*24))+1;
		
		int day = (int) days2;
		
		return day;
	}
	
	
	/** 
	 * Funktion um Veranstaltungskalender per Id zurückzugeben.
	 * @param kalenderId
	 * 			Eindeutige Kalender Id
	 * @param benutzer
	 * 			Übergebendener Veranstalter
	 * @return
	 * 		Gibt einen Kalender der mit der übergeben Id übereinstimmt zurück. Falls nicht
	 * 		Vorhanden wird null returned.
	 */
	public Kalender getVeranstalterKalenderById(int kalenderId, Veranstalter benutzer ){
	    ArrayList<Kalender> kalenderlist = benutzer.getKalenderListe();
	    for(int i=0; i<kalenderlist.size();i++){
	    	Kalender a = kalenderlist.get(i);
	    	if(a.getKalenderId()==kalenderId){
	    		return a;
	    	}
	    } 
	    
	    return null;
	}
	
	/** 
	 * Funktion um alle Veranstaltungskalender zu bekommen
	 * @return
	 * 		Gibt eine ArrayList gefüllt mit allen Veranstaltungskalendern zurück
	 */
	public ArrayList<Kalender> getAllVeranKalender()
	{
		ArrayList<Benutzer> allBen = a.ListOfBenutzer();
		ArrayList<Kalender> allKal = new ArrayList<Kalender>();
		for(Benutzer b : allBen)
		{ 
			if(b instanceof Veranstalter)
			{
				allKal.addAll(((Veranstalter) b).getKalenderListe());
				
			}
		}
		return allKal;
	}
	
	
	/** 
	 * Funktion um Kalender per Id zurückzugeben.
	 * @param kalenderId
	 * 			Eindeutige Kalender Id
	 * @return
	 * 		Gibt einen Kalender der mit der übergeben Id übereinstimmt zurück. Falls nicht
	 * 		Vorhanden wird null returned.
	 */
	public Kalender getKalenderById(int kalenderId){
		ArrayList <Benutzer> benutzerlist = a.ListOfBenutzer();
		for(int i=0; i<benutzerlist.size(); i++){
			Benutzer a = benutzerlist.get(i);
			if(a instanceof Veranstalter){
				ArrayList <Kalender> kalenderliste = ((Veranstalter) a).getKalenderListe();
				for(int j=0; j<kalenderliste.size(); j++){
					Kalender neu = kalenderliste.get(j);
						if(neu.getKalenderId() == kalenderId){
							return neu;
						}
					}
				}
			}	
		
		
		return null;	
	
		
	}
	
	/** 
	 * Funktion um Termin per Id zurückzugeben.
	 * @param terminId
	 * 			Eindeutige Termin Id
	 * @param benutzer
	 * 			Übergebener Veranstalter
	 * @return
	 * 		Gibt einen Termin der mit der übergeben Id übereinstimmt zurück. Falls nicht
	 * 		Vorhanden wird null returned.
	 */
	public Termin getVeranstalterTerminById(int terminId, Veranstalter benutzer){
		ArrayList<Kalender> terminlist = benutzer.getKalenderListe();
		for(int j=0; j<terminlist.size();j++){
			Kalender k = terminlist.get(j);
			 for(int i=0; i<k.getTerminlist().size();i++){
				Termin t = k.getTerminlist().get(i);
				if(t.getTerminId()==terminId){
					return t;
				}
			}
		}		
		
		return null;
	}
	
	/** 
	 * Funktion um Privatbenutzer per Benutzernamen zurückzugeben.
	 * @param username
	 * 			Benutzername des Privatnutzers
	 * @return
	 * 		Gibt einen Privatbenutzer zurück falls ein vorhandener mit dem username übereinstimmt.
	 *  	Falls nicht Vorhanden wird null returned.
	 */
	public Benutzer getPrivatnutzerByUsername(String username)
	{
		ArrayList<Benutzer> allBenutzer = a.ListOfBenutzer();
		for(Benutzer benutzer : allBenutzer)
		{
			if(benutzer instanceof Privatnutzer)
			{
				if(benutzer.getBenutzername().equals(username))
				{
					return benutzer;
				}
			}
		}
		return null;
	}
	
	/** 
	 * Funktion um Veranstalter per Benutzernamen zurückzugeben.
	 * @param username
	 * 			Benutzername des Veranstalters
	 * @return
	 * 		Gibt einen Veranstalter zurück falls ein vorhandener mit dem username übereinstimmt.
	 *  	Falls nicht Vorhanden wird null returned.
	 */
	public Benutzer getVeranstalterByUsername(String username)
	{
		ArrayList<Benutzer> allBenutzer = a.ListOfBenutzer();
		for(Benutzer benutzer : allBenutzer){
			
			if(benutzer instanceof Veranstalter)
			{
			
				if(benutzer.getBenutzername().equals(username))
				{
					return benutzer;
				}	
			}
		}
			
		return null;
	}
	
	/** 
	 * Funktion zum hinzufügen von Terminen bei Veranstaltern
	 * @param termin
	 * 			Übergebener Termin der hinzugefügt werden soll
	 * @param benutzer
	 * 			Benutzername des Veranstalters
	 * @param kalenderId
	 * 			Eindeutige Kalender Id	 
	 */
	public void addTermin(Termin termin, Veranstalter benutzer, int kalenderId){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Termin> terminlist = getVeranstalterKalenderById(kalenderId, benutzer).getTerminlist();
	    		terminlist.add(termin);
	    		getVeranstalterKalenderById(kalenderId, benutzer).setTerminlist(terminlist);
	    		a.speichereBenutzer(benutzer);
	    		a.loescheBenutzer(b.getId());
	    	}
	    } 
	}
	
	/** 
	 * Funktion zum hinzufügen von Terminen bei Privatnutzer
	 * @param termin
	 * 			Übergebener Termin der hinzugefügt werden soll
	 * @param benutzer
	 * 			Benutzername des Veranstalters 
	 */
	public void addTermin(Termin termin, Privatnutzer benutzer){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(benutzer.getId()==b.getId()){
	    		ArrayList<Termin> terminlist = benutzer.getKalender().getTerminlist();
	    		terminlist.add(termin);
	    		benutzer.getKalender().setTerminlist(terminlist);
	    		a.speichereBenutzer(benutzer);
	    		a.loescheBenutzer(b.getId());
	    	}
	    } 
	}
	
	/** 
	 * Funktion zum löschen von Terminen bei Veranstaltern
	 * @param termin
	 * 			Übergebener Termin der hinzugefügt werden soll
	 * @param benutzer
	 * 			Benutzername des Veranstalters 
	 * @param kalenderId
	 * 			Eindeutige Kalender Id
	 */
	public void deleteTermin(int terminId, Veranstalter benutzer, int kalenderId){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Termin> terminlist = getVeranstalterKalenderById(kalenderId, benutzer).getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin t = terminlist.get(j);
	    			if(t.getTerminId()==terminId){
	    	    		terminlist.remove(t);
	    	    		getVeranstalterKalenderById(kalenderId, benutzer).setTerminlist(terminlist);
	    	    		a.speichereBenutzer(benutzer);
	    	    		a.loescheBenutzer(b.getId());
	    			}	
	    		}
	    	}
	    	if(b instanceof Privatnutzer){
	    		ArrayList<Termin> benTerminlist = ((Privatnutzer) b).getKalender().getTerminlist();
	    		ArrayList<Kalender> benOeffentlichKalenderlist = ((Privatnutzer) b).getKalenderlist();
	    		ArrayList<Termin> benOeffentlichTerminlist = ((Privatnutzer) b).getTerminlist();
	    		for(int j=0; j<benTerminlist.size(); j++){
	    			Termin benTermin = benTerminlist.get(j);
	    			if(benTermin.getTerminId() == terminId){
	    				benTerminlist.remove(benTermin);
	    				((Privatnutzer) b).getKalender().setTerminlist(benTerminlist);
	    	    		a.loescheBenutzer(b.getId());
	    	    		a.speichereBenutzer(b);
	    			}
	    		}
	    		for(int k=0; k<benOeffentlichKalenderlist.size(); k++){
	    			ArrayList<Termin> benOeKalenderTerminlist = benOeffentlichKalenderlist.get(k).getTerminlist();
	    			for(int l=0; l<benOeKalenderTerminlist.size(); l++){
	    				Termin benOeTermin = benOeKalenderTerminlist.get(l);
	    				if(benOeTermin.getTerminId() == terminId){
	    					benOeKalenderTerminlist.remove(benOeTermin);
	    					benOeffentlichKalenderlist.get(k).setTerminlist(benOeKalenderTerminlist);
		    	    		a.loescheBenutzer(b.getId());
		    	    		a.speichereBenutzer(b);
	    				}
	    			}
	    		}
	    		for(int m=0; m<benOeffentlichTerminlist.size(); m++){
	    			Termin oeTermin = benOeffentlichTerminlist.get(m);
	    			if(oeTermin.getTerminId() == terminId){
	    				benOeffentlichTerminlist.remove(oeTermin);
	    				((Privatnutzer) b).setTerminlist(benOeffentlichTerminlist);
	    	    		a.loescheBenutzer(b.getId());
	    	    		a.speichereBenutzer(b);
	    			}
	    		}
	    	}
	    } 	
	}
	
	/** 
	 * Funktion zum löschen von Terminen bei Privatnutzern
	 * @param terminid
	 * 			Eindeutige Termin Id
	 * @param benutzer
	 * 			Benutzername des Privatnutzers 
	 */
	public void deleteTermin(int terminId, Privatnutzer benutzer){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Termin> terminlist = benutzer.getKalender().getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin t = terminlist.get(j);
	    			if(t.getTerminId()==terminId){
	    	    		terminlist.remove(t);
	    	    		benutzer.getKalender().setTerminlist(terminlist);
	    	    		a.speichereBenutzer(benutzer);
	    	    		a.loescheBenutzer(b.getId());
	    			}	
	    		}
	    	}
	    } 	
	}
	
	/** 
	 * Funktion zum bearbeiten von Terminen bei Veranstaltern
	 * @param terminid
	 * 			Eindeutige Termin Id
	 * @param benutzer
	 * 			Benutzername des Veranstalters
	 * @param kalenderId
	 * 			Eindeutige Kalender Id
	 * @param termin
	 * 			Der zu bearbeitende Termin	 
	 */
	public void editTermin(int terminId, Veranstalter benutzer, int kalenderId, Termin termin){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Termin> terminlist = getVeranstalterKalenderById(kalenderId, benutzer).getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin t = terminlist.get(j);
	    			if(t.getTerminId()==terminId){
	    				int anzahlGeteilt = t.getAnzahlGeteilt();
	    				terminlist.remove(t);
	    				termin.setAnzahlGeteilt(anzahlGeteilt);
	    	    		terminlist.add(termin);
	    	    		getVeranstalterKalenderById(kalenderId, benutzer).setTerminlist(terminlist);
	    	    		a.speichereBenutzer(benutzer);
	    	    		a.loescheBenutzer(b.getId());
	    			}	
	    		}
	    	}
	    } 
	}
	
	/** 
	 * Funktion zum bearbeiten von Terminen bei Privatnutzern
	 * @param terminid
	 * 			Eindeutige Termin Id
	 * @param benutzer
	 * 			Benutzername des Privatnutzer
	 * @param kalenderId
	 * 			Eindeutige Kalender Id
	 * @param termin
	 * 			Der zu bearbeitende Termin	 
	 */
	public void editTermin(int terminId, Privatnutzer benutzer, Termin termin){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Termin> terminlist = benutzer.getKalender().getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin t = terminlist.get(j);
	    			if(t.getTerminId()==terminId){
	    				terminlist.remove(t);
	    	    		terminlist.add(termin);
	    	    		benutzer.getKalender().setTerminlist(terminlist);
	    	    		a.speichereBenutzer(benutzer);
	    	    		a.loescheBenutzer(b.getId());
	    			}	
	    		}
	    	}
	    } 
	}
	
	/** 
	 * Funktion um Termine von Veranstaltern per Id zurückzugeben
	 * @param terminid
	 * 			Eindeutige Termin Id
	 * @param benutzer
	 * 			Benutzername des Verantaltern
	 * @param kalenderId
	 * 			Eindeutige Kalender Id
	 * @return 
	 * 			Termin der mit der übergeben Termin Id übereinstimmt. Falls
	 * 			keine übereinstimmung gibt wird null returned
	 */
	public Termin showTermin(int terminId, Veranstalter benutzer, int kalenderId){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Termin> terminlist = getVeranstalterKalenderById(kalenderId, benutzer).getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin t = terminlist.get(j);
	    			if(t.getTerminId()==terminId){
	    	    		return t;
	    			}	
	    		}
	    	}
	    } 	
	    return null;
	}
	
	/** 
	 * Funktion um Termine von Privatnutzern per Id zurückzugeben
	 * @param terminid
	 * 			Eindeutige Termin Id
	 * @param benutzer
	 * 			Benutzername des Privatnutzers
	 * @return 
	 * 			Termin der mit der übergeben Termin Id übereinstimmt. Falls
	 * 			keine übereinstimmung gibt wird null returned
	 */
	public Termin showTermin(int terminId, Privatnutzer benutzer){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Termin> terminlist = benutzer.getKalender().getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin t = terminlist.get(j);
	    			if(t.getTerminId()==terminId){
	    	    		return t;
	    			}	
	    		}
	    	}
	    } 	
	    return null;
	}
	
	
	/** 
	 * Funktion um zum hinzufügen von Kalendern
	 * @param kalender
	 * 		Übergebener Kalender der angelegt werden soll
	 * @param benutzer
	 * 		Übergebener Veranstalter
	 */
	public void addKalender(Kalender kalender, Veranstalter benutzer){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Kalender> kalenderlist = benutzer.getKalenderListe();
	    		kalenderlist.add(kalender);
	    		benutzer.setKalenderListe(kalenderlist);
	    		a.speichereBenutzer(benutzer);
	    		a.loescheBenutzer(b.getId());
	    	}
	    } 
	}
	
	/** 
	 * Funktion um Kalender zu löschen.
	 * @param benutzer
	 * 		Übergebener Veranstalter
	 * @param kalenderId
	 * 		Eindeutige Kalender Id
	 */
	public void deleteKalender(int kalenderId, Veranstalter benutzer){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Kalender> kalenderlist = benutzer.getKalenderListe();
	    		for(int j=0; j<kalenderlist.size();j++){
	    			Kalender k = kalenderlist.get(j);
	    			if(k.getKalenderId()==kalenderId){
	    	    		ArrayList<Termin> terminliste = k.getTerminlist();
	    	    		for(int l=0; l<terminliste.size(); l++){
	    	    			Termin t = terminliste.get(l);
	    	    			int terminid = t.getTerminId();
	    	    			for(int m=0; m<benutzerlist.size(); m++){
	    	    				Benutzer ben = benutzerlist.get(m);
	    	    				if(ben instanceof Privatnutzer){
	    	    					ArrayList<Termin> benTerminlist = ((Privatnutzer) ben).getKalender().getTerminlist();
	    	    					ArrayList<Kalender> benOeffentlicheKalenderlist = ((Privatnutzer) ben).getKalenderlist();
	    	    					ArrayList<Termin> benOeffentlicheTerminlist = ((Privatnutzer) ben).getTerminlist();
	    	    					for(int n=0; n<benTerminlist.size(); n++){
	    	    						Termin benTermin = benTerminlist.get(n);
	    	    						if(benTermin.getTerminId() == terminid){
	    	    		    	    		benTerminlist.remove(benTermin);
	    	    		    	    		((Privatnutzer) ben).getKalender().setTerminlist(benTerminlist);
	    	    		    	    		a.loescheBenutzer(ben.getId());
	    	    		    	    		a.speichereBenutzer(ben);	
	    	    						}
	    	    					}
	    	    					for(int o=0; o<benOeffentlicheKalenderlist.size(); o++){
	    	    						Kalender benOeKalender = benOeffentlicheKalenderlist.get(o);
	    	    						if(benOeKalender.getKalenderId() == kalenderId){
	    	    							benOeffentlicheKalenderlist.remove(benOeKalender);
	    	    							((Privatnutzer) ben).setKalenderlist(benOeffentlicheKalenderlist);
	    	    		    	    		a.loescheBenutzer(ben.getId());
	    	    		    	    		a.speichereBenutzer(ben);
	    	    						}
	    	    					}
	    	    					for(int p=0; p<benOeffentlicheTerminlist.size(); p++){
	    	    						Termin benOeTermin = benOeffentlicheTerminlist.get(p);
	    	    						if(benOeTermin.getTerminId() == terminid){
	    	    							benOeffentlicheTerminlist.remove(benOeTermin);
	    	    		    	    		((Privatnutzer) ben).setTerminlist(benOeffentlicheTerminlist);
	    	    		    	    		a.loescheBenutzer(ben.getId());
	    	    		    	    		a.speichereBenutzer(ben);	
	    	    						}
	    	    					}
	    	    				}
	    	    			}
	    	    		}
	    				kalenderlist.remove(k);
	    	    		benutzer.setKalenderListe(kalenderlist);
	    	    		a.speichereBenutzer(benutzer);
	    	    		a.loescheBenutzer(b.getId());
	    			}	
	    		}
	    	}
	    } 
	}
	

	
	/** 
	 * Funktion zum Generieren von Eindeutigen Benutzer Id's
	 * @return
	 * 		liefert eine eindeutige Id als int zurück.
	 */
	public int generateBenutzerId(){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
		int id = (int)System.currentTimeMillis();
		for(int i=0; i<benutzerlist.size();i++){
			Benutzer b = benutzerlist.get(i);
			if(b.getId()==id){
				id++;
				i=-1;
			}
		}
		return id;
	}
	
	/** 
	 * Funktion zum Generieren von Eindeutigen Termin Id's
	 * @return
	 * 		liefert eine eindeutige Id als int zurück.
	 */
	public int generateBenutzerTerminId(){
		int id = (int)System.currentTimeMillis();
		ArrayList <Benutzer> benutzerlist = a.ListOfBenutzer();
		for(int i=0; i<benutzerlist.size(); i++){
			Benutzer a = benutzerlist.get(i);
			if(a instanceof Privatnutzer){
				ArrayList <Termin> terminliste = ((Privatnutzer) a).getKalender().getTerminlist();
				for(int j = 0; j<terminliste.size(); j++){
					Termin t = terminliste.get(j);
					if(t.getTerminId() == id){
						id++;
						i=-1;
					}
				}
			}else if(a instanceof Veranstalter){
				ArrayList <Kalender> kalenderliste = ((Veranstalter) a).getKalenderListe();
				for(int j=0; j<kalenderliste.size(); j++){
					ArrayList <Termin> terminliste = kalenderliste.get(j).getTerminlist();
					for(int k = 0; k<terminliste.size(); k++){
						Termin t = terminliste.get(k);
						if(t.getTerminId() == id){
							id++;
							i=-1;
						}
					}
				}
			}	
		}
		return id;
	}
	
	/** 
	 * Funktion zum Generieren von Eindeutigen Kalender Id's
	 * @return
	 * 		liefert eine eindeutige Id als int zurück.
	 */
	public int generateVeranstalterKalenderId(Veranstalter benutzer){
		int id = (int)System.currentTimeMillis();
		ArrayList <Benutzer> benutzerlist = a.ListOfBenutzer();
		for(int i=0; i<benutzerlist.size(); i++){
		Benutzer a = benutzerlist.get(i);
		if(a instanceof Veranstalter){
			ArrayList <Kalender> kalenderliste = ((Veranstalter) a).getKalenderListe();
			for(int j=0; j<kalenderliste.size(); j++){
				Kalender k = kalenderliste.get(j);
					if(k.getKalenderId() == id){
						id++;
						i=-1;
					}
				}
			}
		}
		return id;
	}
	
	/** 
	 * Funktion zum versenden von Termineneinladungen von Veranstalter an Privatbenutzer
	 * @param terminId
	 * 		eindeutige Termin Id
	 * @param benutzer
	 * 		Übergebener Privatnutzer, an dem der Termin gesendet wird
	 * @param ver
	 * 		Übergebene Veranstalter, der den Termin sendet
	 */
	public void sendTermin(int terminId, Privatnutzer benutzer, Veranstalter ver){
		
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId() == benutzer.getId()){
	    		Termin t = this.getVeranstalterTerminById(terminId, ver);
	    		ArrayList<Termin> terminlist = benutzer.getTerminlist();
	    		for(int u=0;u<terminlist.size();u++){
					Termin alt = terminlist.get(u);
					if(t.getTerminId() == alt.getTerminId()){
						
						return;
					}
				}
	    		terminlist.add(t);
	    		benutzer.setTerminlist(terminlist);
	    		a.speichereBenutzer(benutzer);
	    		a.loescheBenutzer(b.getId());
	    		}
	    }
		
	}
	
	/**
	 * Funktion um Termin per Id zurückzugeben.
	 * @param terminId
	 * 		Eindeutige Termin Id
	 * @return
	 *		Gibt einen Termin der mit der übergeben Id übereinstimmt zurück. Falls nicht
	 * 		Vorhanden wird null returned.
	 */
	public Termin getTerminById(int terminId){
		ArrayList <Benutzer> benutzerlist = a.ListOfBenutzer();
		for(int i=0; i<benutzerlist.size(); i++){
		Benutzer a = benutzerlist.get(i);
			if(a instanceof Veranstalter){
				ArrayList <Kalender> kalenderliste = ((Veranstalter) a).getKalenderListe();
				for(int j=0; j<kalenderliste.size(); j++){
					Kalender neu = kalenderliste.get(j);
					ArrayList<Termin> terminliste = neu.getTerminlist();
					
					for(int z=0; z<terminliste.size();z++){
						Termin t = terminliste.get(z);
						if(t.getTerminId() == terminId){
							return t;
							}
						}
					}
				}
			}	
		
		
		return null;
	}
	
	/**
	 * Funktion um Benutzer(Veranstalter) per Termin Id zurückzugeben
	 * @param terminId
	 * 		Eindeutige Termin Id
	 * @return
	 * 		Gibt einen Benutzer der mit der übergeben Id übereinstimmt zurück. Falls nicht
	 * 		Vorhanden wird null returned.
	 */		
	public Benutzer getBenuzterByTerminId(int terminId){
		ArrayList <Benutzer> benutzerlist = a.ListOfBenutzer();
		for(int i=0; i<benutzerlist.size(); i++){
		Benutzer a = benutzerlist.get(i);
			if(a instanceof Veranstalter){
				ArrayList <Kalender> kalenderliste = ((Veranstalter) a).getKalenderListe();
				for(int j=0; j<kalenderliste.size(); j++){
					Kalender neu = kalenderliste.get(j);
					ArrayList<Termin> terminliste = neu.getTerminlist();
					
					for(int z=0; z<terminliste.size();z++){
						Termin t = terminliste.get(z);
						if(t.getTerminId() == terminId){
							return a;
							}
						}
					}
				}
			}	
		
		
		return null;
	}
	
	/**
	 * Funktion zum zählen wie oft ein Termin geteilt wurde
	 * @param terminId
	 * 		Eindeutige Termin Id
	 */		
	public void zaehlen(int terminId){
	ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
    for(int i=0; i<benutzerlist.size();i++){
    	Benutzer b = benutzerlist.get(i);
    	Benutzer usersave = this.getBenuzterByTerminId(terminId);
    	if(b.getId() == usersave.getId()){
    	ArrayList<Kalender> userkalenderlist = ((Veranstalter) usersave).getKalenderListe();
    	for(int o=0;o<userkalenderlist.size();o++){
    		Kalender userkalender = userkalenderlist.get(o);
    		ArrayList<Termin> userterminlist = userkalender.getTerminlist();
    		for(int s=0;s<userterminlist.size();s++){
    			Termin usertermin = userterminlist.get(s);
    			if(usertermin.getTerminId() == terminId){
    				
    				Termin alt = this.getTerminById(terminId);
    				
    				int count = alt.getAnzahlGeteilt() + 1;
    				alt.setAnzahlGeteilt(count);

    				
    				userterminlist.remove(usertermin);
    				userterminlist.add(alt);
    				userkalender.setTerminlist(userterminlist);
    				a.speichereBenutzer(usersave);
    				a.loescheBenutzer(b.getId());
    				return;
    			}
    		}
    	}
    } 	
    		
    	
}
	
	
	
	}
	
	/**
	 * Funktion um Termine nach meisten geteilt zu erst zu sortieren
	 * @return
	 * 		geordnete Termine werden in einer ArrayList zurückgegeben nach meisten zuerst
	 */		
	public ArrayList<Termin> showTopTermin(){
		ArrayList<Termin> anzeigen = new ArrayList<Termin>();
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b instanceof Veranstalter){
	    		ArrayList<Kalender> kalenderliste = ((Veranstalter) b).getKalenderListe();
	    		for(int u=0;u<kalenderliste.size();u++){
	    			Kalender kalender = kalenderliste.get(u);
	    			ArrayList<Termin> terminliste = kalender.getTerminlist();
	    			for(int v=0;v<terminliste.size();v++){
	    				Termin t = terminliste.get(v);
	    				if(t.getAnzahlGeteilt() != 0){
	    					anzeigen.add(t);
	    				}
	    			}
	    		}
	    	}
	    }
		
		Collections.sort(anzeigen, new Comparator<Termin>() {
			@Override
			public int compare(final Termin object1, final Termin object2) {
				
				return object2.getAnzahlGeteilt() - object1.getAnzahlGeteilt();
			}
		});
		
		return anzeigen; 

		}
		

	
	
	/** 
	 * Funktion zum speichern von Terminen von Termineinladungen
	 * @param terminId
	 * 		eindeutige Termin Id
	 * @param benutzer
	 * 		Übergebener Privatnutzer
	 */
	public void saveTerminEinladung(int terminId, Privatnutzer benutzer){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Termin> terminList = benutzer.getTerminlist();
	    		for(int j=0; j<terminList.size();j++){
	    			Termin t = terminList.get(j);
	    			if(t.getTerminId()==terminId){
	    				ArrayList<Termin> terminlist = benutzer.getKalender().getTerminlist();
	    				for(int u=0;u<terminlist.size();u++){
	    					Termin alt = terminlist.get(u);
	    					if(t.getTerminId() == alt.getTerminId()){
	    						
	    						return;
	    					}
	    				}
	    				this.zaehlen(terminId);
	    				terminlist.add(t);
	    	    		benutzer.getKalender().setTerminlist(terminlist);
	    	    		terminList.remove(t);
	    	    		benutzer.setTerminlist(terminList);
	    	    		a.speichereBenutzer(benutzer);
	    	    		a.loescheBenutzer(b.getId());
	    			}
	    		}
	    	}
	    }
	}
	
	
	
	/** 
	 * Funktion zum löschen von Terminen von Termineinladungen
	 * @param terminId
	 * 		eindeutige Termin Id
	 * @param benutzer
	 * 		Übergebener Privatnutzer
	 */
	public void deleteTeminEinladung(int terminId, Privatnutzer benutzer){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Termin> terminList = benutzer.getTerminlist();
	    		for(int j=0; j<terminList.size();j++){
	    			Termin t = terminList.get(j);
	    			if(t.getTerminId()==terminId){
	    	    		terminList.remove(t);
	    	    		benutzer.setTerminlist(terminList);
	    	    		a.speichereBenutzer(benutzer);
	    	    		a.loescheBenutzer(b.getId());
	    			}	
	    		}
	    	}
	    } 
		
	}
	
	/** 
	 * Funktion zum versenden von Kalendern von Veranstaltern an Privatbenutzern
	 * @param kalenderId
	 * 		eindeutige Kalender Id
	 * @param benutzer
	 * 		Übergebener Privatnutzer, an dem der Termin gesendet wird
	 * @param ver
	 * 		Übergebene Veranstalter, der den Kalender sendet
	 */
	public void sendKalender(int kalenderId, Privatnutzer benutzer, Veranstalter ver){
		BenutzerManagement man = new BenutzerManagement();
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId() == benutzer.getId()){
	    		Kalender k = man.getVeranstalterKalenderById(kalenderId, ver);
	    		ArrayList<Kalender> kalenderlist = benutzer.getKalenderlist();
	    		for(int u=0;u<kalenderlist.size();u++){
					Kalender alt = kalenderlist.get(u);
					if(k.getKalenderId() == alt.getKalenderId()){
						
						return;
					}
				}
	    		kalenderlist.add(k);
	    		benutzer.setKalenderlist(kalenderlist);
	    		a.speichereBenutzer(benutzer);
	    		a.loescheBenutzer(b.getId());
	    		}
	    }
		
	}
	
	/** 
	 * Funktion zum speichern Terminen aus einem gesendeten Kalender
	 * @param kalenderId
	 * 		eindeutige Kalender Id
	 * @param benutzer
	 * 		Übergebener Privatnutzer, an dem der Kalender gesendet wurde
	 */
	public void saveKalenderEinladung(int kalenderId, Privatnutzer benutzer){

		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Kalender> kalenderlist = benutzer.getKalenderlist();
	    		for(int j=0; j<kalenderlist.size();j++){
	    			Kalender k = kalenderlist.get(j);
	    			if(k.getKalenderId()==kalenderId){
	    				ArrayList<Termin> terminlist = benutzer.getKalender().getTerminlist(); 
	    				ArrayList<Termin> termine = k.getTerminlist(); 
	    				ArrayList<Termin> templist = new ArrayList<Termin>();
	    				for(int a=0; a<terminlist.size();a++){
	    					Termin end = terminlist.get(a);
	    				for(int c=0; c<termine.size();c++){
	    					Termin zwi = termine.get(c);
	    					if(end.getTerminId() == zwi.getTerminId()){
	    						templist.add(zwi);
	    						
	    					}
	    				}
	    				}
	    				termine.removeAll(templist);
	    				for(int y=0;y<termine.size();y++){
	    					Termin v = termine.get(y);
	    	    		terminlist.add(v);
	    	    			this.zaehlen(v.terminId);
	    				}
	    				benutzer.getKalender().setTerminlist(terminlist);
	    	    		kalenderlist.remove(k);
	    	    		benutzer.setKalenderlist(kalenderlist);
	    	    		a.speichereBenutzer(benutzer);
	    	    		a.loescheBenutzer(b.getId());
	    			}	
	    		}
	    	}
	    }   
	
	}
	
	
	
	/** 
	 * Funktion zum löschen von Kalendereinladungen
	 * @param kalenderId
	 * 		eindeutige Kalender Id
	 * @param benutzer
	 * 		Übergebener Privatnutzer, an dem der Kalender gesendet wurde
	 */
	public void deleteKalenderEinladung(int kalenderId, Privatnutzer benutzer){
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getId()==benutzer.getId()){
	    		ArrayList<Kalender> kalenderlist = benutzer.getKalenderlist();
	    		for(int j=0; j<kalenderlist.size();j++){
	    			Kalender k = kalenderlist.get(j);
	    			if(k.getKalenderId()==kalenderId){
	    	    		kalenderlist.remove(k);
	    	    		benutzer.setKalenderlist(kalenderlist);
	    	    		a.speichereBenutzer(benutzer);
	    	    		a.loescheBenutzer(b.getId());
	    			}	
	    		}
	    	}
	    }   
	}
	
	/** 
	 * Funktion, die die Anzahl der Veranstalter berechnet
	 * @return
	 * 		Anzahl der Veranstalter
	 */
	public int berechnungVeranstalter(){
		int z = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
		for(Benutzer nutzer : benutzerlist){
			if(nutzer instanceof Veranstalter && nutzer.getStatus() == true){
				z++;
			}	
		}
		
		return z;
	}
	
	/** 
	 * Funktion, die die Anzahl der Privatnutzer berechnet
	 * @return
	 * 		Anzahl der Privatnutzer
	 */
	public int berechnungPrivatnutzer(){
		int z = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
		for(Benutzer nutzer : benutzerlist){
			if(nutzer instanceof Privatnutzer && nutzer.getStatus() == true){
				z++;
			}	
		}
		
		return z;
		
	}
	
	/** 
	 * Funktion, die die Anzahl der Analytiker berechnet
	 * @return
	 * 		Anzahl der Analytiker
	 */
	public int berechnungAnalytiker(){
		int z = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
		for(Benutzer nutzer : benutzerlist){
			if(nutzer instanceof Analytiker && nutzer.getStatus() == true){
				z++;
			}	
		}
		

		
		return z;
		
	}
	
	/** 
	 * Funktion, die die Anzahl der Kalender mit der Kategorie Kultur berechnet
	 * @return
	 * 		Anzahl der Kalender mit der Kategorie Kultur
	 */
	public int berechnungKalenderKultur(){
		int kultur = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
		
		 for(int i=0; i<benutzerlist.size();i++){
			Benutzer b = benutzerlist.get(i);
				if(b instanceof Veranstalter && b.getStatus() == true){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
					for(Kalender k: kalenderlist){
					if(k.getKategorie().equals(Kategorie.Kultur)){
						kultur++;
						}
					}
				}
	    		
		 }
		
		    	return kultur;
		    
	}
	
	/** 
	 * Funktion, die die Anzahl der Kalender mit der Kategorie Arbeit berechnet
	 * @return
	 * 		Anzahl der Kalender mit der Kategorie Arbeit
	 */
	public int berechnungKalenderArbeit(){
		int arbeit = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
		
		 for(int i=0; i<benutzerlist.size();i++){
			Benutzer b = benutzerlist.get(i);
			if(b instanceof Veranstalter && b.getStatus() == true){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
					for(Kalender k: kalenderlist){
					if(k.getKategorie().equals(Kategorie.Arbeit)){
						arbeit++;
						}
					}
	    		
	    		}
		 }
	
		    	return arbeit;
		    
	}
	
	/** 
	 * Funktion, die die Anzahl der Kalender mit der Kategorie Familie berechnet
	 * @return
	 * 		Anzahl der Kalender mit der Kategorie Familie
	 */
	public int berechnungKalenderFamilie(){
		int familie = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
		
		 for(int i=0; i<benutzerlist.size();i++){
			Benutzer b = benutzerlist.get(i);
			if(b instanceof Veranstalter && b.getStatus() == true){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
					for(Kalender k: kalenderlist){
					if(k.getKategorie().equals(Kategorie.Familie)){
						familie++;
						}
					}
	    		
	    		}
		 }
	
		    
		    	return familie;
		    
	}
	
	/** 
	 * Funktion, die die Anzahl der Kalender mit der Kategorie Freizeit berechnet
	 * @return
	 * 		Anzahl der Kalender mit der Kategorie Freizeit
	 */
	public int berechnungKalenderFreizeit(){
		int freizeit = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
		
		 for(int i=0; i<benutzerlist.size();i++){
			Benutzer b = benutzerlist.get(i);
			if(b instanceof Veranstalter && b.getStatus() == true){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
					for(Kalender k: kalenderlist){
					if(k.getKategorie().equals(Kategorie.Freizeit)){
						freizeit++;
						}
					}
	    		
	    		}
		 }
	
		    	return freizeit;
		    
	}
	
	/** 
	 * Funktion, die die Anzahl der Kalender mit der Kategorie Nachtleben berechnet
	 * @return
	 * 		Anzahl der Kalender mit der Kategorie Nachtleben
	 */
	public int berechnungKalenderNachtleben(){
		int nachtleben = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
		
		 for(int i=0; i<benutzerlist.size();i++){
			Benutzer b = benutzerlist.get(i);
			if(b instanceof Veranstalter && b.getStatus() == true){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
					for(Kalender k: kalenderlist){
					if(k.getKategorie().equals(Kategorie.Nachtleben)){
						nachtleben++;
						}
					}
	    		
	    		}
		 }

		    	return nachtleben;
		    
	}
	
	/** 
	 * Funktion, die die Anzahl der Kalender mit der Kategorie Politik berechnet
	 * @return
	 * 		Anzahl der Kalender mit der Kategorie Politik
	 */
	public int berechnungKalenderPolitik(){
		int politik = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
		
		 for(int i=0; i<benutzerlist.size();i++){
			Benutzer b = benutzerlist.get(i);
			if(b instanceof Veranstalter && b.getStatus() == true){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
					for(Kalender k: kalenderlist){
					if(k.getKategorie().equals(Kategorie.Politik)){
						politik++;
						}
					}
	    		
	    		}
		 }

		    
		    	return politik;
		    
	}
	
	/** 
	 * Funktion, die die Anzahl der Kalender mit der Kategorie Sport berechnet
	 * @return
	 * 		Anzahl der Kalender mit der Kategorie Sport
	 */
	public int berechnungKalenderSport(){
		int sport = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
		
		 for(int i=0; i<benutzerlist.size();i++){
			Benutzer b = benutzerlist.get(i);
			if(b instanceof Veranstalter && b.getStatus() == true){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
					for(Kalender k: kalenderlist){
					if(k.getKategorie().equals(Kategorie.Sport)){
						sport++;
						}
					}
	    		
	    		}
		 }

		    	return sport;
		    
	}
	
	/** 
	 * Funktion, die die Anzahl aller Kalender berechnet
	 * @return
	 * 		Anzahl aller Kalender
	 */
	public int berechnungKalender(){
		int veran = 0;
		int privat = 0;
		int sum = 0;
		
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
		
		 for(int i=0; i<benutzerlist.size();i++){
			Benutzer b = benutzerlist.get(i);
			if(b instanceof Veranstalter){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
	    		for(int j=0; j<kalenderlist.size();j++){
	    			veran++;
	    			}
			}
		 }
		 
		for(int y=0; y<benutzerlist.size();y++){
			Benutzer a = benutzerlist.get(y);
			if(a instanceof Privatnutzer){
				privat++;
			}
		} 
		 	sum = veran + privat;

		    return sum;
		    
		
	}
	
	/** 
	 * Funktion, die die Anzahl aller Termine berechnet
	 * @return
	 * 		Anzahl aller Termine
	 */
	public int berechnungTermin(){
		int privat = 0;
		int veran = 0;
		int sum = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();  
		
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Privatnutzer){
	    		ArrayList<Termin> terminlist = ((Privatnutzer) b).getKalender().getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			privat++;
	    			}
	    	}
	    }
	    for(int u=0; u<benutzerlist.size();u++){
	    	Benutzer c = benutzerlist.get(u);
	    	if(c.getStatus() == true && c instanceof Veranstalter){
	    		ArrayList<Kalender> kalenderliste = ((Veranstalter) c).getKalenderListe();
	    		for(int v=0;v<kalenderliste.size();v++){
	    			Kalender kalender = kalenderliste.get(v);
	    			ArrayList<Termin> term = kalender.getTerminlist();
	    			for(int n=0;n<term.size();n++){
	    				veran++;
	    			}
	    		}
	    	}
	    }
	    
	    sum = veran + privat;
	    

	    return sum;
	    
		
	
	}
	
	/** 
	 * Funktion, die die Anzahl aller Termine mit der Kategorgie Kultur berechnet
	 * @return
	 * 		Anzahl aller Termine mit der Kategorie Kultur
	 */
	public int berechnungKultur(){
		int kultur = 0;
		int privat = 0;
		int sum = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();  
		
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Veranstalter){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
				for(int j=0; j<kalenderlist.size();j++){
					Kalender k = kalenderlist.get(j);
					for(int u=0; u<k.getTerminlist().size();u++){	
						Termin t = k.getTerminlist().get(u);	
						if(t.getKategorie().equals(Kategorie.Kultur)){
							kultur++;
						}
					}
	    		}
	    	}
	    } 
	    
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Privatnutzer){
	    		ArrayList<Termin> terminlist = ((Privatnutzer) b).getKalender().getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin user = terminlist.get(j);
	    			if(user.getKategorie() != null){
	    			if(user.getKategorie().equals(Kategorie.Kultur)){
	    				privat++;
	    			}
	    		  }
	    		}
	    	}
	    }
	    
	    sum = privat + kultur;
	    

	    
	    return sum;
	    
		
	}
	
	/** 
	 * Funktion, die die Anzahl aller Termine mit der Kategorgie Sport berechnet
	 * @return
	 * 		Anzahl aller Termine mit der Kategorie Sport
	 */
	public int berechnungSport(){
		int sport = 0;
		int privat = 0;
		int sum = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();  
		
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Veranstalter){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
				for(int j=0; j<kalenderlist.size();j++){
					Kalender k = kalenderlist.get(j);
					 
					for(int u=0; u<k.getTerminlist().size();u++){
						
						Termin t = k.getTerminlist().get(u);
						
						if(t.getKategorie().equals(Kategorie.Sport)){
							sport++;
						}
						}
	    			}
	    	}
	    } 
	    
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Privatnutzer){
	    		ArrayList<Termin> terminlist = ((Privatnutzer) b).getKalender().getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin user = terminlist.get(j);
	    			if(user.getKategorie() != null){
	    			if(user.getKategorie().equals(Kategorie.Sport)){
	    				privat++;
	    			}
	    		}}
	    	}
	    }
	    
	    sum = privat + sport;
	    

	    return sum;
	    
		
		
	}
	
	/** 
	 * Funktion, die die Anzahl aller Termine mit der Kategorgie Familie berechnet
	 * @return
	 * 		Anzahl aller Termine mit der Kategorie Familie
	 */
	public int berechnungFamilie(){
		int familie = 0;
		int privat = 0;
		int sum = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();  
		
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Veranstalter){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
				for(int j=0; j<kalenderlist.size();j++){
					Kalender k = kalenderlist.get(j);
					 
					for(int u=0; u<k.getTerminlist().size();u++){
						
						Termin t = k.getTerminlist().get(u);
						
						if(t.getKategorie().equals(Kategorie.Familie)){
							familie++;
						}
						}
	    			}
	    	}
	    } 
	    
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Privatnutzer){
	    		ArrayList<Termin> terminlist = ((Privatnutzer) b).getKalender().getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin user = terminlist.get(j);
	    			if(user.getKategorie() != null){
	    			if(user.getKategorie().equals(Kategorie.Familie)){
	    				privat++;
	    			}
	    		}}
	    	}
	    }
	    
	    sum = privat + familie;
	    

	    return sum;
	    
		
	}
	
	/** 
	 * Funktion, die die Anzahl aller Termine mit der Kategorgie Politik berechnet
	 * @return
	 * 		Anzahl aller Termine mit der Kategorie Politik
	 */
	public int berechnungPolitik(){
		int politik = 0;
		int privat = 0;
		int sum = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();  
		
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Veranstalter){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
				for(int j=0; j<kalenderlist.size();j++){
					Kalender k = kalenderlist.get(j);
					 
					for(int u=0; u<k.getTerminlist().size();u++){
						
						Termin t = k.getTerminlist().get(u);
						
						if(t.getKategorie().equals(Kategorie.Politik)){
							politik++;
						}
						}
	    			}
	    	}
	    } 
	    
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Privatnutzer){
	    		ArrayList<Termin> terminlist = ((Privatnutzer) b).getKalender().getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin user = terminlist.get(j);
	    			if(user.getKategorie() != null){
	    			if(user.getKategorie().equals(Kategorie.Politik)){
	    				privat++;
	    			}
	    		}}
	    	}
	    }
	    
	    sum = privat + politik;
	    

	    return sum;
	    
		
		
	}
	
	/** 
	 * Funktion, die die Anzahl aller Termine mit der Kategorgie Freizeit berechnet
	 * @return
	 * 		Anzahl aller Termine mit der Kategorie Freizeit
	 */
	public int berechnungFreizeit(){
		int freizeit = 0;
		int privat = 0;
		int sum = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();  
		
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Veranstalter){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
				for(int j=0; j<kalenderlist.size();j++){
					Kalender k = kalenderlist.get(j);
					 
					for(int u=0; u<k.getTerminlist().size();u++){
						
						Termin t = k.getTerminlist().get(u);
						
						if(t.getKategorie().equals(Kategorie.Freizeit)){
							freizeit++;
						}
						}
	    			}
	    	}
	    } 
	    
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Privatnutzer){
	    		ArrayList<Termin> terminlist = ((Privatnutzer) b).getKalender().getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin user = terminlist.get(j);
	    			if(user.getKategorie() != null){
	    			if(user.getKategorie().equals(Kategorie.Freizeit)){
	    				privat++;
	    			}
	    		}}
	    	}
	    }
	    
	    sum = privat + freizeit;
	    

	    return sum;
	    
		
		
	}
	
	/** 
	 * Funktion, die die Anzahl aller Termine mit der Kategorgie Nachtleben berechnet
	 * @return
	 * 		Anzahl aller Termine mit der Kategorie Nachleben
	 */
	public int berechnungNachtleben(){
		int nl = 0;
		int privat = 0;
		int sum = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();  
		
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Veranstalter){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
				for(int j=0; j<kalenderlist.size();j++){
					Kalender k = kalenderlist.get(j);
					 
					for(int u=0; u<k.getTerminlist().size();u++){
						
						Termin t = k.getTerminlist().get(u);
						
						if(t.getKategorie().equals(Kategorie.Nachtleben)){
							nl++;
						}
						}
	    			}
	    	}
	    } 
	    
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Privatnutzer){
	    		ArrayList<Termin> terminlist = ((Privatnutzer) b).getKalender().getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin user = terminlist.get(j);
	    			if(user.getKategorie() != null){
	    			if(user.getKategorie().equals(Kategorie.Nachtleben)){
	    				privat++;
	    			}
	    		}}
	    	}
	    }
	    
	    sum = privat + nl;
	    

	    return sum;
	    
		
	}
	
	/** 
	 * Funktion, die die Anzahl aller Termine mit der Kategorgie Arbeit berechnet
	 * @return
	 * 		Anzahl aller Termine mit der Kategorie Arbeit
	 */
	public int berechnungArbeit(){
		int arbeit = 0;
		int privat = 0;
		int sum = 0;
		ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();  
		
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Veranstalter){
				ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
				for(int j=0; j<kalenderlist.size();j++){
					Kalender k = kalenderlist.get(j);
					 
					for(int u=0; u<k.getTerminlist().size();u++){
						
						Termin t = k.getTerminlist().get(u);
						
						if(t.getKategorie().equals(Kategorie.Arbeit)){
							arbeit++;
						}
						}
	    			}
	    	}
	    } 
	    
	    for(int i=0; i<benutzerlist.size();i++){
	    	Benutzer b = benutzerlist.get(i);
	    	if(b.getStatus() == true && b instanceof Privatnutzer){
	    		ArrayList<Termin> terminlist = ((Privatnutzer) b).getKalender().getTerminlist();
	    		for(int j=0; j<terminlist.size();j++){
	    			Termin user = terminlist.get(j);
	    			if(user.getKategorie() != null){
	    			if(user.getKategorie().equals(Kategorie.Arbeit)){
	    				privat++;
	    			}
	    		}}
	    	}
	    }
	    
	    sum = privat + arbeit;
	    

	    return sum;
	    
		
	}
	
	/** 
	 * Funktion um Termine alphabetisch zu sortieren
	 * @param	benutzer
	 * 		Übergebener Privatnutzer, von jenen die Termine Sortiert werden sollen
	 * @return
	 * 		alphabetisch geordnete Termine zurückgegeben in einer ArrayList 
	 */
	public ArrayList<Termin> sortierenAlphabetisch(Privatnutzer benutzer){
		ArrayList<Termin> terminliste = benutzer.getKalender().getTerminlist();
	
		Collections.sort(terminliste, new Comparator<Termin>() {
			@Override
			public int compare(final Termin object1, final Termin object2) {
				return object1.getName().toLowerCase().compareTo(object2.getName().toLowerCase());
			}
		});
		return terminliste;
	}
	
	/** 
	 * Funktion um Termine alphabetisch zu sortieren
	 * @param	kalenderId
	 * 		Eindeutige Kalender Id, um den zu sortierenden Kalender zu bestimmen
	 * @param	benutzer
	 * 		Übergebener Veranstalter, von jenen die Termine Sortiert werden sollen
	 * @return
	 * 		alphabetisch geordnete Termine zurückgegeben in einer ArrayList 
	 */
	public ArrayList<Termin> sortierenAlphabetisch(int kalenderId ,Veranstalter benutzer){
		ArrayList<Kalender> kalenderliste = benutzer.getKalenderListe();
		ArrayList<Termin> terminliste = new ArrayList<Termin>();
			for(int i=0; i<kalenderliste.size(); i++){
				Kalender k = kalenderliste.get(i);
				if(k.getKalenderId()==kalenderId){
					terminliste = k.getTerminlist();
				}
			}
		Collections.sort(terminliste, new Comparator<Termin>() {
			@Override
			public int compare(final Termin object1, final Termin object2) {
				return object1.getName().toLowerCase().compareTo(object2.getName().toLowerCase());
			}
		});
		return terminliste;
	}
	
	/** 
	 * Funktion um Termine nach frühersten zu erst zu sortieren
	 * @param	benutzer
	 * 		Übergebener Privatnutzer, von jenen die Termine Sortiert werden sollen
	 * @return
	 * 		geordnete Termine werden in einer ArrayList zurückgegeben nach frühersten zu erst
	 */
	public ArrayList<Termin> sortierenNeuerstesDatum(Privatnutzer benutzer){
		ArrayList<Termin> terminliste = benutzer.getKalender().getTerminlist();
	
		Collections.sort(terminliste, new Comparator<Termin>() {
			@Override
			public int compare(final Termin object1, final Termin object2) {
				
				return object1.getBeginndatum().compareTo(object2.getBeginndatum());
			}
		});
		return terminliste;
	}
	
	/** 
	 * Funktion um Termine nach frühersten zu erst zu sortieren
	 * @param	kalenderId
	 * 		Eindeutige Kalender Id, um den zu sortierenden Kalender zu bestimmen
	 * @param	benutzer
	 * 		Übergebener Veranstalter, von jenen die Termine Sortiert werden sollen
	 * @return
	 * 		geordnete Termine werden in einer ArrayList zurückgegeben nach frühersten zu erst
	 */
	public ArrayList<Termin> sortierenNeuerstesDatum(int kalenderId, Veranstalter benutzer){
		ArrayList<Kalender> kalenderliste = benutzer.getKalenderListe();
		ArrayList<Termin> terminliste = new ArrayList<Termin>();
			for(int i=0; i<kalenderliste.size(); i++){
				Kalender k = kalenderliste.get(i);
				if(k.getKalenderId()==kalenderId){
					terminliste = k.getTerminlist();
				}
			}
		Collections.sort(terminliste, new Comparator<Termin>() {
			@Override
			public int compare(final Termin object1, final Termin object2) {
				
				return object1.getBeginndatum().compareTo(object2.getBeginndatum());
			}
		});
		return terminliste;
	}
	
	/** 
	 * Funktion um Termine nach spätesten zu erst zu sortieren
	 * @param	benutzer
	 * 		Übergebener Privatnutzer, von jenen die Termine Sortiert werden sollen
	 * @return
	 * 		geordnete Termine werden in einer ArrayList zurückgegeben nach spätesten zu erst
	 */
	public ArrayList<Termin> sortierenAeltestesDatum(Privatnutzer benutzer){
		ArrayList<Termin> terminliste = benutzer.getKalender().getTerminlist();
		
		Collections.sort(terminliste, new Comparator<Termin>() {
			@Override
			public int compare(final Termin object1, final Termin object2) {
				
				return object2.getBeginndatum().compareTo(object1.getBeginndatum());
			}
		});
		return terminliste;
	}
	
	/** 
	 * Funktion um Termine nach spätesten zu erst zu sortieren
	 * @param	kalenderId
	 * 		Eindeutige Kalender Id, um den zu sortierenden Kalender zu bestimmen
	 * @param	benutzer
	 * 		Übergebener Veranstalter, von jenen die Termine Sortiert werden sollen
	 * @return
	 * 		geordnete Termine werden in einer ArrayList zurückgegeben nach spätesten zu erst
	 */
	public ArrayList<Termin> sortierenAeltestesDatum(int kalenderId, Veranstalter benutzer){
		ArrayList<Kalender> kalenderliste = benutzer.getKalenderListe();
		ArrayList<Termin> terminliste = new ArrayList<Termin>();
			for(int i=0; i<kalenderliste.size(); i++){
				Kalender k = kalenderliste.get(i);
				if(k.getKalenderId()==kalenderId){
					terminliste = k.getTerminlist();
				}
			}
		Collections.sort(terminliste, new Comparator<Termin>() {
			@Override
			public int compare(final Termin object1, final Termin object2) {
				
				return object2.getBeginndatum().compareTo(object1.getBeginndatum());
			}
		});
		return terminliste;
	}
}



	
