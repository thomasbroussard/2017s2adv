/**
 * Copyright Thomas Broussard
 */
package fr.epita.iam.tests;

import java.util.Date;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.tbr.iam.datamodel.Identity;

/**
 * <h3>Description</h3>
 * <p>Cette classe permet de ...</p>
 *
 * <h3>Utilisation</h3>
 * <p>Elle s'utilise de la manière suivante :
 *   <pre><code>${type_name} instance = new ${type_name}();</code></pre>
 * </p>
 *
 * @since $${version}
 * @see Voir aussi $${link}
 * @author ${user}
 *
 * ${tags}
 */

// integrate spring and junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestHibernateIntegration {

	@Inject
	private SessionFactory sessionFactory;

	@Test
	public void testFirstIntegration() {
		final Identity identity = new Identity(0, "Thomas", "tbr@tbr.com", new Date());


		final Session session = sessionFactory.openSession();
		final Transaction tx = session.beginTransaction();
		session.saveOrUpdate(identity);
		tx.commit();

	}

}
