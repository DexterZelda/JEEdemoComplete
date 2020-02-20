package packageFilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CGU
 * 
 * http://spoonless.github.io/epsi-b3-javaee/javaee_web/web_listeners_filters.html
 * 
 */
public class CGU implements Filter {

	/**
	 * Default constructor.
	 */
	public CGU() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		Cookie[] lesCookies = ((HttpServletRequest) request).getCookies();
		if (lesCookies != null) {
			for (Cookie cookie : lesCookies) {
				if (cookie.getName().equals("cgu")) {
					request.setAttribute("cgu", cookie.getValue());
				}
			}
		} else {
			Cookie cookie = new Cookie("cgu", "cgu");
			cookie.setMaxAge(60 * 60 * 24 * 30);
			((HttpServletResponse) response).addCookie(cookie);
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
