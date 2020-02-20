<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="packageBO.Animateur"%>
<%@ page info="Accès Animateur JSP"%>
<!-- pour le prefix on peut mettre ce que l'on veut -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page JSP - Accès Animateur</title>
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
		Animateur animateurConnecte = (Animateur) request.getAttribute("animateurConnecte");
		String val = monNom("Bonjour " + animateurConnecte.get_prenom() + " " + animateurConnecte.get_nom()
				+ " (vous êtes connecté)");
		int id = animateurConnecte.get_id();
		String nom = animateurConnecte.get_nom();
		String prenom = animateurConnecte.get_prenom();
		String email = animateurConnecte.get_email();
		String utilisateur = (String) session.getAttribute("connexion");
	%>
	<div class="container-fluid" id="haut">
		<div class="text-success">
			<h3>Accès Animateur</h3>
		</div>
		<div class="p-3 mb-2 bg-primary text-white"><%=val%></div>
		<br>
		<div>
			<div>
				<ul>
					<li>
						<%
							out.print(animateurConnecte.toString());
						%>
					</li>
					<br>
					<li>Modifier votre mot de passe :
						<div>
							<form method="POST" name="mdpForm" action="majMdpAnimateur">
								<input type="hidden" name="idAnimateur" value="<%=id%>">
								<input type="text" name="majMdpAnimateur"
									placeholder="Nouveau mot de passe ?"> <input
									class="btn btn-success" type="submit" value="Valider"
									title="Valider votre nouveau mot de passe ?">
							</form>
						</div>
					</li>
				</ul>
			</div>

		</div>
		<div>
			<ul>
				<li>Mettre à jour vos données personnelles :<br> <br>
					<form method="POST" name="majDataForm" action="majDataAnimateur">
						<input type="hidden" name="idAnimateur" value="<%=id%>">
						<div>
							<input type="text" name="majNom" value="<%=nom%>"><span
								class="text-primary"> NOM</span><br> <br> <input
								type="text" name="majPrenom" value="<%=prenom%>"><span
								class="text-primary"> PRENOM</span><br> <br> <input
								type="text" name="majEmail" value="<%=email%>"><span
								class="text-primary"> EMAIL</span><br> <br> <input
								class="btn btn-success" type="submit" value="Mettre à jour"
								title="Mettre à jour vos données personnelles ?">
						</div>
					</form>
				</li>
			</ul>
		</div>
		<br>
		<div>
			<form method="POST" name="deconnexionForm"
				action="ServletDeconnexion">
				<input class="btn btn-danger" type="submit" value="Déconnexion"
					title="Se déconnecter ?">
			</form>
		</div>
		<br>
		<div>
			<a class="btn btn-primary" data-toggle="accueil" href="./"
				role="button" aria-expanded="false" aria-controls="accueil"
				title="Retour à l'accueil ?"> Retour Accueil </a> <a
				class="btn btn-success" data-toggle="accesFormations"
				href="ServletListeFormations" role="button" aria-expanded="false"
				aria-controls="accesFormations" title="Accéder aux formations ?">
				Accéder aux formations </a> <br> <br>
			<form method="POST" name="supprimerCompteForm"
				action="supprimerCompte">
				<input type="hidden" name="id" value="<%=id%>"> <input
					type="hidden" name="utilisateur" value="<%=utilisateur%>">
				<input class="btn btn-danger" type="submit"
					value="Supprimer votre compte"
					title="Supprimer votre compte utilisateur ?">
			</form>
		</div>
	</div>
	<!-- https://www.javatpoint.com/jsp-include-action -->
	<%@include file="/pagesJSP/piedDePage.jspf"%>
</body>
</html>