package at.fhj.swd.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import at.fhj.swd.controller.Helpers.CookieHelper;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.UserService;

/**
 * UserSite Controller. Handles the requests from the UserSite.
 * 
 * @author Michael Spörk
 */

@Named
@SessionScoped
public class UserSiteController implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	UserService userService;

	User user;

	private List<User> contacts;
	private User selectedUser;
	private int phoneNumber;
	private String username;
	private String email;
	
	/* 
	 * needed do disable functions if the current UserSite is not the own UserSite
	 * if so the user should not be able do make changes
	 * 
	 *  */
	private boolean otherUser = false;

	public UserSiteController()
	{
		contacts = new ArrayList<User>();
	}

	@PostConstruct
	public void init()
	{

		String authToken = "";
		if (FacesContext.getCurrentInstance() != null)
			authToken = CookieHelper.getAuthTokenValue();

		user = userService.getRegisteredUser(authToken);

		if (user != null)
		{
			username = user.getUsername();
			email = user.getEmail();
			// TODO no Phone Number in User available
			phoneNumber = 123456;
		}
		else
		{
			username = "No Name Available";
			email = "no.mail@vailable.yet";
			phoneNumber = 12345;
		}

		/*
		 * the following Users are dummy objects for testing only.
		 * in iteration 2 they will be replaced with real contacts
		 */

		User user1 = new User();
		user1.setId(10l);
		user1.setUsername("Testuser 1");
		user1.setPassword("TeSt");
		user1.setEmail("testuser1@test.test");

		User user2 = new User();
		user2.setId(11l);
		user2.setUsername("Testuser 2");
		user2.setPassword("TeSt");
		user2.setEmail("testuser2@test.test");

		User user3 = new User();
		user3.setId(12l);
		user3.setUsername("Testuser 3");
		user3.setPassword("TeSt");
		user3.setEmail("testuser3@test.test");

		contacts.add(user1);
		contacts.add(user2);
		contacts.add(user3);
	}

	/**
	 * 
	 * @return current users username or No Name Available if the user is null
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * 
	 * @return current users email or no.mail@vailable.yet if the user is null
	 */
	public String getEmail()
	{
		return email;
	}

	public int getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setPhoneNumber(int phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public boolean isotherUser()
	{
		return otherUser;
	}

	public void setotherUser(boolean saveButtonDisabled)
	{
		this.otherUser = saveButtonDisabled;
	}

	/**
	 *  not needed in iteration 1
	 *  
	 * @return all contacts
	 */
	public List<User> getContacts()
	{
		return contacts;
	}

	/**
	 *  not needed in iteration 1
	 * 
	 * @return
	 */
	public User getSelectedUser()
	{
		return selectedUser;
	}
	
	
	/**
	 * not needed in iteration 1
	 * 
	 * @param selectedUser
	 */
	public void setSelectedUser(User selectedUser)
	{
		this.selectedUser = selectedUser;
	}

	/**
	 * if user is available: update user
	 * else: send back error message.
	 *  
	 * @param actionEvent
	 */
	public void editUser(ActionEvent actionEvent)
	{
		if (user != null)
		{
			userService.updateUser(user);
		}
		else
		{
			addMessage("Error: Could not save.");
		}
	}

	/**
	 * creates a message to notify the User about Errors
	 * 
	 * @param summary
	 */
	private void addMessage(String summary)
	{
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
