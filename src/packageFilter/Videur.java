package packageFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Videur implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String typeNavigateur = ((HttpServletRequest) request).getHeader("User-Agent");
		if (typeNavigateur.contains("Trident")) {
			((HttpServletResponse) response).sendRedirect("http://google.fr");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig chain) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Je suis dans le init du Filter Videur\n");
	}
}
