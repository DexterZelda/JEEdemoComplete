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
 * Servlet implementation class majDataAnimateur
 * 
 * https://web.maths.unsw.edu.au/~lafaye/CCM/servlets/servsession.htm
 * 
 */
public class majDataAnimateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		// Récupération des paramètres du formulaire
		String id = request.getParameter("idAnimateur");
		String nom = request.getParameter("majNom");
		String prenom = request.getParameter("majPrenom");
		String email = request.getParameter("majEmail");
		try {
			DalDAOJdbc.majDataAnimateur(cfg, Integer.parseInt(id), nom, prenom, email);
			// Recupère la session
			HttpSession session = request.getSession(true);
			// Stocke les valeurs de connexion maj
			session.setAttribute("prenom", prenom);
			session.setAttribute("nom", nom);
			session.setAttribute("email", email);
			// Renvoi vers la page d'accueil : données mises à jour
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/");
			rd.forward(request, response);
		} catch (DalException e) {
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
		System.out.println("Je suis dans le init du majDataAnimateur\n");
	}
}
