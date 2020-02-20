<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page JSP - Affichage du texte</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<!-- http://www.favicon.pro/fr/ -->
<link rel="shortcut icon" type="image/x-icon" href="../favicon.ico">
</head>
<body>
	<%
		String val = request.getParameter("testParam");
		if (val == "") {
			val = "aucun texte saisi";
		}
	%>
	<div class="container-fluid">
		<div class="text-success">
			<h3>Le texte saisi est :</h3>
		</div>
		<div><%=val%></div>
		<br>
		<div>
			<a class="btn btn-primary" data-toggle="accueil" href="../"
				role="button" aria-expanded="false" aria-controls="accueil"
				title="Retour à l'accueil ?"> Retour Accueil </a>
		</div>
	</div>
	<!-- https://www.javatpoint.com/jsp-include-action -->
	<%@include file="piedDePage.jspf"%>
</body>
</html>