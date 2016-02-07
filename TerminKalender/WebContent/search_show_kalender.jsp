<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="swe.terminkalender.model.*" %>
<%@ page import = "java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/standard.css">
<link rel="stylesheet" href="css/bootstrap.min.css">         
<script src="js/bootstrap.min.js"></script> 
<title>Kalender anzeigen</title>
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
<h2>Termine</h2>
<% try{
	if(request.getSession().getAttribute("loggedInPrivate") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
%>
<form  action="SearchServlet" method="post">
<%Kalender k = (Kalender)request.getAttribute("kalender");%> 
	<button name="kalenderspeichern" class="btn btn-default" type="submit" value="<%= k.getKalenderId()%>">Kalender übernehmen</button>
</form><br>
<%Kalender k1 = (Kalender)request.getAttribute("kalender"); 
ArrayList<Termin> termine = k1.getTerminlist();
SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
for(Termin t: termine)
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
	<form  action="SearchServlet" method="post">
	<button name="speichern" class="btn btn-default" type="submit" value="<%= t.getTerminId()%>">Speichern</button>
	</form><br>
	
	
<%
	}
	}catch(Exception e){
		RequestDispatcher rd = request.getRequestDispatcher("search_show_kalender.jsp");
		rd.forward(request, response);
	}%>
</div>
</body>
</html>