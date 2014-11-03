package at.fhj.swd.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import at.fhj.swd.model.service.UserService;

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
	private FacesContext facesContext;

	@Inject
	private UserService userService;

	@PostConstruct
	public void logout() {
		
	}
}
