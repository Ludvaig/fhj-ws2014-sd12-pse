package at.fhj.swd.controller;

import java.io.Console;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;

import at.fhj.swd.controller.Helpers.CookieHelper;
import at.fhj.swd.model.entity.AuthInfo;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.UserService;

/***
 * 
 * @author Steven Hagelm�ller
 * 
 *         Controller for the Login page. Uses the 'UserService' class for
 *         authentication.
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

			// try to register user
			String token = userService.registerUser(authInfo.getUsername(),
					authInfo.getPassword());

			// store new token
			CookieHelper.setAuthTokenValue(token);
			System.out.println("token:" + token);

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

	public void insertUser() {
		try {
			User user = new User();
			user.setUsername(authInfo.getUsername());
			user.setPassword(authInfo.getPassword());
			userService.insertUser(user);

		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, errorMessage,
					"Login unsuccessful."));
		}
	}
}
