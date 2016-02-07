package swe.terminkalender.model;

import java.io.*;
import java.util.*;

/**
 * SerializedBenutzerDAO implementiert das BenutzerDAO interface. Die Klasse ist 
 * für die Serializierung der Benutzer zuständig.
 * @author Daniel Hanzer, Michael Schneider, Can Özkan
 * @version 1.0; 12/01/2016
 */
public class SerializedBenutzerDAO implements BenutzerDAO{
	
	/** Pfad auf dem die Datei gespeichert wird */
	private String path = (System.getProperty("catalina.base") + "/userDB.ser");
	
	/** 
	 * Liste von allen Gespeicherten Benutzern
	 * 
	 * @return ArrayList von allen Benutzern
	 */
	@Override
	public ArrayList<Benutzer> ListOfBenutzer()
	{
		ArrayList<Benutzer> nutzerlist = new ArrayList<Benutzer>();
	    FileInputStream is = null;
	    
		try {
			is = new FileInputStream(path);
	        while (true) {
	           ObjectInputStream os = new ObjectInputStream(is);
	            nutzerlist.add((Benutzer)os.readObject());
	            }
		} catch (IOException e) {
	        
		} catch (ClassNotFoundException e) {
			System.err.println(e);
	    } finally {
	    	try {
	    	if (is != null)
	    		is.close();
		    } catch (Exception e) {
			}
	    }
		
		
		return nutzerlist;
	}
	

	
	
	/** 
	 * Funktion zum Speichern von Benutzern 
	 * @param benutzer
	 * 			Der zu Speichernde Benutzer
	 */
	@Override
	public void speichereBenutzer(Benutzer benutzer) {
		ArrayList<Benutzer> arrlist = new ArrayList<Benutzer>();
	    FileInputStream is = null;
		try {
			is = new FileInputStream(path);
	        while (true) {
	           ObjectInputStream os = new ObjectInputStream(is);
	            arrlist.add((Benutzer)os.readObject());
	            }
		} catch (IOException e) {
	        if(is == null){				
	        	arrlist.add((Benutzer) benutzer);
	        }
		} catch (ClassNotFoundException e) {
			System.err.println(e);
	    } finally {
	    	try {
	    	if (is != null)
	    		is.close();
		    } catch (Exception e) {
			}
	    }
	    if (is != null){
	    arrlist.add((Benutzer) benutzer);
	    }
		int sizeArrlist = arrlist.size();			
		FileOutputStream fos = null;
	    try {
	        fos = new FileOutputStream(path);
	        for (int i=0; i<sizeArrlist;i++){
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            Benutzer b = arrlist.get(i);
	            oos.writeObject(b);
	        }
	    } catch (IOException e) {
	    } finally {
	    	try {
	        if (fos != null)
	            fos.close();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }	
	}

	/** 
	 * Funktion zum löschen eines Benutzer
	 * @param id
	 * 			Benutzer Id des zu löschenden Benutzers
	 */
	@Override
	public void loescheBenutzer(int id) {
		  ArrayList<Benutzer> arrlist = new ArrayList<Benutzer>();
		    FileInputStream is = null;
			try {
				is = new FileInputStream(path);
		        while (true) {
		           ObjectInputStream os = new ObjectInputStream(is);
		            arrlist.add((Benutzer)os.readObject());
		            }
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
				System.err.println(e);
		    } finally {
		    	try {
		    	if (is != null)
		    		is.close();
			    } catch (Exception e) {
				}
		    }
		    int sizeArrlist = arrlist.size();
		    for(int i=0; i<sizeArrlist;i++){
		    	Benutzer a = arrlist.get(i);
		    	if(id == a.getId()){
		    		arrlist.remove(i);
		    		break;
		    	}
		    }
			sizeArrlist = arrlist.size();
		    FileOutputStream fos = null;
		    try {
		        fos = new FileOutputStream(path);
		        for (int i=0; i<sizeArrlist;i++){
		            ObjectOutputStream oos = new ObjectOutputStream(fos);
		            Benutzer b = arrlist.get(i);
		            oos.writeObject(b);
		        }
		    } catch (IOException e) {
		    } finally {
		    	try {
		        if (fos != null)
		            fos.close();
		    	} catch (Exception e) {
		    		e.printStackTrace();
		    	}
		    }
	}
	
	
}

