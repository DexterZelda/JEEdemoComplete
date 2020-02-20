<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test Web Service REST</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<!-- http://www.favicon.pro/fr/ -->
<link rel="shortcut icon" type="image/x-icon" href="../favicon.ico">
</head>
<body>
	<div class="container-fluid">
		<div class="text-success">
			<h3>Différents Tests de Web Service REST</h3>
		</div>
		<br>
		<div>
			<form method="GET" name="test1Form" action="/Demo1/rest/couleurs">
				<input class="btn btn-info" type="submit"
					value="Test 1 (affiche la liste des couleurs)"
					title="/Demo1/rest/couleurs en GET">
			</form>
		</div>
		<br>
		<div>
			<form method="GET" name="test2Form"
				action="/Demo1/rest/couleurs/bleu">
				<input class="btn btn-info" type="submit"
					value="Test 2 (affiche une couleur fixée)"
					title="/Demo1/rest/couleurs/bleu en GET">
			</form>
		</div>
		<br>
		<div>
			<form method="GET" name="test3Form" action="/Demo1/rest/couleurs/2">
				<input class="btn btn-info" type="submit"
					value="Test 3 (affiche une couleur by id)"
					title="/Demo1/rest/couleurs/2 en GET">
			</form>
		</div>
		<br>
		<div>
			<form method="GET" name="test4Form" action="/Demo1/rest/zoo">
				<input class="btn btn-success" type="submit"
					value="Test 4 (affichage du zoo JSON)"
					title="/Demo1/rest/zoo en GET">
			</form>
		</div>
		<br>
		<div>
			<form method="GET" name="test5Form" action="/Demo1/rest/zoo/2">
				<input class="btn btn-info" type="submit"
					value="Test 5 (affiche un animal by id JSON)"
					title="/Demo1/rest/zoo/2 en GET">
			</form>
		</div>
		<br>
		<div>
			<form method="POST" name="test6Form" action="/Demo1/rest/zoo">
				<input type="text" placeholder="Rentrer un id" name="id"> <input
					type="text" placeholder="Rentrer un nom" name="nom"> <input
					type="text" placeholder="Rentrer une couleur" name="couleur">
				<input type="text" placeholder="Rentrer une taille" name="taille">
				<input type="text" placeholder="Rentrer nombre de pattes"
					name="nbPattes"> <input class="btn btn-info" type="submit"
					value="Test 6 (ajoute un animal JSON)"
					title="/Demo1/rest/zoo en POST">
			</form>
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