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
 * Servlet implementation class ShowKalenderVeranstalter
 */
@WebServlet("/ShowKalenderVeranstalter")
public class ShowKalenderVeranstalter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowKalenderVeranstalter() {
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
		
	 	SerializedBenutzerDAO dao = new SerializedBenutzerDAO();
		ArrayList<Benutzer> nutzerlist = dao.ListOfBenutzer();
		
		Veranstalter benutzer = (Veranstalter) man.getVeranstalterByUsername(username);
		ArrayList<Kalender> kalenderList = new ArrayList<Kalender>();
		kalenderList = benutzer.getKalenderListe();

		String sex = request.getParameter("sex");
		String hidden = request.getParameter("kalender");
		
		
		String page="kalender_termin_send.jsp";
		
		if(hidden != null &&  sex != null){
			int hid = Integer.parseInt(hidden);
		for(int i=0; i<kalenderList.size();i++){
	    	Kalender a = kalenderList.get(i);   
	    	
	    	for(int u=0;u<nutzerlist.size();u++){   		
	    		Benutzer k = nutzerlist.get(u);
	    		String bname = k.getBenutzername(); 
	    		Privatnutzer pnutzer = (Privatnutzer) man.getPrivatnutzerByUsername(bname);
 		
				if( sex.equals(bname) && hid == a.getKalenderId()){
					man.sendKalender(a.getKalenderId(), pnutzer, benutzer); 
					page = "overview_veranstalter.jsp";
					
					
					
				}
	    	}
		}
	}
		
		
	    RequestDispatcher dd=request.getRequestDispatcher(page);
	    dd.forward(request, response);
		
			}
		}
	
