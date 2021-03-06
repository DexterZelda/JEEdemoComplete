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
 * Servlet implementation class majMdpAnimateur
 * 
 * https://web.maths.unsw.edu.au/~lafaye/CCM/servlets/servsession.htm
 * 
 */
public class majMdpAnimateur extends HttpServlet {
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
		String mdp = request.getParameter("majMdpAnimateur");
		try {
			DalDAOJdbc.majMdpAnimateur(cfg, Integer.parseInt(id), mdp);
			// Recupère la session
			HttpSession session = request.getSession(true);
			// Stocke les valeurs de connexion maj
			session.setAttribute("mdp", mdp);
			// Renvoi vers la page d'accueil : mdp mis à jour
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
		System.out.println("Je suis dans le init du majMdpAnimateur\n");
	}
}
