<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
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
			<li class="active"><a href="kalender_veranstalter.jsp">Kalender erstellen</a></li>
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


<div class="center">
  <h2>Kalender erstellen</h2>
  <% 
	if(request.getSession().getAttribute("loggedInVeran") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
  %>
  
	<br>
	<form action="AddKalender" method="post">
	<div class="form-group">
	
	<input type="text" class="form-control" id="usr" name="titel_kalender" required="required" title="Geben Sie einen Titel ein." placeholder="Name des Kalender eintragen">
	<br>
	<label for="sel1">Kategorie wählen:</label>
	<select class="form-control" id="sel1" name="kategorie">
    	<option value="kultur">Kultur</option>
    	<option value="sport">Sport</option>
    	<option value="familie">Familie</option>
    	<option value="politik">Politik</option>
    	<option value="freizeit">Freizeit</option>
    	<option value="nachtleben">Nachtleben</option>
    	<option value="arbeit">Arbeit</option>
 	 </select>
 	 </div>
  	
  	<button class="btn btn-default" type="submit" value="erstellen">Erstellen</button>	
	</form>
	
	
</div>


</body>
</html>