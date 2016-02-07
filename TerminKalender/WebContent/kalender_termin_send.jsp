<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
<%@page import="swe.terminkalender.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/standard.css">
<link rel="stylesheet" href="css/bootstrap.min.css">         
<script src="js/bootstrap.min.js"></script> 
<title>Kalender eintragen</title>
</head>
<body>


<nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand">MiCalenDar</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
			<li><a href="overview_veranstalter.jsp">Übersicht</a></li>
			<li><a href="search_veranstalter.jsp">Suchen</a></li>
			<li><a href="kalender_veranstalter.jsp">Kalender erstellen</a></li>
			<li class="active"><a href="kalender_termin_send.jsp">Kalender verschicken</a></li>
			<li><a href="termin_send.jsp">Termin verschicken</a></li>
          </ul>
         <div id="navbar" class="navbar-collapse collapse">
         	 <ul class="nav navbar-nav navbar-right">
              <li><a><%= session.getAttribute( "loggedInVeran" ) %></a></li>
              <form class="navbar-form navbar-right" action="LogoutAdminServlet" method="post">
              <li><button type="submit" class="btn btn-danger" name="logout" >Logout</button></li>
              </form>
      	 	</ul>
      	 </div>
        </div><!-- /.nav-collapse -->
       </div><!-- /.container -->
    </nav><!-- /.navbar -->



<div class="row">

<div class="col-md-6">

<div class="center">
<form action="ShowKalenderVeranstalter" method="post">
  <h2>Kalenderübersicht</h2>
  
	<ul>
<% 	try{
	SerializedBenutzerDAO dao = new SerializedBenutzerDAO();
	//ArrayList<Benutzer> nutzerlist =  dao.ListOfBenutzer();

	BenutzerManagement man = new BenutzerManagement();
	if(request.getSession().getAttribute("loggedInVeran") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
	String username = request.getSession().getAttribute("loggedInVeran").toString();
	Veranstalter benutzer = (Veranstalter) man.getVeranstalterByUsername(username);
	
	ArrayList<Privatnutzer> nutzerlist = benutzer.getBenutzerListe();
	
	ArrayList<Kalender> kalenderList = new ArrayList<Kalender>();
	kalenderList = benutzer.getKalenderListe();
	if(kalenderList == null){
	}else{
		for(Kalender kalender : kalenderList) {	%>
		
			
 		
 	<div class="radio">
		 <input type="radio" name="kalender" value="<%= kalender.getKalenderId() %>">
	</div>
		<table class="table">
	<tr>
		<td width="150" valign="top">
			Kalendername: 				
		</td>
		<td width="100" valign="top">
			<%= kalender.getName() %><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Kategorie: 	
		</td>
		<td width="100" valign="top">
			<%= kalender.getKategorie() %><br>
		</td>
	</tr>
</table><br>
		
		
<% 
	}
} %>
</div>
 </div>
 <div class="row">	
 	<div class="col-md-4">
 	
	<h2>Liste der Privatnutzer</h2>
	
	<%  
	if(nutzerlist!=null){ 
		for (Privatnutzer ben : nutzerlist)
		{
			%>
				
				
		<input type="radio" name="sex" value="<%= ben.getBenutzername() %>" ><%= ben.getBenutzername()%>
				
				 <br><% 
				}
	}
}catch(Exception e){
	RequestDispatcher rd = request.getRequestDispatcher("kalender_termin_send.jsp");
	rd.forward(request, response);
}
			
	
%>	
<br>
<button class="btn btn-default" type="submit" value="senden">Senden</button>
</form>
<br>





</div>
</div>
</body>
</html>