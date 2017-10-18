package fr.epita.iam.web.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public abstract class AbstractSpringServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

}
