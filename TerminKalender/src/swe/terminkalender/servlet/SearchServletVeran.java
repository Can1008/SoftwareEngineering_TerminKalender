package swe.terminkalender.servlet;

import java.io.IOException;
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
 * Servlet implementation class SearchServletVeran
 */
@WebServlet("/SearchServletVeran")
public class SearchServletVeran extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServletVeran() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("loggedInVeran") == null){
			RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
			rd.forward(request, response);
		}
		String username = request.getSession().getAttribute("loggedInVeran").toString();
		String kategorie = request.getParameter("kategorie");
		String suchinput = request.getParameter("suchen_veranstalter");
		BenutzerManagement man = new BenutzerManagement();
		Veranstalter benutzer = (Veranstalter) man.getVeranstalterByUsername(username);
		
		ArrayList<Kalender> benKalenderList = new ArrayList<Kalender>();
		benKalenderList = benutzer.getKalenderListe();
		ArrayList<Termin> alleTermineVonKal = new ArrayList<Termin>();
		ArrayList<Termin> searchResults = new ArrayList<Termin>();
		if(kategorie!=null)
		{
			
			for(Kalender k: benKalenderList)
			{
				ArrayList<Termin> tempTermine = k.getTerminlist();
				for(Termin t: tempTermine)
				{
					alleTermineVonKal.add(t);
				}
			}
			
			for(Termin t:alleTermineVonKal)
			{
				String name = t.getName().toLowerCase();
				String ort = t.getOrt().toLowerCase();
				String notiz = t.getNotiz().toLowerCase();
				String suche = suchinput.toLowerCase();
				Calendar start = t.getBeginndatum();
				Calendar end = t.getEnddatum();
				int styear = start.get(Calendar.YEAR);
				int endyear = end.get(Calendar.YEAR);
				String styeartostring = Integer.toString(styear);
				String endyeartostring = Integer.toString(endyear);
				if(styeartostring.equals(suchinput)||endyeartostring.equals(suchinput)||notiz.contains(suche) || name.contains(suche) || ort.contains(suche))
				{
					if(kategorie.equals(t.getKategorie().toString()))
					{
						searchResults.add(t);
					}					
				}	
			}
		}
		if(suchinput!="" && kategorie == null)
		{
			for(Kalender k: benKalenderList)
			{
				ArrayList<Termin> tempTermine = k.getTerminlist();
				for(Termin t: tempTermine)
				{
					alleTermineVonKal.add(t);
				}
			}
			
			for(Termin t:alleTermineVonKal)
			{
				String name = t.getName().toLowerCase();
				String ort = t.getOrt().toLowerCase();
				String notiz = t.getNotiz().toLowerCase();
				String suche = suchinput.toLowerCase();
				Calendar start = t.getBeginndatum();
				Calendar end = t.getEnddatum();
				int styear = start.get(Calendar.YEAR);
				int endyear = end.get(Calendar.YEAR);
				String styeartostring = Integer.toString(styear);
				String endyeartostring = Integer.toString(endyear);
				if(styeartostring.equals(suchinput)||endyeartostring.equals(suchinput)||notiz.contains(suche) || name.contains(suche) || ort.contains(suche))
				{
					
					searchResults.add(t);
				}	
			}	
		}
					
		request.setAttribute("searchResults", searchResults);
		request.getRequestDispatcher("search_veranstalter.jsp").forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
