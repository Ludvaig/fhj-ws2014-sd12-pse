package at.fhj.swd.controller;

import at.fhj.swd.model.entity.User;
import junit.framework.TestCase;

public class UserSiteControllerTest extends TestCase
{
	public void testgetUsernameWithoutUser_success()
	{
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStup();
		userSiteController.init();
				
		// Tests
		assertEquals(true, userSiteController.getUsername().equals("No Name Available"));
	}
	
	public void testgetUsernameWithoutUser_fail()
	{
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStup();
		userSiteController.init();
				
		// Tests
		assertEquals(false, userSiteController.getUsername().isEmpty());
	}
	
	public void testgetUsernameWithUser_success()
	{
		String username = "Testuser";
		String password = "Testpassword";
		
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStup();
		userSiteController.userService.registerUser(username, password);
		userSiteController.init();
				
		// Tests
		assertEquals(true, userSiteController.getUsername().equals(username));
	}
	
	public void testgetUsernameWithUser_fail()
	{
		String username = "Testuser";
		String password = "Testpassword";
		
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStup();
		userSiteController.userService.registerUser(username, password);
		userSiteController.init();
				
		// Tests
		assertEquals(false, userSiteController.getUsername().isEmpty());
	}
	
	public void testgetEmailWithoutUser_success()
	{
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStup();
		userSiteController.init();
		
		// Tests
		assertEquals(true, userSiteController.getEmail().equals("no.mail@vailable.yet"));
	}
	
	public void testgetEmailWithoutUser_fail()
	{
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStup();
		userSiteController.init();
		
		// Tests
		assertEquals(false, userSiteController.getEmail().isEmpty());
	}
	
	public void testgetEmailWithUser_success()
	{
		String username = "Testuser";
		String password = "Testpassword";
		String email = "test@test.mail";
		
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStup();
		userSiteController.userService.registerUser(username, password);
		userSiteController.userService.getRegisteredUser().setEmail(email);
		userSiteController.init();

		// Tests
		assertEquals(true, userSiteController.getEmail().equals(email));
	}
	
	public void testgetEmailWithUser_fail()
	{
		String username = "Testuser";
		String password = "Testpassword";
		String email = "test@test.mail";
		
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.userService = new UserServiceTestStup();
		userSiteController.userService.registerUser(username, password);
		userSiteController.userService.getRegisteredUser().setEmail(email);
		userSiteController.init();

		// Tests
		assertEquals(false, userSiteController.getEmail().isEmpty());
	}
	
	public void testSelectedUser_success()
	{
		User selectedUser = new User();
		
		UserSiteController userSiteController = new UserSiteController();
		
		userSiteController.setSelectedUser(selectedUser);
		
		// Tests
		assertEquals(true, selectedUser.equals(userSiteController.getSelectedUser()));
	}
	
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
