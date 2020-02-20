package packageBO;

import java.util.Date;

public class Note {

	// Les attributs
	private int _id;
	private String _contenu;
	private Date _date;

	// Constructeurs
	public Note(int id, String contenu, Date date) {
		this._id = id;
		this._contenu = contenu;
		this._date = date;
	}

	public Note(String contenu, Date date) {
		this._contenu = contenu;
		this._date = date;
	}

	// Getters et Setters
	public int get_id() {
		return this._id;
	}

	public void set_id(int id) {
		this._id = id;
	}

	public String get_contenu() {
		return this._contenu;
	}

	public void set_contenu(String contenu) {
		this._contenu = contenu;
	}

	public Date get_date() {
		return this._date;
	}

	public void set_date(Date date) {
		this._date = date;
	}
}
