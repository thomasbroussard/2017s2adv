package fr.epita.iam.web.actions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.tbr.iam.datamodel.Identity;
import fr.tbr.iam.services.HibernateIdentityDAO;

/**
 * Servlet implementation class IdentityCreation
 */
public class IdentityCreation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	HibernateIdentityDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IdentityCreation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			final String displayName = request.getParameter("displayName");
			final String email = request.getParameter("email");
			final String rawDate = request.getParameter("date");

			final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			final Date date = sdf.parse(rawDate);

			final Identity identity = new Identity(0, displayName, email, date);

			dao.saveOrUpdate(identity);

			request.getSession().setAttribute("message", "Identity has been successfully created");

			response.sendRedirect("creation.jsp");

		} catch (final Exception pe) {
			request.getSession().setAttribute("message",
					"A problem occured with the identity creation, contact the admistrator at ...@admin.com");
			response.sendRedirect("creation.jsp");
		}
	}

}
