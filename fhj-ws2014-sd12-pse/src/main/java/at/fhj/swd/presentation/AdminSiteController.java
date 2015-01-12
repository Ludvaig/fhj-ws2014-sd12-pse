package at.fhj.swd.presentation;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.UserService;
import at.fhj.swd.service.impl.NewsServiceImpl;

@ManagedBean(name = "adminSiteBean")
@RequestScoped
public class AdminSiteController implements Serializable {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(NewsServiceImpl.class.getName());
	
	/**
	 * Injected members.
	 */
	@Inject
    private transient FacesContext facesContext;
	
	@Inject
	private transient UserService userService;
	
	
	/**
	 * Properties
	 */
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	
	
	/**
	 * Bean interaction methods
	 */
	@PostConstruct
	public void initController() {
		logger.entering(getClass().getName(), "initController");		
		String authToken = CookieHelper.getAuthTokenValue();
		logger.log(Level.INFO, "authToken: " + authToken );
		User user = userService.getRegisteredUser(authToken);
		if(user != null) {
			if(!userService.userIsAdmin(user) && !userService.userIsPortalAdmin(user)) {
				// user is not administrator.
				logger.log(Level.SEVERE, "User is not admin");
				facesContext.addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_INFO, "You are not signed in as Administrator", "Failure"));
			} else {
				logger.log(Level.INFO, "adminSiteController allowed for <" + user.getUsername() + ">");
				setUsername(user.getUsername());
			}
		} else {
			// user is not signed in.
			logger.log(Level.INFO, "user is not logged in");
			facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Please sign in as Administrator first.", "Failure"));
		}
	}	
}
