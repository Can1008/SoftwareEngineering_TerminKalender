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
 * Servlet implementation class OverviewAdministratorServlet
 */
@WebServlet("/OverviewAdminServlet")
public class OverviewAdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OverviewAdministratorServlet() {
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
		if(request.getSession().getAttribute("loggedInUser") == null){
			RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
			rd.forward(request, response);
		}
	 	SerializedBenutzerDAO dao = new SerializedBenutzerDAO();
		ArrayList<Benutzer> nutzerlist = new ArrayList<Benutzer>();
				nutzerlist = dao.ListOfBenutzer();
				String name = request.getParameter("name");
				String status = request.getParameter("status");
				
				
				for (Benutzer benutzer : nutzerlist)
				{ 
					
					String i = Integer.toString(benutzer.getId());
					if(i.equals(name)){
					dao.loescheBenutzer(benutzer.getId());
					}
					if(i.equals(status)){
						if(benutzer.getStatus() == false){
						benutzer.setStatus(true);
						dao.speichereBenutzer(benutzer);
						dao.loescheBenutzer(benutzer.getId());
						}
						else{
							benutzer.setStatus(false);
							dao.speichereBenutzer(benutzer);
							dao.loescheBenutzer(benutzer.getId());
						}
					}
				}
					
		
		RequestDispatcher rd = request.getRequestDispatcher("overview_admin.jsp");
		rd.forward(request, response);
	}

}
