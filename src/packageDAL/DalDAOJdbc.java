package packageDAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletConfig;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import packageBO.Animateur;
import packageBO.Formation;
import packageBO.Inscription;
import packageBO.Note;
import packageBO.Stagiaire;

public class DalDAOJdbc {

	// Variables Globales
	private static Connection con = null;
	private static PreparedStatement stmt = null;

	// Méthode de conexion à la base de données
	private static void connectDB(ServletConfig cfg) {
		try {
			// Etape 1 - Charger le driver jdbc
			DriverManager.registerDriver(new SQLServerDriver());
			// OU Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// Etape 2 - Connection
			String url = cfg.getServletContext().getInitParameter("url_bd");
			con = DriverManager.getConnection(url, cfg.getServletContext().getInitParameter("id_bd"),
					cfg.getServletContext().getInitParameter("mdp_bd"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Méthode de conexion à la base de données
	private static void connectDB_JEE_TP_Static() {
		try {
			// Etape 1 - Charger le driver jdbc
			DriverManager.registerDriver(new SQLServerDriver());

			// Etape 2 - Connection
			String url = "jdbc:sqlserver://localhost;databasename=JEE_TP";
			con = DriverManager.getConnection(url, "sa", "Pa$$w0rd");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Méthode de déconexion à la base de données
	private static void deconnectDB() {
		try {
			if (con != null) {
				con.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**** RETOUR DES TOUTES LES FORMATIONS (LISTE) *******************************/
	public static List<Formation> selectAllFormations(ServletConfig cfg) throws DalException {
		List<Formation> listeFormations = new ArrayList<Formation>();
		// Connexion DB --------------
		connectDB(cfg);
		try {
			// Requête de préparation / paramétrée
			// OU String sql = "select * from formations";
			String sql = "select * from dbo.formations";
			// Quand pas de ? dans la requête ------
			Statement cstmt = con.createStatement();
			// Traiter la requête
			ResultSet rs = cstmt.executeQuery(sql);
			// Formatter les dates
			// https://mkyong.com/java/how-to-convert-string-to-date-java/
			String pattern = "EEEE dd MMMMM yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
			// Traiter le résultat avec lecture des lignes
			while (rs.next()) {
				Formation f = new Formation(rs.getInt(1), rs.getString(2), rs.getString(3),
						dateFormat.format(rs.getDate(4)), dateFormat.format(rs.getDate(5)));
				// Ajout à la liste des formations
				listeFormations.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
		return listeFormations;
	}

	/**** SUPPRIMER UNE FORMATION *******************************/
	public static void deleteFormation(ServletConfig cfg, int idFormation) throws DalException {
		// Connexion DB --------------
		connectDB(cfg);
		try {
			// Requête de préparation / paramétrée
			String sql = "delete from dbo.formations where id=?";
			stmt = con.prepareStatement(sql);
			// Affecte le ? ----------
			stmt.setInt(1, idFormation);
			// Traiter la requête
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}

	/**** MODIFIER UNE FORMATION *******************************/
	public static void modifFormation(ServletConfig cfg, Formation formation) throws DalException {
		// Connexion DB --------------
		connectDB(cfg);
		// Formatter les dates
		// https://mkyong.com/java/how-to-convert-string-to-date-java/
		// String pattern = "yyyy-MM-dd";
		// SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, new Locale("fr",
		// "FR"));
		try {
			// Requête de préparation / paramétrée
			String sql = "update dbo.formations set libelle=?, description=? where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, formation.get_libelle());
			stmt.setString(2, formation.get_description());
			/*
			 * try { stmt.setDate(3, new
			 * java.sql.Date(dateFormat.parse(formation.get_debut()).getTime()));
			 * stmt.setDate(4, new
			 * java.sql.Date(dateFormat.parse(formation.get_fin()).getTime())); } catch
			 * (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
			 * }
			 */
			stmt.setInt(3, formation.get_id());
			// Traiter la requête
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}

	/**** AJOUT D'UNE FORMATION *********************************************/
	public static void addFormation(ServletConfig cfg, Formation formation) throws DalException {
		// Connexion DB --------------
		connectDB(cfg);
		// Formatter les dates
		// https://mkyong.com/java/how-to-convert-string-to-date-java/
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
		try {
			// Requête de préparation / paramétrée
			String sql = "insert into dbo.formations (libelle, description, debut, fin) values (?,?,?,?)";
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// Ajouter les éléments
			stmt.setString(1, formation.get_libelle());
			stmt.setString(2, formation.get_description());
			// Transformer date.util en date.sql
			try {
				stmt.setDate(3, new java.sql.Date(dateFormat.parse(formation.get_debut()).getTime()));
				stmt.setDate(4, new java.sql.Date(dateFormat.parse(formation.get_fin()).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Traiter la requête
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int idFormationGenere = rs.getInt(1);
				formation.set_id(idFormationGenere);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}

	/**** AJOUT D'UN UTILISATEUR *********************************************/
	public static void addUtilisateur(ServletConfig cfg, Object objet) throws DalException {
		// Connexion DB --------------
		connectDB(cfg);
		// instance de l'objet ----------
		String tableDe = "";
		if (objet instanceof Animateur) {
			tableDe += "animateurs";
		} else if (objet instanceof Stagiaire) {
			tableDe += "stagiaires";
		}
		try {
			// Requête de préparation / paramétrée
			String sql = "insert into dbo." + tableDe + " (nom, prenom, email, motdepasse) values (?,?,?,?)";
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// Ajouter les éléments
			if (objet instanceof Animateur) {
				stmt.setString(1, ((Animateur) objet).get_nom());
				stmt.setString(2, ((Animateur) objet).get_prenom());
				stmt.setString(3, ((Animateur) objet).get_email());
				stmt.setString(4, ((Animateur) objet).get_motdepasse());
			} else if (objet instanceof Stagiaire) {
				stmt.setString(1, ((Stagiaire) objet).get_nom());
				stmt.setString(2, ((Stagiaire) objet).get_prenom());
				stmt.setString(3, ((Stagiaire) objet).get_email());
				stmt.setString(4, ((Stagiaire) objet).get_motdepasse());
			}
			// Traiter la requête
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (objet instanceof Animateur) {
				if (rs.next()) {
					int idAnimateur = rs.getInt(1);
					((Animateur) objet).set_id(idAnimateur);
				}
			} else if (objet instanceof Stagiaire) {
				if (rs.next()) {
					int idStagiaire = rs.getInt(1);
					((Stagiaire) objet).set_id(idStagiaire);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}

	/**** SUPPRIMER UN UTILISATEUR *******************************/
	public static void supprimerCompteUtilisateur(ServletConfig cfg, int id, String utilisateur) throws DalException {
		// Connexion DB --------------
		connectDB(cfg);
		try {
			// Requête de préparation / paramétrée
			String sql = "delete from dbo." + utilisateur + " where id=?";
			stmt = con.prepareStatement(sql);
			// Affecte le ? ----------
			stmt.setInt(1, id);
			// Traiter la requête
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}

	/***** RETOUR DES TOUTES LES INSCRIPTIONS (LISTE) ****************/
	public static List<Inscription> selectAllInscriptions(ServletConfig cfg) throws DalException {
		List<Inscription> listeInscriptions = new ArrayList<Inscription>();
		// Connexion DB --------------
		connectDB(cfg);
		try {
			// Requête de préparation / paramétrée
			// OU String sql = "select * from formations";
			String sql = "select * from dbo.inscriptions";
			// Quand pas de ? dans la requête ------
			Statement cstmt = con.createStatement();
			// Traiter la requête
			ResultSet rs = cstmt.executeQuery(sql);
			// Formatter les dates
			String pattern = "EEEE dd MMMMM yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
			// Traiter le résultat avec lecture des lignes
			while (rs.next()) {
				Inscription i = new Inscription(rs.getInt(1), rs.getInt(2), dateFormat.format(rs.getDate(3)));
				// Ajout à la liste des inscriptions
				listeInscriptions.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
		return listeInscriptions;
	}

	/**** RETOUR DES TOUS LES STAGIAIRES (LISTE) *******************************/
	public static List<Stagiaire> selectAllStagiaire(ServletConfig cfg) throws DalException {
		List<Stagiaire> listeStagiaire = new ArrayList<Stagiaire>();
		// Connexion DB --------------
		connectDB(cfg);
		try {
			// Requête de préparation / paramétrée
			// OU String sql = "select * from formations";
			String sql = "select * from dbo.stagiaires";
			// Quand pas de ? dans la requête ------
			Statement cstmt = con.createStatement();
			// Traiter la requête
			ResultSet rs = cstmt.executeQuery(sql);
			// Traiter le résultat avec lecture des lignes
			while (rs.next()) {
				Stagiaire s = new Stagiaire(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				// Ajout à la liste des stagiaires
				listeStagiaire.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
		return listeStagiaire;
	}

	/**** RETOUR DE LA CONNEXION D'UN ANIMATEUR *******************************/
	public static Animateur rechercherAnimateur(ServletConfig cfg, String email, String mdp) throws DalException {
		Animateur animateurConnecte = null;
		// Connexion DB --------------
		connectDB(cfg);
		try {
			// Requête de préparation / paramétrée
			String sql = "select * from dbo.animateurs where email=? and motdepasse=?";
			stmt = con.prepareStatement(sql);
			// Affecte le ? ----------
			stmt.setString(1, email);
			stmt.setString(2, mdp);
			// Traiter la requête
			ResultSet rs = stmt.executeQuery();
			// Lecture de la ligne
			if (rs.next()) {
				animateurConnecte = new Animateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
		return animateurConnecte;
	}

	/**** RETOUR DE LA CONNEXION D'UN STAGIAIRE *******************************/
	public static Stagiaire rechercherStagiaire(ServletConfig cfg, String email, String mdp) throws DalException {
		Stagiaire stagiaireConnecte = null;
		// Connexion DB --------------
		connectDB(cfg);
		try {
			// Requête de préparation / paramétrée
			String sql = "select * from dbo.stagiaires where email=? and motdepasse=?";
			stmt = con.prepareStatement(sql);
			// Affecte le ? ----------
			stmt.setString(1, email);
			stmt.setString(2, mdp);
			// Traiter la requête
			ResultSet rs = stmt.executeQuery();
			// Lecture de la ligne
			if (rs.next()) {
				stagiaireConnecte = new Stagiaire(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
		return stagiaireConnecte;
	}

	/**** MAJ DU MOT DE PASSE D'UN ANIMATEUR *******************************/
	public static void majMdpAnimateur(ServletConfig cfg, int id, String mdp) throws DalException {
		// Connexion DB --------------
		connectDB(cfg);
		try {
			// Requête de préparation / paramétrée
			String sql = "update dbo.animateurs set motdepasse=? where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, mdp);
			stmt.setInt(2, id);
			// Traiter la requête
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}

	/**** MAJ DU MOT DE PASSE D'UN ANIMATEUR *******************************/
	public static void majMdpStagiaire(ServletConfig cfg, int id, String mdp) throws DalException {
		// Connexion DB --------------
		connectDB(cfg);
		try {
			// Requête de préparation / paramétrée
			String sql = "update dbo.stagiaires set motdepasse=? where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, mdp);
			stmt.setInt(2, id);
			// Traiter la requête
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}

	/**** MAJ DES DATAS D'UN ANIMATEUR *******************************/
	public static void majDataAnimateur(ServletConfig cfg, int id, String nom, String prenom, String email)
			throws DalException {
		// Connexion DB --------------
		connectDB(cfg);
		try {
			// Requête de préparation / paramétrée
			String sql = "update dbo.animateurs set nom=?, prenom=?, email=? where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nom);
			stmt.setString(2, prenom);
			stmt.setString(3, email);
			stmt.setInt(4, id);
			// Traiter la requête
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}

	/**** MAJ DES DATAS D'UN STAGIAIRE *******************************/
	public static void majDataStagiaire(ServletConfig cfg, int id, String nom, String prenom, String email)
			throws DalException {
		// Connexion DB --------------
		connectDB(cfg);
		try {
			// Requête de préparation / paramétrée
			String sql = "update dbo.stagiaires set nom=?, prenom=?, email=? where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nom);
			stmt.setString(2, prenom);
			stmt.setString(3, email);
			stmt.setInt(4, id);
			// Traiter la requête
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}

	/**** RETOUR DES TOUTES LES NOTES (LISTE) *******************************/
	public static List<Note> selectAllNotes() throws DalException {
		List<Note> listeNotes = new ArrayList<Note>();
		// Connexion DB --------------
		connectDB_JEE_TP_Static();
		try {
			// Requête de préparation / paramétrée
			// OU String sql = "select * from Notes";
			String sql = "select * from dbo.Notes";
			// Quand pas de ? dans la requête ------
			Statement cstmt = con.createStatement();
			// Traiter la requête
			ResultSet rs = cstmt.executeQuery(sql);
			// Traiter le résultat avec lecture des lignes
			while (rs.next()) {
				Note n = new Note(rs.getInt(1), rs.getString(2), rs.getDate(3));
				// Ajout à la liste des stagiaires
				listeNotes.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
		return listeNotes;
	}

	/**** SUPPRIMER UNE NOTE *******************************/
	public static void supprimerNote(int id) throws DalException {
		// Connexion DB --------------
		connectDB_JEE_TP_Static();
		try {
			// Requête de préparation / paramétrée
			String sql = "delete from dbo.Notes where id=?";
			stmt = con.prepareStatement(sql);
			// Affecte le ? ----------
			stmt.setInt(1, id);
			// Traiter la requête
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}

	/**** AJOUT D'UNE NOTE *********************************************/
	public static void addNote(Note N) throws DalException {
		// Connexion DB --------------
		connectDB_JEE_TP_Static();
		// Formatter les dates
		try {
			// Requête de préparation / paramétrée
			String sql = "insert into dbo.Notes (contenu, date) values (?,?)";
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, N.get_contenu());
			// Transformer date.util en date.sql
			stmt.setDate(2, new java.sql.Date(N.get_date().getTime()));
			// Traiter la requête
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int idNote = rs.getInt(1);
				N.set_id(idNote);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}

	/**** MODIFIER UNE NOTE *******************************/
	public static void modifierNote(int id, String contenu) throws DalException {
		// Connexion DB --------------
		connectDB_JEE_TP_Static();
		try {
			// Requête de préparation / paramétrée
			String sql = "update dbo.Notes set contenu=? where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, contenu);
			stmt.setInt(2, id);
			// Traiter la requête
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}
}
