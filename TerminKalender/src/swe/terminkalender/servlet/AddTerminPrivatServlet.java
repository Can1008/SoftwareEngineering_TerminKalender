package swe.terminkalender.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe.terminkalender.model.*;


/**
 * Servlet implementation class AddTerminPrivatServlet
 */
@WebServlet("/AddTerminPrivatServlet")
public class AddTerminPrivatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTerminPrivatServlet() {
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
		
		String beginndat = request.getParameter("beginntag");
		String enddat = request.getParameter("endtag");
		String tagevorher = request.getParameter("tagevorher");
		if(beginndat.equals("")||enddat.equals("")){
			RequestDispatcher rd = request.getRequestDispatcher("termin_privatnutzer.jsp");
			rd.forward(request, response);
		}else{
			Calendar beginncal = Calendar.getInstance();
			Calendar endcal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			try {
				beginncal.setTime(sdf.parse(beginndat));
				endcal.setTime(sdf.parse(enddat));
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
				        request.getRequestDispatcher("termin_privatnutzer.jsp").forward(request, response);
				}else{
					request.getSession(true).setAttribute("BeginnDat", beginndat);
					request.getSession(true).setAttribute("EndDat", enddat);
					
					String titel = request.getParameter("titel");
					String ort = request.getParameter("ort");
					String beschr = request.getParameter("beschreibung");
					
					
					BenutzerManagement man = new BenutzerManagement();
					Privatnutzer benutzer = (Privatnutzer) man.getPrivatnutzerByUsername(username);
					Termin termin = new Termin(titel,ort,beschr,beginncal,endcal, man.generateBenutzerTerminId());
					try{
						if(tagevorher!=null)
						{
							int tage = Integer.parseInt(tagevorher);
							if(tage < 0){
								RequestDispatcher rd = request.getRequestDispatcher("termin_privatnutzer.jsp");
								rd.forward(request, response);
							}
							termin.setBenachrichtigungTage(tage);
							
						}
					}catch(Exception e){
						RequestDispatcher rd = request.getRequestDispatcher("termin_privatnutzer.jsp");
						rd.forward(request, response);
					}
					
					String checked = request.getParameter("kontrollCheck");
					
					if(checked != null){
						Calendar cal2 = Calendar.getInstance();
						SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
						cal2.setTime(sdf.parse(beginndat));
						cal2.add(Calendar.YEAR, 1);
						
						String wiederdat = request.getParameter("wiederholen");
						Calendar wiedercal = Calendar.getInstance();
						wiedercal.setTime(sdf3.parse(wiederdat));
						if(wiedercal.after(cal2)){
							String message4 = "Der Termin kann maximal ein Jahr lang wiederholt werden.";
					        request.setAttribute("message4", message4);
					        request.getRequestDispatcher("termin_privatnutzer.jsp").forward(request, response);
						}else{
							String wiederholungsdat = request.getParameter("wiederholen");
							if(!wiederholungsdat.equals("")){
								Calendar wiederholungscal = Calendar.getInstance();
								SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
								wiederholungscal.setTime(sdf2.parse(wiederholungsdat));
								wiederholungscal.set(Calendar.HOUR, 23);
								wiederholungscal.set(Calendar.MINUTE, 59);
								if(wiederholungscal.before(beginncal)){
									String message3 = "Das Wiederholungsdatum kann nicht vor dem Beginndatum liegen.";
							        request.setAttribute("message3", message3);
							        request.getRequestDispatcher("termin_privatnutzer.jsp").forward(request, response);
								}else{
									man.addTermin(termin, benutzer);
								}
	
								long time = wiederholungscal.getTime().getTime() - beginncal.getTime().getTime();
								long days = Math.round((double)time / (24. * 60.*60.*1000.));
								
								int diff = (int)days/7;
								Calendar calendar = Calendar.getInstance();
								Date currentDate = beginncal.getTime();
								calendar.setTime(currentDate);
								
								Calendar calendar2 = Calendar.getInstance();
								Date currentDate2 = endcal.getTime();
								calendar2.setTime(currentDate2);
								
								for(int i=0; i<diff; i++){
									calendar.add(Calendar.DATE, 7);
									calendar2.add(Calendar.DATE, 7);
									Calendar a = Calendar.getInstance();
									Calendar b = Calendar.getInstance();
									a.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), beginncal.get(Calendar.HOUR_OF_DAY), beginncal.get(Calendar.MINUTE));
									b.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH), endcal.get(Calendar.HOUR_OF_DAY), endcal.get(Calendar.MINUTE));
									if(a.after(beginncal)){
										Termin termin2 = new Termin(titel,ort,beschr,a ,b, man.generateBenutzerTerminId());
										try{
											if(tagevorher!=null)
											{
												int tage = Integer.parseInt(tagevorher);
												if(tage < 0){
													RequestDispatcher rd = request.getRequestDispatcher("termin_privatnutzer.jsp");
													rd.forward(request, response);
												}
												termin2.setBenachrichtigungTage(tage);
											}									
										}catch(Exception e){
											RequestDispatcher rd = request.getRequestDispatcher("termin_privatnutzer.jsp");
											rd.forward(request, response);
										}
										
										man.addTermin(termin2, benutzer);
										
									}
								}
							}
						}
					}else{
						man.addTermin(termin, benutzer);
					}
					RequestDispatcher rd = request.getRequestDispatcher("overview_privatnutzer.jsp");
					rd.forward(request, response);
				}
		} catch (ParseException e) {
			RequestDispatcher rd = request.getRequestDispatcher("termin_privatnutzer.jsp");
			rd.forward(request, response);
			}
		}
	}
}
