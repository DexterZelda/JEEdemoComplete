<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="packageBO.Formation"%>
<%@ page import="packageBO.Inscription"%>
<%@ page import="packageBO.Stagiaire"%>
<%@ page info="Liste des formations JSP"%>
<!-- pour le prefix on peut mettre ce que l'on veut -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page JSP - Liste des formations</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script type="text/javascript" src="./js/ajax.js"></script>
<script type="text/javascript" src="./js/heure.js"></script>
<!-- http://www.favicon.pro/fr/ -->
<link rel="shortcut icon" type="image/x-icon" href="./favicon.ico">
</head>
<%!private String monNom(String valeur) {
		return valeur;
	}

	boolean inFormation = false;
	String lesStagiairesInFormations = "";

	private boolean stagiaireInFormation(int id, List<Inscription> listeI, List<Stagiaire> listeS) {
		for (Inscription element : listeI) {
			if (id == element.get_idFormation()) {
				for (Stagiaire s : listeS) {
					if (element.get_idStagiaire() == s.get_id()) {
						inFormation = true;
						lesStagiairesInFormations += "[" + s.get_id() + "] ";
						break;
					}
				}
			}
		}
		return inFormation;
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
		<div class="text-success">
			<h3>Liste des formations :</h3>
		</div>
		<div class="p-3 mb-2 bg-primary text-white"><%=val%></div>
		<br>
		<div>
			<ul>
				<%
					List<Formation> listeFormations = (List<Formation>) request.getAttribute("listeFormations");
					List<Inscription> listeInscriptions = (List<Inscription>) request.getAttribute("listeInscriptions");
					List<Stagiaire> listeStagiaire = (List<Stagiaire>) request.getAttribute("listeStagiaire");
					String connexion = "";
					String email = "";
					String mdp = "";
					if (connected) {
						connexion = (String) session.getAttribute("connexion");
						email = (String) session.getAttribute("email");
						mdp = (String) session.getAttribute("mdp");
					}
					for (Formation element : listeFormations) {
						int id = element.get_id();
						String libelle = element.get_libelle();
						String description = element.get_description();
						String debut = element.get_debut();
						String fin = element.get_fin();
						out.print("<li>" + element.toString() + "</li>");
						inFormation = stagiaireInFormation(id, listeInscriptions, listeStagiaire);
						if (connected && connexion.equals("animateur") && !inFormation) {
							out.print(
									"<form method='POST' name='smForm' action='servletSM'><input type='hidden' name='idFormation' value='");
							out.print(id);
							out.print(
									"'><input class='btn btn-danger' type='submit' name='suppr' value='Supprimer' title='Supprimer cette formation n°");
							out.print(id);
							out.print(" ?'>");
				%>
				<details>
					<summary>Modifier la formation</summary>
					<fieldset>
						<div>
							<input type="text" name="libelle" value="<%=libelle%>"><span
								class="text-primary"> LIBELLE</span><br> <br>
							<div class="form-group">
								<span class="text-primary"> DESCRIPTION</span> <label
									for="ControlTextarea"></label>
								<textarea class="form-control" id="ControlTextarea"
									name="description" rows="3"><%=description%></textarea>
							</div>
							<br> <input type="text" name="debut" value="<%=debut%>"
								disabled><span class="text-primary"> DATE DE
								DEBUT</span><br> <br> <input type="text" name="fin"
								value="<%=fin%>" disabled><span class="text-primary">
								DATE DE FIN</span><br> <br>
						</div>
						<div>
							<input class="btn btn-success" type="submit" value="Modifier"
								name="modif">
						</div>
						<fieldset>
				</details>
				<%
					} else if (connected && connexion.equals("animateur") && inFormation) {
							out.print("impossible de supprimer ou modifier : stagiaires " + lesStagiairesInFormations
									+ "en formation");
						}
						// initialisation des variables et fin du formulaire
						inFormation = false;
						lesStagiairesInFormations = "";
						out.print("</form>");
					}
				%>
				<%
					if (connected && connexion.equals("animateur")) {
				%>
				<br>
				<br>
				<!-- https://www.alsacreations.com/article/lire/1335-html5-details-summary.html -->
				<form method="POST" name="addFormationForm"
					action="ServletAddFormation">
					<details>
						<summary>Ajouter une formation</summary>
						<fieldset>
							<div>
								<input type="text" name="addLibelle"
									placeholder="Rentrer un libelle"><span
									class="text-primary"> LIBELLE</span><br> <br>
								<div class="form-group">
									<span class="text-primary"> DESCRIPTION</span> <label
										for="ControlTextarea"></label>
									<textarea class="form-control" id="ControlTextarea"
										name="addDescription" rows="3"></textarea>
								</div>
								<br> <input type="date" name="addDateDeb"><span
									class="text-primary"> DATE DE DEBUT</span><br> <br> <input
									type="date" name="addDateFin"><span
									class="text-primary"> DATE DE FIN</span><br> <br>
							</div>
							<div>
								<input class="btn btn-info" type="submit"
									value="Ajouter cette formation">
							</div>
							<fieldset>
					</details>
				</form>
				<%
					}
				%>
			</ul>
		</div>
		<br>
		<div>
			<a class="btn btn-primary" data-toggle="accueil" href="./"
				role="button" aria-expanded="false" aria-controls="accueil"
				title="Retour à l'accueil ?"> Retour Accueil </a>
			<%
				if (connected) {
					if (connexion.equals("animateur")) {
						// On passe en session la liste des stagiaires
						session.setAttribute("listeStagiaire", listeStagiaire);
			%><br> <br>
			<form method="POST" name="animateurForm" action="ServletCnxAnimateur">
				<input type="hidden" name="idAnimateur" value="<%=email%>">
				<input type="hidden" name="mdpAnimateur" value="<%=mdp%>"> <input
					class="btn btn-success" type="submit"
					value="Accéder à votre espace Animateur"
					title="Accéder à votre espace Animateur ?">
			</form>
			<br>
			<div class="text-success">
				LES STAGIAIRES : <span id="listeDesStagiaires"> <!-- ici va s'afficher via Servlet LS -->
				</span> <br> <input type="button"
					onclick="executerRequete('LS','',ls_ok,erreur);"
					value="Afficher la liste des stagiaires" class="btn btn-success"
					title="Afficher la liste des stagiaires ?">
			</div>
			<%
				} else if (connexion.equals("stagiaire")) {
			%><br> <br>
			<form method="POST" name="stagiaireForm" action="ServletCnxStagiaire">
				<input type="hidden" name="idStagiaire" value="<%=email%>">
				<input type="hidden" name="mdpStagiaire" value="<%=mdp%>"> <input
					class="btn btn-success" type="submit"
					value="Accéder à votre espace Stagiaire"
					title="Accéder à votre espace Stagiaire ?">
			</form>
			<%
				}
				}
			%>
		</div>
	</div>
	<!-- https://www.javatpoint.com/jsp-include-action -->
	<%@include file="/pagesJSP/piedDePage.jspf"%>
</body>
</html>