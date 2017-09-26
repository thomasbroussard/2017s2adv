/**
 * Copyright Thomas Broussard
 */
package fr.epita.iam.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.tbr.iam.datamodel.Identity;
import fr.tbr.iam.services.HibernateIdentityDAO;

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
public class TestHibernateDAO {

	private static final Logger LOGGER = LogManager.getLogger(TestHibernateDAO.class);

	@Inject
	HibernateIdentityDAO dao;

	@Inject
	DataSource ds;

	@Test
	public void testCreateThroughSaveOrUpdate() throws SQLException {
		LOGGER.info("beginning test");
		// GIVEN
		final Identity identity = new Identity(0, "tbr", "tbr@tbr.com", new Date());

		// WHEN
		dao.saveOrUpdate(identity);

		// THEN
		final Connection connection = ds.getConnection();
		final PreparedStatement prepareStatement = connection
				.prepareStatement("SELECT count(*)  FROM IDENTITIES WHERE IDENTITIES.IDENTITY_EMAIL='tbr@tbr.com'");
		final ResultSet rs = prepareStatement.executeQuery();
		rs.next();
		final int count = rs.getInt(1);
		Assert.assertEquals(1, count);
		LOGGER.info("got this count {}", count);
		LOGGER.info("test successful");

	}

	@Test
	public void testUpdateThroughSaveOrUpdate() throws SQLException, ParseException {
		LOGGER.info("beginning test");
		// GIVEN
		final Identity identity = new Identity(0, "tbr", "tbr@tbr.com", new Date());

		// WHEN
		dao.saveOrUpdate(identity);
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		identity.setBirthDate(sdf.parse("09/04/1986"));
		dao.saveOrUpdate(identity);

		// THEN
		final Connection connection = ds.getConnection();
		final PreparedStatement prepareStatement = connection
				.prepareStatement("SELECT IDENTITY_BIRTHDATE FROM IDENTITIES WHERE IDENTITIES.IDENTITY_EMAIL='tbr@tbr.com'");
		final ResultSet rs = prepareStatement.executeQuery();
		rs.next();
		final Date date = rs.getDate(1);
		Assert.assertEquals("09/04/1986", sdf.format(date));
		LOGGER.info("got this modified {}", date);
		LOGGER.info("test successful");

	}

	@After
	public void clean() throws SQLException {
		LOGGER.info("beginning clean");
		final Connection connection = ds.getConnection();
		final PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM IDENTITIES");
		prepareStatement.execute();
		LOGGER.info("clean successful");

	}

}
