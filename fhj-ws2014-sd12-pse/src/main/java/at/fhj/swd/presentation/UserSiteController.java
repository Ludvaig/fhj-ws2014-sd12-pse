package at.fhj.swd.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.UserService;

/**
 * UserSite Controller. Handles the requests from the UserSite.
 * 
 * @author Michael Spörk
 */


@ManagedBean
@SessionScoped
public class UserSiteController implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private transient Logger logger;
	
	@Inject
	private transient UserService userService;

	private User user;

	private List<User> contacts;
	private User selectedUser;
	private String authToken = "";
	
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
		logger.info("Entering UserSiteController.init() method.");
		
		if (FacesContext.getCurrentInstance() != null)
			authToken = CookieHelper.getAuthTokenValue();
		
		if (user == null)
			loadUser();

		User user1 = userService.getUserById(1);
		User user2 = userService.getUserById(2);

		contacts.add(user1);
		contacts.add(user2);
	}
	
	public void loadUser()
	{
		logger.info("Entering UserSiteController.loadUser() method.");
		
		user = userService.getRegisteredUser(authToken);

		if (user == null)
		{
			user = new User();
			user.setUsername("No Name Available");
			user.setEmail("no.mail@vailable.yet");
			user.setTelephone("12345");
		}
		
		logger.info(String.format("Loaded user '%s',", user.getUsername()));
	}

	/**
	 * 
	 * @return current users username or No Name Available if the user is null
	 */
	public String getUsername()
	{
		return user.getUsername();
	}

	/**
	 * 
	 * @return current users email or no.mail@vailable.yet if the user is null
	 */
	public String getEmail()
	{
		return user.getEmail();
	}

	public String getPhoneNumber()
	{
		return user.getTelephone();
	}

	public void setUsername(String username)
	{
		user.setUsername(username);
	}

	public void setEmail(String email)
	{
		user.setEmail(email);
	}

	public void setPhoneNumber(String phoneNumber)
	{
		user.setTelephone(phoneNumber);
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
		if (user != null && (userService.getUserByUsername(user.getUsername()) != null))
		{
			user = userService.updateUser(user);
			addInfoMessage("Userdata changed");
		}
		else
		{
			addErrorMessage("Error: Could not save. User does not exist.");
		}
	}

	/**
	 * creates a message to notify the User about Errors
	 * 
	 * @param summary
	 */
	private void addErrorMessage(String summary)
	{
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	private void addInfoMessage(String summary)
	{
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
