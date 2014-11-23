package at.fhj.swd.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import at.fhj.swd.controller.Helpers.CookieHelper;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.UserService;

/***
 * 
 * @author Steven Hagelmüller 
 * 
 * Controller for the Login page. Uses the 'UserService' class for authentication.
 * 
 */
@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private UserService userService;
	
	private String username;
	private String password;
	
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String login() {
		try {
			String token = userService.registerUser(username, password);
			CookieHelper.setAuthTokenValue(token);

			//String url = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getContextPath();
			//facesContext.getExternalContext().redirect(url);
			
			//Todo:Change to real page:
			return "tmpindex?faces-redirect=true";
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, ""));
		}
		return null;
	}
	
	/***
	 * Should be removed before release.
	 */
	public void insertUser() {
		try {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			userService.insertUser(user);

		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, ""));
		}
	}
}
