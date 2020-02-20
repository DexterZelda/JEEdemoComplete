package packageServlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import packageBO.Stagiaire;
import packageDAL.DalDAOJdbc;
import packageDAL.DalException;

/**
 * Servlet implementation class ServletCnxStagiaire
 * 
 * https://web.maths.unsw.edu.au/~lafaye/CCM/servlets/servsession.htm
 * 
 */
public class ServletCnxStagiaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Stagiaire stagiaireConnecte;
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
		// "/ValiderAcces?typeUser=Stagiaire"
		RequestDispatcher rd = getServletContext().getNamedDispatcher("servletStagiairesJSP");
		String email = request.getParameter("idStagiaire");
		String mdp = request.getParameter("mdpStagiaire");
		try {
			stagiaireConnecte = DalDAOJdbc.rechercherStagiaire(cfg, email, mdp);
		} catch (DalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (stagiaireConnecte != null) {
			// Recupère la session
			HttpSession session = request.getSession(true);
			// Stocke les valeurs de connexion
			session.setAttribute("prenom", stagiaireConnecte.get_prenom());
			session.setAttribute("nom", stagiaireConnecte.get_nom());
			session.setAttribute("email", email);
			session.setAttribute("mdp", mdp);
			session.setAttribute("connexion", "stagiaire");
			// Attribut le retour du stagiaire connecté dans la page JSP
			request.setAttribute("stagiaireConnecte", stagiaireConnecte);
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
		System.out.println("Je suis dans le init du ServletCnxStagiaire\n");
	}

}
