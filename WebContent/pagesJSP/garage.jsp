<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- pour le prefix on peut mettre ce que l'on veut -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- https://www.javatpoint.com/jsp-useBean-action -->
<!-- https://www.jmdoudoux.fr/java/dej/chap-jstl.htm -->
<!-- https://www.javatpoint.com/Iteration-using-jsp-custom-tag -->
<!-- COPIER LE jstl-1.2.jar dans lib de WEB-INF = OK -->
<title>useBean - Les Voitures</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<!-- http://www.favicon.pro/fr/ -->
<link rel="shortcut icon" type="image/x-icon" href="./favicon.ico">
</head>
<%!private String monNom(String valeur) {
		return valeur;
	}%>
<body>
	<%
		String val = monNom("Bonjour David");
	%>
	<div class="container-fluid">
		<div class="text-success">
			<h3>Voici Les Voitures :</h3>
		</div>
		<div class="p-3 mb-2 bg-primary text-white"><%=val%></div>
		<br>
		<jsp:useBean id="laVoiture" scope="request"
			class="packageBEAN.Voiture">
		</jsp:useBean>
		<span class="text-success"> Une Voiture useBean :</span><br> <br>
		Marque :
		<jsp:getProperty property="_marque" name="laVoiture" /><br>Type
		:
		<jsp:getProperty property="_type" name="laVoiture" /><br>Nombre
		de KM :
		<jsp:getProperty property="_nbKm" name="laVoiture" /><br> <br>
		<!-- SANS CODE JAVA et avec La bibliothèque jstl Core -->
		<span class="text-success"> Et par Le langage EL (Expression
			Language) :</span><br> <br> Marque : ${laVoiture._marque}<br>
		Type : ${laVoiture._type}<br> Nombre de KM : ${laVoiture._nbKm}<br>
		<br> ${message} <br> <br> <span class="text-success">
			Les Voitures par itération :</span><br> <br>
		<ul>
			<c:forEach var="V" items="${lesVoitures}" varStatus="compteur">
				<li><span class="text-danger"><span
						class="font-weight-bold">Voiture N° ${compteur.count}</span></span><br>Marque
					: ${V._marque}<br> Type : ${V._type}<br> Nombre de KM :
					${V._nbKm}<br></li>
			</c:forEach>
		</ul>
		<br>
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