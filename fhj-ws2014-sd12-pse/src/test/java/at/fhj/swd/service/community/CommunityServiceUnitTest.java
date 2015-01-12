package at.fhj.swd.service.community;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.Community;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.impl.CommunityServiceImpl;

/**
 * 
 * @author Julia Viehberger
 *
 */

public class CommunityServiceUnitTest {
	
	private CommunityServiceImpl service;
	
	private CommunityDAO communityDao;
	private UserDAO userDao;
	
	private Logger serviceLogger;
	
	
	@Before
	public void setUp() throws NamingException {
		
		communityDao = Mockito.mock(CommunityDAO.class);
		userDao = Mockito.mock(UserDAO.class);
		serviceLogger = Mockito.mock(Logger.class);
		
		service = new CommunityServiceImpl();
		service.setCommunityDAO(communityDao);
		service.setUserDAO(userDao);
		service.setLogger(serviceLogger);

		List<Community> testCommunities = getTestCommunities();
		Mockito.when(communityDao.findAllCommunities()).thenReturn(testCommunities);
	}
	
	@Test
	public void getAllCommunities() { 
		List<Community> allCommunities = service.getAllCommunities();
		Assert.assertNotNull(allCommunities);
		Assert.assertEquals(2, allCommunities.size());
	}
	
	@Test
	public void releaseCommunity(){
		
		Community com1 = new Community();
		com1.setName("reise");
		com1.setVisible(false);
				
		Mockito.when(communityDao.findCommunityById(1)).thenReturn(com1);
		service.releaseCommunity(1, true);
		
		Assert.assertTrue(com1.isVisible());
		service.releaseCommunity(1, false);
		Assert.assertFalse(com1.isVisible());
	}
	
	@Test
	public void getAllSubscribedCommunitiesForUser() { 
		
		User existingUser = new User();
		existingUser.setUsername("Herbert");
		existingUser.setSubscribedCommunities(getTestCommunities());
		
		// non existing user
		Mockito.when(userDao.findByToken("nonExistingUser")).thenReturn(null);
		List<Community> communities = service.getAllSubscribedCommunitiesForUser("nonExistingUser");
		Assert.assertNull(communities);
		
		// exsting user, no subscriptions
		Mockito.when(communityDao.findSubscribedCommunitiesForSearchTextOfCurrentUser("", existingUser)).thenReturn(null);
		Mockito.when(userDao.findByToken("Herbert")).thenReturn(existingUser);

		communities = service.getAllSubscribedCommunitiesForUser("Herbert");
		Assert.assertNull(communities);
		
		// existig user, subscriptions
		Mockito.when(communityDao.findSubscribedCommunitiesForSearchTextOfCurrentUser("", existingUser)).thenReturn(getTestCommunities());
		Mockito.when(userDao.findByToken("Herbert")).thenReturn(existingUser);

		communities = service.getAllSubscribedCommunitiesForUser("Herbert");
		Assert.assertEquals(communities, existingUser.getSubscribedCommunities());
			
	}
	
	
	@Test
	public void getCommunityByID(){
		Community com1 = new Community();
		com1.setName("reise");
		
		Mockito.when(communityDao.findCommunityById(1)).thenReturn(com1);

		Community result = service.getCommunityById(1);
		Assert.assertNotNull(result);
		
		Assert.assertEquals("reise", result.getName());
	}
	
	private List<Community> getTestCommunities() {
		List<Community> testCommunities = new ArrayList<Community>();
		Community test1 = new Community();
		test1.setName("community1");
		test1.setVisible(true);
		testCommunities.add(test1);
		Community test2 = new Community();
		test1.setName("community2");
		test1.setVisible(true);
		testCommunities.add(test2);
		return testCommunities;
	}
}
