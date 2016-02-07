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
 * Servlet implementation class TerminConfirm
 */
@WebServlet("/TerminConfirm")
public class TerminConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TerminConfirm() {
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
		if(request.getSession().getAttribute("loggedInPrivate") == null){
			RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
			rd.forward(request, response);
		}
		String username = request.getSession().getAttribute("loggedInPrivate").toString();
		
		BenutzerManagement man = new BenutzerManagement();
		
		Privatnutzer benutzer = (Privatnutzer) man.getPrivatnutzerByUsername(username);
		ArrayList<Termin> terminList = new ArrayList<Termin>();
		terminList = benutzer.getTerminlist();
		
		String loeschen = request.getParameter("termin_loeschen");
		String annehmen = request.getParameter("termin_annehmen");
		String page="overview_privatnutzer.jsp";
		
		
	    			for(int j=0; j<terminList.size();j++){	
	    				Termin t = terminList.get(j);
	    				String i1 = Integer.toString(t.getTerminId());
				
	    					if(i1.equals(loeschen)){
	    						man.deleteTeminEinladung(t.getTerminId(), benutzer);
	    						page="einladungen_termin_privatnutzer.jsp";
	    		}			
	    					else if(i1.equals(annehmen)){
	    						man.saveTerminEinladung(t.getTerminId(), benutzer);
	    						page="einladungen_termin_privatnutzer.jsp";
	    					}
		}
		
	    RequestDispatcher dd=request.getRequestDispatcher(page);
	    dd.forward(request, response);
	}

}
