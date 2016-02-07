<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
    <%@page import="swe.terminkalender.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/standard.css">
<link rel="stylesheet" href="css/bootstrap.min.css">         
<script src="js/bootstrap.min.js"></script> 
<title>Übersicht Analytiker</title>
</head>
<body>

    <nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand">MiCalenDar</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="overview_analytiker.jsp">Übersicht</a></li>
          </ul>
         <div id="navbar" class="navbar-collapse collapse">
         	 <ul class="nav navbar-nav navbar-right">
              <li><a><%= session.getAttribute( "loggedInAna" ) %></a></li>
              <form class="navbar-form navbar-right" action="LogoutAdminServlet" method="post">
              <li><button type="submit" class="btn btn-danger" name="logout" >Logout</button></li>
              </form>
      	 	</ul>
      	 </div>
        </div><!-- /.nav-collapse -->
       </div><!-- /.container -->
    </nav><!-- /.navbar -->

	
 

 	<% 
 	BenutzerManagement man = new BenutzerManagement(); 
 	if(request.getSession().getAttribute("loggedInAna") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
 		%>
 		
 		<div class="container">
 		     <div class="row">
       			 <div class="col-md-6">
 		
 	<h3>User</h3>	
 	
<table class="table">
	<tr>
		<td width="150" valign="top">
			Anzahl der Privatnutzer: 
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungPrivatnutzer() %><br><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Anzahl der Analytiker: 
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungAnalytiker() %><br><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Anzahl der Veranstalter:
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungVeranstalter() %><br><br>
				
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Anzahl der Benutzer:
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungAnalytiker() + man.berechnungPrivatnutzer() + man.berechnungVeranstalter() %><br><br>
				
		</td>
	</tr>
</table>
			
 	<h3>Kalender</h3>
 	
 	<table class="table">
	<tr>
		<td width="150" valign="top">
			Anzahl der Kalender: 
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungKalender() %><br><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Anzahl der Kalender(Kultur): 
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungKalenderKultur() %><br><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Anzahl der Kalender(Arbeit):
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungKalenderArbeit() %><br><br>
				
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Anzahl der Kalender(Familie):
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungKalenderFamilie() %><br><br>
				
		</td>
	</tr>
		<tr>
		<td width="150" valign="top">
			Anzahl der Kalender(Freizeit):
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungKalenderFreizeit() %><br><br>
				
		</td>
	</tr>
		<tr>
		<td width="150" valign="top">
			Anzahl der Kalender(Nachtleben):
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungKalenderNachtleben() %><br><br>
				
		</td>
	</tr>
		<tr>
		<td width="150" valign="top">
			Anzahl der Kalender(Politik):
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungKalenderPolitik() %><br><br>
				
		</td>
	</tr>
		<tr>
		<td width="150" valign="top">
			Anzahl der Kalender(Sport):
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungKalenderSport() %><br><br>
				
		</td>
	</tr>
</table>
 	<h3>Termin</h3>
 	
 	 	<table class="table">
	<tr>
		<td width="150" valign="top">
			Anzahl der Termine: 
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungTermin() %><br><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Anzahl der Termine(Kultur): 
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungKultur() %><br><br>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Anzahl der Termine(Arbeit):
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungArbeit() %><br><br>
				
		</td>
	</tr>
	<tr>
		<td width="150" valign="top">
			Anzahl der Termine(Familie):
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungFamilie() %><br><br>
				
		</td>
	</tr>
		<tr>
		<td width="150" valign="top">
			Anzahl der Termine(Freizeit):
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungFreizeit() %><br><br>
				
		</td>
	</tr>
		<tr>
		<td width="150" valign="top">
			Anzahl der Termine(Nachtleben):
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungNachtleben() %><br><br>
				
		</td>
	</tr>
		<tr>
		<td width="150" valign="top">
			Anzahl der Termine(Politik):
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungPolitik() %><br><br>
				
		</td>
	</tr>
		<tr>
		<td width="150" valign="top">
			Anzahl der Termine(Sport):
				
		</td>
		<td width="100" valign="top">
			<%= man.berechnungSport() %><br><br>
				
		</td>
	</tr>
</table>
 	
 	
</div>

</div>

</div>





</body>
</html>