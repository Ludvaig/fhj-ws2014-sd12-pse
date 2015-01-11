package at.fhj.swd.presentation;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.UserService;
import at.fhj.swd.service.exceptions.UserLoginException;

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
	private transient Logger logger;
	
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
			logger.info(String.format("Entering LoginController.login() method. Username:  '%s'...", username));
			
			String token = userService.registerUser(username, password);
			CookieHelper.setAuthTokenValue(token);

			logger.info(String.format("Login for user '%s' successful.", username));
			String url = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getContextPath();
			facesContext.getExternalContext().redirect(url);
		} 
		catch (Exception e) {
			if(e instanceof EJBException && ((EJBException) e).getCausedByException() instanceof UserLoginException) {
				logger.warning(String.format("Login for user '%s' failed.", username));
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ((EJBException) e).getCausedByException().getLocalizedMessage(), ""));
			}
			else {
				String msg ="An unexpected exception occured: " + e.getLocalizedMessage();
				logger.severe(msg);
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error occurred. Please contact the system administrator.", ""));
			}
		}
		return null;
	}
}
