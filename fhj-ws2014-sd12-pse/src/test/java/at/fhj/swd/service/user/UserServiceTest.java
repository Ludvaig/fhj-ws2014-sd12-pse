package at.fhj.swd.service.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.mock.LoggerStub;
import at.fhj.swd.service.mock.SimpleInjector;
import at.fhj.swd.service.mock.UserDAOMock;
import at.fhj.swd.service.exceptions.ServiceLayerException;
import at.fhj.swd.service.exceptions.UserLoginException;
import at.fhj.swd.service.impl.UserServiceImpl;

public class UserServiceTest {

	UserServiceImpl UserService;
	UserDAOMock userDAOmock = new UserDAOMock();
	LoggerStub loggerStub = new LoggerStub();
	User user;

	@Before
	public void setUp() {

		UserService = new UserServiceImpl();
		SimpleInjector.injectProperty(UserService, "userDAO", userDAOmock);
		SimpleInjector.injectProperty(UserService, "log", loggerStub);

		user = new User();
		user.setUsername("Herbert");
		user.setPassword("vergessen");
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
	
	@Test
	public void testUpdateUser_inputErrror() {
		try {
			UserService.updateUser(null);
			fail();
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		
	}
	
	@Test
	public void testRegisterUser_inputErrror() {
		try {
			UserService.registerUser(null);
			fail();
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		
	}
	
	@Test
	public void testProveUserPassswordCombination_inputErrror_Username() {
		try {
			UserService.proveUserPassswordCombination(null, "");
			fail();
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		
	}
	@Test
	public void testProveUserPassswordCombination_inputErrror_Password() {
		try {
			UserService.proveUserPassswordCombination("", null);
			fail();
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		
	}
	
	@Test
	public void testProveUserPassswordCombination_correctPassword() {
		
		
		userDAOmock.user = user;
		
		User testUser = UserService.proveUserPassswordCombination("Herbert", "vergessen");		
		
		assertEquals(user, testUser);
	}
	
	@Test
	public void testProveUserPassswordCombination_wrongPassword() {
		
		
		userDAOmock.user = user;
		
		User testUser = UserService.proveUserPassswordCombination("Herbert", "");
		
		assertEquals(null, testUser);
	}
	
	@Test
	public void testProveUserPassswordCombination_UserIsNull() {
		
		User user = null;
		
		userDAOmock.user = user;
		
		User testUser = UserService.proveUserPassswordCombination("Herbert", "");
		
		assertEquals(null, testUser);
	}
	
	@Test
	public void testProveUserPassswordCombination_ServiceLayerException() {
		
		userDAOmock.findByNameException = new RuntimeException("Error");
		
		try {
			UserService.proveUserPassswordCombination("Herbert", "vergessen");
			fail();
		} catch (Exception e) {
			assertEquals(ServiceLayerException.class, e.getClass());
		}
	}
	
	@Test
	public void testRegisterUser_inputErrror_Password() {
		try {
			UserService.registerUser("", null);
			fail();
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		
	}
	
	@Test
	public void testRegisterUser_inputErrror_Username() {
		try {
			UserService.registerUser(null, "");
			fail();
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		
	}
	
	@Test
	public void testRegisterUser_correctPassword() {
		
	
		userDAOmock.user = user;
		
		String token  = UserService.registerUser("Herbert", "vergessen");	
		User testUser = UserService.getRegisteredUser(token);
	
		
		assertEquals(user, testUser);
	}

	@Test
	public void testRegisterUser_wrongPassword() {
		
	
		userDAOmock.user = user;
		
		try {
			UserService.registerUser("Herbert", "");
			fail();
		} catch (Exception e) {
			assertEquals(UserLoginException.class, e.getClass());
		}

	}
	@Test
	public void testRegisterUser_correctInput() {
		
		UserService.registerUser(user);
		assertEquals(user, userDAOmock.user);
		
	}
	
	@Test
	public void testRegisterUserWithUserObject_ServiceLayerException() {
		
		userDAOmock.user = user;
		userDAOmock.insertException = new RuntimeException("Error");
		
		try {
			UserService.registerUser(user);
			fail();
		} catch (Exception e) {
			assertEquals(ServiceLayerException.class, e.getClass());
		}
	}

	@Test
	public void testGetRegisteredUser_inputError_NoToken() {

		user = UserService.getRegisteredUser(null);
		assertEquals(null, user);
	}
	
	@Test
	public void testGetRegisteredUser_inputError_ToShortToken() {

		user = UserService.getRegisteredUser("123");
		assertEquals(null, user);
	}
	
	@Test
	public void testRegisterUser_ServiceLayerException() {
		
		userDAOmock.user = user;
		userDAOmock.insertException = new RuntimeException("Error");
		
		try {
			UserService.registerUser("Herbert", "vergessen");
			fail();
		} catch (Exception e) {
			assertEquals(ServiceLayerException.class, e.getClass());
		}
	}

	@Test
	public void testUserIsAdmin_inputError() {
		try {
			UserService.userIsAdmin(null);
			fail();
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
	}
	
	@Test
	public void testUserIsPortalAdmin_inputError() {
		try {
			UserService.userIsPortalAdmin(null);
			fail();
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
	}
	
	
	@Test
	public void testloggoutUser_wrongUsername() {
		
		userDAOmock.findByNameException = new RuntimeException("Error");
		
		try {
			UserService.loggoutUser(null);
			fail();
		} catch (Exception e) {
			assertEquals(ServiceLayerException.class, e.getClass());
		}
	}
	
	@Test
	public void testloggoutUser_correctUser() {
		
		userDAOmock.user = user;

		UserService.loggoutUser(user.getUsername());
	}
	
	@Test
	public void testloggoutUser_userIsNull() {
		
		userDAOmock.user = null;

		try {
			UserService.loggoutUser("Herbert");
			fail();
		} catch (Exception e) {
			assertEquals(ServiceLayerException.class, e.getClass());
		}
	}
	
	@Test
	public void testUpdateUser_ServiceLayerException() {
		
		userDAOmock.updateException = new RuntimeException();

		try {
			UserService.updateUser(user);
			fail();
		} catch (Exception e) {
			assertEquals(ServiceLayerException.class, e.getClass());
		}
	}
	
	@Test
	public void testUpdateUser_CorrectUser() {
		
		userDAOmock.user = user;
		
		User testUser = UserService.updateUser(user);

		assertEquals(user, testUser);
	}
	
	@Test
	public void testGetUserById_ServiceLayerException() {
		
		userDAOmock.findByIdException = new ServiceLayerException();

		try {
			UserService.getUserById(1);
			fail();
		} catch (Exception e) {
			assertEquals(ServiceLayerException.class, e.getClass());
		}
	}
	
	@Test
	public void testGetUserById_CorrectId() {
		
		userDAOmock.user = user;

		User testUser = UserService.getUserById(1);

		assertEquals(user, testUser);
	}
	
	@Test
	public void testGetUserByUsername_ServiceLayerException() {
		
		userDAOmock.findByNameException = new ServiceLayerException();

		try {
			UserService.getUserByUsername(user.getUsername());
			fail();
		} catch (Exception e) {
			assertEquals(ServiceLayerException.class, e.getClass());
		}
	}
	
	@Test
	public void testGetUserByUsername_CorrectUsername() {
		
		userDAOmock.user = user;

		User testUser = UserService.getUserByUsername(user.getUsername());
		assertEquals(user, testUser);
	}
}
