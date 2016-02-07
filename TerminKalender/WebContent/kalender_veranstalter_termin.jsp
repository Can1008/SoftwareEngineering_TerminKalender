<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
<%@page import="swe.terminkalender.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
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
  <h2>Terminübersicht</h2>
  <form action="DeleteTerminVeranstalter" method="post">

<div class="form-group">
<label for="sort"></label>
  <select id="sort" class="form-control"name="dropdownSort">
    <option value="alphabetisch">alphabetisch</option>
    <option value="datAuf">frühester Termin zuerst</option>
    <option value="datAb">spätester Termin zuerst</option>
  </select>
  <br>
  <input type="submit"  class="btn btn-default"name="buttonVerEdit" value="Sortieren">
</div>
<% 
	try{
	BenutzerManagement man = new BenutzerManagement();
	SerializedBenutzerDAO b = new SerializedBenutzerDAO();
	ArrayList <Benutzer> sendList = b.ListOfBenutzer();
	
	
	if(request.getSession().getAttribute("loggedInVeran") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}
	String username = request.getSession().getAttribute("loggedInVeran").toString();
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm"); 
	Veranstalter benutzer = (Veranstalter) man.getVeranstalterByUsername(username);
	ArrayList<Kalender> kalenderList = benutzer.getKalenderListe();
	String terminid = (String)session.getAttribute( "TerminId" );
	int id = Integer.parseInt(terminid);
	
	
	if(kalenderList.isEmpty()){
	}else{
		for(int j=0; j<kalenderList.size();j++){
			Kalender k = kalenderList.get(j);
			if(k.getKalenderId()==id){ 
			for(int i=0; i<k.getTerminlist().size();i++){
				
				Termin t = k.getTerminlist().get(i);
				%>
				<br>
		
			<input type="radio" name="name" value="<%= t.getTerminId()%>">
				<br>
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
		</li>		
	<br>
			<% 
				}	
			}
		}
	}
	}catch(Exception e){
		RequestDispatcher rd = request.getRequestDispatcher("kalender_veranstalter_termin.jsp");
		rd.forward(request, response);
	}
			%>
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
				<br><input type="submit" class="btn btn-default" name="buttonVerEdit" value="Bestätigen"/>
			</TD>
			<TD WIDTH="100" VALIGN="TOP">
			</TD>
		</tr>
	</table>

</ul>
</form>

		
</div>

</body>
</html>