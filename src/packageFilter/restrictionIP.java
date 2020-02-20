package packageFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class restrictionIP
 * 
 * http://spoonless.github.io/epsi-b3-javaee/javaee_web/web_listeners_filters.html
 * 
 */
public class restrictionIP implements Filter {
	private List<String> _listeIP;
	private final static int monIP = 3;
	private final static int NB_IP = 255;

	/**
	 * Default constructor.
	 */
	public restrictionIP() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	// intialise les adresses ip qui n'auront pas accès à mon application
	private void initListeIP() {
		this._listeIP = new ArrayList<String>();
		// String ipProf = "10.140.0.254";
		String ip_tmp = "10.140.200.";
		// this._listeIP.add(ipProf);
		for (int i = 0; i < NB_IP; i++) {
			if (i == monIP) {
				continue;
			}
			ip_tmp += i;
			this._listeIP.add(ip_tmp);
			// init ip initiale
			ip_tmp = "10.140.200.";
		}
	}

	// vérifie si l'ip est dans la liste des ip interdites
	private boolean ipExiste(String ip) {
		boolean ok = false;
		for (String l : this._listeIP) {
			if (l.equals(ip)) {
				ok = true;
				break;
			}
		}
		return ok;
	}

	// Affichage de la liste des ip interdites
	/*
	 * private void affListe() { for (String l : this._listeIP) {
	 * System.out.println(l); } }
	 */

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		if (!((HttpServletRequest) request).getServletPath().contains("interdit.html")
				&& !((HttpServletRequest) request).getServletPath().contains("accesrefuse.jpg")
				&& !((HttpServletRequest) request).getServletPath().contains("interdit2.html")
				&& !((HttpServletRequest) request).getServletPath().contains("accesrefuse2.jpg")) {
			this.initListeIP();
			// this.affListe();
			String ip = request.getRemoteAddr();
			if (this.ipExiste(ip) && !ip.equals("10.140.200.11")) {
				((HttpServletResponse) response).sendRedirect("/Demo1/interdit.html");
				return;
			} else if (ip.equals("10.140.200.11")) {
				// IP de Nathalie
				((HttpServletResponse) response).sendRedirect("/Demo1/interdit2.html");
				return;
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Je suis dans le init du Filter restrictionIP\n");
	}

}
