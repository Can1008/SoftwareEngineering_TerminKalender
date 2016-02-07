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
           
       		<li class="active"><a href="overview_privatnutzer.jsp">Übersicht</a></li>
			<li><a href="search_privatnutzer.jsp">Suchen</a></li>
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
  <h2>Übersicht</h2>
  
  <div class="center">
    <form action="OverviewPrivatnutzerEditServlet" method="post">
  <div class="form-group">
  <label for="sort_d"></label>
  <select class="form-control" name="dropdownSort" id="sort_d">
    <option value="alphabetisch">alphabetisch</option>
    <option value="datAuf">frühester Termin zuerst</option>
    <option value="datAb">spätester Termin zuerst</option>
  </select>
  <br>
  <input type="submit" name="button" class="btn btn-default" value="Sortieren">
 </div>
 
 <hr>
  
  
<% try{
	BenutzerManagement man = new BenutzerManagement();
	
	if(request.getSession().getAttribute("loggedInPrivate") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
	String username = request.getSession().getAttribute("loggedInPrivate").toString();
	Privatnutzer benutzer = (Privatnutzer) man.getPrivatnutzerByUsername(username);
	
	int i=0;
	try{
	i = (Integer) man.sizeOfBenachrichtigungen(benutzer);
	}catch(Exception e){
		RequestDispatcher rd = request.getRequestDispatcher("overview_privatnutzer.jsp");
		rd.forward(request, response);
	}
	  if(i==1 && i!=0){
		  %><a href="benachrichtigungen.jsp"><font color="#cc0000">1 Benachrichtigung!</font></a><%
	  }if(i>1){
		  %><a href="benachrichtigungen.jsp"><font color="#cc0000"><%=i %> Benachrichtigungen!</font></a><%
	  }
	ArrayList<Termin> terminList = (ArrayList<Termin>)request.getSession().getAttribute("terminlistSort");
	
	if(terminList == null){
	}else{
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm"); 
		for (Termin termine : terminList){ 
		%>
	<br>
		<div class="radio">
			<input type="radio" name="name" value="<%= termine.getTerminId()%>">
		</div>	
			<TABLE class="table">
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
			</TABLE>	
	
<%			
		}
	}
}catch(Exception e){
	RequestDispatcher rd = request.getRequestDispatcher("overview_privatnutzer.jsp");
	rd.forward(request, response);
}
%>
<hr>
	<table>
		<tr>
			<TD WIDTH="150" VALIGN="TOP">
				<input type="radio" name="editdelete" value="edit"> Bearbeiten
			</TD>
			<TD WIDTH="100" VALIGN="TOP">
				<input type="radio" name="editdelete" value="delete"> Löschen 
			</TD>
		</tr>
		<tr>
			<TD WIDTH="150" VALIGN="TOP">
				<br><input type="submit" class="btn btn-default" name="button" value="Bestätigen"/>
			</TD>
			<TD WIDTH="100" VALIGN="TOP">
			</TD>
		</tr>
	</table>


</form>

		
</div>


</div>

</body>
</html>