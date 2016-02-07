<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import ="swe.terminkalender.model.*" %>
<%@ page import = "java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/standard.css">
<link rel="stylesheet" href="css/bootstrap.min.css">         
<script src="js/bootstrap.min.js"></script> 
<title>Suchen Privatnutzer</title>
</head>
<body>

<nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand">MiCalenDar</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="overview_privatnutzer.jsp">Übersicht</a></li>
			<li class="active"><a href="search_privatnutzer.jsp">Suchen</a></li>
			<li><a href="termin_privatnutzer.jsp">Termin erstellen</a></li>
			<li><a href="einladungen_privatnutzer.jsp">Kalendereinladungen</a></li>
			<li><a href="einladungen_termin_privatnutzer.jsp">Termineinladungen</a></li>
			<li><a href="benachrichtigungen.jsp">Benachrichtigungen</a></li>
			<li><a href="show_Top_Termin_Privatnutzer.jsp">Top 10</a></li>
          </ul>
         <div id="navbar" class="navbar-collapse collapse">
         	 <ul class="nav navbar-nav navbar-right">
              <li><a><%= session.getAttribute( "loggedInPrivate" ) %></a></li>
              <form class="navbar-form navbar-right" action="LogoutAdminServlet" method="post">
              <li><button type="submit" class="btn btn-danger" name="logout" >Logout</button></li>
              </form>
      	 	</ul>
      	 </div>
        </div><!-- /.nav-collapse -->
       </div><!-- /.container -->
    </nav><!-- /.navbar -->


<div class="center">
  <h2>Suchen</h2>
  <%
	if(request.getSession().getAttribute("loggedInPrivate") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
%>
  <br>
  <form action="SearchServlet" method="post">
 
  <input type="text" class="form-control" id="search" name="suchen_privatnutzer">
  <br>
  <label for="aus">Öffentlich oder Privat</label>
  <select class="form-control" id="aus" name="dropdownsearch">
    <option value="Privat">Privat</option>
    <option value="Oeffentlich">Öffentlich</option>
  </select>
  
  <br>
  <label for ="kat">Kategorie wählen</label><br>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Kultur"> Kultur<br></label>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Sport"> Sport<br></label>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Familie"> Familie<br></label>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Politik"> Politik<br></label><br>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Freizeit"> Freizeit<br></label>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Nachtleben"> Nachtleben<br></label>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Arbeit"> Arbeit<br></label>
  
  <br><br>
  <input type="submit" class="btn btn-default" value="suchen" name="button">
</form>


<% 
try{
ArrayList<Termin> searchResults = new ArrayList<Termin>();
	searchResults= (ArrayList<Termin>)request.getAttribute("searchResults");
	ArrayList<Kalender> kal = new ArrayList<Kalender>();
	kal = (ArrayList<Kalender>) request.getAttribute("searchKalender");
	ArrayList<Termin> kalTermine = new ArrayList<Termin>();
	kalTermine = (ArrayList<Termin>) request.getAttribute("searchKalenderTermine");
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
	if(kal != null)
	{
		boolean check = true;
		
		for(Kalender k : kal)
		{
			if(check)
			{
				%><h2>Kalender</h2><%
				check = false;
			}
			%>
			<br>
			<TABLE class="table">
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Kalendername: 
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= k.getName() %>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Kategorie: 
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= k.getKategorie() %>
					</TD>
				</TR>
			</TABLE>
			
			<form  action="SearchServlet" method="post">
			
			<button name="kalender_anzeigen" class="btn btn-default" type="submit" value="<%= k.getKalenderId()%>">Alle Termine anzeigen</button>
			</form>
		<%
			
		}%>
  <%}%>
  <%if(kalTermine != null)
	{
	  boolean check= true;
	  
	  
	  	for(Termin t:kalTermine)
	  	{
	  		if(check)
	  		{
	  			%><h2>Termine</h2><%
	  			check=false;
	  		}
	  		%>
	  		
	  		<li>
			<TABLE class="table">
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Termin: 
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getName()%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Kategorie: 
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getKategorie() %>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Beginn: 
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= sdf.format(t.getBeginndatum().getTime())%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Ende:
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= sdf.format(t.getEnddatum().getTime())%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Ort: 
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getOrt()%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Beschreibung:
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getNotiz()%>
					</TD>
				</TR>
			</TABLE>	
			</li>

			<form  action="SearchServlet" method="post">
			<button name="speichern" class="btn btn-default" type="submit" value="<%= t.getTerminId()%>">Speichern</button>
			</form><br>
			
		<% 		  	
		
	  	}
	  
	}%>	
	<%
	if(searchResults != null)
	{ 
		%><br><% 
		for(Termin t: searchResults)
		{
			%>
	  			
			<TABLE class="table">
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Termin: 
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getName()%>
					</TD>
				</TR>
			<%if(t.getKategorie()!=null)
			{%>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Kategorie: 
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getKategorie() %>
					</TD>
				</TR>
			<%
			}%>	
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Beginn: 
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= sdf.format(t.getBeginndatum().getTime())%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Ende:
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= sdf.format(t.getEnddatum().getTime())%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Ort: 
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getOrt()%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Beschreibung:
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getNotiz()%>
					</TD>
				</TR>
			</TABLE><br>	
			
			</li>	
		<% 	
		}
	}
	}catch(Exception e){
		RequestDispatcher rd = request.getRequestDispatcher("search_privatnutzer.jsp");
		rd.forward(request, response);
	}%>	
</div>


</body>
</html>