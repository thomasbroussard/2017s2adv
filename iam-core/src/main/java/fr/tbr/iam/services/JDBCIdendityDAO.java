package fr.tbr.iam.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
public class JDBCIdendityDAO {

	private static final Logger LOGGER = LogManager.getLogger(JDBCIdendityDAO.class);

	@Inject
	private DataSource ds;

	@Inject
	@Named("insertionQuery")
	private String insertionQuery;
	
	public void create(Identity identity) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = ds.getConnection();
			pstmt = connection.prepareStatement(insertionQuery);
//			pstmt = connection.prepareStatement("INSERT INTO IDENTITIES"
//					+ " (IDENTITY_DISPLAY_NAME, IDENTITY_EMAIL, IDENTITY_BIRTHDATE)\r\n" + " VALUES\r\n" + " (?, ?, ?)");
			pstmt.setString(1, identity.getDisplayName());
			pstmt.setString(2, identity.getEmail());
			pstmt.setDate(3, new java.sql.Date(identity.getBirthDate().getTime()));
			pstmt.execute();

		} catch (SQLException e) {
			LOGGER.error("an error occured");
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				
			} catch (SQLException sqle) {
				LOGGER.error("unrecoverable error", sqle);
			}
		}

	}

	public void delete(Identity identity) {

	}

	public void update(Identity identity) {

	}

	public List<Identity> search(Identity criteria) {
		List<Identity> results = new ArrayList<>();

		return results;
	}

}
