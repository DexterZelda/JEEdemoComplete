package packageREST;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import packageBO.Animal;

/*
 * 
 * https://fr.wikipedia.org/wiki/Representational_state_transfer (avec Postman)
 * 
 */

@Path("/zoo")
@Produces("application/xml,application/json")
public class GestionZoo {

	// Pour le jeu d'essai
	private static List<Animal> parc;
	static {
		parc = new ArrayList<Animal>();
		parc.add(new Animal(1, "Lion", "Vert", 20, 4));
		parc.add(new Animal(2, "Chat", "Violet", 120, 4));
		parc.add(new Animal(3, "Chien", "Noir", 50, 6));
		parc.add(new Animal(4, "Kangourou", "Bleu", 80, 2));
	}

	@GET
	public List<Animal> getZoo() {
		return parc;
	}

	@GET
	@Path("/{id}")
	public Animal getAnimal(@PathParam("id") int id) {
		return parc.get(id - 1);
	}

	@POST
	public Animal addAnimal(@FormParam("id") int id, @FormParam("nom") String nom, @FormParam("couleur") String couleur,
			@FormParam("taille") int taille, @FormParam("nbPattes") int nbPattes) {
		Animal animal = new Animal(id, nom, couleur, taille, nbPattes);
		parc.add(animal);
		return animal;
	}

	@DELETE
	@Path("/{cle}")
	public void deleteAnimal(@PathParam("id") int id) {
		parc.remove(id - 1);
	}
}
