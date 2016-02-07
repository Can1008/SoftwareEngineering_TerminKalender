package swe.terminkalender.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe.terminkalender.model.*;

/**
 * Servlet implementation class RegistrationServlet_Veranstalter_Analytiker
 */
@WebServlet("/RegistrationServlet_Veranstalter_Analytiker")
public class RegistrationServlet_Veranstalter_Analytiker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean vorhanden = false;
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String passwordwiederholen = request.getParameter("passwordwiederholen");
		String company = request.getParameter("company");
		String email = request.getParameter("email");
		String auswahl = request.getParameter("test");
		String page= "";
		
		SerializedBenutzerDAO c = new SerializedBenutzerDAO();
		ArrayList <Benutzer> benutzerList = c.ListOfBenutzer();
		
		for (int i = 0; i < benutzerList.size ( ); i++){
			Benutzer us = benutzerList.get(i);
			if(us.getBenutzername().equals(name) || "admin".equals(name)){
				
			
			vorhanden = true;
			page= "registration_veranstalter_analytiker.jsp";}
	        
			}
		
		if("admin".equals(name) || name.trim().equals("") || name.isEmpty()){
			
			vorhanden = true;
			page= "registration_veranstalter_analytiker.jsp";
		}
		
		if(!password.equals(passwordwiederholen) || password.trim().equals("") || password.isEmpty()){
			
			vorhanden = true;
			page= "registration_veranstalter_analytiker.jsp";
			
		}
		
		BenutzerManagement man = new BenutzerManagement();	
		SerializedBenutzerDAO a = new SerializedBenutzerDAO();
		int id = man.generateBenutzerId();
		
		if("veranstalter".equals(auswahl) && (vorhanden == false)){
			
			ArrayList <Kalender> cal = new ArrayList<Kalender>();
			
			Veranstalter ver = new Veranstalter(name,password,false,id,company,email,cal);
			int idk = man.generateVeranstalterKalenderId(ver);
			Kalender kalender = new Kalender("MyCal", Kategorie.Arbeit, idk); 
			Termin t = new Termin("Test","Wien","Jetzt",null,null,100);
			man.addTermin(t, ver, idk);
			cal.add(kalender);
			cal.remove(kalender);
			ver.setKalenderListe(cal);
			a.speichereBenutzer(ver);
			page = "login_sign_up.jsp";
			
		}
		else if("analytiker".equals(auswahl) && (vorhanden == false)){
			Analytiker ana = new Analytiker(name,password,false,id,company,email);
			a.speichereBenutzer(ana);
			page = "login_sign_up.jsp";
			
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);	
	}

}
