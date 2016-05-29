package ge.books.intbooks.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileFilter implements Filter {

	private static final String LOGIN_PAGE = "/login";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Nothing...
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);
		if (session == null || session.getAttribute("user_object") == null) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
			requestDispatcher.forward(httpRequest, httpResponse);
			return;
		}

		// Continue chain...
		chain.doFilter(httpRequest, httpResponse);
	}

	@Override
	public void destroy() {
		// Nothing...
	}

}
