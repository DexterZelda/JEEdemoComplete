package packageServlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import packageBO.Animateur;
import packageDAL.DalException;
import packageDAL.DalDAOJdbc;

/**
 * Servlet implementation class ServletCnxAnimateur
 * 
 * https://web.maths.unsw.edu.au/~lafaye/CCM/servlets/servsession.htm
 * 
 */
public class ServletCnxAnimateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Animateur animateurConnecte;
	private static ServletConfig cfg;

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
		// Appel du Servlet servletAnimateursJSP
		// ON AURAIT PU faire une seule Servlet d'authentification avec
		// "/ValiderAcces?typeUser=Animateur"
		RequestDispatcher rd = getServletContext().getNamedDispatcher("servletAnimateursJSP");
		String email = request.getParameter("idAnimateur");
		String mdp = request.getParameter("mdpAnimateur");
		try {
			animateurConnecte = DalDAOJdbc.rechercherAnimateur(cfg, email, mdp);
		} catch (DalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (animateurConnecte != null) {
			// Recupère la session
			HttpSession session = request.getSession(true);
			// Stocke les valeurs de connexion
			session.setAttribute("prenom", animateurConnecte.get_prenom());
			session.setAttribute("nom", animateurConnecte.get_nom());
			session.setAttribute("email", email);
			session.setAttribute("mdp", mdp);
			session.setAttribute("connexion", "animateur");
			// Attribut le retour de l'animateur connecté dans la page JSP
			request.setAttribute("animateurConnecte", animateurConnecte);
			rd.forward(request, response);
		} else {
			// Renvoi vers la page d'accueil : utilisateur inconnu
			rd = getServletContext().getRequestDispatcher("/");
			rd.forward(request, response);
		}
	}

	/*
	 * Les paramètres d'initialisation
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = config;
		System.out.println("Je suis dans le init du ServletCnxAnimateur\n");
	}
}
