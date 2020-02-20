package packageServlet;

import java.io.IOException;
import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet02
 */
// @WebServlet(name = "Servlet2", urlPatterns = { "/Servlet2" }) 
// remplacé dans le WEB-INF/web.xml pour la 2ème façon de faire
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Bonjour, je suis dans le doGet du Servlet 2");
		// instance de ce servlet
		System.out.println("instance du Servlet 2 : " + this.hashCode() + "\n");
		// AU BESOIN SI ON VEUT DIRE IDEM QUE DO POST : doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Bonjour, je suis dans le doPost du Servlet 2");
		// instance de ce servlet
		System.out.println("instance du Servlet 2 : " + this.hashCode() + "\n");
		// AU BESOIN SI ON VEUT DIRE IDEM QUE DO GET : doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("Je suis dans le init du Servlet 2\n");
	}

}
