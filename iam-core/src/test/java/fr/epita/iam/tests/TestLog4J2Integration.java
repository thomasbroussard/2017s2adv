package fr.epita.iam.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

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
public class TestLog4J2Integration {
	
	private static final Logger LOGGER = LogManager.getLogger(TestLog4J2Integration.class);
	
	@Test
	public void testLogger() {
		LOGGER.info("test");
		
		
	}
	

}
