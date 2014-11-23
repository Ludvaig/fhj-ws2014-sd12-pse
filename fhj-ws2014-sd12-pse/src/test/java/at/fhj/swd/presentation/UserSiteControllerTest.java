package at.fhj.swd.presentation;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.presentation.UserSiteController;

public class UserSiteControllerTest
{
	private final static String authToken = "authToken";
	
	@Test
	public void testgetUsernameWithoutUser_success()
	{
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStub();
		userSiteController.init();
				
		// Tests
		assertEquals(true, userSiteController.getUsername().equals("No Name Available"));
	}
	
	@Test
	public void testgetUsernameWithoutUser_fail()
	{
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStub();
		userSiteController.init();
				
		// Tests
		assertEquals(false, userSiteController.getUsername().isEmpty());
	}
	
	@Test
	public void testgetUsernameWithUser_success()
	{
		String username = "Testuser";
		String password = "Testpassword";
		
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStub();
		userSiteController.userService.registerUser(username, password);
		userSiteController.init();
				
		// Tests
		assertEquals(true, userSiteController.getUsername().equals(username));
	}
	
	@Test
	public void testgetUsernameWithUser_fail()
	{
		String username = "Testuser";
		String password = "Testpassword";
		
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStub();
		userSiteController.userService.registerUser(username, password);
		userSiteController.init();
				
		// Tests
		assertEquals(false, userSiteController.getUsername().isEmpty());
	}
	
	@Test
	public void testgetEmailWithoutUser_success()
	{
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStub();
		userSiteController.init();
		
		// Tests
		assertEquals(true, userSiteController.getEmail().equals("no.mail@vailable.yet"));
	}
	
	@Test
	public void testgetEmailWithoutUser_fail()
	{
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStub();
		userSiteController.init();
		
		// Tests
		assertEquals(false, userSiteController.getEmail().isEmpty());
	}
	
	@Test
	public void testgetEmailWithUser_success()
	{
		String username = "Testuser";
		String password = "Testpassword";
		String email = "test@test.mail";
		
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStub();
		userSiteController.userService.registerUser(username, password);
		userSiteController.userService.getRegisteredUser(authToken).setEmail(email);
		userSiteController.init();

		// Tests
		assertEquals(true, userSiteController.getEmail().equals(email));
	}
	
	@Test
	public void testgetEmailWithUser_fail()
	{
		String username = "Testuser";
		String password = "Testpassword";
		String email = "test@test.mail";
		
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStub();
		userSiteController.userService.registerUser(username, password);
		userSiteController.userService.getRegisteredUser(authToken).setEmail(email);
		userSiteController.init();

		// Tests
		assertEquals(false, userSiteController.getEmail().isEmpty());
	}
	
	@Test
	public void testSelectedUser_success()
	{
		User selectedUser = new User();
		
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.setSelectedUser(selectedUser);
		
		// Tests
		assertEquals(true, selectedUser.equals(userSiteController.getSelectedUser()));
	}
	
	@Test
	public void testSelectedUser_fail()
	{
		User selectedUser = new User();
		User otherUser = new User();
		
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.setSelectedUser(selectedUser);
		
		// Tests
		assertEquals(false, otherUser.equals(userSiteController.getSelectedUser()));
	}	
}
