package at.fhj.swd.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import at.fhj.swd.model.entity.AuthInfo;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.UserService;

/***
 * 
 * @author Steven Hagelm�ller 
 * 
 * Controller for the Login page. Uses the 'UserService' class for authentication.
 * 
 */
@Model
public class LoginController {
	@Inject
	private FacesContext facesContext;

	@Inject
	private UserService userService;

	private AuthInfo authInfo;
	
    @Produces
    @Named
    public AuthInfo getAuthInfo() {
        return authInfo;
    }
    
    @PostConstruct
    public void initAuthInfo() {
    	authInfo = new AuthInfo();
    }

	public void login() {
		try {
				
			userService.registerUser(authInfo.getUsername(), authInfo.getPassword());

			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Logged in!",
					"Log in successful."));

		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, errorMessage,
					"Login unsuccessful."));
		}
	}
	
	public void insertUserMax() {
		try {
			User user = new User();
			user.setUsername("max");
			user.setPassword("muster");
			userService.insertUser(user);
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, errorMessage,
					"insert failed"));
		}
	}

	public void registerUserMax() {
		try {
			userService.registerUser("max", "muster");

		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, errorMessage,
					"registration failed"));
		}
	}
}
