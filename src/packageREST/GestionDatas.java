package packageREST;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/*
 * 
 * https://fr.wikipedia.org/wiki/Representational_state_transfer (avec Postman)
 * 
 */

@Path("/couleurs")
public class GestionDatas {

	// Les attributs
	private static List<String> listeCouleurs;

	// Initialiser cette classe (Cas unique ici sans instance)
	static {
		listeCouleurs = new ArrayList<String>();
		// Jeu d'essai (simulation pour tester sinon couche DAO BD)
		listeCouleurs.add("Bleu");
		listeCouleurs.add("Vert");
		listeCouleurs.add("Rouge");
		listeCouleurs.add("Jaune");
	}

	/**** /urlprojet/rest/couleurs ****/
	@GET
	public String getCouleurs() {
		return listeCouleurs.toString();
	}

	/**** /urlprojet/rest/couleurs/bleu ****/
	@GET
	@Path("/bleu")
	public String getCouleurBleu() {
		return "Bleu";
	}

	/**** /urlprojet/rest/couleurs/2 ****/
	@GET
	@Path("/{id}")
	public String getCouleur(@PathParam("id") int id) {
		return listeCouleurs.get(id);
	}
}
