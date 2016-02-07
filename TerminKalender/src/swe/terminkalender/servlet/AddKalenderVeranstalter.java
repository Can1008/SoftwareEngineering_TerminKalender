package swe.terminkalender.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe.terminkalender.model.*;

/**
 * Servlet implementation class AddKalenderVeranstalter
 */
@WebServlet("/AddKalender")
public class AddKalenderVeranstalter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddKalenderVeranstalter() {
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
		
		String kalendername = request.getParameter("titel_kalender");
		String kategorie = request.getParameter("kategorie");
		String page ="overview_veranstalter.jsp";
		
		if(kalendername.trim().equals("")){
			page="kalender_veranstalter.jsp";
			
		}
		
		else if("kultur".equals(kategorie)){
		Kategorie a = Kategorie.Kultur;
		Kalender kalender = new Kalender(kalendername,a,man.generateVeranstalterKalenderId(benutzer));
		man.addKalender(kalender, benutzer);
		}
		else if("sport".equals(kategorie)){
		Kategorie b = Kategorie.Sport;	
		Kalender kalender = new Kalender(kalendername,b,man.generateVeranstalterKalenderId(benutzer));
		man.addKalender(kalender, benutzer);
		}
		else if("familie".equals(kategorie)){
		Kategorie c = Kategorie.Familie;
		Kalender kalender = new Kalender(kalendername,c,man.generateVeranstalterKalenderId(benutzer));
		man.addKalender(kalender, benutzer);
		}
		else if("politik".equals(kategorie)){
		Kategorie d = Kategorie.Politik;
		Kalender kalender = new Kalender(kalendername,d,man.generateVeranstalterKalenderId(benutzer));
		man.addKalender(kalender, benutzer);
		}
		else if("freizeit".equals(kategorie)){
		Kategorie e = Kategorie.Freizeit;
		Kalender kalender = new Kalender(kalendername,e,man.generateVeranstalterKalenderId(benutzer));
		man.addKalender(kalender, benutzer);
		}
		else if("nachtleben".equals(kategorie)){
		Kategorie f = Kategorie.Nachtleben;
		Kalender kalender = new Kalender(kalendername,f,man.generateVeranstalterKalenderId(benutzer));
		man.addKalender(kalender, benutzer);
		}
		else {
		Kategorie g = Kategorie.Arbeit;
		Kalender kalender = new Kalender(kalendername,g,man.generateVeranstalterKalenderId(benutzer));
		man.addKalender(kalender, benutzer);
		}
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
		
		
	}

}
