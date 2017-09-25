package fr.tbr.iam.datamodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

@Entity
@Table(name = "IDENTITIES")
public class Identity {

	@Id
	private long id;

	@Column(name = "IDENTITY_DISPLAY_NAME", length = 255)
	private String displayName;

	@Column(name = "IDENTITY_EMAIL", length = 255)
	private String email;

	@Column(name = "IDENTITY_BIRTHDATE")
	@Temporal(TemporalType.DATE)
	private Date birthDate;


	private Identity() {

	}

	/**
	 * @param id
	 * @param displayName
	 * @param email
	 * @param birthDate
	 */
	public Identity(long id, String displayName, String email, Date birthDate) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.email = email;
		this.birthDate = birthDate;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


}
