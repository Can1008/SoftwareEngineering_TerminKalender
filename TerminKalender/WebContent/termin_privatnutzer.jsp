
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
<title>Termin erstellen Privatnutzer</title>
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
			<li class="active"><a href="termin_privatnutzer.jsp">Termin erstellen</a></li>
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
  <h2>Termin erstellen</h2>
  
<%
	if(request.getSession().getAttribute("loggedInPrivate") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
	Calendar cal = Calendar.getInstance();
	Calendar cal2 = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	cal2.add(Calendar.YEAR, 1);
%>
	<form  name="data" action="AddTerminPrivatServlet"  method="post">
	<table class="table">
		<tr>
			<td width="150" valign="top">		
				Titel: 
			</td>
			<td width="100" valign="top">
				<input type="text" class="form-control" name="titel" required="required" title="Geben Sie einen Titel ein." placeholder="Titel">
			</td>
		</tr>
		<tr>
			<td width="150" valign="top">
				Ort:
			</td>
			<td width="100" valign="top">
				<input type="text" class="form-control" name="ort" placeholder="Ort">
		</tr>
		<tr>
			<td width="150" valign="top">
				Beschreibung:
			</td>
			<td width="100" valign="top">
				<input type="text" class="form-control" name="beschreibung" placeholder="Beschreibung">
			</td>
		</tr>
		<tr>
			<td width="150" valign="top">
				Beginn: 
			</td>
			<td width="100" valign="top">
				<input type="datetime-local" class="form-control" name="beginntag" min="<%= sdf.format(cal.getTime()) %>" value="<%= sdf.format(cal.getTime()) %>"  pattern="[0-9]{4}\-[0-1]{1}[0-2]{1}\-[0-3]{1}[0-9]{1}\T[0-2]{1}[0-9]{1}\:[0-5]{1}[0-9]{1}" title="yyyy-MM-dd'T'HH:mm  Beispiel: 2015-01-01T12:00" placeholder="2015-01-01T12:00" required="required">
			<font color="red">${message1}</font>
			</td>
		</tr>
		<tr>
			<td width="150" valign="top">
				Ende:
			</td>
			<td width="100" valign="top">
				<input type="datetime-local" class="form-control" name="endtag" min="<%= sdf.format(cal.getTime()) %>" value="<%= sdf.format(cal.getTime()) %>" pattern="[0-9]{4}\-[0-1]{1}[0-2]{1}\-[0-3]{1}[0-9]{1}\T[0-2]{1}[0-9]{1}\:[0-5]{1}[0-9]{1}" title="yyyy-MM-dd'T'HH:mm   Beispiel: 2015-01-01T12:00" placeholder="2015-01-01T12:00" required="required">
			 <font color="red">${message2}<br></font>
			 <font color="red">${message}</font>
			</td>
		</tr>
		<tr>
			<td width="150" valign="top">
				<input type="checkbox"  name="kontrollCheck" onclick="var input = document.getElementById('wiederholen'); if(this.checked){ input.disabled = false; input.focus();}else{input.disabled=true;}" />
				Wöchentlich 
				<br>Wiederholen bis:
			</td>
			<td width="100" valign="top">
   				 <input type="date" class="form-control" id="wiederholen" name="wiederholen" min="<%= sdf2.format(cal.getTime()) %>" max="<%= sdf2.format(cal2.getTime()) %>" value="<%= sdf2.format(cal.getTime()) %>" pattern="[0-9]{4}\-[0-1]{1}[0-2]{1}\-[0-3]{1}[0-9]{1}" title="yyyy-MM-dd Beispiel: 2015-01-01
Termine können maximal ein Jahr lang wiederholt werden."  disabled="disabled" required="required"/>
				<font color="red">${message3}</font>
				<font color="red">${message4}</font>
			</td>
		</tr>
		<tr>
			<td width="150" valign="top">
				<input type="checkbox"  name="benachrCheck" onclick="var input = document.getElementById('tagevorher'); if(this.checked){ input.disabled = false; input.focus();}else{input.disabled=true;}" />
				Erinnerung Tage zuvor:
			</td>
			<td width="100" valign="top">
   				 <input type="number" class="form-control" name="tagevorher" id="tagevorher" placeholder="Tage vorher" min="1" max="365" pattern=".{1,}" title="Geben Sie bitte einen Wert von 0-365 ein." required="required" disabled="disabled">
			</td>
		</tr>
			<td width="100" valign="top"><br>
				<input type="submit" class="btn btn-default"  value="erstellen">
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