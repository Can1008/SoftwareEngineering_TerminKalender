<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
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
<title>Übersicht Privatnutzer</title>
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
			<li><a href="einladungen_privatnutzer.jsp">Kalendereinladungen</a></li>
			<li><a href="einladungen_termin_privatnutzer.jsp">Termineinladungen</a></li>
			<li><a href="benachrichtigungen.jsp">Benachrichtigungen</a></li>
			<li class="active"><a href="show_Top_Termin_Privatnutzer.jsp">Top 10</a></li>
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
  <h2>Übersicht</h2>
  
  <div class="center">
    <form action="" method="post">
    <%
    try{
	if(request.getSession().getAttribute("loggedInPrivate") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	} 
	BenutzerManagement man = new BenutzerManagement();
	ArrayList<Termin> showliste = new ArrayList<Termin>();
	showliste = man.showTopTermin();
	int rang = 1;
	if(showliste == null){
	}else{
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm"); 
		for(int i=0; i<10 && i<showliste.size(); i++){
			if(showliste.get(i) != null && showliste.get(i).getAnzahlGeteilt() > 0){
				Termin termine = showliste.get(i);
				
				if(i>0 && showliste.get(i).getAnzahlGeteilt() != showliste.get(i-1).getAnzahlGeteilt()){
					rang++;
				}
		%>
	<br>
			
			<TABLE class="table">
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Rang: 
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= rang%>
					</TD>
				</TR>
				<TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Termin: 
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= termine.getName()%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Beginn: 
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= sdf.format(termine.getBeginndatum().getTime())%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Ende:
					</TD> 
					<TD WIDTH="100" VALIGN="TOP">
						<%= sdf.format(termine.getEnddatum().getTime())%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Ort: 
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= termine.getOrt()%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Beschreibung:
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= termine.getNotiz()%>
					</TD>
				</TR>
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						Geteilt: 
					</TD>
					<TD WIDTH="100" VALIGN="TOP">
						<%= termine.getAnzahlGeteilt()%>
					</TD
				><TR>
			</TABLE>
				
	
<%
			}
		}
	}
	}catch(Exception e){
		RequestDispatcher rd = request.getRequestDispatcher("show_Top_Termin_Privatnutzer.jsp");
		rd.forward(request, response);
	}
%>



</form>

		
</div>


</div>

</body>
</html>