package packageServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import packageBEAN.Voiture;

/**
 * Servlet implementation class dateHeure pour AJAX url de index.jsp
 */
@WebServlet("/dateHeure")
public class dateHeure extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		out.print(sdf.format(d));
		response.flushBuffer();
		Gson gs = new Gson();
		ArrayList<Voiture> voitures = new ArrayList<>();
		Voiture vehicule1 = new Voiture("Ferrari", "F40", 44000);
		Voiture vehicule2 = new Voiture("Ford", "GT40", 22000);
		Voiture vehicule3 = new Voiture("Lamborghini", "Aventador", 88700);
		Voiture vehicule4 = new Voiture("Peugeot", "208", 92500);
		voitures.add(vehicule1);
		voitures.add(vehicule2);
		voitures.add(vehicule3);
		voitures.add(vehicule4);
		out.print("<br><br>Les Voitures :<br>" + gs.toJson(voitures) + "<br>");
		response.flushBuffer();
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
