package at.fhj.swd.controller;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import at.fhj.swd.model.service.UserService;

/***
 * 
 * @author Steven Hagelmüller 
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

	private String username;
	private String password;

	public void login() {
		try {
				
			userService.registerUser(username, password);

			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Logged in!",
					"Log in successful for user " + username));

		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, errorMessage,
					"Login unsuccessful"));
		}
	}
}
