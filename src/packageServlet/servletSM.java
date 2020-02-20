package packageServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packageBO.Formation;
import packageDAL.DalException;
import packageDAL.DalDAOJdbc;

/**
 * Servlet implementation class servletSM
 */
@WebServlet("/servletSM")
public class servletSM extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ServletConfig cfg;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupération des paramètres du formulaire
		String idFormation = request.getParameter("idFormation");
		String libelle = request.getParameter("libelle");
		String description = request.getParameter("description");
		// String debut = request.getParameter("debut");
		// String fin = request.getParameter("fin");
		Formation formation = new Formation();
		formation.set_id(Integer.parseInt(idFormation));
		formation.set_libelle(libelle);
		formation.set_description(description);
		// formation.set_debut(debut);
		// formation.set_fin(fin);
		try {
			if (request.getParameter("suppr") != null) {
				DalDAOJdbc.deleteFormation(cfg, Integer.parseInt(idFormation));
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/ServletListeFormations");
				rd.forward(request, response);
			} else if (request.getParameter("modif") != null) {
				DalDAOJdbc.modifFormation(cfg, formation);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/ServletListeFormations");
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
	}
}
