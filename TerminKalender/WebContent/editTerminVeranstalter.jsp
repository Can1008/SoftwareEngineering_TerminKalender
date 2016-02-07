<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"
    import="java.text.SimpleDateFormat"
	import= "java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/standard.css">
<link rel="stylesheet" href="css/bootstrap.min.css">         
<script src="js/bootstrap.min.js"></script> 
<title>Termin erstellen Veranstalter</title>
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


<div class="center">
  <h2>Termin bearbeiten</h2>  
  <% 
	if(request.getSession().getAttribute("loggedInVeran") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
  %>
	<form name="data" action="EditTerminVeranstalter" method="post">
	<table class="table">
		<tr>
			<td width="150" valign="top">
				Titel: 
					
			</td>
			<td width="100" valign="top">
				<input type="text" class="form-control" name="titelEdit" value="<%= session.getAttribute("TerminName") %>" required="required" title="Geben Sie einen Titel ein." placeholder="Titel">
			</td>
		</tr>
		<tr>
			<td width="150" valign="top">
				Ort: 
					
			</td>
			<td width="100" valign="top">
				<input type="text" class="form-control" name="ortEdit" 	value="<%= session.getAttribute("TerminOrt") %>"	placeholder="Ort">
			</td>
		</tr>
		<tr>
			<td width="150" valign="top">
				Beschreibung:
					
			</td>
			<td width="100" valign="top">
				<input type="text" class="form-control" name="beschreibungEdit" value="<%= session.getAttribute("TerminBeschreibung") %>"	placeholder="Beschreibung">
			</td>
		</tr>
		<tr>
			<td width="150" valign="top">
				Beginn: 
					
			</td>
			<td width="100" valign="top">
				<input type="datetime-local" class="form-control" name="beginnEdit" min="<%= sdf.format(cal.getTime()) %>" value="<%= session.getAttribute("beginndatumEdit") %>"  pattern="[0-9]{4}\-[0-1]{1}[0-2]{1}\-[0-3]{1}[0-9]{1}\T[0-2]{1}[0-9]{1}\:[0-5]{1}[0-9]{1}" title="yyyy-MM-dd'T'HH:mm  Beispiel: 2015-01-01T12:00" placeholder="2015-01-01T12:00" required="required">
				<font color="red">${message1}</font>	
			</td>
		</tr>
		<tr>
			<td width="150" valign="top">
				Ende: 
			
			</td>
			<td width="100" valign="top">
				<input type="datetime-local" class="form-control" name="endEdit" min="<%= sdf.format(cal.getTime()) %>" value="<%= session.getAttribute("enddatumEdit") %>" pattern="[0-9]{4}\-[0-1]{1}[0-2]{1}\-[0-3]{1}[0-9]{1}\T[0-2]{1}[0-9]{1}\:[0-5]{1}[0-9]{1}" title="yyyy-MM-dd'T'HH:mm   Beispiel: 2015-01-01T12:00" placeholder="2015-01-01T12:00" required="required">
			<font color="red">${message2}<br></font>
			<font color="red">${message}</font>
			</td>
		</tr>
				<input type="hidden" name="TerminID" value="<%= session.getAttribute("TerminID") %>" ><br>
		<tr>
			<td width="150" valign="top"><br>
				<input type="submit" class="btn btn-default" value="aktualisieren">
			</td>
		</tr>
	</table>	
	</form>
	<ul>
	</ul>
</div>

<div id="msg"></div>


</body>
</html>