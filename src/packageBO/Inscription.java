package packageBO;

import java.io.Serializable;

// https://www.jmdoudoux.fr/java/dej/chap-serialisation.htm
public class Inscription implements Serializable {
	private static final long serialVersionUID = 1L;

	// Les Attributs
	private int _idStagiaire;
	private int _idFormation;
	private String _date;

	// Constructeur
	public Inscription(int idStagiaire, int idFormation, String date) {
		this._idStagiaire = idStagiaire;
		this._idFormation = idFormation;
		this._date = date;
	}

	public Inscription() {
	}

	// Getters et Setters
	public int get_idStagiaire() {
		return _idStagiaire;
	}

	public void set_idStagiaire(int _idStagiaire) {
		this._idStagiaire = _idStagiaire;
	}

	public int get_idFormation() {
		return _idFormation;
	}

	public void set_idFormation(int _idFormation) {
		this._idFormation = _idFormation;
	}

	public String get_date() {
		return _date;
	}

	public void set_date(String _date) {
		this._date = _date;
	}
}
