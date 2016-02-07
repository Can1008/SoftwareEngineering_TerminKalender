package swe.terminkalender.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe.terminkalender.model.*;

/**
 * Servlet implementation class EditTerminPrivatServlet
 */
@WebServlet("/EditTerminPrivatServlet")
public class EditTerminPrivatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTerminPrivatServlet() {
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
		if(request.getSession().getAttribute("loggedInPrivate") == null){
			RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
			rd.forward(request, response);
		}
		String username = request.getSession().getAttribute("loggedInPrivate").toString();
		
		String beginndat = request.getParameter("beginnEdit");
		String enddat = request.getParameter("endEdit");
			Calendar beginncal = Calendar.getInstance();
			Calendar endcal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			try {
				beginncal.setTime(sdf.parse(beginndat));
				endcal.setTime(sdf.parse(enddat));
			} catch (ParseException e) {
				RequestDispatcher rd = request.getRequestDispatcher("editTerminPrivatnutzer.jsp");
				rd.forward(request, response);
			}
			Calendar heutecal = Calendar.getInstance();
			heutecal.add(Calendar.MINUTE, -1);
			if(heutecal.after(beginncal) || heutecal.after(endcal) || endcal.before(beginncal)){
				if(heutecal.after(beginncal)){
					String message1 = "Das Beginndatum kann nicht in der Vergangenheit liegen.";
			        request.setAttribute("message1", message1);
				}
				if(heutecal.after(endcal)){
					String message2 = "Das Enddatum kann nicht in der Vergangenheit liegen.";
			        request.setAttribute("message2", message2);
				}
				if(endcal.before(beginncal)){
					String message = "Das Enddatum kann nicht vor dem Beginndatum liegen.";
			        request.setAttribute("message", message);
				}
			        request.getRequestDispatcher("editTerminPrivatnutzer.jsp").forward(request, response);
			}else{
				String titel = request.getParameter("titelEdit");
				String ort = request.getParameter("ortEdit");
				String beschr = request.getParameter("beschreibungEdit");
				int id = Integer.parseInt(request.getParameter("TerminID")); 
	
				BenutzerManagement man = new BenutzerManagement();
				Privatnutzer benutzer = (Privatnutzer) man.getPrivatnutzerByUsername(username);
				Termin termin = new Termin(titel,ort,beschr,beginncal,endcal, id);
				man.editTermin(id, benutzer, termin);
				RequestDispatcher rd = request.getRequestDispatcher("overview_privatnutzer.jsp");
				rd.forward(request, response);
			}
	
		}
	}
