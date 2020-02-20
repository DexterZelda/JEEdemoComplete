package packageServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class monServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Pour l'initialisation
	private static ServletConfig cfg;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// AU BESOIN : redirection ----------
		// processRequest(request, response);

		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// AU BESOIN AVANT pour la forme HTML : reponse.setContentType("text/html");
		// OU response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("**** Bonjour, je suis dans le doGet du Servlet 1");
		// informations sur l'url serveur et sur la machine client
		out.println("\n**** INFOS URL :\n----------------\nprotocole = " + request.getScheme()
				+ "\ndestination de la requête = " + request.getServerName() + "\nport N° = " + request.getServerPort()
				+ "\napplication = " + request.getContextPath() + "\nressource = " + request.getServletPath());
		out.println("\n**** INFOS CLIENT :\n-------------------\nip = " + request.getRemoteAddr() + "\nnom machine = "
				+ request.getRemoteHost() + "\nlangue = " + request.getLocale().getDisplayLanguage() + "\nnavigateur = "
				+ request.getHeader("User-Agent"));
		// énumération de toute l'entête HTTP
		Enumeration<String> listeEntetes = request.getHeaderNames();
		out.println("\n**** LISTE DE TOUTE L'ENTETE HTTP : \n-----------------------------------");
		while (listeEntetes.hasMoreElements()) {
			String name_save = listeEntetes.nextElement();
			out.println(name_save + " = " + request.getHeader(name_save));
		}
		// instance de ce servlet
		out.println("\n**** instance du Servlet 1 : " + this.hashCode() + "\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// AU BESOIN : redirection ----------
		// processRequest(request, response);

		// doGet(request, response);
		System.out.println("**** Bonjour, je suis dans le doPost du Servlet 1");
		// récupération des paramètres de la page index.html
		System.out.println("**** Récupération de la page index.html : ");
		System.out.println("-----------------------------------------");
		System.out.println("Nom client    = " + request.getParameter("nomClient"));
		System.out.println("Prénom client = " + request.getParameter("prenomClient"));
		System.out.println("Couleur       = " + request.getParameter("couleur"));
		// si plusieures couleurs en multiple sélectionnées :
		// String[] couleurs = request.getParameterValues("couleur");
		// instance de ce servlet
		System.out.println("**** instance du Servlet 1 : " + this.hashCode() + "\n");
		// AUTRE AFFICHAGE pertinent pour la réponse coté WEB -----------------------
		// AU BESOIN AVANT pour la forme HTML : reponse.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("**** Bonjour, je suis dans le doPost du Servlet 1");
		// récupération des paramètres de la page index.html
		out.println("\n**** Récupération de la page index.html : ");
		out.println("-----------------------------------------");
		out.println("Nom client    = " + request.getParameter("nomClient"));
		out.println("Prénom client = " + request.getParameter("prenomClient"));
		out.println("Couleur       = " + request.getParameter("couleur"));
		// si plusieures couleurs en multiple sélectionnées :
		// String[] couleurs = request.getParameterValues("couleur");
		// instance de ce servlet
		out.println("\n**** instance du Servlet 1 : " + this.hashCode() + "\n");
	}

	/*
	 * Méthode pour permettre de modifier une redirection
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		response.setHeader("Location", "https://www.eni-ecole.fr/");
	}

	/*
	 * Les paramètres d'initialisation
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = config;
		System.out.println("Je suis dans le init du Servlet 1");
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
