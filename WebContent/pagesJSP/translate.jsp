<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- http://blog.paumard.org/cours/servlet/chap05-jsp-i18n.html -->
<title>Page JSP - Traduction</title>
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
			<h3>Traducteur</h3>
		</div>
		<p>
			Locale =
			<%=request.getHeader("Accept-language")%></p>
		<fmt:setBundle basename="message_fr" var="francais" />
		<fmt:setLocale value="en" />
		<fmt:bundle basename="message">
			<fmt:message key="msg_bonheur"></fmt:message>
		</fmt:bundle>
		<fmt:message key="ms_bonjour" bundle="${francais}">
			<fmt:param value="David"></fmt:param>
		</fmt:message>
		<br> <br>
		<div>
			<span class="text-success">Votre prénom :</span>
			<form method="POST" name="testForm" action="affTranslate.jsp">
				<input type="text" name="testParam"
					placeholder="Saisir quelque chose"> <input
					class="btn btn-primary" type="submit" value="Valider">
			</form>
		</div>
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