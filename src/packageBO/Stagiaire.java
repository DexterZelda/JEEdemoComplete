package packageBO;

public class Stagiaire {
	// Les attributs
	private int _id;
	private String _nom;
	private String _prenom;
	private String _email;
	private String _motdepasse;

	// Constructeurs
	public Stagiaire(int id, String nom, String prenom, String email, String motdepasse) {
		this._id = id;
		this._nom = nom;
		this._prenom = prenom;
		this._email = email;
		this._motdepasse = motdepasse;
	}

	public Stagiaire(String nom, String prenom, String email, String motdepasse) {
		this._nom = nom;
		this._prenom = prenom;
		this._email = email;
		this._motdepasse = motdepasse;
	}

	public Stagiaire() {
	}

	// Getters et Setters
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_nom() {
		return _nom;
	}

	public void set_nom(String _nom) {
		this._nom = _nom;
	}

	public String get_prenom() {
		return _prenom;
	}

	public void set_prenom(String _prenom) {
		this._prenom = _prenom;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_motdepasse() {
		return _motdepasse;
	}

	public void set_motdepasse(String _motdepasse) {
		this._motdepasse = _motdepasse;
	}

	// Méthodes
	public String toString() {
		String tmpMDP = (!this._motdepasse.equals("") ? this._motdepasse
				: "<span class='text-danger'>aucun mot de passe n'est défini</span>");
		return "<span class='font-weight-bold'><span class='text-success'>Vous êtes bien connecté en tant que :</span></span><br><br>Stagiaire N° "
				+ this._id + "<br>Email = " + this._email
				+ "<span class='text-success'> (qui est votre identifiant)</span><br>Mot de passe = " + tmpMDP;
	}
}