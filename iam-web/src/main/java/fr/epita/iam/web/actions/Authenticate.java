package fr.epita.iam.web.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class Authenticate
 */

public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(Authenticate.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Get Method is not allowed");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String login = request.getParameter("firstName");
		final String password = request.getParameter("password");
		LOGGER.info("trying to authenticate with that login {}", login);
		final HttpSession session = request.getSession();
		// TODO you have to implement a *REAL* authentication mechanism

		if ("admin".equals(login)) {
			// then we are authenticated, we can move to the welcome page
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
			LOGGER.info("authenticated");

			session.setAttribute("authenticated", true);
		} else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			LOGGER.info("not authenticated");
			session.setAttribute("authenticated", false);
		}
	}

}
