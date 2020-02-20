package packageServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import packageDAL.DalException;
import packageDAL.DalDAOJdbc;

/**
 * Servlet implementation class supprimerCompte
 */
public class supprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ServletConfig cfg;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupération des paramètres du formulaire
		String id = request.getParameter("id");
		String utilisateur = request.getParameter("utilisateur");
		try {
			if (utilisateur.equals("animateur")) {
				DalDAOJdbc.supprimerCompteUtilisateur(cfg, Integer.parseInt(id), "animateurs");
				// Recupère la session
				HttpSession session = request.getSession(false);
				if (session != null) {
					// supprime les sessions actives
					session.invalidate();
				}
				// Suppression du cache -----------------------------------------------
				// https://developer.mozilla.org/fr/docs/Web/HTTP/Headers/Cache-Control
				response.addHeader("Cache-Control", "no-cache");
				// Renvoi vers la page d'accueil
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/");
				rd.forward(request, response);
			} else if (utilisateur.equals("stagiaire")) {
				DalDAOJdbc.supprimerCompteUtilisateur(cfg, Integer.parseInt(id), "stagiaires");
				// Recupère la session
				HttpSession session = request.getSession(false);
				if (session != null) {
					// supprime les sessions actives
					session.invalidate();
				}
				// Suppression du cache -----------------------------------------------
				// https://developer.mozilla.org/fr/docs/Web/HTTP/Headers/Cache-Control
				response.addHeader("Cache-Control", "no-cache");
				// Renvoi vers la page d'accueil
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/");
				rd.forward(request, response);
			}
		} catch (DalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/*
	 * Les paramètres d'initialisation
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = config;
		System.out.println("Je suis dans le init du supprimerCompte\n");
	}
}
