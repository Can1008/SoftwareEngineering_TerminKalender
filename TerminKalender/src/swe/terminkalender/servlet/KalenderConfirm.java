package swe.terminkalender.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe.terminkalender.model.*;

/**
 * Servlet implementation class KalenderConfirm
 */
@WebServlet("/KalenderConfirm")
public class KalenderConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KalenderConfirm() {
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
		ArrayList<Kalender> kalenderList = new ArrayList<Kalender>();
		kalenderList = benutzer.getKalenderlist();
		
		String loeschen = request.getParameter("kalender_loeschen");
		String annehmen = request.getParameter("kalender_annehmen");
		String show = request.getParameter("kalender_show");
		String page="overview_privatnutzer.jsp";
		
	    for(int i=0; i<kalenderList.size();i++){
	    	Kalender a = kalenderList.get(i);
	    		
	    		
				String i1 = Integer.toString(a.getKalenderId());
				if(i1.equals(loeschen)){
					man.deleteKalenderEinladung(a.getKalenderId(), benutzer);
					page = "einladungen_privatnutzer.jsp";
					//response.sendRedirect(overview);
				}
				else if(i1.equals(annehmen)){
					man.saveKalenderEinladung(a.getKalenderId(), benutzer);
					
					page = "einladungen_privatnutzer.jsp";
					
				}
				else if(i1.equals(show)){
					session = request.getSession();
					session.setAttribute("KalenderNumber", i1);
					page="einladungen_termin_show.jsp";
					
				}
	    }
		
		
	    RequestDispatcher dd=request.getRequestDispatcher(page);
	    dd.forward(request, response);
	}

}
