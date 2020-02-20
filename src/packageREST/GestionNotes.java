package packageREST;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import packageBO.Note;
import packageDAL.DalDAOJdbc;
import packageDAL.DalException;

/*
 * 
 * https://fr.wikipedia.org/wiki/Representational_state_transfer (avec Postman)
 * 
 */

@Path("/agenda")
public class GestionNotes {

	// Les attributs
	private static List<Note> listeNotes;

	/**** Récupère et affichera la liste de notes ****/
	@GET
	public List<Note> getNotes() {
		try {
			listeNotes = DalDAOJdbc.selectAllNotes();
		} catch (DalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeNotes;
	}

	/**** Ajoute une note ****/
	@POST
	public void addNote(@FormParam("contenu") String contenu, @FormParam("dateNote") String dateNote) {
		// Formatage de la date String => Date
		// https://mkyong.com/java/how-to-convert-string-to-date-java/
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
		Note note;
		try {
			note = new Note(contenu, dateFormat.parse(dateNote));
			DalDAOJdbc.addNote(note);
		} catch (DalException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**** Supprimera une note et liste se mettra à jour ****/
	@DELETE
	@Path("/{cle}")
	// https://www.freecodecamp.org/forum/t/javascript-ajax-delete-method-not-working/277446
	// https://laracasts.com/discuss/channels/javascript/how-to-do-an-ajax-delete
	public void deleteNote2(@PathParam("id") int id) {
		try {
			DalDAOJdbc.supprimerNote(id);
		} catch (DalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
