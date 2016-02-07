package swe.terminkalender.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe.terminkalender.model.*;

/**
 * Servlet implementation class EditTerminVeranstalter
 */
@WebServlet("/EditTerminVeranstalter")
public class EditTerminVeranstalter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTerminVeranstalter() {
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
		
		if(request.getSession().getAttribute("loggedInVeran") == null){
			RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
			rd.forward(request, response);
		}
		String username = request.getSession().getAttribute("loggedInVeran").toString();
		ServletContext sc = getServletContext();
		String test = (String)sc.getAttribute("TerminNumber");
		int KalenderId = Integer.parseInt(test);
		BenutzerManagement man = new BenutzerManagement();
		SerializedBenutzerDAO dao = new SerializedBenutzerDAO();
		Kalender cali = man.getKalenderById(KalenderId);
		Kategorie kategorie = cali.getKategorie();
		
		String beginndat = request.getParameter("beginnEdit");
		String enddat = request.getParameter("endEdit");

			Calendar beginncal = Calendar.getInstance();
			Calendar endcal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			try {
				beginncal.setTime(sdf.parse(beginndat));
				endcal.setTime(sdf.parse(enddat));
			} catch (ParseException e) {
				RequestDispatcher rd = request.getRequestDispatcher("editTerminVeranstalter.jsp");
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
			        request.getRequestDispatcher("editTerminVeranstalter.jsp").forward(request, response);
			}else{
				String titel = request.getParameter("titelEdit");
				String ort = request.getParameter("ortEdit");
				String beschr = request.getParameter("beschreibungEdit");
				int idi = (int) request.getSession().getAttribute("TerminIdent");
				
				Veranstalter benutzer = (Veranstalter) man.getVeranstalterByUsername(username);
				Termin termin = new Termin(titel,ort,beschr,beginncal,endcal,kategorie, idi);
				man.editTermin(idi, benutzer,KalenderId, termin);
				
				ArrayList<Benutzer> benutzerListe = dao.ListOfBenutzer();
				for(int i=0; i<benutzerListe.size();i++){
					Benutzer b = benutzerListe.get(i);
					if(b instanceof Privatnutzer){
						ArrayList<Termin> terminListe = ((Privatnutzer) b).getKalender().getTerminlist();
						for(int j=0; j<terminListe.size(); j++){
							Termin t = terminListe.get(j);
							if(t.getTerminId() == idi){
								man.editTermin(idi, (Privatnutzer) b, termin);
							}
						}
						ArrayList<Termin> terminListeEin = ((Privatnutzer) b).getTerminlist();
						for(int k=0; k<terminListeEin.size(); k++){
							Termin tEin = terminListeEin.get(k);
							if(tEin.getTerminId() == idi){
								terminListeEin.remove(tEin);
								terminListeEin.add(termin);
								((Privatnutzer) b).setTerminlist(terminListeEin);
			    	    		dao.speichereBenutzer((Privatnutzer) b);
			    	    		dao.loescheBenutzer(b.getId());
							}
						}
					}	
				}
				RequestDispatcher rd = request.getRequestDispatcher("overview_veranstalter.jsp");
				rd.forward(request, response);
			}
		}
	}
