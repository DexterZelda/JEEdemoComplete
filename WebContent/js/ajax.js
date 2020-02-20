/**
 * Fichier Ajax
 * 
 * https://developer.mozilla.org/fr/docs/Web/Guide/AJAX
 * 
 */

// Vérification du navigateur
function createXHR() {
	if (window.XMLHttpRequest) {
		xhr = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		// si internet explorer
		xhr = new ActiveXObject("Msxml2.XMLHTTP");
	}
	return xhr;
}

// Récupérer l'objet compatible (chaines de caractères et fonction)
function executerRequeteXML(url, params, okFunction, errorFunction) {
	let xhr = createXHR();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				okFunction(xhr.responseXML);
			} else {
				errorFunction(xhr.status);
			}
		}
	}
	xhr.open("GET", url + "?" + params, true);
	xhr.send(null);
}

// Requête GET (chaines de caractères et fonction)
function executerRequete(url, params, okFunction, errorFunction) {
	let xhr = createXHR();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				okFunction(xhr.responseText);
			} else {
				errorFunction(xhr.status);
			}
		}
	}
	xhr.open("GET", url + "?" + params, true);
	xhr.send(null);
}

// Requête POST (chaines de caractères et fonction)
function executerRequetePost(url, params, okFunction, errorFunction) {
	let xhr = createXHR();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				okFunction(xhr.responseText);
			} else {
				errorFunction(xhr.status);
			}
		}
	}
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Accept", "application/json");
	xhr.send(params);
}