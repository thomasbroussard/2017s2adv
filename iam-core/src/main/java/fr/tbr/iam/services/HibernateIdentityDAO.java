/**
 * Copyright Thomas Broussard
 */
package fr.tbr.iam.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.tbr.iam.datamodel.Identity;

/**
 * <h3>Description</h3>
 * <p>
 * Cette classe permet de ...
 * </p>
 *
 * <h3>Utilisation</h3>
 * <p>
 * Elle s'utilise de la manière suivante :
 *
 * <pre>
 * <code>${type_name} instance = new ${type_name}();</code>
 * </pre>
 * </p>
 *
 * @since $${version}
 * @see Voir aussi $${link}
 * @author ${user}
 *
 *         ${tags}
 */
public class HibernateIdentityDAO {

	@Inject
	private SessionFactory sessionFactory;

	public void saveOrUpdate(Identity identity) {

		final Session session = sessionFactory.openSession();
		final Transaction tx = session.beginTransaction();
		session.saveOrUpdate(identity);
		tx.commit();

	}

	public void delete(Identity identity) {

		final Session session = sessionFactory.openSession();
		final Transaction tx = session.beginTransaction();
		session.delete(identity);
		tx.commit();

	}

	public Collection<Identity> search(Identity criteria) {
		List<Identity> identitiesList = new ArrayList<>();
		final Session session = sessionFactory.openSession();
		identitiesList = session.createQuery("from Identity", Identity.class).list();

		return identitiesList;
	}
}
