package packageBEAN;

import java.io.Serializable;

/**** POUR https://www.javatpoint.com/jsp-useBean-action ****/
public class Voiture implements Serializable {
	private static final long serialVersionUID = 1L;

	// Les attributs
	private String _marque;
	private String _type;
	private int _nbKm;

	// Constructeurs
	public Voiture() {
		super();
	}

	public Voiture(String marque, String type, int nbKm) {
		super();
		this._marque = marque;
		this._type = type;
		this._nbKm = nbKm;
	}

	// Getters et Setters
	public String get_marque() {
		return _marque;
	}

	public void set_marque(String _marque) {
		this._marque = _marque;
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public int get_nbKm() {
		return _nbKm;
	}

	public void set_nbKm(int _nbKm) {
		this._nbKm = _nbKm;
	}
}
