package at.fhj.swd.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.UserService;


/**
 * UserSite Controller. Just a simple placeholder as a result of local database issues.
 * This class is just a test class and will be completed with full functionality if the issues are fixed. 
 * 
 * @author Michael Spörk
 */

@Named  
@SessionScoped
public class UserSiteController implements Serializable
{
	private static final long serialVersionUID = 1L;
		
	@Inject
	private UserService userService;
	
	private User user;
	
	
	public UserSiteController()
	{
		
	}
	
	/**
	 * 
	 * @return current users username or No Name Available if the user is null
	 */
	public String getUsername()
	{
		user = userService.getRegisteredUser();
		
		if(user == null)
		{
			return "No Name Available";
		}
		else
		{
			return user.getUsername();
		}
	}
	
	/**
	 * 
	 * @return current users email or no.mail@vailable.yet if the user is null
	 */
	public String getEmail()
	{
		user = userService.getRegisteredUser();
		
		if(user == null)
		{
			return "no.mail@vailable.yet";
		}
		else
		{
			return user.getEmail();
		}
	}
	
	/**
	 * This is just a test method while the database didn't work for me.
	 * 
	 * @param actionEvent
	 */
	public void editUser(ActionEvent actionEvent)
	{
		addMessage("UserName changed!");
	}
	
	private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
