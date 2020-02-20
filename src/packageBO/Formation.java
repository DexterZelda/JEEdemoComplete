package packageBO;

public class Formation {

	// Les Attributs
	private int _id;
	private String _libelle;
	private String _description;
	private String _debut;
	private String _fin;

	// Constructeurs
	public Formation(int id, String libelle, String description, String debut, String fin) {
		this._id = id;
		this._libelle = libelle;
		this._description = description;
		this._debut = debut;
		this._fin = fin;
	}

	public Formation(String libelle, String description, String debut, String fin) {
		this._libelle = libelle;
		this._description = description;
		this._debut = debut;
		this._fin = fin;
	}

	public Formation() {
	}

	// Getters et Setters
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_libelle() {
		return _libelle;
	}

	public void set_libelle(String _libelle) {
		this._libelle = _libelle;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public String get_debut() {
		return _debut;
	}

	public void set_debut(String _debut) {
		this._debut = _debut;
	}

	public String get_fin() {
		return _fin;
	}

	public void set_fin(String _fin) {
		this._fin = _fin;
	}

	// Méthodes
	public String toString() {
		return "<span class='font-weight-bold'><span class='text-danger'>Formation n°" + this._id + " : "
				+ this._libelle + "</span></span><br><span class='text-info'>" + this._description
				+ "</span><br><span class='text-success'>Du " + this._debut + " au " + this._fin + "</span>";
	}
}
