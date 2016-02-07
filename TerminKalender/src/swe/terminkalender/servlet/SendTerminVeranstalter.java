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
 * Servlet implementation class SendTerminVeranstalter
 */
@WebServlet("/SendTermin")
public class SendTerminVeranstalter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendTerminVeranstalter() {
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
		
		String tnutzer = request.getParameter("termin_send");
		String termin = request.getParameter("termin");

	 	SerializedBenutzerDAO dao = new SerializedBenutzerDAO();
		ArrayList<Benutzer> nutzerlist = dao.ListOfBenutzer();
		
		
		Veranstalter benutzer = (Veranstalter) man.getVeranstalterByUsername(username);
		ArrayList<Kalender> kalenderList = new ArrayList<Kalender>();
		kalenderList = benutzer.getKalenderListe();
		String bname="";
		String page="termin_send.jsp";
		
		if(termin != null && tnutzer != null){
			int ter = Integer.parseInt(termin);
		for(int i=0; i<kalenderList.size();i++){
	    	Kalender a = kalenderList.get(i);
	    			for(int j=0; j<a.getTerminlist().size();j++){	
	    				Termin t = a.getTerminlist().get(j);
	    				for(int s=0; s<nutzerlist.size();s++){
	    				Benutzer k = nutzerlist.get(s);
	    	    		bname = k.getBenutzername();
	    			
						Privatnutzer pnutzer = (Privatnutzer) man.getPrivatnutzerByUsername(bname);
	    						if(tnutzer.equals(bname) && ter == t.getTerminId()){
	    						man.sendTermin(t.getTerminId(), pnutzer, benutzer);
	    						page = "overview_veranstalter.jsp";
	    						}
	    					}
	    				}
					}
				}
		
			
	    RequestDispatcher dd=request.getRequestDispatcher(page);
	    dd.forward(request, response);
	}
}
