<%@page import="java.util.*"%>
<%@page import="java.io.PrintWriter" %>
<%@page import="swe.terminkalender.model.*"%>
<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/standard.css">
<link rel="stylesheet" href="css/bootstrap.min.css">         
<script src="js/bootstrap.min.js"></script> 
<title>Übersicht Administrator</title>
</head>
<body>

    <nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand">MiCalenDar</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="overview_admin.jsp">Übersicht</a></li>
          </ul>
         <div id="navbar" class="navbar-collapse collapse">
         	 <ul class="nav navbar-nav navbar-right">
              <li><a><%= session.getAttribute( "loggedInUser" ) %></a></li>
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

  
  <form action="OverviewAdminServlet" method="post">
  <ul>
<%  try{
	if(request.getSession().getAttribute("loggedInUser") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
 	SerializedBenutzerDAO dao = new SerializedBenutzerDAO();
	ArrayList<Benutzer> nutzerlist = new ArrayList<Benutzer>();
			nutzerlist = dao.ListOfBenutzer();
			
			PrintWriter today = response.getWriter();
			String pri = "Privatnutzer";
			String ver = "Veranstalter";
			String ana = "Analytiker";
			request.getSession();
			request.setAttribute("PRIVAT", pri);
			request.setAttribute("VERAN", ver);
			request.setAttribute("ANALY", ana);
			
	if(nutzerlist == null){
		
		
		
	}
	
	else{
		
		
		
		for (Benutzer benutzer : nutzerlist)
		{  %>
			
			<div class="col-md-4">
			
	<%		if(benutzer instanceof Privatnutzer){ %>
			
			<h2><%= request.getAttribute( "PRIVAT" ) %></h2>
	
<table class="table">
	<tr>
		<td width="150" valign="top">
			Benutzername: 
				
		</td>
		<td width="100" valign="top">
			<%= benutzer.getBenutzername()%><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Passwort: 
				
		</td>
		<td width="100" valign="top">
			<%= benutzer.getPasswort()%><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Status: 
				
		</td>
		<td width="100" valign="top">
			<%= benutzer.getStatus()%><br>
		</td>
	</tr>
</table>
		<% 	
			}
			else if(benutzer instanceof Veranstalter){ 
			
			Veranstalter v = (Veranstalter)benutzer;%>
			
			<h2><%= request.getAttribute( "VERAN" ) %></h2>
			
<table class="table">
	<tr>
		<td width="150" valign="top">
			Benutzername: 
				
		</td>
		<td width="100" valign="top">
			<%= benutzer.getBenutzername()%><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Passwort: 
				
		</td>
		<td width="100" valign="top">
			<%= benutzer.getPasswort()%><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Status: 
				
		</td>
		<td width="100" valign="top">
			<%= benutzer.getStatus()%><br>
		</td>
	</tr>
</table>	
		<% 	}
			else{ 	
			Analytiker a = (Analytiker)benutzer;%>
			<h2><%= request.getAttribute( "ANALY" ) %></h2>
<table class="table">
	<tr>
		<td width="150" valign="top">
			Benutzername: 
				
		</td>
		<td width="100" valign="top">
			<%= benutzer.getBenutzername()%><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Passwort: 
				
		</td>
		<td width="100" valign="top">
			<%= benutzer.getPasswort()%><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Status: 
				
		</td>
		<td width="100" valign="top">
			<%= benutzer.getStatus()%><br>
		</td>
	</tr>
</table>
			
			
		<% 	} %>
			
			<button name="name" class="btn btn-danger" type="submit" value="<%= benutzer.getId()%>">Löschen</button>
			<button name="status" class="btn btn-info" type="submit" value="<%= benutzer.getId()%>">Status ändern</button>
			<br><br>
			
			</div>
			<% 
			
		}
	}
}catch(Exception e){
	RequestDispatcher rd = request.getRequestDispatcher("overview_admin.jsp");
	rd.forward(request, response);
}
%>
		
</ul>
</form>
</div>


</body>
</html>