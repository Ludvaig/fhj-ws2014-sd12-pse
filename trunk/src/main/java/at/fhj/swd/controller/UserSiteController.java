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
 * UserSite Controller. Just a simple placeholder as a result of local database
 * issues. This class is just a test class and will be completed with full
 * functionality if the issues are fixed.
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

	public List<User> getContacts()
	{
		return contacts;
	}

	public User getSelectedUser()
	{
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser)
	{
		this.selectedUser = selectedUser;
	}

	/**
	 * This is just a test method while the database didn't work for me.
	 * 
	 * @param actionEvent
	 */
	public void editUser(ActionEvent actionEvent)
	{
		if (user != null)
		{
			// to persist stuff
		}
		else
		{
			addMessage("Username: " + username + " Email: " + email + " Phone: " + phoneNumber);
		}
	}

	/**
	 * creates a message. test method which will be deleted after real
	 * implementation of editUser
	 * 
	 * @param summary
	 */
	private void addMessage(String summary)
	{
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
