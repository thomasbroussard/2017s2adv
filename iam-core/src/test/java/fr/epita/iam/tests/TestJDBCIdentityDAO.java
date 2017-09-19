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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.tbr.iam.datamodel.Identity;
import fr.tbr.iam.services.JDBCIdendityDAO;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestJDBCIdentityDAO {

	@Inject
	DataSource ds;

	@Inject
	JDBCIdendityDAO dao;

	private static boolean isInit = false;

	@Before
	public void initializeDB() throws SQLException {
		if (!isInit) {
			Connection connection = ds.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("CREATE TABLE IDENTITIES (\r\n"
					+ "    IDENTITY_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,\r\n" + "    IDENTITY_DISPLAY_NAME VARCHAR(255),\r\n"
					+ "    IDENTITY_EMAIL VARCHAR(255),\r\n" + "    IDENTITY_BIRTHDATE DATE\r\n" + ")");
			prepareStatement.execute();
			prepareStatement.close();
			connection.close();
		}

	}

	@Test
	public void testIdentityInsertion() throws ParseException, SQLException {
		// Given the following identity
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("09/04/1986");
		Identity identity = new Identity(0, "Thomas", "tbr@tbr.com", date);

		// When
		dao.create(identity);

		// Then
		Connection connection = ds.getConnection();
		ResultSet resultSet = connection.prepareStatement("SELECT count(*) FROM IDENTITIES").executeQuery();
		resultSet.next();
		Assert.assertNotEquals(0, resultSet.getInt(1));
		resultSet.close();
		connection.close();
	}

}
