package swe.terminkalender.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe.terminkalender.model.*;


/**
 * Servlet implementation class OverviewVeranstalterServlet
 */
@WebServlet("/OverviewVeranstalterServlet")
public class OverviewVeranstalterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OverviewVeranstalterServlet() {
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
		
		if(request.getSession().getAttribute("loggedInVeran") == null){
			RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
			rd.forward(request, response);
		}
		String username = request.getSession().getAttribute("loggedInVeran").toString();

		BenutzerManagement man = new BenutzerManagement();
		
		Veranstalter benutzer = (Veranstalter) man.getVeranstalterByUsername(username);
		ArrayList<Kalender> kalenderList = new ArrayList<Kalender>();
		kalenderList = benutzer.getKalenderListe();
		
		String loeschen = request.getParameter("kalender_loeschen");
		String anzeigen = request.getParameter("kalender_anzeigen");
		
		String add = request.getParameter("termin_add");
		String page = "overview_veranstalter.jsp";
		
		
		
	    for(int i=0; i<kalenderList.size();i++){
	    	Kalender a = kalenderList.get(i);
	    		
	    		
				String i1 = Integer.toString(a.getKalenderId());
				if(i1.equals(loeschen)){
					man.deleteKalender(a.getKalenderId(), benutzer);
					 page = "overview_veranstalter.jsp";
				}
				else if(i1.equals(anzeigen)){
					session = request.getSession();
					session.setAttribute("TerminId",i1);
					ServletContext sc = getServletContext();
					sc.setAttribute("TerminNumber", i1);
					 page = "kalender_veranstalter_termin.jsp";
				}
				else if(i1.equals(add)){
					
					ServletContext sc = getServletContext();
					sc.setAttribute("TerminID", i1);
					 page = "termin_veranstalter.jsp";
				}
		}
		
		
	    RequestDispatcher dd=request.getRequestDispatcher(page);
	    dd.forward(request, response);

		
	}

}
