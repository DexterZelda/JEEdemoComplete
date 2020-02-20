package packageServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

/**
 * Servlet implementation class monServlet
 * 
 * https://www.jmdoudoux.fr/java/dej/chap-servlets.htm
 * 
 * https://docs.oracle.com/en/database/oracle/oracle-database/18/tdpjd/creating-servlet-process-request.html
 * 
 */

// déclaration des servlets ---------------------
// @WebServlet({ "/monServlet", "/premiereServlet" })
// @WebServlet(name = "/monServlet", urlPatterns = { "/monServlet", "/premiereServlet" }, loadOnStartup = 1)
// remplacé dans le WEB-INF/web.xml pour la 2ème façon de faire
// Classe monServlet ------------------------
public class servletFormations extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Pour l'initialisation du servlet si on a besoin + tard
	private static ServletConfig cfg;
	// Variables Globales pour la partie connexion BD
	private static Connection con = null;
	private static PreparedStatement stmt = null;

	// Méthode de connexion à la base de données
	private static void connectDB() {
		try {
			// Etape 1 - Charger le driver jdbc
			DriverManager.registerDriver(new SQLServerDriver());

			// Etape 2 - Connection
			String url = cfg.getServletContext().getInitParameter("url_bd");
			con = DriverManager.getConnection(url, cfg.getServletContext().getInitParameter("id_bd"),
					cfg.getServletContext().getInitParameter("mdp_bd"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Méthode de déconnexion à la base de données
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

	/**** AFFICHAGE DE LA LISTE DES FORMATIONS *******************************/
	private void affListeFormations(HttpServletResponse response) throws Exception {
		// Connexion DB --------------
		connectDB();
		try {
			// Requête de préparation / paramétrée
			String sql = "select * from dbo.formations";
			// Quand pas de ? dans la requête ------
			Statement cstmt = con.createStatement();
			// Traiter la requête
			ResultSet rs = cstmt.executeQuery(sql);
			// Pour afficher les résultats --------
			PrintWriter out = response.getWriter();
			// Formatter les dates
			Locale locale = new Locale("fr", "FR");
			DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
			// Traiter le résultat avec lecture des lignes
			while (rs.next()) {
				out.print("ID : " + rs.getInt(1) + "<br>LIBELLE : " + rs.getString(2) + "<br>DESCRIPTION : "
						+ rs.getString(3) + "<br>DEBUT : " + dateFormat.format(rs.getDate(4)) + "<br>FIN : "
						+ dateFormat.format(rs.getDate(5)) + "<br><br>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Déconnexion DB ----------------
			deconnectDB();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// redirection ------------------------
		this.processRequest(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// redirection ------------------------
		this.processRequest(request, response);
	}

	/*
	 * Méthode pour permettre de modifier une redirection
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// AU BESOIN AVANT pour la forme HTML : response.setContentType("text/html");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("Liste des formations : <br><br>");
		try {
			this.affListeFormations(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Les paramètres d'initialisation
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = config;
		System.out.println("Je suis dans le init du Servlet Formations");
		System.out.println("Liste des paramètres d'initialisation :");
		Enumeration<String> eParam = config.getInitParameterNames();
		while (eParam.hasMoreElements()) {
			String saveName = eParam.nextElement();
			System.out.println(saveName);
			System.out.println(getInitParameter(saveName));
		}
		System.out.println();
	}
}
