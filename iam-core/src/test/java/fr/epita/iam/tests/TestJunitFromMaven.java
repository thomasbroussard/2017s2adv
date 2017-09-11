package fr.epita.iam.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
	
	
	private static boolean init;

	
	@AfterClass
	public static void globalTearDown() throws IOException {
		System.out.println("global tearDown");
		
		Files.delete(new File("test.txt").toPath());
		Files.delete(new File("test2.txt").toPath());
	}

	
	@BeforeClass
	public static void globalSetup() {
		System.out.println("global setup");
	}
	
	
	@Before
	public void setUp() {
		System.out.println("setup");
	}
	
	@After
	public void tearDown() {
		System.out.println("tearDown");
	}
	
	
	@Test
	public void testJunit() throws IOException {
		
		//GIVEN
		File file = new File("test.txt");

		//WHEN
		file.createNewFile();
		
		
		//THEN
		Assert.assertTrue(file.exists());
		
		System.out.println("test");
	}
	
	@Test
	public void testJunit2() throws IOException {
		
		//GIVEN
		File file = new File("test2.txt");

		//WHEN
		file.createNewFile();
		
		
		//THEN
		Assert.assertTrue(file.exists());
		
		System.out.println("test2");
	}
	

	
	
	

}
