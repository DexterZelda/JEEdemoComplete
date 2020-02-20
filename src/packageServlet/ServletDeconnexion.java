package packageServlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletDeconnexion
 * 
 * https://web.maths.unsw.edu.au/~lafaye/CCM/servlets/servsession.htm
 * 
 */
public class ServletDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

	/*
	 * Les paramètres d'initialisation
	 */
	@Override
	public void init() throws ServletException {
		System.out.println("Je suis dans le init du ServletDeconnexion\n");
	}
}
