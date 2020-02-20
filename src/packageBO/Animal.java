package packageBO;

public class Animal {

	// Les attributs
	private int _id;
	private String _nom;
	private String _couleur;
	private int _taille;
	private int _nombreDePattes;

	// Constructeur
	public Animal(int id, String nom, String couleur, int taille, int nombreDePattes) {
		super();
		this._id = id;
		this._nom = nom;
		this._couleur = couleur;
		this._taille = taille;
		this._nombreDePattes = nombreDePattes;
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

	public String get_couleur() {
		return _couleur;
	}

	public void set_couleur(String _couleur) {
		this._couleur = _couleur;
	}

	public int get_taille() {
		return _taille;
	}

	public void set_taille(int _taille) {
		this._taille = _taille;
	}

	public int get_nombreDePattes() {
		return _nombreDePattes;
	}

	public void set_nombreDePattes(int _nombreDePattes) {
		this._nombreDePattes = _nombreDePattes;
	}
}
