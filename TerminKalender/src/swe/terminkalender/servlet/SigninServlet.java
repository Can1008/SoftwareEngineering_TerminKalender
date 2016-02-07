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
 * Servlet implementation class SigninServlet
 */
@WebServlet("/SigninServlet")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private Administrator admin = new Administrator("admin","test",true,1);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninServlet() {
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
		response.setContentType("text/html");
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    SerializedBenutzerDAO dao = new SerializedBenutzerDAO();
	    ArrayList<Benutzer> nutzerlist = dao.ListOfBenutzer();
	    
		if(admin.getBenutzername().equals(username) && admin.getPasswort().equals(password)){
			session = request.getSession();
			session.setAttribute("loggedInUser", username);
			RequestDispatcher rd = request.getRequestDispatcher("overview_admin.jsp");
	        rd.forward(request, response);		
		}
		boolean check = false;
		for(Benutzer nutzer : nutzerlist)
		{
			if(nutzer.getBenutzername().equals(username) && nutzer.getPasswort().equals(password))
			{			
				if(nutzer instanceof Privatnutzer && nutzer.getStatus() == true){
			    	session = request.getSession();
					session.setAttribute("loggedInPrivate", username);
			    	response.sendRedirect("overview_privatnutzer.jsp");
			    	check = true;
				}
				else if(nutzer instanceof Veranstalter && nutzer.getStatus() == true){
			    	session = request.getSession();
					session.setAttribute("loggedInVeran", username);
			    	response.sendRedirect("overview_veranstalter.jsp");
			    	check = true;
				}
				else if(nutzer instanceof Analytiker && nutzer.getStatus() == true){
			    	session = request.getSession();
					session.setAttribute("loggedInAna", username);
			    	response.sendRedirect("overview_analytiker.jsp");
			    	check = true;
				}
			}
		}
		
		if(check){
			doGet(request, response);
		}else{
			
			session = request.getSession();
			response.sendRedirect("login_sign_up.jsp");
		}
}

}