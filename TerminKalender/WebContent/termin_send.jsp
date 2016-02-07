<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
    <%@page import="java.text.SimpleDateFormat"%>
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
			<li><a href="kalender_termin_send.jsp">Kalender verschicken</a></li>
			<li class="active"><a href="termin_send.jsp">Termin verschicken</a></li>
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

  <h2>Terminübersicht</h2>
 
  <form action="SendTermin" method="post">
   <ul>
<% 
	try{
	BenutzerManagement man = new BenutzerManagement();
	SerializedBenutzerDAO b = new SerializedBenutzerDAO();
	
	if(request.getSession().getAttribute("loggedInVeran") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
	String username = request.getSession().getAttribute("loggedInVeran").toString();
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm"); 
	Veranstalter benutzer = (Veranstalter) man.getVeranstalterByUsername(username);
	ArrayList<Privatnutzer> nutzerlist = benutzer.getBenutzerListe();
	ArrayList<Kalender> kalenderList = benutzer.getKalenderListe();
	
		for(int j=0; j<kalenderList.size();j++){
			Kalender k = kalenderList.get(j);
			 
			for(int i=0; i<k.getTerminlist().size();i++){
				
				Termin t = k.getTerminlist().get(i);
				%>
				<div class="radio">
				<input type="radio" name="termin" value="<%= t.getTerminId()%>" >
				</div>
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
			<br>
							
<% 		
				}	
			}
		
	
			%>
			</div>
 </div>
 <div class="row">	
 	<div class="col-md-4">
			
			<h2>Liste der Privatnutzer</h2>
			
						<% if(nutzerlist!=null){ 
							for (Privatnutzer ben : nutzerlist){
				%>
					
			<input type="radio" name="termin_send" value="<%= ben.getBenutzername() %>" ><%= ben.getBenutzername()%>	
				
					 
					
					 <br><% 
					}
				}
			}catch(Exception e){
		RequestDispatcher rd = request.getRequestDispatcher("termin_send.jsp");
		rd.forward(request, response);
	}%>
<br>
<button class="btn btn-default" type="submit" value="senden">Senden</button>
</form>

</div>
</div>
	
</div>


</body>
</html>