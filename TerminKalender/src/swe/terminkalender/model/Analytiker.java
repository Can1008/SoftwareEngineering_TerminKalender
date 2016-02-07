package swe.terminkalender.model;

/**
 * Analytiker erbt von der Abstrakten Klasse Benutzer. Analytiker haben keinen eigenen Kalender
 * können aber exklusiv auf Statistiken zugreifen. Der Administrator muss nach der Registration
 * jenen Analytiker bestätigen, damit dieser sich einloggen kann.
 * @author Daniel Hanzer, Michael Schneider, Can Özkan
 * @version 1.0; 12/01/2016
 */
public class Analytiker extends Benutzer{
	
	/** Varibale um den Analytiker Serializieren zu können */
	private static final long serialVersionUID = 1L;
	
	/** Firma des Analytikers */
	private String firma;
	
	/** E-mail Adresse des Analytikers */
	private String email;
	
	public Analytiker(String benutzername, String passwort, boolean status, int id, String firma, String email){
		super(benutzername, passwort, status, id);
		this.firma = firma; this.email = email;
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

}
