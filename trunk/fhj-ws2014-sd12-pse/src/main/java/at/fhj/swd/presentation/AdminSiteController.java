package at.fhj.swd.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.UserService;

@ManagedBean(name = "adminSiteBean")
@RequestScoped
public class AdminSiteController implements Serializable {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Injected members.
	 */
	@Inject
    private FacesContext facesContext;
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private UserService userService;
	
	
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
				
		String authToken = CookieHelper.getAuthTokenValue();
		System.out.println("authToken <" + authToken + ">");
		User user = userService.getRegisteredUser(authToken);
		if(user != null) {
			if(!userService.UserIsAdmin(user) && !userService.UserIsPortalAdmin(user)) {
				// user is not administrator.
				System.out.println("user is not admin");
				facesContext.addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_INFO, "You are not signed in as Administrator", "Failure"));
			} else {
				System.out.println("adminSiteController allowed for <" + user.getUsername() + ">");
				setUsername(user.getUsername());
			}
		} else {
			// user is not signed in.
			System.out.println("user is not logged in");
			facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Please sign in as Administrator", "Failure"));
		}
	}	
}
