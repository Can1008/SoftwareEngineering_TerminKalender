package swe.terminkalender.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe.terminkalender.model.*;

/**
 * Servlet implementation class OverviewPrivatnutzerEditServlet
 */
@WebServlet("/OverviewPrivatnutzerEditServlet")
public class OverviewPrivatnutzerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OverviewPrivatnutzerEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("loggedInPrivate") == null){
			RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
			rd.forward(request, response);
		}
		String buttonPressed = request.getParameter("button");
		String button = request.getParameter("editdelete");
		
		String username = request.getSession().getAttribute("loggedInPrivate").toString();
		BenutzerManagement man = new BenutzerManagement();
		Privatnutzer benutzer = (Privatnutzer) man.getPrivatnutzerByUsername(username);

		String testId = request.getParameter("name");
		
		if(buttonPressed.equals("Bestätigen")){
		
			if (button != null && testId != null){
				if(button.equals("edit")){
					int id = Integer.parseInt(request.getParameter("name")); 
					request.getSession(true).setAttribute("TerminID", id);
					Termin termin = man.showTermin(id, benutzer);
					String name = termin.getName();
					request.getSession(true).setAttribute("TerminName", name);
					
					String ort = termin.getOrt();
					request.getSession(true).setAttribute("TerminOrt", ort);
					
					String beschreibung = termin.getNotiz();
					request.getSession(true).setAttribute("TerminBeschreibung", beschreibung);
					
					Calendar beginndatum = termin.getBeginndatum();
					Calendar enddatum = termin.getEnddatum();
					
					Date date = beginndatum.getTime(); 
					Date date1 = enddatum.getTime();
					SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");          
					String beginndatstring = null;
					String enddatstring = null;
					beginndatstring = format1.format(date);
					enddatstring = format1.format(date1);
					request.getSession(true).setAttribute("beginndatumEdit", beginndatstring);
					request.getSession(true).setAttribute("enddatumEdit", enddatstring);
	
					RequestDispatcher rd = request.getRequestDispatcher("editTerminPrivatnutzer.jsp");
					rd.forward(request, response);
					
				}else if(button.equals("delete")){
					int id = Integer.parseInt(request.getParameter("name")); 
					man.deleteTermin(id, benutzer);
					RequestDispatcher rd = request.getRequestDispatcher("overview_privatnutzer.jsp");
					rd.forward(request, response);
				}else{
					
					RequestDispatcher rd = request.getRequestDispatcher("overview_privatnutzer.jsp");
					rd.forward(request, response);
				}
			}else{
			RequestDispatcher rd = request.getRequestDispatcher("overview_privatnutzer.jsp");
			rd.forward(request, response);
			}
		}else if(buttonPressed.equals("Sortieren")){
				String valueDropdown = request.getParameter("dropdownSort");
				ArrayList<Termin> terminlist = new ArrayList<Termin>();
				if(valueDropdown.equals("alphabetisch")){
					terminlist = man.sortierenAlphabetisch(benutzer);
					request.getSession(true).setAttribute("terminlistSort", terminlist);
				}else if(valueDropdown.equals("datAuf")){
					terminlist = man.sortierenNeuerstesDatum(benutzer);
					request.getSession(true).setAttribute("terminlistSort", terminlist);
				}else if(valueDropdown.equals("datAb")){
					terminlist = man.sortierenAeltestesDatum(benutzer);
					request.getSession(true).setAttribute("terminlistSort", terminlist);
				}
				RequestDispatcher rd = request.getRequestDispatcher("overview_privatnutzer_sortiert.jsp");
				rd.forward(request, response);
			}	
		}
	}

