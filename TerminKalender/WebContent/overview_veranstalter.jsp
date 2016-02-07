<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
    <%@page import="swe.terminkalender.model.*"%>
    <%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/standard.css">
<link rel="stylesheet" href="css/bootstrap.min.css">         
<script src="js/bootstrap.min.js"></script> 
<title>Übersicht Veranstalter</title>
</head>
<body>

<nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand">MiCalenDar</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
			<li class="active"><a href="overview_veranstalter.jsp">Übersicht</a></li>
			<li><a href="search_veranstalter.jsp">Suchen</a></li>
			<li><a href="kalender_veranstalter.jsp">Kalender erstellen</a></li>
			<li><a href="kalender_termin_send.jsp">Kalender verschicken</a></li>
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


<div class="container">
      <!-- Example row of columns -->
      <div class="row">
      
     
      <h2 class="text-center">Übersicht der Kalender</h2>
		
  
  <br>
  <form action="OverviewVeranstalterServlet" method="post">
	
	<ul>
<% 	try{
	SerializedBenutzerDAO b = new SerializedBenutzerDAO();
	ArrayList <Benutzer> sendList = b.ListOfBenutzer();
	
	BenutzerManagement man = new BenutzerManagement();
	
	if(request.getSession().getAttribute("loggedInVeran") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
	String username = request.getSession().getAttribute("loggedInVeran").toString();
	Veranstalter benutzer = (Veranstalter) man.getVeranstalterByUsername(username);
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm"); 
	ArrayList<Kalender> kalenderList = new ArrayList<Kalender>();
	kalenderList = benutzer.getKalenderListe();
	String now = "";
	if(kalenderList == null){
	}else{
		
		for(Kalender kalender : kalenderList) {	%>
		
		<div class="col-md-4">
		
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
		<button name="kalender_anzeigen" class="btn btn-info" type="submit" value="<%= kalender.getKalenderId()%>">Anzeigen</button>
		<button name="termin_add" class="btn btn-success" type="submit" value="<%= kalender.getKalenderId()%>">Termin hinzufügen</button>
		<br><br>
		
		</div>
		 <% 
	}
}
%>	
</form>
</div>
</div>
<br>

<h2 class="text-center">Übersicht der Termine</h2>
<br>
<% 
		
	if(kalenderList.isEmpty()){
		
	}else{
		
		for(int j=0; j<kalenderList.size();j++){
			Kalender k = kalenderList.get(j);
			 
			for(int i=0; i<k.getTerminlist().size();i++){
				Termin t = k.getTerminlist().get(i);
				%>
				
				<div class="col-md-6 col-md-offset-3">
				
				<table class="table">
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						<strong>Termin: </strong>
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getName()%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						<strong>Kategorie: </strong>
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getKategorie()%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						<strong>Beginn: </strong>
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= sdf.format(t.getBeginndatum().getTime())%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						<strong>Ende:</strong>
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= sdf.format(t.getEnddatum().getTime())%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						<strong>Ort: </strong>
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getOrt()%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						<strong>Beschreibung:</strong>
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getNotiz()%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						<strong>Anzahl geteilt:</strong> 
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= t.getAnzahlGeteilt()%>
					</TD>
				</TR>
			</TABLE>
			<br>
			</div>
			<% 
				}	
			
		}
	}
}catch(Exception e){
	RequestDispatcher rd = request.getRequestDispatcher("overview_veranstalter.jsp");
	rd.forward(request, response);
}
			%>


		
</div>


</body>
</html>