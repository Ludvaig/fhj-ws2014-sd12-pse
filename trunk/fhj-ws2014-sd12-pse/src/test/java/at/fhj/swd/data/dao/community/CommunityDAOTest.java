package at.fhj.swd.data.dao.community;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.data.entity.Community;
import at.fhj.swd.data.entity.User;

/** 
 * Test-class for CommunityDAO-component-happy-path-CDI testing.
 * */
public class CommunityDAOTest {

	@Inject
	private CommunityDAO dao;
	
	@Inject
	private Connection connection;
	
	/** test-communities */
	private static Community comHerbert;
	private static Community comGeorge;
	private static Community comCookieMonster;
	
	/** test-user */
	private static User userHerbert;
	private static User userGeorge;
	private static User userCookieMonster;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
		/* initialize common set of communities for all test-cases */
		comHerbert = new Community();
		comHerbert.setName("istNieDa");
		
		comGeorge = new Community();
		comGeorge.setName("übernachtetBeiHerbert");
		
		comCookieMonster = new Community();
		comCookieMonster.setName("cookiesSindAlle");
		
		/* initialize common set of users for all test-cases */
		userHerbert = new User();
		userHerbert.setUsername("Herbert");
		
		userGeorge = new User();
		userGeorge.setUsername("George");
		
		userCookieMonster = new User();
		userCookieMonster.setUsername("CookieMonster");
	}
	
	@Before
	public void setUp() {
		
	}
	
	@After 
	public void tearDown() throws SQLException {
		connection.rollback();
	}
	
	@Ignore
	public void testFindSubscribedCommunitiesForSearchTextOfCurrentUser() {
		
	}
	
	@Ignore
	public void testFindCommunityById() {
		
	}
	
	@Ignore
	public void testInsertCommunity() {
		dao.insertCommunity(comHerbert);
		
		Assert.assertEquals(comHerbert.getId(), dao.findCommunityById(comHerbert.getId()));
	}
	
	@Ignore
	public void testFindAllCommunities() {
		
	}
	
	@Ignore
	public void testUpdate() {
		
	}
}
