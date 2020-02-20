package packageServlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packageBO.Formation;
import packageBO.Inscription;
import packageBO.Stagiaire;
import packageDAL.DalException;
import packageDAL.DalDAOJdbc;

/**
 * Servlet implementation class appelPageJspVisible
 * 
 * https://www.jmdoudoux.fr/java/dej/chap-servlets.htm
 * https://www.jmdoudoux.fr/java/dej/chap-jsp.htm
 * 
 */
//@WebServlet("/ServletListeFormations")
public class ServletListeFormations extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<Formation> listeFormations;
	private static List<Inscription> listeInscriptions;
	private static List<Stagiaire> listeStagiaire;
	private static ServletConfig cfg;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Appel du Servlet servletFormationsJSP
		RequestDispatcher rd = getServletContext().getNamedDispatcher("servletFormationsJSP");
		// AJOUTE A LA SUITE le servlet concerné ou le code/page HTML concerné
		// RequestDispatcher rd2 =
		// getServletContext().getNamedDispatcher("servletFormations");
		// getServletContext().getNamedDispatcher("/maPage.html");
		// AJOUT : rd2.include(request, response);
		try {
			listeFormations = DalDAOJdbc.selectAllFormations(cfg);
			listeInscriptions = DalDAOJdbc.selectAllInscriptions(cfg);
			listeStagiaire = DalDAOJdbc.selectAllStagiaire(cfg);
		} catch (DalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listeFormations", listeFormations);
		request.setAttribute("listeInscriptions", listeInscriptions);
		request.setAttribute("listeStagiaire", listeStagiaire);
		rd.forward(request, response);
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
		System.out.println("Je suis dans le init du Servlet appelPageJspVisible\n");
	}
}
