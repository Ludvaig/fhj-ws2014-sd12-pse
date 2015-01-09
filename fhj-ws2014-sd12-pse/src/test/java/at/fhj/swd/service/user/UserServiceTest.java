package at.fhj.swd.service.user;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.mock.LoggerStub;
import at.fhj.swd.service.mock.SimpleInjector;
import at.fhj.swd.service.mock.UserDAOMock;
import at.fhj.swd.service.exceptions.UserLoginException;
import at.fhj.swd.service.impl.UserServiceImpl;
import junit.framework.TestCase;

public class UserServiceTest extends TestCase {

	UserServiceImpl UserService;
	UserDAOMock userDAOmock = new UserDAOMock();
	LoggerStub loggerStub = new LoggerStub();
	User user;

	@Before
	public void setUp() {

		UserService = new UserServiceImpl();
		SimpleInjector.injectProperty(UserService, "userDAO", userDAOmock);
		SimpleInjector.injectProperty(UserService, "_log", loggerStub);

		user = new User();
	}

	@Test
	public void testUserIsAdmin() {
		user.setUsername("test");

		assertEquals(false, UserService.userIsAdmin(user));

		user.setUsername("test_a");

		assertEquals(true, UserService.userIsAdmin(user));
		assertEquals(false, UserService.userIsPortalAdmin(user));
	}

	@Test
	public void testUserIsPortalAdmin() {

		user.setUsername("test");

		assertEquals(false, UserService.userIsPortalAdmin(user));

		user.setUsername("test_pa");
		assertEquals(true, UserService.userIsPortalAdmin(user));
		assertEquals(false, UserService.userIsAdmin(user));
	}
	
	@Test
	public void testGetUserByUsername_inputErrror() {
		try {
			UserService.getUserByUsername(null);
			fail();
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		
	}

}
