package fr.epita.iam.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class TestJunitFromMaven {
	
	private static final Logger LOGGER = LogManager.getLogger(TestJunitFromMaven.class);
	
	
	@AfterClass
	public static void globalTearDown() throws IOException {
		LOGGER.info("global tear down");
		
		Files.delete(new File("test.txt").toPath());
		Files.delete(new File("test2.txt").toPath());
	}

	
	@BeforeClass
	public static void globalSetup() {
		LOGGER.info("global setup");
	}
	
	
	@Before
	public void setUp() {
		LOGGER.info("setup");
	}
	
	@After
	public void tearDown() {
		LOGGER.info("tearDown");
	}
	
	
	@Test
	public void testJunit() throws IOException {
		
		//GIVEN
		File file = new File("test.txt");

		//WHEN
		file.createNewFile();
		
		
		//THEN
		Assert.assertTrue(file.exists());
		
		LOGGER.info("test");
	}
	
	@Test
	public void testJunit2() throws IOException {
		
		//GIVEN
		File file = new File("test2.txt");

		//WHEN
		file.createNewFile();
		
		
		//THEN
		Assert.assertTrue(file.exists());
		
		LOGGER.info("test2");
	}
	

	
	
	

}
