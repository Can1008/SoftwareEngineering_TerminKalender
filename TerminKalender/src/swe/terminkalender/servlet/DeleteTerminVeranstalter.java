package swe.terminkalender.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe.terminkalender.model.*;



/**
 * Servlet implementation class DeleteTerminVeranstalter
 */
@WebServlet("/DeleteTerminVeranstalter")
public class DeleteTerminVeranstalter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTerminVeranstalter() {
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
		
		if(request.getSession().getAttribute("loggedInVeran") == null){
			RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
			rd.forward(request, response);
		}
		String button = request.getParameter("editdelete");
		String buttonPressed = request.getParameter("buttonVerEdit");
		
		String username = request.getSession().getAttribute("loggedInVeran").toString();
		BenutzerManagement man = new BenutzerManagement();
		Veranstalter benutzer = (Veranstalter) man.getVeranstalterByUsername(username);
		ServletContext sc = getServletContext();
		String test = (String)sc.getAttribute("TerminNumber");
		int id = Integer.parseInt(test);
		String testId = request.getParameter("name");
		
		if(buttonPressed.equals("Bestätigen")){
		
		if (button != null && testId != null){
			if(button.equals("edit")){
				int idtermin = Integer.parseInt(request.getParameter("name"));
				request.getSession().setAttribute("TerminIdent", idtermin);
	
				Termin termin = man.showTermin(idtermin, benutzer, id);
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

				RequestDispatcher rd = request.getRequestDispatcher("editTerminVeranstalter.jsp");
				rd.forward(request, response);
				
			}else if(button.equals("delete")){
				int idtermin = Integer.parseInt(request.getParameter("name")); 
				man.deleteTermin(idtermin, benutzer, id);
				RequestDispatcher rd = request.getRequestDispatcher("overview_veranstalter.jsp");
				rd.forward(request, response);
			}else{
				RequestDispatcher rd = request.getRequestDispatcher("overview_veranstalter.jsp");
				rd.forward(request, response);
			}
		} else {
		RequestDispatcher rd = request.getRequestDispatcher("overview_veranstalter.jsp");
		rd.forward(request, response);
		}
	}else if(buttonPressed.equals("Sortieren")){
		String valueDropdown = request.getParameter("dropdownSort");
		ArrayList<Termin> terminlist = new ArrayList<Termin>();
		if(valueDropdown.equals("alphabetisch")){
			terminlist = man.sortierenAlphabetisch(id, benutzer);
			request.getSession(true).setAttribute("terminlistVerSort", terminlist);
		}else if(valueDropdown.equals("datAuf")){
			terminlist = man.sortierenNeuerstesDatum(id, benutzer);
			request.getSession(true).setAttribute("terminlistVerSort", terminlist);
		}else if(valueDropdown.equals("datAb")){
			terminlist = man.sortierenAeltestesDatum(id, benutzer);
			request.getSession(true).setAttribute("terminlistVerSort", terminlist);
		}
		RequestDispatcher rd = request.getRequestDispatcher("kalender_veranstalter_termin_sortiert.jsp");
		rd.forward(request, response);
	}	
	}
}
	



