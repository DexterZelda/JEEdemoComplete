/**
 * Fichier Ajax
 * 
 * https://developer.mozilla.org/fr/docs/Web/Guide/AJAX
 * 
 */

// Appelé dans index.jsp (affichage heure et voitures)
function ok(reponse) {
	document.getElementById("date").innerHTML = reponse;
}

// Appelé dans index.jsp (affichage ZOO)
function zoo_ok(reponse) {
	var repServer = JSON.parse(reponse);
	// init le span vide de index (sinon affiche successivement)
	document.getElementById("zoo").innerHTML = "";
	for (let i = 0; i < repServer.length; i++) {
		document.getElementById("zoo").innerHTML = document
				.getElementById("zoo").innerHTML
				+ "  " + repServer[i]._nom;
	}
}

// Appelé dans agendaNotes.jsp (affichage des Notes)
function notes_ok(reponse) {
	var repServer = JSON.parse(reponse);
	var elem = document.getElementById("agendaNotes");
	var codeHTML = "";
	// https://www.w3schools.com/jsref/met_node_insertadjacenthtml.asp
	for (let i = 0; i < repServer.length; i++) {
		codeHTML += '<br><div><hr style="border-color: blue">';
		codeHTML += '<form method="POST" name="ajouterForm" action="/Demo1/rest/agenda"';
		codeHTML += '<div class="form-group">';
		codeHTML += '<span class="text-primary">NOTE du ' + repServer[i]._date
				+ '</span><label for="ControlTextarea"></label>';
		codeHTML += '<textarea class="form-control" id="contenuSuppr" name="contenu" rows="3">';
		codeHTML += repServer[i]._contenu + '</textarea></div>';
		codeHTML += '<input type="hidden" name="idNote" value="'
				+ repServer[i]._id + '">';
		codeHTML += '<input class="btn btn-danger" type="button" value="Supprimer" title="Supprimer cette note n°'
				+ repServer[i]._id
				+ ' ?" onClick="majPageValidSupprNote();"></div>';
		// Ajout du codeHTML dans la balise <div id="agendaNotes"> de
		// agendaNotes.jsp
		elem.insertAdjacentHTML('afterend', codeHTML);
		// Initialisation du codeHTML
		codeHTML = "";
	}
}

// Appelé dans index.jsp (affichage d'un animal)
function zoo_ok_2(reponse) {
	document.getElementById("zoo2").innerHTML = reponse;
}

// Appelé dans listeformations.jsp
function ls_ok(reponse) {
	document.getElementById("listeDesStagiaires").innerHTML = reponse;
}

// Message d'alerte
function erreur(status) {
	alert(status);
}

// POUR LA PAGE agendaNotes.jsp et submit le formulaire Ajout Note
function majPageValidAddNote() {
	var ok = confirm('Êtes-vous certain de vouloir ajouter cette note ?');
	if (ok) {
		document.getElementById("addSubmit").submit();
		// Reload la page
		window.location.replace("agendaNotes.jsp");
		// Vide les champs
		// document.getElementById("contenuAdd").innerHTML = "";
		// document.getElementById("idDate").innerHTML = "";
	}
}

// POUR LA PAGE agendaNotes.jsp et Suppression Note
function majPageValidSupprNote() {
	var ok = confirm('Êtes-vous certain de vouloir supprimer cette note ?');
	if (ok) {
		document.getElementById("addSubmit").submit();
		// Reload la page
		window.location.replace("agendaNotes.jsp");
	}
}