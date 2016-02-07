<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import ="swe.terminkalender.model.*" %>
<%@ page import = "java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/standard.css">
<link rel="stylesheet" href="css/bootstrap.min.css">         
<script src="js/bootstrap.min.js"></script> 
<title>Suchen Veranstalter</title>
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
			<li class="active"><a href="search_veranstalter.jsp">Suchen</a></li>
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
  
  <h2>Suchen</h2>
  <br>
  <form action="SearchServletVeran" method="post">
  <div class="form-group">
  
  
  <input type="text" class="form-control" id="search" name="suchen_veranstalter">
  <br>
  <label for ="kat">Kategorie wählen</label><br>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Kultur"> Kultur</label>
  <label class="radio-inline"> <input type="radio" name="kategorie" value="Sport"> Sport</label>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Familie"> Familie</label>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Politik"> Politik</label><br>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Freizeit"> Freizeit</label>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Nachtleben"> Nachtleben</label>
  <label class="radio-inline"><input type="radio" name="kategorie" value="Arbeit"> Arbeit</label>
  </div>
  <button class="btn btn-default" type="submit" value="suchen">Suchen</button>	
  <hr>
</form>
</div>
<% 
try{
ArrayList<Termin> searchResults = new ArrayList<Termin>();
searchResults = (ArrayList<Termin>)request.getAttribute("searchResults");
SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm");

	if(request.getSession().getAttribute("loggedInVeran") == null){
		RequestDispatcher rd = request.getRequestDispatcher("login_sign_up.jsp");
		rd.forward(request, response);
	}


if(searchResults!=null)
{
	for(Termin t: searchResults)
	{
		%>
		
<div class="container">
      <!-- Example row of columns -->
      <div class="row">
      
     	 <div class="col-md-6 col-md-offset-3">
	
			<TABLE class="table">
				<TR>
					<TD WIDTH="150" VALIGN="TOP">
						<strong>Termin:</strong> 
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
						<%= t.getKategorie() %>
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
			</TABLE><br>	
			</div>
			
			
	
<% 		  	
	}
}
}catch(Exception e){
	RequestDispatcher rd = request.getRequestDispatcher("search_veranstalter.jsp");
	rd.forward(request, response);
}
%>	
	</div>
			</div>	

</body>
</html>