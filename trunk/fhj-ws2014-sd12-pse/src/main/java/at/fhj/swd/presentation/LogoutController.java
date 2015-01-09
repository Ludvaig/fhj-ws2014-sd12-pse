package at.fhj.swd.presentation;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.UserService;

/***
 * 
 * @author Steven Hagelmüller 
 * 
 * Controller for the Logout page. Uses the 'UserService' class.
 * 
 */
@ManagedBean(name = "logoutController")
@RequestScoped
public class LogoutController {
	@Inject
	private Logger logger;
	
	@Inject
	private FacesContext facesContext;

	@Inject
	private UserService userService;

	public void logout() throws IOException {
		logger.info("Entering LogoutController.logout() method.");
		
		try {
			String token = CookieHelper.getAuthTokenValue();
			if(token != null) {
				User user = userService.getRegisteredUser(token);
				if(user != null) {
					userService.loggoutUser(user.getUsername());
				}
			}
			logger.info("Logout succesful.");
		} catch(Exception e) {
			if(e instanceof EJBException) {
				e = ((EJBException) e).getCausedByException();
			}
			logger.severe(String.format("An unexpected exception occured: ", e.getLocalizedMessage()));
		}
		finally {
			String url = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getContextPath();
			facesContext.getExternalContext().redirect(url);
		}
	}
}
