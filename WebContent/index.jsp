<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="packageLIREfichier.lireXML"%>
<!-- pour le prefix on peut mettre ce que l'on veut -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TP Web - Accueil</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<!-- http://www.favicon.pro/fr/ -->
<link rel="shortcut icon" type="image/x-icon" href="./favicon.ico">
<script type="text/javascript" src="./js/ajax.js"></script>
<script type="text/javascript" src="./js/heure.js"></script>
</head>
<%!private String monNom(String valeur) {
		return valeur;
	}%>
<body>
	<%
		String prenomSession = (String) session.getAttribute("prenom");
		String nomSession = (String) session.getAttribute("nom");
		String val = "";
		boolean connected = false;
		if (prenomSession != null && nomSession != null) {
			val = monNom("Bonjour " + prenomSession + " " + nomSession + " (vous êtes connecté)");
			connected = true;
		} else if (prenomSession != null && nomSession == null) {
			val = monNom("Bonjour " + prenomSession + " (vous êtes connecté)");
			connected = true;
		} else if (prenomSession == null && nomSession != null) {
			val = monNom("Bonjour " + nomSession + " (vous êtes connecté)");
			connected = true;
		} else {
			val = monNom("aucun utilisateur connecté");
		}
	%>
	<div class="container-fluid" id="haut">
		<div>
			<h1>
				TP Web - Accueil JEE <a href="./"><img src="./img/jee.png"
					width="100" height="60"
					title="Bienvenue dans cette démo JEE - Accueil"></a> <a
					href="https://www.eni-ecole.fr/" target="_blank"><img
					src="./img/eni.png" width="60" height="60"
					title="Aller sur le site web de ENI"></a> <a href="#connexions"
					title="Aller à l'ancrage des connexions">Espace Connexions</a>
				<!-- https://www.alsacreations.com/astuce/lire/5-lien-precis-page-ancre-anchor-diese.html -->
			</h1>
			<%
				lireXML lire = new lireXML("0");
				String timeout = lire.retourDeValeurXML("session-config", "session-timeout",
						"C:/Users/dchantoi2019/Desktop/ENI/WorkspaceEclipse/Demo1/WebContent/WEB-INF/web.xml");
			%>
			<!-- https://www.dewep.net/realisations/compte-a-rebours-en-javascript -->
			<h4 class="text-primary">
				Timeout déconnexion =
				<%=timeout%>
				<%
					if (timeout.equals("0") || timeout.equals("1")) {
				%>minute<%
					} else {
				%>minutes<%
					}
				%>
			</h4>
		</div>
		<div class="p-3 mb-2 bg-primary text-white"><%=val%></div>
		<div class="text-danger">
			<h3>AJAX</h3>
		</div>
		<div class="text-danger">
			Date et Heure du serveur : <span id="date"> <!-- ici va s'afficher le résultat -->
			</span> <br> <input type="button"
				onclick="executerRequete('dateHeure','',ok,erreur);"
				value="Afficher Heure du Serveur" class="btn btn-danger"
				title="Afficher l'heure du serveur ?">
		</div>
		<br>
		<div class="text-danger">
			Affichage des animaux : <span id="zoo"> <!-- ici va s'afficher le résultat -->
			</span> <br> <input type="button"
				onclick="executerRequete('http://localhost:8080/Demo1/rest/zoo','',zoo_ok,erreur);"
				value="Afficher le ZOO" class="btn btn-danger"
				title="Afficher le ZOO ?">
		</div>
		<br>
		<div class="text-danger">
			Affichage d'un animal : <span id="zoo2"> <!-- ici va s'afficher le résultat -->
			</span> <br> <input type="button"
				onclick="executerRequetePost('http://localhost:8080/Demo1/rest/zoo','id=8&nom=Rat&couleur=Azur&taille=200&nbPattes=4',zoo_ok_2,erreur);"
				value="Afficher l'animal" class="btn btn-danger"
				title="Afficher l'animal ?">
		</div>
		<br>
		<div>
			<div class="text-success">
				<h3>Développeur JEE</h3>
			</div>
			<div>
				<!-- https://getbootstrap.com/docs/4.4/components/collapse/#example -->
				<a class="btn btn-primary" data-toggle="collapse"
					href="./monServlet" role="button" aria-expanded="false"
					aria-controls="collapseExample">Servlet 1 méthode GET</a> <a
					class="btn btn-primary" data-toggle="collapse" href="Servlet2"
					role="button" aria-expanded="false" aria-controls="collapseExample">Servlet
					2 méthode GET</a>
			</div>
			<div>
				<br>
				<form method="GET" action="http://www.google.com/custom"
					class="google_form">
					<input class="google_box" type="text" name="q" maxlength="20"
						value="" placeholder="Rechercher sur Google"> <input
						type="submit" value="Recherche"> <input type="hidden"
						name="domains" value="https://www.google.fr/"> <input
						type="hidden" name="meta" value="lr=lang_fr">
				</form>
			</div>
			<br>
			<div class="text-success">
				<h3>Test des servlets en méthode POST</h3>
			</div>
			<div>
				<form method="POST" action="monServlet">
					<input type="text" name="nomClient" placeholder="Rentrer un nom">
					<input type="text" name="prenomClient"
						placeholder="Rentrer un prénom"> <select name="couleur">
						<option value="Rouge">Rouge</option>
						<option value="Vert">Vert</option>
						<option value="Bleu">Bleu</option>
					</select> <input class="btn btn-primary" type="submit"
						value="Servlet 1 méthode POST">
				</form>
			</div>
			<br>
			<div>
				<form method="POST" action="Servlet2">
					<input class="btn btn-primary" type="submit"
						value="Servlet 2 méthode POST">
				</form>
			</div>
			<br>
			<div class="text-success">
				<h3>Test des paramètres dans une page JSP</h3>
			</div>
			<form method="POST" name="testForm" action="./pagesJSP/getparam.jsp">
				<input type="text" name="testParam"
					placeholder="Saisir quelque chose"> <input
					class="btn btn-primary" type="submit"
					value="Envoyer les paramètres">
			</form>
			<br>
			<div class="text-success">
				<h3>Liste des formations</h3>
			</div>
			<div>
				<div>
					<a class="btn btn-primary" data-toggle="collapse"
						href="./formations/servletFormations" role="button"
						aria-expanded="false" aria-controls="collapseExample">Voir la
						liste</a>
				</div>
				<br>
				<div>
					<form method="POST" name="listeForm"
						action="ServletListeFormations">
						<input class="btn btn-success" type="submit"
							value="Voir la liste (et accéder à votre espace si vous êtes connecté)"
							title="Voir la liste des formations ?">
					</form>
				</div>
			</div>
			<br>
			<div class="text-success">
				<h3>Test Servlet Context</h3>
			</div>
			<div>
				<div>
					<form method="POST" name="contextForm" action="testServletContext">
						<input class="btn btn-primary" type="submit"
							value="Tester (affichage console)">
					</form>
				</div>
			</div>
			<br>
			<div class="text-success">
				<h3>Test des Cookies</h3>
			</div>
			<div>
				<div>
					<form method="POST" name="cookiesForm" action="DemoCookie">
						<input class="btn btn-primary" type="submit"
							value="Tester (affichage console)">
					</form>
				</div>
			</div>
			<br>
			<div class="text-success">
				<h3>Test des Sessions</h3>
			</div>
			<div>
				<div>
					<form method="POST" name="sessionsForm" action="DemoSession">
						<input class="btn btn-primary" type="submit"
							value="Tester (affichage console)">
					</form>
				</div>
			</div>
			<br>
			<div class="text-success">
				<h3>Afficher Les Voitures useBean</h3>
			</div>
			<div>
				<div>
					<form method="POST" name="useBeanForm" action="Garage">
						<input class="btn btn-primary" type="submit" value="Afficher">
					</form>
				</div>
			</div>
			<br>
			<div class="text-success">
				<h3>Test des messages properties dans une page JSP</h3>
			</div>
			<form method="POST" name="testForm" action="./pagesJSP/translate.jsp">
				<input class="btn btn-success" type="submit"
					value="Aller à la partie Traducteur"
					title="Aller à la partie Traducteur ?">
			</form>
			<br>
			<div class="text-success">
				<h3>Test Web Service REST</h3>
			</div>
			<form method="POST" name="testForm"
				action="./pagesJSP/testWebService.jsp">
				<input class="btn btn-success" type="submit"
					value="Aller à la partie Test" title="Aller à la partie Test ?">
			</form>
			<br>
			<div class="text-success">
				<h3>Accès Agenda Notes</h3>
			</div>
			<form method="POST" name="testForm"
				action="./pagesJSP/agendaNotes.jsp">
				<input class="btn btn-success" type="submit"
					value="Aller à la partie Agenda Notes"
					title="Aller à la partie Agenda Notes ?">
			</form>
		</div>
		<br>
		<div id="connexions">
			<div class="text-success">
				<h3>Accès animateur</h3>
			</div>
			<div>
				<form method="POST" name="animateurForm"
					action="ServletCnxAnimateur">
					<input type="text" name="idAnimateur"
						placeholder="Votre identifiant ?"><br> <br> <input
						type="password" name="mdpAnimateur"
						placeholder="Votre mot de passe ?"><br> <br> <input
						class="btn btn-primary" type="submit" value="Connexion"
						title="Se connecter ?">
				</form>
			</div>
			<br>
			<div class="text-success">
				<h3>Accès stagiaire</h3>
			</div>
			<div>
				<form method="POST" name="stagiaireForm"
					action="ServletCnxStagiaire">
					<input type="text" name="idStagiaire"
						placeholder="Votre identifiant ?"><br> <br> <input
						type="password" name="mdpStagiaire"
						placeholder="Votre mot de passe ?"><br> <br> <input
						class="btn btn-primary" type="submit" value="Connexion"
						title="Se connecter ?">
				</form>
			</div>
			<br>
			<div class="text-success">
				<h3>Créer un compte utilisateur</h3>
			</div>
			<div>
				<form method="POST" name="compteForm" action="ajouterUnUtilisateur">
					<details>
						<summary>Créer un compte</summary>
						<fieldset>
							<div>
								<input type="text" name="nom" placeholder="Votre Nom ?"><span
									class="text-primary"> NOM</span><br> <br> <input
									type="text" name="prenom" placeholder="Votre Prénom ?"><span
									class="text-primary"> PRENOM</span><br> <br> <input
									type="text" name="email"
									placeholder="Votre Email / Identifiant ?"><span
									class="text-primary"> EMAIL</span><br> <br> <input
									type="password" name="mdp" placeholder="Mot de passe ?"><span
									class="text-primary"> MOT DE PASSE</span><br> <br>
							</div>
							<div>
								<div>
									<input type="radio" id="idAnimateur" name="animateur"
										value="Animateur"> <label for="huey">Animateur</label>
								</div>
								<div>
									<input type="radio" id="idStagiaire" name="stagiaire"
										value="Stagiaire"> <label for="huey">Stagiaire</label>
								</div>
							</div>
							<div>
								<input class="btn btn-info" type="submit"
									value="Créer votre compte">
							</div>
							<fieldset>
					</details>
				</form>
			</div>
		</div>
	</div>
	<!-- https://www.javatpoint.com/jsp-include-action -->
	<%@include file="/pagesJSP/piedDePage.jspf"%>
</body>
</html>