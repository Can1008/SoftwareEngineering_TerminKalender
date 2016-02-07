<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/signin.css">
<title>Registrierung Veranstalter oder Analytiker</title>
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


  
  
  	<form class="form-signin" action="RegistrationServlet_Veranstalter_Analytiker" method="post">
		<h2 >Sign up organizer or analyst</h2>
		
		<div class="checkbox">
				<input type="radio" name="test" value="veranstalter" checked> Veranstalter 
				<input type="radio" name="test" value="analytiker"> Analytiker
		</div>
		
				<input type="text" class="form-control" name="name" size="25" maxlength="25" placeholder="Name eingeben" required="required">
				<br>
				<input type="password" class="form-control" name="password" size="25" maxlength="25" placeholder="Password eingeben" required="required">
				<br>
				<input type="password" class="form-control" name="passwordwiederholen" size="25" maxlength="25" placeholder="Password wiederholen" required="required">
				<br>
				<input type="text" class="form-control" name="company" size="25" maxlength="25" placeholder="Firma eingeben" required="required">
				<br>
				<input type="email" class="form-control" name="email" size="25" maxlength="25" placeholder="muster@outlook.com" required="required">
				<br>
				<input type="submit"  class="btn btn-lg btn-primary btn-block" value="Sign Up!"> 	
	</form>



</body>
</html>