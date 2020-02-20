package packageServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packageBEAN.Voiture;

/**
 * Servlet implementation class Garage
 * 
 * https://www.javatpoint.com/jsp-useBean-action
 * https://www.jmdoudoux.fr/java/dej/chap-jstl.htm
 * https://www.javatpoint.com/Iteration-using-jsp-custom-tag
 * 
 */
@WebServlet("/Garage")
public class Garage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Variables
		ArrayList<Voiture> voitures = new ArrayList<>();
		Voiture vehicule1 = new Voiture("Ferrari", "F40", 44000);
		Voiture vehicule2 = new Voiture("Ford", "GT40", 22000);
		Voiture vehicule3 = new Voiture("Lamborghini", "Aventador", 88700);
		Voiture vehicule4 = new Voiture("Peugeot", "208", 92500);
		voitures.add(vehicule1);
		voitures.add(vehicule2);
		voitures.add(vehicule3);
		voitures.add(vehicule4);
		// Les setAttribute avant l'envoi au Servlet
		request.setAttribute("laVoiture", vehicule1);
		request.setAttribute("lesVoitures", voitures);
		request.setAttribute("message", "La Vie Est Belle");
		// Envoi vers la page jsp concernée
		getServletContext().getRequestDispatcher("/pagesJSP/garage.jsp").forward(request, response);
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
}
