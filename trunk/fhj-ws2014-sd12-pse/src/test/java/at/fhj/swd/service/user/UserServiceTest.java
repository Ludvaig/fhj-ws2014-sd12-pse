package at.fhj.swd.service.user;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.mock.LoggerStub;
import at.fhj.swd.service.mock.UserDAOMock;
import at.fhj.swd.service.exceptions.UserLoginException;
import at.fhj.swd.service.impl.UserServiceImpl;
import junit.framework.TestCase;

public class UserServiceTest extends TestCase {

	UserServiceImpl UserService;
	UserDAOMock userDAOmock = new UserDAOMock();
	LoggerStub logerStub = new LoggerStub();
	User user;

	@Before
	public void setUp() {

		UserService = new UserServiceImpl();
		UserService.setUserDAO(userDAOmock);
		UserService.setLogger(logerStub);

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
