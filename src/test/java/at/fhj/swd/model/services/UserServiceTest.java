package at.fhj.swd.model.services;

import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.Impl.UserServiceImpl;
import junit.framework.TestCase;

public class UserServiceTest extends TestCase {

	public void testUserIsAdmin() {
		
		UserServiceImpl UserService = new UserServiceImpl();
		
		User user = new User();
		user.setUsername("test");
		
		assertEquals(false, UserService.UserIsAdmin(user));
		
		user.setUsername("test_a");

		assertEquals(true, UserService.UserIsAdmin(user));
		assertEquals(false, UserService.UserIsPortalAdmin(user));
	}
	

	public void testUserIsPortalAdmin() {
		
		UserServiceImpl UserService = new UserServiceImpl();
		
		User user = new User();
		user.setUsername("test");
		
		assertEquals(false, UserService.UserIsPortalAdmin(user));
		
		user.setUsername("test_pa");
		assertEquals(true, UserService.UserIsPortalAdmin(user));
		assertEquals(false, UserService.UserIsAdmin(user));
	}
}
