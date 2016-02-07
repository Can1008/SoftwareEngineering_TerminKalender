package swe.terminkalender.servlet;


import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe.terminkalender.model.*;


/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
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


		boolean drinnen = false;
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		String pwd_w = request.getParameter("passwordw");
		String page = "registration_privatnutzer.jsp";
		
		
		SerializedBenutzerDAO b = new SerializedBenutzerDAO();
		ArrayList <Benutzer> benutzerList = b.ListOfBenutzer();
		
		for (int i = 0; i < benutzerList.size ( ); i++){
			Benutzer v = benutzerList.get(i);
				
			if(v.getBenutzername().equals(username)){
				
			drinnen = true;
			RequestDispatcher rd = request.getRequestDispatcher("registration_privatnutzer.jsp");
			rd.forward(request, response);
			return;
			}
		}
		
			if("admin".equals(username) || username.trim().equals("") || username.isEmpty()){
				
				drinnen = true;
				page ="registration_privatnutzer.jsp";
			}
		
			
		
			if(!pwd.equals(pwd_w) || pwd.trim().equals("") || pwd.isEmpty()){
			
	    	drinnen = true;
	    	page ="registration_privatnutzer.jsp";
	    	
			}
			
			
			
			if(drinnen == false)
			{
			
			BenutzerManagement man = new BenutzerManagement();	
			SerializedBenutzerDAO a = new SerializedBenutzerDAO();
			int id = man.generateBenutzerId();
			
			
			
			Privatnutzer benutzer = new Privatnutzer(username,pwd,true,id);
			benutzer.setStatus(true);
			ArrayList<Kalender> kal = new ArrayList<Kalender>();
			ArrayList<Termin> tal = new ArrayList<Termin>();
			Kalender kalender = new Kalender("MyCal",Kategorie.Arbeit, 1);
			kal.add(kalender);
			kal.remove(kalender);
			Calendar beginncal = Calendar.getInstance();
			Calendar endcal = Calendar.getInstance();
			Termin t = new Termin("Test","Wien","Jetzt",beginncal,endcal,100);
			tal.add(t);
			tal.remove(t);
			benutzer.setTerminlist(tal);
			benutzer.setKalenderlist(kal);
			benutzer.setKalender(kalender);
			
			a.speichereBenutzer(benutzer);

		    page= "login_sign_up.jsp";	
			}	

		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
