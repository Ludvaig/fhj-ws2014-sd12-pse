package at.fhj.swd.data.entity;

/**
 * PostTest
 * Gruppe 4
 */

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PostTest {

	private Post p;

	@Before
	public void setUp() throws Exception {
		p = new Post();
	}

	// There is no need to test the constructor since the default constructor is used!

	@Test
	public void testGetTitle() {
		String title = "Neuer Post";
		p.setTitle(title);
		Assert.assertEquals(title, p.getTitle());
	}

	@Test
	public void testSetTitle() {
		String title = "Selenium - UserManual gesucht";
		p.setTitle(title);
		Assert.assertEquals(title, p.getTitle());
	}

	@Test
	public void testGetText() {
		String text = "Ich suche für Selenium ein UserManual";
		p.setText(text);
		Assert.assertEquals(text, p.getText());
	}

	@Test
	public void testSetText() {
		String text = "Mary had a little lamb";
		p.setText(text);
		Assert.assertEquals(text, p.getText());
	}

	@Test
	public void testEqualsSameObjectReference() {
		Assert.assertTrue(p.equals(p));
	}
	
	@Test 
	public void testEqualsNullObject() {
		Post nullPost = null;
		Assert.assertEquals(false, p.equals(nullPost));
	}
	
	@Test
	public void testEqualsDifferentType() {
		Community wrongPostType = new Community();
		/* Important: FindBugs suppression could not be used here since findBugs dependencies are missing in this project in order to do so!
		 * @SuppressFBWarnings(value="EC_UNRELATED_TYPES ", justification="The incorrect Object type is intentionally here for this test-case!")
		 * (see http://findbugs.sourceforge.net/api/edu/umd/cs/findbugs/annotations/SuppressFBWarnings.html)
		 */
		Assert.assertEquals(false, p.equals(wrongPostType));
	}
	
	@Test
	public void testEqualsId() throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Long sameId = new Long(22L);
		
		Field id = Post.class.getDeclaredField("id");
		id.setAccessible(true);
		
		id.setLong(p, sameId);
		
		Post anotherPostWithSameId = new Post();
		id.setLong(anotherPostWithSameId, sameId);
		
		Assert.assertEquals(true, p.equals(anotherPostWithSameId));
	}
	
	@Test 
	public void testHashCode() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field id = Post.class.getDeclaredField("id");
		id.setAccessible(true);
		id.setLong(p, 25L);
		Long idValueAsBoxedLong = Long.valueOf(id.getLong(p));
		int expectedHashCode = idValueAsBoxedLong.hashCode();
		
		Assert.assertEquals(expectedHashCode, p.hashCode());
	}
	
	@Test
	public void testToString() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Long sameId = new Long(22L);
		String text = "postText";
		String title = "postTitle";
		
		Field id = Post.class.getDeclaredField("id");
		id.setAccessible(true);
		
		id.setLong(p, sameId);
		p.setText(text);
		p.setTitle(title);
		
		String expectedToString = new StringBuilder().append(p.getClass().getName()).append(": Id='22', title='").append(title).append("', text='").append(text).append("'!").toString();
		Assert.assertEquals(expectedToString, p.toString());
	}
}
