package at.fhj.swd.data.entity;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommunityTest {

	private Community community;
	
	@Before
	public void setUp() {
		community = new Community();
	}
	
	@Test
	public void testGetId() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field id = community.getClass().getDeclaredField("id");
		id.setAccessible(true);
		
		long communityId = 25L;
		
		id.setLong(community, communityId);
		Assert.assertEquals(communityId, community.getId());
	}
	
	@Test
	public void testGetName() {
		String name = "community name";
		community.setName(name);
		Assert.assertEquals(name, community.getName());
	}
	
	@Test
	public void testSetName() {
		String name = "community name";
		community.setName(name);
		Assert.assertEquals(name, community.getName());
	}
	
	@Test
	public void testIsVisible() {
		boolean visible = true;
		community.setVisible(visible);
		Assert.assertEquals(visible, community.isVisible());
	}
	
	@Test
	public void testSetVisible() {
		boolean visible = true;
		community.setVisible(visible);
		Assert.assertEquals(visible, community.isVisible());
	}
	
	@Test
	public void testEqualsSameObjectReference() {
		Assert.assertTrue(community.equals(community));
	}
	
	@Test 
	public void testEqualsNullObject() {
		Community nullCommunity = null;
		Assert.assertEquals(false, community.equals(nullCommunity));
	}
	
	@Test
	public void testEqualsDifferentType() {
		Topic wrongCommunityType = new Topic();
		/* Important: FindBugs suppression could not be used here since findBugs dependencies are missing in this project in order to do so!
		 * @SuppressFBWarnings(value="EC_UNRELATED_TYPES ", justification="The incorrect Object type is intentionally here for this test-case!")
		 * (see http://findbugs.sourceforge.net/api/edu/umd/cs/findbugs/annotations/SuppressFBWarnings.html)
		 */
		Assert.assertEquals(false, community.equals(wrongCommunityType));
	}
	
	@Test
	public void testEqualsId() throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Long sameId = new Long(22L);
		
		Field id = Community.class.getDeclaredField("id");
		id.setAccessible(true);
		
		id.setLong(community, sameId);
		
		Community anotherCommunityWithSameId = new Community();
		id.setLong(anotherCommunityWithSameId, sameId);
		
		Assert.assertEquals(true, community.equals(anotherCommunityWithSameId));
	}
	
	@Test 
	public void testHashCode() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field id = Community.class.getDeclaredField("id");
		id.setAccessible(true);
		id.setLong(community, 25L);
		int expectedHashCode = 56;
		
		Assert.assertEquals(expectedHashCode, community.hashCode());
	}
	
	@Test
	public void testToString() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Long sameId = new Long(22L);
		String name = "communityName";
		
		Field id = Community.class.getDeclaredField("id");
		id.setAccessible(true);
		
		id.setLong(community, sameId);
		community.setName(name);
		
		String expectedToString = new StringBuilder().append(community.getClass().getName()).append(": Id='22', name='").append(name).append("'!").toString();
		Assert.assertEquals(expectedToString, community.toString());
	}
}
