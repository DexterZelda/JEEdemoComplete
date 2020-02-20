package packageServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packageBO.Formation;
import packageDAL.DalDAOJdbc;
import packageDAL.DalException;

/**
 * Servlet implementation class ServletAddFormation
 */
public class ServletAddFormation extends HttpServlet {
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
		String libelle = request.getParameter("addLibelle");
		String description = request.getParameter("addDescription");
		String dateDeb = request.getParameter("addDateDeb");
		String dateFin = request.getParameter("addDateFin");
		Formation formation = new Formation(libelle, description, dateDeb, dateFin);
		try {
			DalDAOJdbc.addFormation(cfg, formation);
			// Renvoi vers la page d'accueil : données mises à jour
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/ServletListeFormations");
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
		System.out.println("Je suis dans le init du ServletAddFormation\n");
	}

}
