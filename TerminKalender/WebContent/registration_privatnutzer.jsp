<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/signin.css">
<title>Registrierung Privatnutzer</title>
<link rel="stylesheet" href="css/bootstrap.min.css">         
<script src="js/bootstrap.min.js"></script> 
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


<div class="container">
 
  <form class ="form-signin" action="RegistrationServlet" method="post">
  <h2>Sign Up now</h2>
	
					
				<input type="text" id="inputUsername" class="form-control" name="username" size="25" placeholder="Username eingeben" maxlength="25" required="required">
				<br>
				
				<input type="password" id="inputPasswort" class="form-control" name="password" size="25" placeholder="Passwort eingeben" maxlength="25" required="required">
				
				
				<input type="password" id="inputPasswortw" class="form-control" name="passwordw" size="25" placeholder="Passwort wiederholen" maxlength="25" required="required">
				
				
				<input type="submit"  class="btn btn-lg btn-primary btn-block" value="Sign Up!"> 
			
	</form>
	
</div>


</body>
</html>