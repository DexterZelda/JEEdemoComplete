<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda Notes</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<!-- http://www.favicon.pro/fr/ -->
<link rel="shortcut icon" type="image/x-icon" href="../favicon.ico">
<script type="text/javascript" src="../js/ajax.js"></script>
<script type="text/javascript" src="../js/heure.js"></script>
<!-- JQUERY -->
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="text-success">
			<h3>Agenda Notes</h3>
		</div>
		<br>
		<div>
			<hr style="border-color: blue">
			<form method="POST" name="ajouterForm" action="/Demo1/rest/agenda"
				id="addSubmit">
				<div class="form-group">
					<span class="text-primary">NOTE</span> <label for="ControlTextarea"></label>
					<textarea class="form-control" id="contenuAdd" name="contenu"
						rows="3"></textarea>
				</div>
				<input type="date" name="dateNote" id="idDate"> <span
					class="text-primary">DATE</span><br> <br> <input
					type="button" class="btn btn-success" value="Ajouter"
					title="Ajouter cette note ?" onClick="majPageValidAddNote();">
			</form>
		</div>
		<div id="agendaNotes">
			<script type="text/javascript">
			<!-- ici s'affiche le ou les résultats -->
				executerRequete('http://localhost:8080/Demo1/rest/agenda', '',
						notes_ok, erreur);
			</script>
		</div>
		<br> <br>
		<div>
			<a class="btn btn-primary" data-toggle="accueil" href="/Demo1"
				role="button" aria-expanded="false" aria-controls="accueil"
				title="Retour à l'accueil ?"> Retour Accueil </a>
		</div>
	</div>
	<!-- https://www.javatpoint.com/jsp-include-action -->
	<%@include file="piedDePage.jspf"%>
</body>
</html>