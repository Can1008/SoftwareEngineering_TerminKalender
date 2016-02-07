package swe.terminkalender.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe.terminkalender.model.*;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		String kategorie = request.getParameter("kategorie");
		String suchinput = request.getParameter("suchen_privatnutzer");
		
		String dropdown = request.getParameter("dropdownsearch");
		String split = request.getParameter("button");
		String speichern = request.getParameter("speichern");
		String kalanzeigen = request.getParameter("kalender_anzeigen");
		String search_kalenderspeichern = request.getParameter("kalenderspeichern");
		String page = "search_privatnutzer.jsp";
		BenutzerManagement man = new BenutzerManagement();
		Privatnutzer benutzer = (Privatnutzer) man.getPrivatnutzerByUsername(username);
		ArrayList<Termin> benTerminList = benutzer.getKalender().getTerminlist();
		ArrayList<Kalender> veranKal = man.getAllVeranKalender();
		ArrayList<Termin> searchResults = new ArrayList<Termin>();
		ArrayList<Kalender> searchKalender = new ArrayList<Kalender>();
		ArrayList<Termin> searchKalenderTermine = new ArrayList<Termin>();
		
		if(split!=null&&split.equals("suchen"))
		{
			if(dropdown.equals("Oeffentlich"))
			{
				if(kategorie!=null)
				{
					for(Kalender k: veranKal)
					  {
						  ArrayList<Termin> kalTermine = k.getTerminlist();
						  String kalname = k.getName().toLowerCase();
						  String suche = suchinput.toLowerCase();
						  if(kalname.contains(suche))
						  {
							  if(kategorie.equals(k.getKategorie().toString()))
							  {
								  searchKalender.add(k);
							  }
							  
						  }
						  for(Termin t: kalTermine)
						  {
							String name = t.getName().toLowerCase();
							String ort = t.getOrt().toLowerCase();
							String notiz = t.getNotiz().toLowerCase();
							
							Calendar start = t.getBeginndatum();
							Calendar end = t.getEnddatum();
							int styear = start.get(Calendar.YEAR);
							int endyear = end.get(Calendar.YEAR);
							String styeartostring = Integer.toString(styear);
							String endyeartostring = Integer.toString(endyear);
							if(styeartostring.equals(suchinput)||endyeartostring.equals(suchinput)||notiz.contains(suche) || name.contains(suche) || ort.contains(suche))
							{
								if(kategorie.equals(k.getKategorie().toString()))
								{
									searchKalenderTermine.add(t);	
								}
														
							}	
						  }					  
					  }
				}
				if(kategorie==null && suchinput!="")
				{
					for(Kalender k: veranKal)
					  {
						  ArrayList<Termin> kalTermine = k.getTerminlist();
						  String kalname = k.getName().toLowerCase();
						  String suche = suchinput.toLowerCase();
						  if(kalname.contains(suche))
						  {
							  searchKalender.add(k);							  
						  }
						  for(Termin t: kalTermine)
						  {
							String name = t.getName().toLowerCase();
							String ort = t.getOrt().toLowerCase();
							String notiz = t.getNotiz().toLowerCase();
							Calendar start = t.getBeginndatum();
							Calendar end = t.getEnddatum();
							int styear = start.get(Calendar.YEAR);
							int endyear = end.get(Calendar.YEAR);
							String styeartostring = Integer.toString(styear);
							String endyeartostring = Integer.toString(endyear);
							if(styeartostring.equals(suchinput)||endyeartostring.equals(suchinput)||notiz.contains(suche) || name.contains(suche) || ort.contains(suche))
							{								
								searchKalenderTermine.add(t);															
							}	
						  }					  
					  }
				}
			}
			if(dropdown.equals("Privat"))
			{
				if(kategorie!=null)
				{
					for(Termin t : benTerminList)
					  {
						String name = t.getName().toLowerCase();
						String ort = t.getOrt().toLowerCase();
						String notiz = t.getNotiz().toLowerCase();
						Calendar start = t.getBeginndatum();
						Calendar end = t.getEnddatum();
						int styear = start.get(Calendar.YEAR);
						int endyear = end.get(Calendar.YEAR);
						String styeartostring = Integer.toString(styear);
						String endyeartostring = Integer.toString(endyear);
						String suche = suchinput.toLowerCase();
						if(styeartostring.equals(suchinput)||endyeartostring.equals(suchinput)||notiz.contains(suche) || name.contains(suche) || ort.contains(suche))
						{
							if(t.getKategorie()!=null)
							{
								if(kategorie.equals(t.getKategorie().toString()))
								{
									searchResults.add(t);
								}
							}							
						}			
					  }
				}
				if(suchinput != "")
				{
				  for(Termin t : benTerminList)
				  {
					String name = t.getName().toLowerCase();
					String ort = t.getOrt().toLowerCase();
					String notiz = t.getNotiz().toLowerCase();
					Calendar start = t.getBeginndatum();
					Calendar end = t.getEnddatum();
					int styear = start.get(Calendar.YEAR);
					int endyear = end.get(Calendar.YEAR);
					String styeartostring = Integer.toString(styear);
					String endyeartostring = Integer.toString(endyear);
					String suche = suchinput.toLowerCase();
					if(styeartostring.equals(suchinput)||endyeartostring.equals(suchinput)||notiz.contains(suche) || name.contains(suche) || ort.contains(suche))
					{
						searchResults.add(t);
					}			
				  }
				}
			}
		}
		if(speichern!=null)
		{		
			
			int terminid = Integer.parseInt(speichern);
			SerializedBenutzerDAO a = new SerializedBenutzerDAO();
			ArrayList<Benutzer> alleBen = a.ListOfBenutzer();
			ArrayList<Termin> alleTermine= new ArrayList<Termin>();
			Termin t1=null;
			for(Benutzer b: alleBen)
			{
				if(b instanceof Veranstalter)
				{
					ArrayList<Kalender> kal = ((Veranstalter) b).getKalenderListe();
					for(Kalender k:kal)
					{
						ArrayList<Termin> termine = k.getTerminlist();
						for(Termin t:termine)
						{
							if(t.getTerminId() == terminid)
							{
								t1=t;
							}
						}
					}		
				}
			}

			alleTermine = benutzer.getKalender().getTerminlist();
			boolean check = false;
			for(Termin t: alleTermine)
			{
				if(t.getTerminId() == terminid)
				{
					check = true;
				}
			}
			if(!check)
			{
				int count = t1.getAnzahlGeteilt() + 1;
				t1.setAnzahlGeteilt(count);
				man.addTermin(t1, benutzer);
				
				for(int k=0; k<alleBen.size(); k++){
					Benutzer b1 = alleBen.get(k);
					if(b1 instanceof Veranstalter){
						ArrayList<Kalender> kalenderlisteVer = ((Veranstalter) b1).getKalenderListe();
						for(int i=0; i<kalenderlisteVer.size();i++){
							Kalender calVer = kalenderlisteVer.get(i);
							ArrayList<Termin> terVer = calVer.getTerminlist();
							for(int j=0; j<terVer.size(); j++){
								Termin tVer = terVer.get(j);
								if(tVer.getTerminId() == t1.getTerminId()){
									man.editTermin(t1.getTerminId(), ((Veranstalter)b1), calVer.getKalenderId(), t1);
								}
							}
						}
					}
				}
				
				ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
				boolean schleifenAb = true;
				for(int j=0; j<benutzerlist.size() && schleifenAb; j++){
					Benutzer b = benutzerlist.get(j);
					if(b instanceof Veranstalter){
						ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
						for(int k=0; k<kalenderlist.size() && schleifenAb; k++){
							ArrayList<Termin> terminlist = kalenderlist.get(k).getTerminlist();
							for(int l=0; l<terminlist.size() && schleifenAb;l++){
								Termin term = terminlist.get(l);
								if(term.getTerminId() == terminid){
									ArrayList<Privatnutzer> nutzlist = new ArrayList<Privatnutzer>();
									if(((Veranstalter) b).getBenutzerListe()!= null){
										nutzlist.addAll(((Veranstalter) b).getBenutzerListe());
									}
									for(int m=0; m<nutzlist.size(); m++){
										if(nutzlist.get(m).getId() == benutzer.getId()){
											schleifenAb = false;
										}
									}
									if(schleifenAb){
										nutzlist.add(benutzer);
										((Veranstalter) b).setBenutzerListe(nutzlist);
										a.loescheBenutzer(b.getId());
										a.speichereBenutzer(b);
										schleifenAb = false;
									}
								}
							}
						}
					}
				}
				
				page = "overview_privatnutzer.jsp";
			}
			else
			{
				page="overview_privatnutzer.jsp";
			}
				
		}
		if(kalanzeigen!=null)
		{
			Kalender ret = null;
			ArrayList<Kalender> kal = man.getAllVeranKalender();
			int i = Integer.parseInt(kalanzeigen);
			for(Kalender k:kal)
			{
				if(k.getKalenderId()==i)
				{
					ret=k;
				}
			}
			request.setAttribute("kalender", ret);
			page = "search_show_kalender.jsp";
			
		}
		if(search_kalenderspeichern!=null)
		{
			int i = Integer.parseInt(search_kalenderspeichern);
			ArrayList<Termin> benka = benutzer.getKalender().getTerminlist();
			ArrayList<Termin> verka = man.getKalenderById(i).getTerminlist();
			SerializedBenutzerDAO a = new SerializedBenutzerDAO();
			ArrayList<Benutzer> benutzerlist = a.ListOfBenutzer();
			boolean set = true;
			boolean schleifenAb1 = true;
			for(int u = 0; u<verka.size(); u++)
			{
				Termin t = verka.get(u);
				for(Termin tt : benka)
				{
					if(t.getTerminId()==tt.getTerminId())
					{
						set = false;
					}
				}
				if(set)
				{
					int count = t.getAnzahlGeteilt() + 1;
					t.setAnzahlGeteilt(count);
					man.addTermin(t, benutzer);
					
					for(int k=0; k<benutzerlist.size(); k++){
						Benutzer b1 = benutzerlist.get(k);
						if(b1 instanceof Veranstalter){
							ArrayList<Kalender> kalenderlisteVer = ((Veranstalter) b1).getKalenderListe();
							for(int l=0; l<kalenderlisteVer.size();l++){
								Kalender calVer = kalenderlisteVer.get(l);
								ArrayList<Termin> terVer = calVer.getTerminlist();
								for(int j=0; j<terVer.size(); j++){
									Termin tVer = terVer.get(j);
									if(tVer.getTerminId() == t.getTerminId()){
										man.editTermin(tVer.getTerminId(), ((Veranstalter)b1), calVer.getKalenderId(), t);
									}
								}
							}
						}
					}
				}set = true;
			}

			for(int j=0; j<benutzerlist.size() && schleifenAb1; j++){
				Benutzer b = benutzerlist.get(j);
					if(b instanceof Veranstalter){
						ArrayList<Kalender> kalenderlist = ((Veranstalter) b).getKalenderListe();
						for(int k=0; k<kalenderlist.size() && schleifenAb1; k++){
							int kalId = kalenderlist.get(k).getKalenderId();
							if(kalId == i){
								ArrayList<Privatnutzer> nutzlist = new ArrayList<Privatnutzer>();
								if(((Veranstalter) b).getBenutzerListe()!= null){
									nutzlist.addAll(((Veranstalter) b).getBenutzerListe());
								}
								for(int m=0; m<nutzlist.size(); m++){
									if(nutzlist.get(m).getId() == benutzer.getId()){
										schleifenAb1 = false;
									}
								}
								if(schleifenAb1){
									nutzlist.add(benutzer);
									((Veranstalter) b).setBenutzerListe(nutzlist);
									a.loescheBenutzer(b.getId());
									a.speichereBenutzer(b);
									schleifenAb1 = false;
									}
								}
							}
						}
					}	
				
			
			page="overview_privatnutzer.jsp";
			
		}
		
				
		request.setAttribute("searchResults", searchResults);
		request.setAttribute("searchKalender", searchKalender);
		request.setAttribute("searchKalenderTermine", searchKalenderTermine);
		request.getRequestDispatcher(page).forward(request, response);
		
		

		//String input = request.getParameter("suchen_privatnutzer");
		//doGet(request, response);
	}

}