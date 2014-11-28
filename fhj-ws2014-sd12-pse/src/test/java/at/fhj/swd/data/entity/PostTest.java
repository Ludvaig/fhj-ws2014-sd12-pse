package at.fhj.swd.data.entity;

/**
 * PostTest
 * Gruppe 4
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PostTest {

	private Post p;

	@Before
	public void setUp() throws Exception {
		p = new Post();
	}

	@Test
	public void testPost() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetTitle() {
		p.setTitle("Neuer Post");
		Assert.assertEquals("Neuer Post", p.getTitle());
	}

	@Test
	public void testSetTitle() {
		p.setTitle("Selenium - UserManual gesucht");
		Assert.assertEquals("Selenium - UserManual gesucht", p.getTitle());
	}

	@Test
	public void testGetText() {
		p.setText("Ich suche für Selenium ein UserManual");
		Assert.assertEquals("Ich suche für Selenium ein UserManual", p.getText());
	}

	@Test
	public void testSetText() {
		p.setText("Mary had a little lamb");
		Assert.assertEquals("Mary had a little lamb", p.getText());
	}

	@Test
	public void testEquals_This() {
		Assert.assertTrue(p.equals(p));
	}
	
	@Test
	public void testHashCode() {
//		Post q = new Post();
//		Assert.assertTrue(p.hashCode() == q.hashCode());
	}

	@Test
	public void testToString() {
//		fail("Not yet implemented");
	}

}
