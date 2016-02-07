<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/signin.css">
<link rel="stylesheet" href="css/bootstrap.min.css">         
<script src="js/bootstrap.min.js"></script> 

<title>Einloggen oder Registrierung</title>
</head>
<body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand">MiCalenDar</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
        </div>
      </div>
    </nav>
    

  
  <form class ="form-signin" action="SigninServlet" method="post">
	
	<h2 class="center"> Login and Sign Up</h2>
	

				<input type="text" class="form-control" name="username" size="25" maxlength="25"	placeholder="Username eingeben" ><br>
				
				
				<input type="password"  class="form-control" name="password" size="25" maxlength="25"	placeholder="Passwort eingeben" >

				<input type="submit"  class="btn btn-lg btn-primary btn-block" value="Login"> 
	
				<input type="submit" formaction="registration_privatnutzer.jsp" class="btn btn-lg btn-primary btn-block"  value="Sign Up!">
				
				<br><a href="registration_veranstalter_analytiker.jsp" >Sign up organizer or analyst</a>	

	
</form>

</body>
</html>