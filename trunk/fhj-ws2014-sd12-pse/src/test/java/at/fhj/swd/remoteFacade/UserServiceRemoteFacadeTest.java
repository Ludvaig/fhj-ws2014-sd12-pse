package at.fhj.swd.remoteFacade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Random;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.UserService;
import at.fhj.swd.service.exceptions.UserLoginException;

public final class UserServiceRemoteFacadeTest {

	private User user;
	private String userName = "Herbert";
	private String password = "vergessen";

	private UserService service;

	@Before
	public void setUp() throws NamingException {
		service = RemoteServiceLocator.getUserService();
		
		user = new User();
		user.setUsername(userName);
		user.setPassword(password);
//		user.setHashedPassword(HashUtil.getPasswordHash(user.getUsername(),
//				user.getPassword()));
	}

	@After
	public void tearDown() {

	}

	@Test
	public void registerUserWithUsernameAndPassword() {
		String token = service.registerUser(userName, password);

		assertEquals(userName, service.getRegisteredUser(token).getUsername());
	}

	@Test
	public void registerUser_defect() {
		try {
			service.registerUser("anyUser", "anyPw");
			fail();
		} catch (Exception e) {
			assertEquals(UserLoginException.class, e.getCause().getClass());
		}
	}

	@Test
	public void testUserIsAdmin() {

		assertEquals(false, service.userIsAdmin(user));

		user.setUsername("test_a");

		assertEquals(true, service.userIsAdmin(user));
		assertEquals(false, service.userIsPortalAdmin(user));
	}

	@Test
	public void testUserIsPortalAdmin() {

		assertEquals(false, service.userIsPortalAdmin(user));

		user.setUsername("test_pa");
		assertEquals(true, service.userIsPortalAdmin(user));
		assertEquals(false, service.userIsAdmin(user));
	}

	@Test
	public void getUserById() {
		User user = service.getUserById(1);

		assertEquals(userName, user.getUsername());
	}

	@Test
	public void getUserByName() {
		User user = service.getUserByUsername(userName);

		assertEquals(userName, user.getUsername());
	}

	@Test
	public void loggoutUser() {
		service.registerUser(userName, password);

		service.loggoutUser(userName);
		user = service.getUserByUsername(userName);

		assertEquals("", user.getToken());
	}

	@Test
	public void updateUser() {
		String email = "mail@mail.at";

		String token = service.registerUser(userName, password);

		user = service.getRegisteredUser(token);

		user.setEmail(email);

		service.updateUser(user);

		assertEquals(email, service.getUserByUsername(userName).getEmail());
	}

	@Test
	public void registerUserNewUser() {
		User newUser = new User();
		
		Random rand = new Random();
		 int randomNum = rand.nextInt(999999)+10000;
		 String randName = new Integer(randomNum).toString();
		
		newUser.setId((long) randomNum);
		newUser.setUsername(randName);
		service.registerUser(newUser);

//		User userFromStorage = service.getUserByUsername(userName);
//
//		assertEquals(userFromStorage.getUsername(), newUser.getUsername());
	}
}
