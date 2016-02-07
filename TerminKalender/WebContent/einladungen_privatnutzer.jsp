<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="swe.terminkalender.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/standard.css">
<link rel="stylesheet" href="css/bootstrap.min.css">         
<script src="js/bootstrap.min.js"></script> 
<title>Übersicht Einladungen</title>
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
			<li><a href="search_privatnutzer.jsp">Suchen</a></li>
			<li><a href="termin_privatnutzer.jsp">Termin erstellen</a></li>
			<li class="active"><a href="einladungen_privatnutzer.jsp">Kalendereinladungen</a></li>
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
    
    <div class="container">
      <!-- Example row of columns -->
      <div class="row">

<div class="center">
  <h2>Kalender Einladungen Übersicht</h2>
  <form action="KalenderConfirm" method="post">
  
	<% 	
	try{
	BenutzerManagement man = new BenutzerManagement();
	if(request.getSession().getAttribute("loggedInPrivate") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
	String username = request.getSession().getAttribute("loggedInPrivate").toString();
	Privatnutzer benutzer = (Privatnutzer) man.getPrivatnutzerByUsername(username);
	
	ArrayList<Kalender> kalenderList = new ArrayList<Kalender>();
	kalenderList = benutzer.getKalenderlist();
	if(kalenderList == null){
	}else{
		for(Kalender kalender : kalenderList) {	%>
		<table class="table">
	<tr>
		<td width="150" valign="top">
			<strong>Kalendername:</strong>
				
		</td>
		<td width="100" valign="top">
			<%= kalender.getName() %><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			<strong>Kategorie:</strong> 
				
		</td>
		<td width="100" valign="top">
			<%= kalender.getKategorie() %><br>
		</td>
	</tr>
</table>
		<button name="kalender_loeschen" class="btn btn-danger" type="submit" value="<%= kalender.getKalenderId()%>">Löschen</button>
		<button name="kalender_annehmen" class="btn btn-info" type="submit" value="<%= kalender.getKalenderId()%>">Annehmen</button> 
		<button name="kalender_show" class="btn btn-success" type="submit" value="<%= kalender.getKalenderId()%>">Anzeigen</button> 
		<br><br>		
		 <% 		
		}
	}
	}catch(Exception e){
		RequestDispatcher rd = request.getRequestDispatcher("einladungen_privatnutzer.jsp");
		rd.forward(request, response);
	}
		
	
%>	
</form>
</div>
</div>
</div>

</body>
</html>