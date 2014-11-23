package at.fhj.swd.service;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.impl.UserServiceImpl;
import junit.framework.TestCase;

public class UserServiceTest extends TestCase {
	
	UserServiceImpl UserService;
	User user;
	
	@Before
	public void setUp() {
		
		UserService = new UserServiceImpl();
		
		user = new User();
	}

	@Test
	public void testUserIsAdmin() {
		

		user.setUsername("test");
		
		assertEquals(false, UserService.UserIsAdmin(user));
		
		user.setUsername("test_a");

		assertEquals(true, UserService.UserIsAdmin(user));
		assertEquals(false, UserService.UserIsPortalAdmin(user));
	}
	
	@Test
	public void testUserIsPortalAdmin() {
	
		user.setUsername("test");
		
		assertEquals(false, UserService.UserIsPortalAdmin(user));
		
		user.setUsername("test_pa");
		assertEquals(true, UserService.UserIsPortalAdmin(user));
		assertEquals(false, UserService.UserIsAdmin(user));
	}
}
